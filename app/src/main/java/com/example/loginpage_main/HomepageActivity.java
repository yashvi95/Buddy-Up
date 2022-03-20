package com.example.loginpage_main;



import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

public class HomepageActivity extends AppCompatActivity {

    ConstraintLayout mainLayout = findViewById(R.id.homepage);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        CreateBuddy();
    }

    private void CreateBuddy(){
        ImageView myView = new ImageView(this);
        myView.getLayoutParams().height = 20;
        myView.getLayoutParams().width = 20;


    }
}