package com.example.loginpage_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private ImageView profilePic;
    private TextView signbtn;
    private EditText editTextfirstname, editTextlastname, editTextusername, editTextpassword, editTextphonenumber, editTextemail, editTextgym,category1, category2, category3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
       // profilePic = findViewById(R.id.ProfilePic);



        editTextfirstname = (EditText) findViewById(R.id.firstname);
        editTextlastname = (EditText) findViewById(R.id.lastname);
        editTextusername = (EditText) findViewById(R.id.firstusername);
        editTextpassword = (EditText) findViewById(R.id.pswd);
        editTextemail = (EditText) findViewById(R.id.email);
        editTextphonenumber = (EditText) findViewById(R.id.phonenumber);
        editTextgym = (EditText) findViewById(R.id.gym);
        category1 = (EditText) findViewById(R.id.category1);
        category2 = (EditText) findViewById(R.id.category2);
        category3 = (EditText) findViewById(R.id.category3);

        signbtn = (Button) findViewById(R.id.signup);
        signbtn.setOnClickListener(this);
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
        String c1 = category1.getText().toString().trim();
        String c2 = category2.getText().toString().trim();
        String c3 = category3.getText().toString().trim();

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
        if (c1.isEmpty()) {
            category1.setError("Required");
            category1.requestFocus();
            return;
        }
        if (c2.isEmpty()) {
            category2.setError("Required");
            category2.requestFocus();
            return;
        }
        if (c3.isEmpty()) {
            category3.setError("Required");
            category3.requestFocus();
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
                            User user = new User(firstname, lastname, username, password, phonenumber, email, gym,c1,c2,c3);

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

