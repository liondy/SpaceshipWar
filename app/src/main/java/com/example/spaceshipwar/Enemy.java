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
    private float width;
    private float height;


    public Enemy(Bitmap mbitmap, float x, float y, float batasX, float batasY, float width, float height) {
        this.mbitmap = mbitmap;
        this.x = x;
        this.y = y;
        this.batasX = batasX;
        this.batasY = batasY;
        this.kecepatan = 0;
        this.flag1 = false;
        this.width=width;
        this.height=height;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public Bitmap getMusuh(){
        return this.mbitmap;
    }

    public float getX() {
        return x;
    }

    public void setX() {
        if(flag1){
            if(this.x+this.kecepatan>0&&this.x+this.kecepatan<this.getBatasX()){
                this.x+=this.kecepatan;
                flag1=false;
            }
        }
        else {
            if(this.x-this.kecepatan>0){
                this.x-=this.kecepatan;
                flag1=true;
            }
        }
    }


    public float getY() {
        return y;
    }

    public void setY(boolean down) {
        if(down){
            if(this.y+this.kecepatan>0 && this.y+this.kecepatan<(this.getBatasY()-this.y) * 1.5f){
                this.y+=this.kecepatan;
            }
        }
        else {
            if(this.y-this.kecepatan>0){
                this.y-=this.kecepatan;
            }
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
