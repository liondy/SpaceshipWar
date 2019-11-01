package com.example.spaceshipwar;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import java.util.ArrayList;

public class UIThreadedWrapper extends Handler {

    protected final static int setEnemy=0;
    protected final static int setEnemies=1;
    protected final static int setBullet=3;
    protected final static int setBullets=4;


    protected Gameplay gameplay;

    public UIThreadedWrapper(Gameplay gameplay){
        this.gameplay = gameplay;
    }

    @Override
    public void handleMessage(Message msg){
        if(msg.what==UIThreadedWrapper.setBullet){
            Bullet parameter = (Bullet) msg.obj;
            this.gameplay.setBullet(parameter);
        }
        else if(msg.what==UIThreadedWrapper.setBullets){
            ArrayList<Bullet> parameter = (ArrayList<Bullet>) msg.obj;
            this.gameplay.setBullets(parameter);
        }
        else if(msg.what==UIThreadedWrapper.setEnemy){
            Bullet parameter = (Bullet) msg.obj;
            this.gameplay.setEnemyBullet(parameter);
        }
        else if(msg.what==UIThreadedWrapper.setEnemies){
            ArrayList<Bullet> parameter = (ArrayList<Bullet>) msg.obj;
            this.gameplay.setEnemiesBullet(parameter);
        }
    }

    public void setBullet(Bullet bullet){
        Message msg = new Message();
        msg.what = setBullet;
        msg.obj = bullet;
        this.sendMessage(msg);
    }

    public void setBullets(ArrayList<Bullet> bullets){
        Message msg = new Message();
        msg.what = setBullets;
        msg.obj = bullets;
        this.sendMessage(msg);
    }

    public void setEnemyBullet(Bullet enemy) {
        Message msg = new Message();
        msg.what = setEnemy;
        msg.obj = enemy;
        this.sendMessage(msg);
    }

    public void setEnemiesBullet(ArrayList<Bullet> enemies){
        Message msg = new Message();
        msg.what = setEnemies;
        msg.obj = enemies;
        this.sendMessage(msg);
    }

}
