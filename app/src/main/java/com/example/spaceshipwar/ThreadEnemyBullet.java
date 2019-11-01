package com.example.spaceshipwar;

public class ThreadEnemyBullet implements Runnable {
    protected Thread thread;
    protected UIThreadedWrapper uiThreadedWrapper;
    protected Enemy enemy;

    public ThreadEnemyBullet(UIThreadedWrapper uiThreadedWrapper, Enemy enemy) {
        this.thread = new Thread();
        this.uiThreadedWrapper = uiThreadedWrapper;
        this.enemy = enemy;
    }

    public void start(){
        this.thread.start();
    }

    @Override
    public void run() {
        while (true){
            Bullet bullet = new Bullet(this.enemy.getX(),this.enemy.getY()-100);
            this.uiThreadedWrapper.setEnemyBullet(bullet);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
