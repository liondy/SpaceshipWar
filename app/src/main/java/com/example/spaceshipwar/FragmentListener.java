package com.example.spaceshipwar;

public interface FragmentListener {
    public static final int COVER = 1;
    public static final int GAME = 2;
    public static final int HIGHSCROE = 3;
    void showPage(int page);
}
