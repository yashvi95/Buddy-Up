package com.example.loginpage_main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class FragmentSecond extends Fragment {

    private View FragmentSecondView;
    private ListView list_view;
    private Messaging adapter2;
    private ArrayList<Information> list_of_users = new ArrayList<>();

    private DatabaseReference UserRef;
    private DatabaseReference UserRef_Contacts;
    private DatabaseReference UserRef_ForThisOne;
    private FirebaseUser user_a;
    Information CurrentUser;


    private FirebaseStorage storage;
    private StorageReference storageReference;


    public FragmentSecond()
    {
        //required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentSecondView = inflater.inflate(R.layout.fragment_second, container, false);

        UserRef = FirebaseDatabase.getInstance().getReference().child("Users");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        user_a = FirebaseAuth.getInstance().getCurrentUser();

        InitializeFields();

        RetrieveAndDisplayUsers();

        return FragmentSecondView;
    }

    private void InitializeFields() {
        list_view = (ListView) FragmentSecondView.findViewById(R.id.list_viewFragSecond);
        adapter2 = new Messaging(getContext(), R.layout.messaging_list_item, list_of_users);
        list_view.setAdapter(adapter2);
    }

    private void RetrieveAndDisplayUsers() {


                UserRef_ForThisOne = FirebaseDatabase.getInstance().getReference("Users").child(user_a.getUid());
                UserRef_Contacts = FirebaseDatabase.getInstance().getReference("Users").child(user_a.getUid()).child("Contacts");

                UserRef_ForThisOne.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot02) {
                            UserRef_Contacts.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for(DataSnapshot Contactsnapshot : snapshot.getChildren()) {
                                        CurrentUser = snapshot02.getValue(Information.class);
                                        Information CurrentContact = Contactsnapshot.getValue(Information.class);
/*
                                        if(CurrentUser.getEmail() != null)
                                        {
                                            //If the logged-in user has the same schedule as a person that has an account
                                            //(we loop through every account), add it to the ArrayAdapter.
                                            if(CurrentContact.getEmail() != null)
                                            {

                                            }
                                        }
                                        //if the CurrentUser doesn't have a preferred schedule time, just add everyone.
                                        else
                                        {
                                            list_of_users.add(CurrentContact);
                                            adapter2.notifyDataSetChanged();
                                        }
*/
                                        list_of_users.add(CurrentContact);
                                        adapter2.notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}