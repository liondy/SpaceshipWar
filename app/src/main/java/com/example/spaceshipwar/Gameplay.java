package com.example.spaceshipwar;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Gameplay extends Fragment {

    private static Gameplay gameplay;
    private MainPresenter presenter;
    private TextView score;
    private ImageButton btn_left;
    private ImageButton btn_right;

    private Bitmap mBitmap;
    private ImageView imgContainer;
    private Canvas mCanvas;

    public Gameplay(){
        //require empty constructor
    }

    public static Gameplay createGameplay(MainPresenter presenter){
        if(gameplay==null){
            gameplay = new Gameplay();
            gameplay.presenter = presenter;
        }
        return gameplay;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view  = inflater.inflate(R.layout.gameplay,container,false);
        score = (TextView) view.findViewById(R.id.score);
        btn_left = (ImageButton) view.findViewById(R.id.btn_left);
        btn_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    btn_left.setColorFilter(getResources().getColor(R.color.white));
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    btn_left.setColorFilter(getResources().getColor(R.color.transparant));
                }
                return true;
            }
        });
        btn_right = (ImageButton) view.findViewById(R.id.btn_right);
        btn_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    btn_right.setColorFilter(getResources().getColor(R.color.white));
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    btn_right.setColorFilter(getResources().getColor(R.color.transparant));
                }
                return true;
            }
        });
        imgContainer = (ImageView) view.findViewById(R.id.imgContainer);
        return view;
    }
}
