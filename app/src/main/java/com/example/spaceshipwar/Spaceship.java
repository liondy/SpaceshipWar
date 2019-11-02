package com.example.spaceshipwar;

import android.graphics.Bitmap;

public class Spaceship {
    private Bitmap spaceship;
    private float x;
    private float y;
    private float batas;
    private float width;
    private float height;

    public Spaceship(Bitmap spaceship,float x,float y,float batas, float width, float height){
        this.spaceship = spaceship;
        this.x=x;
        this.y=y;
        this.batas=batas;
        this.width=width;
        this.height=height;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public Bitmap getSpaceship() {
        return this.spaceship;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void moveLeft(){
        if(this.x-10>=0){
            this.x-=10;
        }
    }

    public void moveRight(){
        if(this.x+10<=batas-spaceship.getWidth()/1.5f){
            this.x+=10;
        }
    }
}
