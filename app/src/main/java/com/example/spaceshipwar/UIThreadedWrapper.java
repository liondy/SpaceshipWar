package com.example.spaceshipwar;

import android.os.Message;
import android.os.Handler;

public class UIThreadedWrapper extends Handler {
    protected final static int MSG_SET_IMAGEVIEW_OUTPUT = 0;
    protected Gameplay gameplay;

    public UIThreadedWrapper(Gameplay mainActivity){
        this.gameplay = mainActivity;
    }

    @Override
    public void handleMessage(Message msg){
        if(msg.what==UIThreadedWrapper.MSG_SET_IMAGEVIEW_OUTPUT){
            Bullet parameter = (Bullet) msg.obj;
//            this.gameplay.setImageViewOut(parameter);
        }
    }

    public void setImageViewOut(Bullet bullet){
        Message msg = new Message();
        msg.what = MSG_SET_IMAGEVIEW_OUTPUT;
        msg.obj = bullet;
        this.sendMessage(msg);
    }
}
