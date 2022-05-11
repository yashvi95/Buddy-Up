package com.example.loginpage_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BaseAfterLoginForFragments extends AppCompatActivity {

    private ListView listView;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_after_login_for_fragments);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new FragmentFirst()).commit();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.firstFragment_Home:
                        fragment = new FragmentFirst();
                        break;
                    case R.id.secondFragment_Messages:
                        fragment = new FragmentSecond();
                        break;
                    case R.id.thirdFragment_Profile:
                        fragment = new FragmentThird();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
                return true;
            }
        });
    }
}