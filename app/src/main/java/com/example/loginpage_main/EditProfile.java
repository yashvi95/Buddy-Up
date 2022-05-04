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
    private EditText editTextfirstname, editTextlastname, editTextusername, editTextphonenumber, editTextemail, editTextgym,category1, category2, category3;

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
        category1 = (EditText) findViewById(R.id.category1);
        category2 = (EditText) findViewById(R.id.category2);
        category3 = (EditText) findViewById(R.id.category3);

        commitBtn = (Button) findViewById(R.id.commitChange);
        commitBtn.setOnClickListener(this);

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

                category1.setText(users.getC1());

                category2.setText(users.getC2());

                category3.setText(users.getC3());
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

    private void commitBtn() {

        String firstname = editTextfirstname.getText().toString().trim();
        String lastname = editTextlastname.getText().toString().trim();
        String username = editTextusername.getText().toString().trim();
        String email = editTextemail.getText().toString().trim();
        String phonenumber = editTextphonenumber.getText().toString().trim();
        String gym = editTextgym.getText().toString().trim();
        String c1 = category1.getText().toString().trim();
        String c2 = category2.getText().toString().trim();
        String c3 = category3.getText().toString().trim();



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
        if (c1.isEmpty()) {
            category1.setError("Required");
            category1.requestFocus();
            commit = true;
            return;
        }
        if (c2.isEmpty()) {
            category2.setError("Required");
            category2.requestFocus();
            commit = true;
            return;
        }
        if (c3.isEmpty()) {
            category3.setError("Required");
            category3.requestFocus();
            commit = true;
            return;
        }

        if (!commit) {
            UserRef.child("firstname").setValue(firstname);
            UserRef.child("lastname").setValue(lastname);
            UserRef.child("username").setValue(username);
            UserRef.child("phonenumber").setValue(phonenumber);
            UserRef.child("gym").setValue(gym);
            UserRef.child("c1").setValue(c1);
            UserRef.child("c2").setValue(c2);
            UserRef.child("c3").setValue(c3);
        }

        startActivity(new Intent(this, BaseAfterLoginForFragments.class));

    }
}