package com.example.loginpage_main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView username = (TextView) findViewById(R.id.username_text);
        TextView password = (TextView) findViewById(R.id.password_text);

        Button loginbtn = (Button) findViewById(R.id.LogIn_Button);
        Button signupbtn = (Button) findViewById(R.id.SignUp_Button);
        signupbtn.setOnClickListener(this);
        TextView forgotpasstxt = (TextView) findViewById(R.id.forgot_pass_text);


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
//        signupbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this,"GOING TO SIGN UP PAGE", Toast.LENGTH_SHORT).show();
//            }
//        });

        forgotpasstxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"GOING TO FORGOT PASSWORD PAGE", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.SignUp_Button:
                startActivity(new Intent(this, SIgn_Up.class ));
                break;
        }
    }
}