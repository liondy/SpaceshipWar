package com.example.spaceshipwar;

import android.graphics.Bitmap;

public class Bullet {
    private Bitmap bullet;
    private float x;
    private float y;
    private int speed;

    public Bullet(Bitmap bullet, float x, float y, int speed){
        this.bullet = bullet;
        this.x=x;
        this.y=y;
        this.speed=speed;
    }

    public Bitmap getBullet() {
        return bullet;
    }

    public void setBullet(Bitmap bullet) {
        this.bullet = bullet;
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

