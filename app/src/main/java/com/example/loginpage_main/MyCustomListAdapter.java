package com.example.loginpage_main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MyCustomListAdapter extends ArrayAdapter<Information> {

    Context context;
    int resource;
    List<Information> userList;

    private FirebaseStorage storage;
    private StorageReference mImageRef;

    Information user;

    public MyCustomListAdapter(@NonNull Context context, int resource, @NonNull List<Information> userList) {
        super(context, resource, userList);

        this.context = context;
        this.resource = resource;
        this.userList = userList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.my_list_item, null);

        TextView textViewName = view.findViewById(R.id.homeName);
//        TextView textViewUserName = view.findViewById(R.id.homeUserName);
        TextView textViewGym = view.findViewById(R.id.homeGym);
//        TextView textViewExercises = view.findViewById(R.id.homeExercises);
        ImageView imageView = view.findViewById(R.id.homePicture);
        ImageView c1View = view.findViewById(R.id.c1View);
        ImageView c2View = view.findViewById(R.id.c2View);
        ImageView c3View = view.findViewById(R.id.c3View);
        TextView c1Text = view.findViewById(R.id.c1Text);
        TextView c2Text = view.findViewById(R.id.c2Text);
        TextView c3Text = view.findViewById(R.id.c3Text);
        TextView schText = view.findViewById(R.id.scheduleText);

        user = userList.get(position);

        storage = FirebaseStorage.getInstance();

        if(user.getImage() != null) {
            mImageRef = storage.getReference("images/" + user.getImage());

            try {
                final File localFile = File.createTempFile("temp", ".jpg");

                mImageRef.getFile(localFile)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                //Toast.makeText(MyCustomListAdapter.this, "Picture Retrieved", Toast.LENGTH_SHORT).show();
                                System.out.println("Image retrieved for user: " + user.getUsername());
                                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                imageView.setImageBitmap(bitmap);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("Could not retrieve image for user: " + user.getUsername());
                        imageView.setImageResource(R.drawable.defaultimage);
                        //Toast.makeText(MyCustomListAdapter.this, "Picture NOT Retrieved", Toast.LENGTH_SHORT).show();
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
        textViewGym.setText("GYM: " + user.getGym());
        c1Text.setText(user.getC1());
        c2Text.setText(user.getC2());
        c3Text.setText(user.getC3());

        if(user.getSchedule() == null)
        {
            schText.setText("");
        }
        else
        {
            schText.setText(user.getSchedule());
        }

        switch (user.getC1().toLowerCase())
        {
            case "outdoor activities":
            {
                c1View.setImageResource(R.drawable.sunny);
                break;
            }
            case "cardio":
            {
                c1View.setImageResource(R.drawable.cardio);
                break;
            }
            case "yoga":
            {
                c1View.setImageResource(R.drawable.yoga);
                break;
            }
            case "weight loss":
            {
                c1View.setImageResource(R.drawable.tape);
                break;
            }
            case "strength training":
            {
                c1View.setImageResource(R.drawable.lift);
                break;
            }
            case "body building":
            {
                c1View.setImageResource(R.drawable.dumbell);
                break;
            }
            default:
            {
                c1View.setImageResource(R.drawable.pastelorange);
                c1Text.setText("");
                break;
            }
        }

        switch (user.getC2().toLowerCase())
        {
            case "outdoor activities":
            {
                c2View.setImageResource(R.drawable.sunny);
                break;
            }
            case "cardio":
            {
                c2View.setImageResource(R.drawable.cardio);
                break;
            }
            case "yoga":
            {
                c2View.setImageResource(R.drawable.yoga);
                break;
            }
            case "weight loss":
            {
                c2View.setImageResource(R.drawable.tape);
                break;
            }
            case "strength training":
            {
                c2View.setImageResource(R.drawable.lift);
                break;
            }
            case "body building":
            {
                c2View.setImageResource(R.drawable.dumbell);
                break;
            }
            default:
            {
                c2View.setImageResource(R.drawable.pastelorange);
                c2Text.setText("");
                break;
            }
        }

        switch (user.getC3().toLowerCase())
        {
            case "outdoor activities":
            {
                c3View.setImageResource(R.drawable.sunny);
                break;
            }
            case "cardio":
            {
                c3View.setImageResource(R.drawable.cardio);
                break;
            }
            case "yoga":
            {
                c3View.setImageResource(R.drawable.yoga);
                break;
            }
            case "weight loss":
            {
                c3View.setImageResource(R.drawable.tape);
                break;
            }
            case "strength training":
            {
                c3View.setImageResource(R.drawable.lift);
                break;
            }
            case "body building":
            {
                c3View.setImageResource(R.drawable.dumbell);
                break;
            }
            default:
            {
                c3View.setImageResource(R.drawable.pastelorange);
                c3Text.setText("");
                break;
            }
        }

        Button messageButton = (Button) view.findViewById(R.id.messageButton);
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do what you want to do when button is clicked
                System.out.println("BUTTON CLICKED");
                Information user_with_button = userList.get(position);
                System.out.println("YOU CLICKED ON: " + user_with_button.getEmail() + " " + position);
            }
        });

        return view;
    }

}
