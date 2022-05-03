package com.example.loginpage_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

    String c1 = "Choose a Category";
    String c2 = "Choose a Category";
    String c3 = "Choose a Category";

    Spinner spinnerCategories01;
    Spinner spinnerCategories02;
    Spinner spinnerCategories03;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
       // profilePic = findViewById(R.id.ProfilePic);

        spinnerCategories01 = findViewById(R.id.spinnerCategories01);
        ArrayAdapter<CharSequence>adapter01 = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_dropdown_item);

        adapter01.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories01.setAdapter(adapter01);
        spinnerCategories01.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    //Choose a Category
                    case 0:
                        TextView tv = (TextView) view;
                        tv.setTextColor(Color.RED);
                        c1 = "Choose a Category";
                        Log.i("CATEGORY 1:",c1);
                        Toast.makeText(SignUp.this, "Please select a Category", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        c1 = spinnerCategories01.getSelectedItem().toString();
                        Log.i("CATEGORY 1:",c1);
                        Toast.makeText(SignUp.this, "Chosen", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerCategories02 = findViewById(R.id.spinnerCategories02);
        ArrayAdapter<CharSequence>adapter02 = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_dropdown_item);

        adapter02.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories02.setAdapter(adapter02);
        spinnerCategories02.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    //Choose a Category
                    case 0:
                        TextView tv = (TextView) view;
                        tv.setTextColor(Color.RED);
                        c2 = "Choose a Category";
                        Log.i("CATEGORY 2:",c2);
                        Toast.makeText(SignUp.this, "Please select a Category", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        c2 = spinnerCategories02.getSelectedItem().toString();
                        Log.i("CATEGORY 2:",c2);
                        Toast.makeText(SignUp.this, "Chosen", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerCategories03 = findViewById(R.id.spinnerCategories03);
        ArrayAdapter<CharSequence>adapter03 = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_dropdown_item);

        adapter03.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories03.setAdapter(adapter03);
        spinnerCategories03.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    //Choose a Category
                    case 0:
                        TextView tv = (TextView) view;
                        tv.setTextColor(Color.RED);
                        c3 = "Choose a Category";
                        Log.i("CATEGORY 3:",c3);
                        Toast.makeText(SignUp.this, "Please select a Category", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        c3 = spinnerCategories03.getSelectedItem().toString();
                        Log.i("CATEGORY 3:",c3);
                        Toast.makeText(SignUp.this, "Chosen", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editTextfirstname = (EditText) findViewById(R.id.firstname);
        editTextlastname = (EditText) findViewById(R.id.lastname);
        editTextusername = (EditText) findViewById(R.id.firstusername);
        editTextpassword = (EditText) findViewById(R.id.pswd);
        editTextemail = (EditText) findViewById(R.id.email);
        editTextphonenumber = (EditText) findViewById(R.id.phonenumber);
        editTextgym = (EditText) findViewById(R.id.gym);
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
        if (c1.isEmpty() || c1 == "Choose a Category") {
            ((TextView)spinnerCategories01.getSelectedView()).setError("Please choose a first category");
            spinnerCategories01.requestFocus();
            return;
        }
        if (c2.isEmpty() || c2 == "Choose a Category") {
            ((TextView)spinnerCategories02.getSelectedView()).setError("Please choose a second category");
            spinnerCategories02.requestFocus();
            return;
        }
        if (c3.isEmpty() || c3 == "Choose a Category") {
            ((TextView)spinnerCategories03.getSelectedView()).setError("Please choose a third category");
            spinnerCategories03.requestFocus();
            return;
        }

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

