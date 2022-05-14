package com.example.loginpage_main;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Messaging extends ArrayAdapter<Information> {

    Context context;
    int resource;
    List<Information> userList;

    private FirebaseStorage storage;
    private StorageReference mImageRef;

    public Messaging(@NonNull Context context, int resource, @NonNull List<Information> userList) {
        super(context, resource, userList);

        this.context = context;
        this.resource = resource;
        this.userList = userList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.messaging_list_item, null);

        TextView textViewName = view.findViewById(R.id.homeName);
        TextView textViewGym = view.findViewById(R.id.homeGym);
        TextView textViewPhone = view.findViewById(R.id.homePhone);
        ImageView imageView = view.findViewById(R.id.homePicture);

        Information user = userList.get(position);

        storage = FirebaseStorage.getInstance();

        if(user.getImage() != null) {
            mImageRef = storage.getReference("images/" + user.getImage());

            System.out.println("\n\n\n\n");
            System.out.println("AAAAAAAAAAAAAAAAAAAAAASUCCESS: images/" + user.getImage()+ ".jpg");
            System.out.println("\n\n\n\n");

            try {
                final File localFile = File.createTempFile("temp", ".jpg");

                mImageRef.getFile(localFile)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                System.out.println("AAAAAAAAAAAAAAAAAAAAAASUCCESS: images/" + user.getImage());
                                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                imageView.setImageBitmap(bitmap);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAFAILURE: image/" + user.getImage());
                        imageView.setImageResource(R.drawable.defaultimage);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            imageView.setImageResource(R.drawable.defaultimage);
        }

        textViewName.setText(user.getFirstname());
        textViewGym.setText("Email: " + user.getEmail());
        textViewPhone.setText("Phone: " + user.getPhonenumber());

        Button messageRemove = (Button) view.findViewById(R.id.messageRemove);
        messageRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do what you want to do when button is clicked
                System.out.println("BUTTON CLICKED");
                Information user_with_button = userList.get(position);
                String firstname = user_with_button.getFirstname();
                String email = user_with_button.getEmail();
                String phonenumber = user_with_button.getPhonenumber();
                String schdl = user_with_button.getSchedule();
                String image = user_with_button.getImage();
                String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                ContactInformation contact = new ContactInformation(firstname, phonenumber, email, schdl, image);

                FirebaseDatabase.getInstance().getReference().child("Users").child(currentuser).child("Contacts").child(contact.getFirstname()).removeValue();
        }});

        return view;
    }
}


