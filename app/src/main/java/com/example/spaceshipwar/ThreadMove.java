package com.example.spaceshipwar;

import android.util.Log;

import java.util.ArrayList;

public class ThreadMove implements Runnable{

    protected Thread thread;
    protected UIThreadedWrapper uiThreadedWrapper;
    private ArrayList<Bullet> bullets;
    protected Enemy enemy;

    public ThreadMove(UIThreadedWrapper uiThreadedWrapper, ArrayList<Bullet> bullets, Enemy enemy){
        this.uiThreadedWrapper = uiThreadedWrapper;
        this.thread = new Thread(this);
        this.bullets = bullets;
        this.enemy = enemy;
    }

    public void start(){
        this.thread.start();
    }


    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < this.bullets.size(); i++) {
                if (Math.abs(this.bullets.get(i).getX() - this.enemy.getX()) < 75 && Math.abs(this.bullets.get(i).getY() - this.enemy.getY()) < 30) {
                    System.out.println("yes");
                    this.bullets.remove(i);
                    this.uiThreadedWrapper.setBullets(this.bullets);
                }
                this.bullets.get(i).setY((int)this.bullets.get(i).getY() - 100);
            }
            this.uiThreadedWrapper.setBullets(this.bullets);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

}


