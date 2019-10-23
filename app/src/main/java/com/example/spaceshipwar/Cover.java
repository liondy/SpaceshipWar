package com.example.spaceshipwar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class Cover extends Fragment {

    private static Cover spash_screen;
    private ImageButton btn_start;
    private ImageButton btn_high_score;

    public Cover(){
        //require empty constructor
    }

    public static Cover createCover(){
        if(spash_screen==null){
            spash_screen = new Cover();
        }
        return spash_screen;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view  = inflater.inflate(R.layout.cover,container,false);
        this.btn_start = view.findViewById(R.id.btnPlay);
        this.btn_high_score = view.findViewById(R.id.btnHighScore);
        this.btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity ma = (MainActivity) getActivity();

            }
        });
        this.btn_high_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity ma = (MainActivity) getActivity();

            }
        });
        return view;
    }

}