package com.example.spaceshipwar;

import android.graphics.Bitmap;

import java.util.Random;

public class Enemy {
    private Bitmap mbitmap;
    private float x;
    private float y;
    private float batasX;
    private float batasY;
    private float kecepatan;
    private boolean flag1;
    private boolean flag2;


    public Enemy(Bitmap mbitmap, float x, float y, float batasX, float batasY) {
        this.mbitmap = mbitmap;
        this.x = x;
        this.y = y;
        this.batasX = batasX;
        this.batasY = batasY;
        this.kecepatan = 0;
        this.flag1 = false;
        this.flag2 = false;
    }

    public Bitmap getMusuh(){
        return this.mbitmap;
    }

    public float getX() {
        return x;
    }

    public void setX() {
        if(flag1){
            this.x+=this.kecepatan;
            flag1=false;
        }
        else {
            this.x-=this.kecepatan;
            flag1=true;
        }
    }


    public float getY() {
        return y;
    }

    public void setY() {
        if(flag2){
            this.y+=this.kecepatan;
            this.flag2=false;
        }
        else {
            this.y-=this.kecepatan;
            flag2=true;
        }
    }

    public float getBatasX() {
        return batasX;
    }

    public void setBatasX(float batasX) {
        this.batasX = batasX;
    }

    public float getBatasY() {
        return batasY;
    }

    public void setBatasY(float batasY) {
        this.batasY = batasY;
    }

    public float getKecepatan() {
        return kecepatan;
    }

    public void setKecepatan(float kecepatan) {
        this.kecepatan = kecepatan;
    }

    public int randNum(){
        Random rand = new Random();
        return rand.nextInt(300);
    }
}
