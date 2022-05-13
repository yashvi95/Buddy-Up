package com.example.loginpage_main;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.InputStream;
import java.util.ArrayList;

public class FragmentFirst extends Fragment {

    private View FragmentFirstView;
    private ListView list_view;
    private MyCustomListAdapter adapter1;
    private ArrayList<Information> list_of_users = new ArrayList<>();

    private DatabaseReference UserRef;
    private DatabaseReference UserRef_ForThisOne;
    private FirebaseUser user_a;
    Information CurrentUser;


    private FirebaseStorage storage;
    private StorageReference storageReference;


    public FragmentFirst()
    {
        //required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentFirstView = inflater.inflate(R.layout.fragment_first, container, false);

        UserRef = FirebaseDatabase.getInstance().getReference().child("Users");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        user_a = FirebaseAuth.getInstance().getCurrentUser();

        InitializeFields();

        RetrieveAndDisplayUsers();

        return FragmentFirstView;
    }

    private void InitializeFields() {
        list_view = (ListView) FragmentFirstView.findViewById(R.id.list_viewFragFirst);
        adapter1 = new MyCustomListAdapter(getContext(), R.layout.my_list_item, list_of_users);
        list_view.setAdapter(adapter1);
    }

    private void RetrieveAndDisplayUsers() {

        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    UserRef_ForThisOne = FirebaseDatabase.getInstance().getReference("Users").child(user_a.getUid());

                    UserRef_ForThisOne.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot02) {
                            for(DataSnapshot snapshot : dataSnapshot.getChildren())
                            {
                                CurrentUser = snapshot02.getValue(Information.class);
                                Information info = snapshot.getValue(Information.class);

                                if(CurrentUser.getSchedule() != null)
                                {
                                    //If the logged-in user has the same schedule as a person that has an account
                                    //(we loop through every account), add it to the ArrayAdapter.
                                    if(info.getSchedule() != null)
                                    {
                                        String one = CurrentUser.getSchedule();
                                        String two = info.getSchedule();
                                        if(one.equals(two))
                                        {
                                            list_of_users.add(info);
                                            adapter1.notifyDataSetChanged();
                                        }
                                    }
                                }
                                //if the CurrentUser doesn't have a preferred schedule time, just add everyone.
                                else
                                {
                                    list_of_users.add(info);
                                    adapter1.notifyDataSetChanged();
                                }
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

