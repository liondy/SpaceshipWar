package com.example.spaceshipwar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements FragmentListener,GameplayListener {

    private FragmentManager fragmentManager;
    private Cover splash_screen;
    private Gameplay gameplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainPresenter presenter = new MainPresenter(this);

        this.splash_screen = Cover.createCover(presenter);
        this.gameplay = Gameplay.createGameplay(presenter);

        this.fragmentManager=this.getSupportFragmentManager();
        showPage(1);
    }

    @Override
    public void showPage(int page) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page==FragmentListener.COVER){
            if(this.splash_screen.isAdded()){
                ft.show(this.splash_screen);
            }
            else{
                ft.add(R.id.fragment_container,this.splash_screen);
            }
            if(this.gameplay.isAdded()){
                ft.hide(this.gameplay);
            }
        }
        else if(page==FragmentListener.GAME){
            if(this.gameplay.isAdded()){
                ft.show(this.gameplay);
            }
            else{
                ft.add(R.id.fragment_container,this.gameplay);
            }
            if(this.splash_screen.isAdded()){
                ft.hide(this.splash_screen);
            }
        }
        ft.commit();
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
    }

    @Override
    public void setWidth(int width) {
        this.gameplay.setBitmapWidth(width);
    }

    @Override
    public void setHeight(int height) {
        this.gameplay.setBitmapHeight(height);
    }
}
