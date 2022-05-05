package com.example.loginpage_main;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private String img;
    private FirebaseAuth mAuth;
    private CircleImageView profilePic;
    private TextView signbtn,selectbtn;
    ActivityResultLauncher<Intent> activityResultLauncher;
    private EditText editTextfirstname, editTextlastname, editTextusername, editTextpassword, editTextphonenumber, editTextemail, editTextgym;
    Spinner category1, category2, category3, schedule;
    String cat1, cat2, cat3, schdl;
    String[] Categories = {"Choose a Category", "Body Building","Strength Training","Weight Loss","Yoga","Cardio","Outdoor Activities"};
    String[] Scheduling = {"Choose a time that works best for you!","Morning","Afternoon","Evening","Flexible"};
    FirebaseStorage storage;
    StorageReference storageReference;
    private Uri filepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        profilePic = findViewById(R.id.ProfilePic);
        selectbtn = findViewById(R.id.select);

        selectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(SignUp.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        editTextfirstname = (EditText) findViewById(R.id.firstname);
        editTextlastname = (EditText) findViewById(R.id.lastname);
        editTextusername = (EditText) findViewById(R.id.firstusername);
        editTextpassword = (EditText) findViewById(R.id.pswd);
        editTextemail = (EditText) findViewById(R.id.email);
        editTextphonenumber = (EditText) findViewById(R.id.phonenumber);
        editTextgym = (EditText) findViewById(R.id.gym);

        signbtn = (Button) findViewById(R.id.signup);
        signbtn.setOnClickListener(this);


        category1 = (Spinner) findViewById(R.id.category1);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Categories);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category1.setAdapter(arrayAdapter);

        category2 = (Spinner) findViewById(R.id.category2);
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Categories);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category2.setAdapter(arrayAdapter2);

        category3 = (Spinner) findViewById(R.id.category3);
        ArrayAdapter arrayAdapter3 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Categories);
        arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category3.setAdapter(arrayAdapter3);

        schedule = (Spinner) findViewById(R.id.scheduling);
        ArrayAdapter arrayAdapter4 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Scheduling);
        arrayAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schedule.setAdapter(arrayAdapter4);




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        filepath = data.getData();
        profilePic.setImageURI(filepath);
        uploadimage();
    }

    private void uploadimage() {
        if(filepath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();


            img = UUID.randomUUID().toString();
            StorageReference ref = storageReference.child("images/"+ img);
            ref.putFile(filepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(SignUp.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(SignUp.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }
    

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signup:
                signupbtn();
                break;
        }

    }



    private void signupbtn() {
        String firstname = editTextfirstname.getText().toString().trim();
        String lastname = editTextlastname.getText().toString().trim();
        String username = editTextusername.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();
        String email = editTextemail.getText().toString().trim();
        String phonenumber = editTextphonenumber.getText().toString().trim();
        String gym = editTextgym.getText().toString().trim();

        cat1 = category1.getSelectedItem().toString();
        cat2 = category2.getSelectedItem().toString();
        cat3 = category3.getSelectedItem().toString();
        schdl = schedule.getSelectedItem().toString();
        String image = img;
        TextView errorText = (TextView)category1.getSelectedView();
        TextView errorText2 = (TextView)schedule.getSelectedView();



        if (firstname.isEmpty()) {
            editTextfirstname.setError("Required");
            editTextfirstname.requestFocus();
            return;
        }
        if (lastname.isEmpty()) {
            editTextlastname.setError("Required");
            editTextlastname.requestFocus();
            return;
        }
        if (username.isEmpty()) {
            editTextusername.setError("Required");
            editTextusername.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextpassword.setError("Required");
            editTextpassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextpassword.setError("Min password length should be 6 characters");
            editTextpassword.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editTextemail.setError("Required");
            editTextemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextemail.setError("Please provide valid email");
            editTextemail.requestFocus();
            return;
        }
        if (phonenumber.isEmpty()) {
            editTextphonenumber.setError("Required");
            editTextphonenumber.requestFocus();
            return;
        }
        if (gym.isEmpty()) {
            editTextgym.setError("Required");
            editTextgym.requestFocus();
            return;
        }
        if(cat1 == "Choose a Category"){
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Choose one!");//changes the selected item
            return;
        }
        if(schdl == "Choose a time that works best for you!"){
            errorText2.setError("");
            errorText2.setTextColor(Color.RED);//just to highlight that this is an error
            errorText2.setText("Choose one!");//changes the selected item
            return;
        }
        
        /*if((c1.toLowerCase() != "cardio") && (c1.toLowerCase() != "body building" ) && (c1.toLowerCase() != "strength training") && (c1.toLowerCase() != "weight loss") && (c1.toLowerCase() != "yoga")){
            category1.setError("Check Spellingc1 " + c1.toLowerCase());
            category1.requestFocus();
            return;

        }

        if((c2.toLowerCase() != "cardio") && (c2.toLowerCase() != "body building") && (c2.toLowerCase() != "strength training") && (c2.toLowerCase() != "weight loss") && (c2.toLowerCase() != "yoga")){
            category2.setError("Check Spellingc2 " + c2.toLowerCase());
            category2.requestFocus();
            return;

        }
        if((c3.toLowerCase() != "cardio") && (c3.toLowerCase() != "body building") && (c3.toLowerCase() != "strength training") && (c3.toLowerCase() != "weight loss") && (c3.toLowerCase() != "yoga")){
            category3.setError("Check Spellingc3 " + c3.toLowerCase());
            category3.requestFocus();
            return;
        }*/

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            User user = new User(firstname, lastname, username, password, phonenumber, email, gym, cat1,cat2,cat3,schdl,image);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignUp.this, "User has been Signed Up", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent (SignUp.this, LoginPage.class));
                                    } else {
                                        Toast.makeText(SignUp.this, "Failed to Sign Up", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });






                        } else {
                            Toast.makeText(SignUp.this, "Failed to Sign Up", Toast.LENGTH_LONG).show();
                        }
                    }
                });


        }
    }

