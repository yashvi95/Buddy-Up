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



        Information user = userList.get(position);

        textViewName.setText(user.getFirstname());
        textViewGym.setText("GYM: " + user.getGym());
/*
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
*/
        //set to user.getImage() once it is set up.
        imageView.setImageResource(R.drawable.defaultimage);

//        c1View.setImageResource(R.drawable.cardio);
        c1Text.setText(user.getC1());
//        c2View.setImageResource(R.drawable.yoga);
        c2Text.setText(user.getC2());
//        c3View.setImageResource(R.drawable.dumbell);
        c3Text.setText(user.getC3());

        switch (user.getC1().toLowerCase())
        {
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
            case "none":
            {
                c1View.setImageResource(R.drawable.cancel);
                break;
            }
            default:
            {
                c1View.setImageResource(R.drawable.cancel);
            }
        }

        switch (user.getC2().toLowerCase())
        {
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
            case "none":
            {
                c2View.setImageResource(R.drawable.cancel);
                break;
            }
            default:
            {
                c2View.setImageResource(R.drawable.cancel);
            }
        }

        switch (user.getC3().toLowerCase())
        {
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
            case "none":
            {
                c3View.setImageResource(R.drawable.cancel);
                break;
            }
            default:
            {
                c3View.setImageResource(R.drawable.cancel);
            }
        }


        return view;
    }
}
