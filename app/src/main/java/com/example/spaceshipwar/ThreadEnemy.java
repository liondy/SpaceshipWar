package com.example.spaceshipwar;

import java.util.Random;

public class ThreadEnemy implements Runnable {
    protected Thread thread;
    protected Enemy enemy;

    public ThreadEnemy(Enemy enemy) {
        this.thread = new Thread(this);
        this.enemy = enemy;
    }

    public void start(){
        this.thread.start();
    }

    @Override
    public void run() {
        while(this.enemy.getY()<=this.enemy.getBatasY()-this.enemy.getY() && this.enemy.getX()<=this.enemy.getBatasX()-this.enemy.getX()){
            int kecepatan = this.enemy.randNum();
            this.enemy.setKecepatan(kecepatan);
            Random rand = new Random();
            int angkaRand = rand.nextInt(10);
            if(angkaRand>0){
                this.enemy.setX();
            }
            else{
                this.enemy.setY();
            }
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
