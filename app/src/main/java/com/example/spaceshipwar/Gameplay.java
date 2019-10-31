package com.example.spaceshipwar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import static androidx.core.content.res.ResourcesCompat.getColor;

public class Gameplay extends Fragment {

    private static Gameplay gameplay;
    private MainPresenter presenter;
    private TextView score;
    private ImageButton btn_left;
    private ImageButton btn_right;

    private Spaceship spaceship;

    private Bitmap mBitmap;
    private ImageView imgContainer;
    private Canvas canvas;

    private int bitmapHeight;
    private int bitmapWidth;

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
        imgContainer = (ImageView) view.findViewById(R.id.imgContainer);
        this.initiateCanvas();
        score = (TextView) view.findViewById(R.id.score);
        btn_left = (ImageButton) view.findViewById(R.id.btn_left);
        btn_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    btn_left.setColorFilter(getResources().getColor(R.color.white));
                    spaceship.moveLeft();
                    resetCanvas();
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
                    spaceship.moveRight();
                    resetCanvas();
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    btn_right.setColorFilter(getResources().getColor(R.color.transparant));
                }
                return true;
            }
        });
        return view;
    }

    public void setBitmapWidth(int width){
        this.bitmapWidth = width;
    }

    public void setBitmapHeight(int bitmapHeight) {
        this.bitmapHeight = bitmapHeight;
    }

    private void initiateCanvas(){
        this.mBitmap = Bitmap.createBitmap(this.bitmapWidth,this.bitmapHeight,Bitmap.Config.ARGB_8888);
        Bitmap ship = BitmapFactory.decodeResource(getResources(), R.drawable.spaceship);
        this.mBitmap = this.mBitmap.copy(Bitmap.Config.ARGB_8888,true);
        this.canvas = new Canvas(this.mBitmap);
        this.spaceship = new Spaceship(ship,(this.mBitmap.getWidth()) / 2 - ship.getWidth()/2,this.mBitmap.getHeight()/2 + ship.getHeight() * 1.5f,this.bitmapWidth);
        this.imgContainer.setImageBitmap(this.mBitmap);
        this.resetCanvas();
    }

    private void resetCanvas(){
        this.mBitmap.eraseColor(Color.TRANSPARENT);
        Paint paint = new Paint();
        ColorFilter filter = new PorterDuffColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        paint.setColorFilter(filter);
        this.canvas.drawBitmap(spaceship.getSpaceship(), spaceship.getX(), spaceship.getY(), paint);
        this.imgContainer.invalidate();
    }
}
