package com.example.spaceshipwar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Gameplay extends Fragment implements SensorEventListener {

    private Context context;

    private static Gameplay gameplay;
    private MainPresenter presenter;
    private TextView score;
    private ImageButton btn_left;
    private ImageButton btn_right;

    private Spaceship spaceship;
    private Enemy musuh;

    private Bitmap mBitmap;
    private Bitmap uBitmap;
    private ImageView imgContainer;
    private Canvas canvas;
    private Bitmap ship;
    private Bitmap ufo;
    private Paint paint;

    private int bitmapHeight;
    private int bitmapWidth;

    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Bullet> enemiesBullets = new ArrayList<>();
    private ThreadBullet threadBullet;
    private ThreadMove bulletMoveThread;
    private ThreadEnemy threadEnemy;
    private UIThreadedWrapper objUIWrapper;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private PostCalculateTask scoreWebService;

    private boolean gameStart=true;

    public Gameplay(){
        //require empty constructor
    }

    public static Gameplay createGameplay(MainPresenter presenter,Context context){
        if(gameplay==null){
            gameplay = new Gameplay();
            gameplay.presenter = presenter;
            gameplay.context=context;
        }
        return gameplay;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view  = inflater.inflate(R.layout.gameplay,container,false);
        imgContainer = (ImageView) view.findViewById(R.id.imgContainer);
        this.sensorManager = (SensorManager) this.context.getSystemService(Context.SENSOR_SERVICE);
        this.accelerometer = this.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        score = (TextView) view.findViewById(R.id.score);
        btn_left = (ImageButton) view.findViewById(R.id.btn_left);
        btn_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    btn_left.setColorFilter(getResources().getColor(R.color.white));
                    spaceship.moveLeft();
                    resetCanvas();
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    btn_left.setColorFilter(getResources().getColor(R.color.transparant));
                }
                return true;
            }
        });
        btn_right = (ImageButton) view.findViewById(R.id.btn_right);
        btn_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    btn_right.setColorFilter(getResources().getColor(R.color.white));
                    spaceship.moveRight();
                    resetCanvas();
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    btn_right.setColorFilter(getResources().getColor(R.color.transparant));
                }
                return true;
            }
        });
        return view;
    }

    public void setBitmapWidth(int width){
        this.bitmapWidth = width;
    }

    public void setBitmapHeight(int bitmapHeight) {
        this.bitmapHeight = bitmapHeight;
    }

    private void initiateCanvas(){
        this.objUIWrapper = new UIThreadedWrapper(this);

        this.mBitmap = Bitmap.createBitmap(this.bitmapWidth,this.bitmapHeight,Bitmap.Config.ARGB_8888);
        this.uBitmap = Bitmap.createBitmap(this.bitmapWidth,this.bitmapHeight,Bitmap.Config.ARGB_8888);
        this.ship = BitmapFactory.decodeResource(getResources(), R.drawable.spaceship);
        this.ufo = BitmapFactory.decodeResource(getResources(), R.drawable.ufo);
        this.mBitmap = this.mBitmap.copy(Bitmap.Config.ARGB_8888,true);
        this.uBitmap = this.uBitmap.copy(Bitmap.Config.ARGB_8888,true);
        this.canvas = new Canvas(this.mBitmap);
        this.paint = new Paint();

        this.spaceship = new Spaceship(ship,(this.mBitmap.getWidth()) / 2 - ship.getWidth()/2,this.mBitmap.getHeight()/2 + ship.getHeight() * 1.5f,this.bitmapWidth, ship.getWidth(),ship.getHeight());
        this.musuh = new Enemy(ufo, this.mBitmap.getWidth()/2-ufo.getWidth()/2,ufo.getHeight()-200,this.mBitmap.getWidth(),this.mBitmap.getHeight(),ufo.getWidth(),ufo.getHeight());

        this.threadBullet = new ThreadBullet(this.objUIWrapper,this.spaceship);
        this.threadBullet.start();
        this.bulletMoveThread = new ThreadMove(this.objUIWrapper,this.bullets,this.musuh);
        this.bulletMoveThread.start();

        this.threadEnemy = new ThreadEnemy(this.musuh,this.spaceship);
        this.threadEnemy.start();

        this.imgContainer.setImageBitmap(this.mBitmap);
        int tempScore = this.bulletMoveThread.getScore();
        this.score.setText(tempScore + "");
        this.scoreWebService.postExecute("2017730008", 1, tempScore);
        this.resetCanvas();
    }

    @Override
    public void onStart(){
        super.onStart();
        this.initiateCanvas();
        if(this.accelerometer!=null){
            this.sensorManager.registerListener(this,this.accelerometer,SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    public void onStop(){
        super.onStop();
        this.sensorManager.unregisterListener(this);
    }

    private void resetCanvas(){
        this.mBitmap.eraseColor(Color.TRANSPARENT);
        ColorFilter filter = new PorterDuffColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        paint.setColorFilter(filter);
        this.canvas.drawBitmap(spaceship.getSpaceship(), spaceship.getX(), spaceship.getY(), paint);
        this.canvas.drawBitmap(musuh.getMusuh(),musuh.getX(),musuh.getY(),new Paint());
        this.imgContainer.invalidate();
    }

    public void drawBullet(int x, int y){
        Rect rectangle = new Rect(x+175 , y + 50, x + 155, y+20 );
        this.canvas.drawRect(rectangle, paint);
    }

    public void setBullet(Bullet bullet){
        this.bullets.add(bullet);
        resetCanvas();
        for (int i = 0; i < this.bullets.size(); i++) {
            this.drawBullet((int)this.bullets.get(i).getX(), (int)this.bullets.get(i).getY());
        }

    }

    public void setBullets(ArrayList<Bullet> bullets){
        this.bullets = bullets;
        resetCanvas();
        for (int i = 0; i < this.bullets.size(); i++) {
            this.drawBullet((int)this.bullets.get(i).getX(), (int)this.bullets.get(i).getY());
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = this.accelerometer.getType();
        float temp = 0;
        switch (sensorType){
            case Sensor.TYPE_ACCELEROMETER:
                temp = sensorEvent.values[0];
                break;
        }
        if(temp > 1){
            this.spaceship.moveLeft();
        }
        else if(temp < -1){
            this.spaceship.moveRight();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
