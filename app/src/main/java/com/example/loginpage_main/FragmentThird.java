package com.example.loginpage_main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.io.File;
import java.io.IOException;

public class FragmentThird extends Fragment {

    TextView name, email, phone, category1, category2, category3;
    ImageView picture;
    DatabaseReference UserRef;
    FirebaseUser user;
    Button editBtn;
    Information CurrentUser;

    private FirebaseStorage storage;
    private StorageReference mImageRef;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View FragmentThirdView = inflater.inflate(R.layout.fragment_third, container, false);

        name = FragmentThirdView.findViewById(R.id.name_profile);
        email = FragmentThirdView.findViewById(R.id.email_text);
        phone = FragmentThirdView.findViewById(R.id.phone_text);
        category1 = FragmentThirdView.findViewById(R.id.cat1_text);
        category2 = FragmentThirdView.findViewById(R.id.cat2_text);
        category3 = FragmentThirdView.findViewById(R.id.cat3_text);
        picture = FragmentThirdView.findViewById(R.id.ProfilePic);

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
                CurrentUser = datasnapshot.getValue(Information.class);
                storage = FirebaseStorage.getInstance();

                if(CurrentUser.getImage() != null) {
                    mImageRef = storage.getReference("images/" + CurrentUser.getImage());

                    try {
                        final File localFile = File.createTempFile("temp", ".jpg");

                        mImageRef.getFile(localFile)
                                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                        //Toast.makeText(MyCustomListAdapter.this, "Picture Retrieved", Toast.LENGTH_SHORT).show();
                                        System.out.println("AAAAAAAAAAAAAAAAAAAAAASUCCESS: images/" + CurrentUser.getImage());
                                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                        picture.setImageBitmap(bitmap);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAFAILURE: image/" + CurrentUser.getImage());
                                picture.setImageResource(R.drawable.defaultimage);
                                //Toast.makeText(MyCustomListAdapter.this, "Picture NOT Retrieved", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    picture.setImageResource(R.drawable.defaultimage);
                }


                if(CurrentUser.getFirstname() != null && CurrentUser.getLastname() != null)
                {
                    name.setText(CurrentUser.getFirstname() + " " + CurrentUser.getLastname());
                }
                else
                {
                    name.setText("");
                }

                if(CurrentUser.getEmail() != null)
                {
                    email.setText(CurrentUser.getEmail());
                }
                else
                {
                    email.setText("");
                }
                if(CurrentUser.getPhonenumber() != null)
                {
                    phone.setText(CurrentUser.getPhonenumber());
                }
                else
                {
                    phone.setText("");
                }
                if(CurrentUser.getC1() != null)
                {
                    category1.setText(CurrentUser.getC1());
                }
                else
                {
                    category1.setText("");
                }
                if(CurrentUser.getC2() != null)
                {
                    category2.setText(CurrentUser.getC2());
                }
                else
                {
                    category2.setText("");
                }
                if(CurrentUser.getC2() != null)
                {
                    category3.setText(CurrentUser.getC3());
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





