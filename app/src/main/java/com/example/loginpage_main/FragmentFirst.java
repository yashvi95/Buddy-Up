package com.example.loginpage_main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentFirst extends Fragment {

    private View FragmentFirstView;
    private ListView list_view;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list_of_users = new ArrayList<>();

    private DatabaseReference UserRef;

    public FragmentFirst()
    {
        //required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentFirstView = inflater.inflate(R.layout.fragment_first, container, false);

        UserRef = FirebaseDatabase.getInstance().getReference().child("Users");

        InitializeFields();

        RetrieveAndDisplayUsers();

        return FragmentFirstView;
    }

    private void InitializeFields() {
        list_view = (ListView) FragmentFirstView.findViewById(R.id.list_view);
        arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list_of_users);
        list_view.setAdapter(arrayAdapter);
    }

    private void RetrieveAndDisplayUsers() {
        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Information info = snapshot.getValue(Information.class);
                    String profileInfo = info.getFirstname() + " " + info.getLastname() + "\n" + info.getUsername() + "\nGYM: " + info.getGym() + "\n";
                    list_of_users.add(profileInfo);
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    /*
    private View ContactsView;
    private RecyclerView myContactsList;

    private DatabaseReference ContactsRef, UsersRef;
    private FirebaseAuth mAuth;
    private String currentUserID;

    public FragmentFirst() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ContactsView = inflater.inflate(R.layout.fragment_first, container, false);

        myContactsList = (RecyclerView) ContactsView.findViewById(R.id.user_list);
        myContactsList.setLayoutManager(new LinearLayoutManager(getContext()));

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();

        ContactsRef = FirebaseDatabase.getInstance().getReference().child(currentUserID);
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");

        return ContactsView;
    }
     */
/*
    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<User>()
                .setQuery(ContactsRef, User.class)
                .build();

        FirebaseRecyclerAdapter<User, ContactsView> adapter
                = new FirebaseRecyclerAdapter<User, FragmentFirst.ContactsView>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FragmentFirst.ContactsView holder, int position, @NonNull User model)
            {
                String user = getRef(position).getKey();

                UsersRef.child(user).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        String ProfileUsername = snapshot.child("username").getValue().toString();

                        holder.userName.setText(ProfileUsername);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @NonNull
            @Override
            public FragmentFirst.ContactsView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_display_layout, parent, false);
                ContactsView viewHolder = new ContactsView(view);
                return viewHolder;
            }
        };

        myContactsList.setAdapter(adapter);
        adapter.startListening();
    }

    public static class ContactsView extends RecyclerView.ViewHolder
    {
        TextView userName;

        public ContactsView(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.user_profile_name);
        }
    }
    */
}