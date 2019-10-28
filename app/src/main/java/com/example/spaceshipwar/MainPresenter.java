package com.example.spaceshipwar;

public class MainPresenter {
    FragmentListener fl;

    public MainPresenter(MainActivity ma){
        this.fl = ma;
    }

    public void showPage(int page){
        this.fl.showPage(page);
    }
}
