package com.example.spaceshipwar;

public class MainPresenter {
    FragmentListener fl;
    GameplayListener gl;

    public MainPresenter(MainActivity ma){
        this.fl = ma;
        this.gl = ma;
    }

    public void showPage(int page){
        this.fl.showPage(page);
    }

    public void setWidth(int width){
        this.gl.setWidth(width);
    }

    public void setHeight(int height){
        this.gl.setHeight(height);
    }
}
