package com.example.spaceshipwar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class Highscore extends Fragment {
    private static Highscore highscore;
    private MainPresenter presenter;

    public Highscore(){
        //require empty constructor
    }

    public static Highscore createHighscore(MainPresenter presenter){
        if(highscore == null){
            highscore = new Highscore();
            highscore.presenter = presenter;
        }
        return highscore;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view  = inflater.inflate(R.layout.highscore,container,false);
        return view;
    }
}
