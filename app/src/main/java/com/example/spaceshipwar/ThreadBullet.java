package com.example.spaceshipwar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Timer;

public class ThreadBullet implements Runnable {
    protected  Thread thread;
    protected UIThreadedWrapper uiThreadedWrapper;
    protected Timer timer;

    private Bitmap mBitmap;
    private int bitmapHeight;
    private int bitmapWidth;

    public ThreadBullet(UIThreadedWrapper uiThreadedWrapper){
        this.uiThreadedWrapper = uiThreadedWrapper;
        this.thread = new Thread(this);
    }
    public void setBitmapWidth(int width){
        this.bitmapWidth = width;
    }

    public void setBitmapHeight(int bitmapHeight) {
        this.bitmapHeight = bitmapHeight;
    }

    public void startThread(){
        this.thread.start();
    }

    public void stopThread(){
        this.thread.interrupt();
    }

    @Override
    public void run() {
        while(true){
            Bullet bullet = new Bullet(mBitmap,bitmapWidth/2,bitmapHeight/2,100);
            bullet.setY(bullet.getY()-10);
            this.uiThreadedWrapper.setImageViewOut(bullet);
            try{
                Thread.sleep(500);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
