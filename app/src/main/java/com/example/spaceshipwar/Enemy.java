package com.example.spaceshipwar;

import android.graphics.Bitmap;

public class Enemy {
    private Bitmap enemy;
    private float x;
    private float y;
    private float batas;
    private int speed;


    public Enemy(Bitmap enemy, float x, float y, float batas, int speed){
        this.enemy = enemy;
        this.x=x;
        this.y=y;
        this.batas=batas;
        this.speed=speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public Bitmap getEnemy() {
        return enemy;
    }

    public void setEnemy(Bitmap enemy) {
        this.enemy = enemy;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getBatas() {
        return batas;
    }

    public void setBatas(float batas) {
        this.batas = batas;
    }
}
