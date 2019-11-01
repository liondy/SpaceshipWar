package com.example.spaceshipwar;

import java.util.ArrayList;

public class ThreadMoveEnemy implements Runnable {
    protected Thread thread;
    protected UIThreadedWrapper uiThreadedWrapper;
    protected ArrayList<Bullet> bullets = new ArrayList<>();

    public ThreadMoveEnemy(UIThreadedWrapper uiThreadedWrapper, ArrayList<Bullet> bullets) {
        this.thread = new Thread();
        this.uiThreadedWrapper = uiThreadedWrapper;
        this.bullets = bullets;
    }

    public void start(){
        this.thread.start();
    }

    @Override
    public void run() {
        while(true){
            for (int i = 0; i < this.bullets.size(); i++) {
                this.bullets.get(i).setY((int)this.bullets.get(i).getY() + 30);
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

    public void setEnemyBullets(ArrayList<Bullet> bullets){
        this.bullets = bullets;
    }
}
