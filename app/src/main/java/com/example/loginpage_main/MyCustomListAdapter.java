package com.example.loginpage_main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyCustomListAdapter extends ArrayAdapter<Information> {

    Context context;
    int resource;
    List<Information> userList;

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
        TextView textViewGym = view.findViewById(R.id.homeGym);
        TextView textViewExercises = view.findViewById(R.id.homeExercises);
        ImageView imageView = view.findViewById(R.id.homePicture);

        Information user = userList.get(position);

        textViewName.setText(user.getUsername());
        textViewGym.setText("GYM: " + user.getGym());

        String ExerciseInfo = "Preferred Exercises:\n";
        if(user.getC1() != null)
        {
            ExerciseInfo = ExerciseInfo + user.getC1() + "\n";
        }
        if(user.getC2() != null)
        {
            ExerciseInfo = ExerciseInfo + user.getC2() + "\n";
        }
        if(user.getC3() != null)
        {
            ExerciseInfo = ExerciseInfo + user.getC3() + "\n";
        }

        if(user.getC1() == null && user.getC2() == null && user.getC3() == null)
        {
            ExerciseInfo = ExerciseInfo + "None\n";
        }

        textViewExercises.setText(ExerciseInfo);

        //set to user.getImage() once it is set up.
        imageView.setImageResource(R.drawable.defaultimage);


        return view;
    }
}
