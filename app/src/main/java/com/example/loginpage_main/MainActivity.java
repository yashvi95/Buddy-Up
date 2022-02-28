package com.example.loginpage_main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView username = (TextView) findViewById(R.id.username_text);
        TextView password = (TextView) findViewById(R.id.password_text);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.LogIn_Button);
        MaterialButton signupbtn = (MaterialButton) findViewById(R.id.SignUp_Button);
        MaterialTextView forgotpasstxt = (MaterialTextView) findViewById(R.id.forgot_pass_text);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when we click this button, we check the user and password
                if(username.getText().toString().equals("PLACEHOLDER") && password.getText().toString().equals("PLACEHOLDER"))
                {
                    Toast.makeText(MainActivity.this,"LOG IN SUCCESS", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"LOG IN FAILED", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"GOING TO SIGN UP PAGE", Toast.LENGTH_SHORT).show();
            }
        });

        forgotpasstxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"GOING TO FORGOT PASSWORD PAGE", Toast.LENGTH_SHORT).show();
            }
        });
    }
}