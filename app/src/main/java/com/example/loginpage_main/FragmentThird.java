package com.example.loginpage_main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    Button editBtn;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View FragmentThirdView = inflater.inflate(R.layout.fragment_third, container, false);


        name = FragmentThirdView.findViewById(R.id.name_profile);
        email = FragmentThirdView.findViewById(R.id.email_text);
        phone = FragmentThirdView.findViewById(R.id.phone_text);
        category1 = FragmentThirdView.findViewById(R.id.cat1_text);
        category2 = FragmentThirdView.findViewById(R.id.cat2_text);
        category3 = FragmentThirdView.findViewById(R.id.cat3_text);

        user = FirebaseAuth.getInstance().getCurrentUser();

        UserRef = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());

        editBtn = (Button) FragmentThirdView.findViewById(R.id.edit_profile);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity();
            }
        });

        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                //User users = datasnapshot.getValue(User.class);
                Information users = datasnapshot.getValue(Information.class);

                if(users.getFirstname() != null && users.getLastname() != null)
                {
                    name.setText(users.getFirstname() + " " + users.getLastname());
                }
                else
                {
                    name.setText("");
                }

                if(users.getEmail() != null)
                {
                    email.setText(users.getEmail());
                }
                else
                {
                    email.setText("");
                }
                if(users.getPhonenumber() != null)
                {
                    phone.setText(users.getPhonenumber());
                }
                else
                {
                    phone.setText("");
                }
                if(users.getC1() != null)
                {
                    category1.setText(users.getC1());
                }
                else
                {
                    category1.setText("");
                }
                if(users.getC2() != null)
                {
                    category2.setText(users.getC2());
                }
                else
                {
                    category2.setText("");
                }
                if(users.getC2() != null)
                {
                    category3.setText(users.getC3());
                }
                else
                {
                    category3.setText("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return FragmentThirdView;
    }

    public void openNewActivity(){
        Intent intent = new Intent(getActivity(), EditProfile.class);
        startActivity(intent);
    }




}





