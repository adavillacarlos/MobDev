package com.example.villacarlos.crusher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable {
    volatile boolean playing;
    private Thread gameThread = null;

    private Player player;
    private Enemy[] enemies;

    private  int enemyCount = 4;

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    Bitmap background;

    int screenX, screenY;
    private ArrayList<Star> stars = new ArrayList<Star>();

    private Blast blast;

    int countMisses;

    boolean flag ;
    private boolean isGameOver ;
    int score;
    int highScore[] = new int[4];
    SharedPreferences sharedPreferences;

    Context context;

    static MediaPlayer bgmusic;
    final MediaPlayer gameoverSound;
    final MediaPlayer enemyKill;

    public GameView(Context context,int screenX,int screenY){
        super(context);

        player = new Player(context, screenX, screenY);

        surfaceHolder = getHolder();
        paint = new Paint();

        this.screenX = screenX;
        this.screenY = screenY;

        background = BitmapFactory.decodeResource(getResources(),R.drawable.background);

        int starNums = 100;
        for (int i = 0; i < starNums; i++) {
            Star s  = new Star(screenX, screenY);
            stars.add(s);
        }

        enemies = new Enemy[enemyCount];
        for(int i=0; i<enemyCount; i++){
            enemies[i] = new Enemy(context, screenX, screenY);
        }

        blast = new Blast(context);

        countMisses = 0;
        isGameOver = false;

        score = 0;
        sharedPreferences = context.getSharedPreferences("game",Context.MODE_PRIVATE);

        //initializing the array high scores with the previous values
        highScore[0] = sharedPreferences.getInt("score1",0);
        highScore[1] = sharedPreferences.getInt("score2",0);
        highScore[2] = sharedPreferences.getInt("score3",0);
        highScore[3] = sharedPreferences.getInt("score4",0);

        this.context = context;

        bgmusic = MediaPlayer.create(context,R.raw.bg);
        gameoverSound = MediaPlayer.create(context,R.raw.gameover);
        enemyKill= MediaPlayer.create(context,R.raw.explosion);

        bgmusic.setLooping(true);
        bgmusic.start();
    }


    @Override
    public void run() {
        while(playing){
            update();
            draw();
            control();

        }
    }

    private void update() {
        player.update();

        //setting boom outside the screen
        blast.setX(-250);
        blast.setY(-250);

        //Updating the stars with player speed
        for (Star s : stars) {
            s.update(player.getSpeed());
        }

        //updating the enemy coordinate with respect to player speed
        for(int i=0; i<enemyCount; i++){
            //setting the flag true when the enemy just enters the screen
            if(enemies[i].getX()==screenX){
                flag = true;
            }

            enemies[i].update(player.getSpeed());
            if(Rect.intersects(player.getDetectCollision(), enemies[i].getDetectCollision())){
                //incrementing score every collision
                score++;
                //displaying boom at that location
                blast.setX(enemies[i].getX());
                blast.setY(enemies[i].getY());
                enemyKill.start();

                //move the enemy outside the left page
                enemies[i].setX(-300);
            } else {
                if(flag) {
                    //if player's x coordinate is more than the enemies's x coordinate.i.e. enemy has just passed across the player
                    if (player.getDetectCollision().exactCenterX() >= enemies[i].getDetectCollision().exactCenterX()) {
                        countMisses++;
                        //setting the flag false so that the else part is executed only when new enemy enters the screen
                        flag = false;
                        //if no of Misses is equal to 5, then game is over.
                        if(countMisses==5){
                            //setting playing false to stop the game.
                            playing = false;
                            isGameOver = true;
                            //stopping the gameon music
                            bgmusic.stop();
                            //play the game over sound
                            gameoverSound.start();

                            //Assigning the scores to the highscore integer array
                            for(i=0;i<4;i++) {
                                if (highScore[i] < score) {
                                    highScore[i] = score;
                                    break;
                                }
                            }

                            SharedPreferences.Editor e = sharedPreferences.edit();
                            for(i=0;i<4;i++){
                                int j = i+1;
                                e.putInt("score"+j,highScore[i]);
                            }
                            e.apply();

                        }
                    }
                }
            }

        }


    }

    private void draw() {
        //if surface is valid

        if(surfaceHolder.getSurface().isValid()){

            //lock the canvas
            canvas = surfaceHolder.lockCanvas();

            Rect backgroundRect = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());
            canvas.drawBitmap(background,null,backgroundRect,null);

            //setting the paint color to white to draw the stars
            paint.setColor(Color.WHITE);
            //drawing all stars
            for (Star s : stars) {
                paint.setStrokeWidth(s.getStarWidth());
                canvas.drawPoint(s.getX(), s.getY(), paint);
            }


            //draw the player
            canvas.drawBitmap(
                   player.getPlayer(),
                    player.getX(),
                    player.getY(),
                    paint);

            //draw the enemy
            for(int i=0; i< enemyCount; i++){
                canvas.drawBitmap(
                        enemies[i].getEnemy(),
                        enemies[i].getX(),
                        enemies[i].getY(),
                        paint
                );
            }

            //drawing blast image
            canvas.drawBitmap(
                    blast.getBitmap(),
                    blast.getX(),
                    blast.getY(),
                    paint
            );

            //drawing the score on the game screen
            paint.setTextSize(40);
            canvas.drawText("Score:"+score,100,50,paint);

            //drawing the misses on the game screen
            paint.setTextSize(40);
            canvas.drawText("Misses:"+countMisses,300,50,paint);

            //draw game over when the game is over
            if(isGameOver){
                paint.setTextSize(150);
                paint.setTextAlign(Paint.Align.CENTER);

                int yPos=(int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));
                canvas.drawText("GAME OVER",canvas.getWidth()/2,yPos,paint);
            }

            //unblocking the canvas
            surfaceHolder.unlockCanvasAndPost(canvas);
        }

    }

    private void control() {
        try {

            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        //when the game is paused
        //setting the variable to false
        playing = false;
        try {
            //stopping the thread
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        //when the game is resumed
        //starting the thread again
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction() & event.ACTION_MASK){
            case MotionEvent.ACTION_UP:
                //stopping the boosting when screen is released
                player.stopBoosting();
                //play music
                break;
            case MotionEvent.ACTION_DOWN:
                //boosting the space jet when screen is pressed
                player.setBoosting();
                break;
        }
        //if the game's over, tapping on game Over screen sends you to MainActivity
        if(isGameOver){
            if(event.getAction()==MotionEvent.ACTION_DOWN){
                context.startActivity(new Intent(context,MainActivity.class));
            }
        }
        return true;
    }


}
