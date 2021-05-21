package com.example.villacarlos.crusher;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;


public class Player {
    //Bitmap to get character from image
    private Bitmap f1,f2,dead;

    //coordinate
    private int x=0;
    private int y=0;

    //motion speed of the character
    private int speed = 0;

    //animation counter
    int playerCounter =1 ;

    //sizes
    int width, height;

    //boosting or not
    private boolean boosting;

    //gravity value
    private final int GRAVITY = -15;

    //controlling the y coordinate
    private int maxY,minY;

    //limit the bounds of the ship's speed
    private final int MIN_SPEED = 1, MAX_SPEED = 35;

    //creating a rect
    private Rect detectCollision;

    //constructor
    public Player(Context context, int screenX, int screenY){
        x = 75;
        y=50;
        speed =1;

        //Getting the bitmap from drawable resource
        f1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.f1);
        f2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.f2);
        dead = BitmapFactory.decodeResource(context.getResources(),R.drawable.fdead);

        width = (int) (screenX / 7);
        height = (int) (screenX / 7);

        //Scaling it down, have to fix the screen ratio here
        f1 = Bitmap.createScaledBitmap(f1,width,height,false);
        f2 = Bitmap.createScaledBitmap(f2,width,height,false);
        dead = Bitmap.createScaledBitmap(dead,width,height,false);

        //calculating maxY
        maxY = screenY - f1.getHeight();

        //top edge's y point is 0 so min y will always be zero
        minY = 0;

        //setting the boosting value to false initially
        boosting = false;

        //initializing rect object
        detectCollision = new Rect(x,y,f1.getWidth(), f1.getHeight());


    }

    //setting boosting true
    public void setBoosting(){
        boosting = true;
    }

    //setting boosting false
    public void stopBoosting() {
        boosting = false;
    }

    //method to update the coordinate of the character
    public void update(){
        //updating the x coordinate
        if(boosting){
            speed+=30;
        } else {
            speed-=20;
        }
        //controlling the top speed
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        //if the speed is less than min speed
        //controlling it so that it won't stop completely
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }

        //moving the ship down
        y -= speed + GRAVITY;

        //but controlling it also so that it won't go off the screen
        if (y < minY) {
            y = minY;
        }
        if (y > maxY) {
            y = maxY;
        }

        //adding top... to the rect object
        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + f1.getWidth();
        detectCollision.bottom = y + f1.getHeight();
    }

    public Rect getDetectCollision(){
        return detectCollision;
    }

    Bitmap getPlayer(){
        if(playerCounter == 1) {
            playerCounter++;
            return f1;
        }
        playerCounter=1;
        return f2;
    }

    //if the player is dead
    Bitmap getDead(){
        return dead;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }



}
