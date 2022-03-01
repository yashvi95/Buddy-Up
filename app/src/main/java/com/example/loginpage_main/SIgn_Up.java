package com.example.loginpage_main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class SIgn_Up extends AppCompatActivity {
    private EditText editTextfirstname, editTextlastname, editTextusername, editTextpassword, editTextphonenumber, editTextemail, editTextgym;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextfirstname = (EditText) findViewById(R.id.firstname);
        editTextlastname = (EditText) findViewById(R.id.lastname);
        editTextusername = (EditText) findViewById(R.id.firstusername);
        editTextpassword = (EditText) findViewById(R.id.pswd);
        editTextemail = (EditText) findViewById(R.id.email);
        editTextphonenumber = (EditText) findViewById(R.id.phonenumber);
        editTextgym = (EditText) findViewById(R.id.gym);

        MaterialButton signbtn = (MaterialButton) findViewById(R.id.signup);

        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()) {
                    case R.id.signup:
                        signupbtn();
                        break;
                }
            }

            private void signupbtn(){
                String firstname = editTextfirstname.getText().toString().trim();
                String lastname = editTextlastname.getText().toString().trim();
                String username = editTextusername.getText().toString().trim();
                String password = editTextpassword.getText().toString().trim();
                String email = editTextemail.getText().toString().trim();
                String phonenumber = editTextphonenumber.getText().toString().trim();
                String gym = editTextgym.getText().toString().trim();

                if(firstname.isEmpty()){
                    editTextfirstname.setError("Required");
                    editTextfirstname.requestFocus();
                    return;

                }
                if(lastname.isEmpty()) {
                    editTextlastname.setError("Required");
                    editTextlastname.requestFocus();
                    return;
                }
                if(username.isEmpty()) {
                    editTextusername.setError("Required");
                    editTextusername.requestFocus();
                    return;
                }
                if(password.isEmpty()) {
                    editTextpassword.setError("Required");
                    editTextpassword.requestFocus();
                    return;
                }

                if(password.length() < 6){
                      editTextpassword.setError("Min password length should be 6 characters");
                      editTextpassword.requestFocus();
                }
                if(email.isEmpty()) {
                    editTextemail.setError("Required");
                    editTextemail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    editTextemail.setError("Please provide valid email");
                    editTextemail.requestFocus();

                }
                if(phonenumber.isEmpty()) {
                    editTextphonenumber.setError("Required");
                    editTextphonenumber.requestFocus();
                    return;
                }
                if(gym.isEmpty()) {
                    editTextgym.setError("Required");
                    editTextgym.requestFocus();
                    return;
                }


                }
        });



    }
}