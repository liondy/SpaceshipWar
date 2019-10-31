package com.example.spaceshipwar;

import android.util.Log;

import java.util.ArrayList;

public class ThreadMove implements Runnable{

    protected Thread thread;
    protected UIThreadedWrapper uiThreadedWrapper;
    private ArrayList<Bullet> bullets;

    public ThreadMove(UIThreadedWrapper uiThreadedWrapper, ArrayList<Bullet> bullets){
        this.uiThreadedWrapper = uiThreadedWrapper;
        this.thread = new Thread(this);
        this.bullets = bullets;
    }

    public void start(){
        this.thread.start();
    }


    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < this.bullets.size(); i++) {
                this.bullets.get(i).setY((int)this.bullets.get(i).getY() - 100);
            }
            this.uiThreadedWrapper.setBullets(this.bullets);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread");
        }
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

}


