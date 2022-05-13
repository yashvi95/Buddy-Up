package com.example.loginpage_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EditProfile extends AppCompatActivity implements View.OnClickListener  {

    private FirebaseAuth mAuth;
    DatabaseReference UserRef;
    FirebaseUser user;
    private ImageView profilePic;
    private TextView commitBtn;
    private EditText editTextfirstname, editTextlastname, editTextusername, editTextphonenumber, editTextemail, editTextgym;
    Spinner category1, category2, category3, schedule;
    String cat1, cat2, cat3, schdl;
    String[] Categories = {"Choose a Category", "Body Building","Strength Training","Weight Loss","Yoga","Cardio","Outdoor Activities"};
    String[] Scheduling = {"Choose a time that works best for you!","Morning","Afternoon","Evening","Flexible"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mAuth = FirebaseAuth.getInstance();

        user = FirebaseAuth.getInstance().getCurrentUser();

        UserRef = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());

        editTextfirstname = (EditText) findViewById(R.id.firstname);
        editTextlastname = (EditText) findViewById(R.id.lastname);
        editTextusername = (EditText) findViewById(R.id.firstusername);
        editTextemail = (EditText) findViewById(R.id.email);
        editTextphonenumber = (EditText) findViewById(R.id.phonenumber);
        editTextgym = (EditText) findViewById(R.id.gym);


        commitBtn = (Button) findViewById(R.id.commitChange);
        commitBtn.setOnClickListener(this);

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

        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                //User users = datasnapshot.getValue(User.class);
                Information users = datasnapshot.getValue(Information.class);

                editTextfirstname.setText(users.getFirstname());

                editTextlastname.setText(users.getLastname());

                editTextusername.setText(users.getUsername());

                editTextemail.setText(users.getEmail());

                editTextphonenumber.setText(users.getPhonenumber());

                editTextgym.setText(users.getGym());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.commitChange:
                commitBtn();
                break;
        }
    }

    boolean commit = false;

    public void commitBtn() {

        String firstname = editTextfirstname.getText().toString().trim();
        String lastname = editTextlastname.getText().toString().trim();
        String username = editTextusername.getText().toString().trim();
        String email = editTextemail.getText().toString().trim();
        String phonenumber = editTextphonenumber.getText().toString().trim();
        String gym = editTextgym.getText().toString().trim();

        cat1 = category1.getSelectedItem().toString();
        cat2 = category2.getSelectedItem().toString();
        cat3 = category3.getSelectedItem().toString();
        schdl = schedule.getSelectedItem().toString();

        TextView errorText = (TextView)category1.getSelectedView();
        TextView errorText2 = (TextView)schedule.getSelectedView();




        if (firstname.isEmpty()) {
            editTextfirstname.setError("Required");
            editTextfirstname.requestFocus();
            commit = true;
            return;
        }
        if (lastname.isEmpty()) {
            editTextlastname.setError("Required");
            editTextlastname.requestFocus();
            commit = true;
            return;
        }
        if (username.isEmpty()) {
            editTextusername.setError("Required");
            editTextusername.requestFocus();
            commit = true;
            return;
        }
        if (email.isEmpty()) {
            editTextemail.setError("Required");
            editTextemail.requestFocus();
            commit = true;
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextemail.setError("Please provide valid email");
            editTextemail.requestFocus();
            commit = true;
            return;
        }
        if (phonenumber.isEmpty()) {
            editTextphonenumber.setError("Required");
            editTextphonenumber.requestFocus();
            commit = true;
            return;
        }
        if (gym.isEmpty()) {
            editTextgym.setError("Required");
            editTextgym.requestFocus();
            commit = true;
            return;
        }
        if(cat1 == "Choose a Category"){
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Choose one!");//changes the selected item
            commit = true;
            return;
        }
        if(schdl == "Choose a time that works best for you!"){
            errorText2.setError("");
            errorText2.setTextColor(Color.RED);//just to highlight that this is an error
            errorText2.setText("Choose one!");//changes the selected item
            commit = true;
            return;
        }


        if (!commit) {
            UserRef.child("firstname").setValue(firstname);
            UserRef.child("lastname").setValue(lastname);
            UserRef.child("username").setValue(username);
            UserRef.child("phonenumber").setValue(phonenumber);
            UserRef.child("gym").setValue(gym);
            UserRef.child("c1").setValue(cat1);
            UserRef.child("c2").setValue(cat2);
            UserRef.child("c3").setValue(cat3);
            UserRef.child("schedule").setValue(schdl);
        }

        startActivity(new Intent(this, BaseAfterLoginForFragments.class));

    }
}