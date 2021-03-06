package com.example.spaceshipwar;

import android.content.res.Resources;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class Cover extends Fragment {

    private static Cover spash_screen;
    private ImageButton btn_start;
    private ImageButton btn_high_score;
    private MainPresenter presenter;

    public Cover(){
        //require empty constructor
    }

    public static Cover createCover(MainPresenter presenter){
        if(spash_screen==null){
            spash_screen = new Cover();
            spash_screen.presenter = presenter;
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
                int width = getScreenWidth();
                int height = getScreenHeight();
                presenter.setWidth(width);
                presenter.setHeight(height);
                presenter.showPage(FragmentListener.GAME);
            }
        });
        this.btn_high_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == btn_high_score.getId()){
                    presenter.showPage(FragmentListener.HIGHSCROE);
                }
            }
        });
        return view;
    }

    private static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    private static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

}
