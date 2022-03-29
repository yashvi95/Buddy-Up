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

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        TextView username = (TextView) findViewById(R.id.username_text);
        TextView password = (TextView) findViewById(R.id.password_text);

        Button loginbtn = (Button) findViewById(R.id.LogIn_Button);
        Button signupbtn = (Button) findViewById(R.id.SignUp_Button);
        signupbtn.setOnClickListener(this);
        TextView forgotpasstxt = (TextView) findViewById(R.id.forgot_pass_text);

        mAuth = FirebaseAuth.getInstance();


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when we click this button, we check the user and password
                String uName = username.getText().toString().trim();
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

                if(!Patterns.EMAIL_ADDRESS.matcher(uName).matches())
                {
                    username.setError("Please enter a valid email.");
                    username.requestFocus();
                    return;
                }
                if(pWord.isEmpty())
                {
                    password.setError("Password is required.");
                    password.requestFocus();
                    return;
                }

                if(password.length() < 6)
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

//        signupbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this,"GOING TO SIGN UP PAGE", Toast.LENGTH_SHORT).show();
//            }
//        });

        forgotpasstxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginPage.this,"GOING TO FORGOT PASSWORD PAGE", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.SignUp_Button:
                startActivity(new Intent(this, SignUp.class ));
                break;
        }
    }
}