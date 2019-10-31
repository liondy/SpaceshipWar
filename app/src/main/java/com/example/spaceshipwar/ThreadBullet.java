package com.example.spaceshipwar;

import android.graphics.Bitmap;
import java.util.ArrayList;

public class ThreadBullet implements Runnable {
    protected  Thread thread;
    protected UIThreadedWrapper uiThreadedWrapper;
    protected Spaceship spaceship;

    public ThreadBullet(UIThreadedWrapper uiThreadedWrapper, Spaceship spaceship){
        this.uiThreadedWrapper = uiThreadedWrapper;
        this.thread = new Thread(this);
        this.spaceship = spaceship;
    }

    public void start(){
        this.thread.start();
    }

    @Override
    public void run() {
        while(true){
            Bullet bullet = new Bullet(this.spaceship.getX(),this.spaceship.getY()-100);
            this.uiThreadedWrapper.setBullet(bullet);
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
