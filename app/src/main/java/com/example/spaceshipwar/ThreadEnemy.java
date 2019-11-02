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
        while(this.enemy.getY()>=0 && this.enemy.getY()<=this.enemy.getBatasY() && this.enemy.getX()>=0 && this.enemy.getX()<=this.enemy.getBatasX()){
            int kecepatan = this.enemy.randNum();
            this.enemy.setKecepatan(kecepatan);
            Random rand = new Random();
            int angkaRand = rand.nextInt(21)-10;
            if(angkaRand>5){
                this.enemy.setX();
            }
            else{
                if(angkaRand<-8){
                    this.enemy.setY(false);
                }
                else{
                    this.enemy.setY(true);
                }
            }
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
