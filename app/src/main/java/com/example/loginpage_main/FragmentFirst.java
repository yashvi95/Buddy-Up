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
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Information info = snapshot.getValue(Information.class);
                    list_of_users.add(info);
                }
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

