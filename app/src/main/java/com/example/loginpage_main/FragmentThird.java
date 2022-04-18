package com.example.loginpage_main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentThird extends Fragment {

    TextView name, email, phone, category1, category2, category3;
    DatabaseReference UserRef;
    FirebaseUser user;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View FragmentThirdView = inflater.inflate(R.layout.fragment_third, container, false);
/*
        name = FragmentThirdView.findViewById(R.id.name_profile);
        email = FragmentThirdView.findViewById(R.id.email_text);
        phone = FragmentThirdView.findViewById(R.id.phone_text);
        category1 = FragmentThirdView.findViewById(R.id.cat1_text);
        category2 = FragmentThirdView.findViewById(R.id.cat2_text);
        category3 = FragmentThirdView.findViewById(R.id.cat3_text);

        user = FirebaseAuth.getInstance().getCurrentUser();

        UserRef = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());

        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                User users = datasnapshot.getValue(User.class);

                name.setText(users.getFirstname());

                email.setText(users.getEmail());

                phone.setText(users.getPhonenumber());

                category1.setText(users.getC1());

                category2.setText(users.getC2());

                category3.setText(users.getC3());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
*/
        return FragmentThirdView;
    }




}





