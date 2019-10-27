package com.example.spaceshipwar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Gameplay extends Fragment {

    private static Gameplay gameplay;
    private TextView score;
    private ImageButton btn_left;
    private ImageButton btn_right;

    public Gameplay(){
        //require empty constructor
    }

    public static Gameplay createGameplay(){
        if(gameplay==null){
            gameplay = new Gameplay();
        }
        return gameplay;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view  = inflater.inflate(R.layout.gameplay,container,false);
        score = (TextView) view.findViewById(R.id.score);
        btn_left = (ImageButton) view.findViewById(R.id.btn_left);
        btn_right = (ImageButton) view.findViewById(R.id.btn_right);
        return view;
    }
}
