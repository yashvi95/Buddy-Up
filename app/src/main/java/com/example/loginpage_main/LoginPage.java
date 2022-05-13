package com.example.loginpage_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up the login page layout
        setContentView(R.layout.activity_login_page);

        // Set up the text views for username and password
        TextView username = (TextView) findViewById(R.id.username_text);
        TextView password = (TextView) findViewById(R.id.password_text);

        // Set up the buttons for login and signup
        Button loginbtn = (Button) findViewById(R.id.LogIn_Button);
        Button signupbtn = (Button) findViewById(R.id.SignUp_Button);
        signupbtn.setOnClickListener(this);

        loginbtn.setOnClickListener(new View.OnClickListener() {

            // Execute this code when someone clicks the login button
            @Override
            public void onClick(View v) {

                User user = new User(username.getText().toString().trim(),password.getText().toString().trim());

                //when we click the login button, we check the user and password
                if (!user.emailIsValid())
                {
                    Toast.makeText(LoginPage.this, "Invalid email", Toast.LENGTH_SHORT).show();
                    return;

                } else if (!user.passwordIsValid())
                {
                    Toast.makeText(LoginPage.this, "Invalid password", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Enter here if username and password are of valid format, attempt to login to firebase
                attemptFireBaseLogIn(username.getText().toString().trim(),password.getText().toString().trim());

            }
        });
    }

                /*String uName = username.getText().toString().trim();
                String pWord = password.getText().toString().trim();

                if(!uName.isEmpty() && !pWord.isEmpty())
                {
                    userLogin();
                }
                else
                {
                    Toast.makeText(LoginPage.this,"Failed to Log In! Please check your credentials", Toast.LENGTH_SHORT).show();
                }
            }
            private void userLogin()
            {
                String uName = username.getText().toString().trim();
                String pWord = password.getText().toString().trim();

                if(!userValidator(uName))
                {
                    username.setError("Please enter a valid email.");
                    username.requestFocus();
                    return;
                }

                if(!passwordValidator(pWord))
                {
                    password.setError("Minimum password length is 6 characters");
                    password.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(uName,pWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            startActivity(new Intent(LoginPage.this, BaseAfterLoginForFragments.class));

                            //startActivity(new Intent(MainActivity.this,afterLogin_test.class));
                            //redirect to profile
                        }
                        else{
                            Toast.makeText(LoginPage.this, "Failed to Log In! Please check your credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });

                 */
/*
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"GOING TO SIGN UP PAGE", Toast.LENGTH_SHORT).show();
            }
        });
*/
        /*
        forgotpasstxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginPage.this,"GOING TO FORGOT PASSWORD PAGE", Toast.LENGTH_SHORT).show();
            }
        });
        */

    //public LoginPage(String str) {
    //    String NiceJob = str;
    //}

    // This is for the signup button, switch fragments for signup
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.SignUp_Button:
                startActivity(new Intent(this, SignUp.class ));
                break;
        }
    }
/*
    // This functions checks to see if the username, aka email, is of valid format
    public boolean userValidator(String usrName)
    {
        if (usrName.isEmpty()) {
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(usrName).matches()) {
            return false;
        }
        return true;
    }

    // This function checks to see if the password is of valid format
    public boolean passwordValidator(String passWrd)
    {
        if (passWrd.length() < 6) {
            return false;
        }
        return true;
    }
*/
    // This function attempts to login to firebase, given valid formatting of an email and password
    public boolean attemptFireBaseLogIn(String uName, String pWord)
    {
        final boolean[] result = new boolean[1];

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

       // mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(uName,pWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    result[0] = true;
                    startActivity(new Intent(LoginPage.this, BaseAfterLoginForFragments.class));

                } else {
                    result[0] = false;
                    Toast.makeText(LoginPage.this, "Failed to Log In! Wrong Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return result[0];
    }

} // End Login Page Class definition