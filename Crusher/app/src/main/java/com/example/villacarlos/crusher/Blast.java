package com.example.villacarlos.crusher;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Blast {

    //create bitmap
    private Bitmap e1,e2,e3,e4,e5,e6,e7,e8, e9, e10;
    int explosionCounter = 1;

    //coordinate variables
    private int x,y;

    //constructor
    public Blast(Context context) {
        //getting boom image from drawable resource
        e1 = BitmapFactory.decodeResource (context.getResources(), R.drawable.e1);
        e2 = BitmapFactory.decodeResource (context.getResources(), R.drawable.e2);
        e3 = BitmapFactory.decodeResource (context.getResources(), R.drawable.e3);
        e4 = BitmapFactory.decodeResource (context.getResources(), R.drawable.e4);
        e5 = BitmapFactory.decodeResource (context.getResources(), R.drawable.e5);
        e6 = BitmapFactory.decodeResource (context.getResources(), R.drawable.e6);
        e7 = BitmapFactory.decodeResource (context.getResources(), R.drawable.e7);
        e8 = BitmapFactory.decodeResource (context.getResources(), R.drawable.e8);
        e9 = BitmapFactory.decodeResource (context.getResources(), R.drawable.e9);
        e10 = BitmapFactory.decodeResource (context.getResources(), R.drawable.e10);

        //setting the coordinate outside the screen
        //so that it won't shown up in the screen
        x = -250;
        y = -250;
    }

    //setters for x and y to make it visible at the place of collision
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    //getters
    public Bitmap getBitmap() {
        if(explosionCounter == 1) {
            explosionCounter++;
            return e1;
        }

        if(explosionCounter == 2) {
            explosionCounter++;
            return e2;
        }

        if(explosionCounter == 3) {
            explosionCounter++;
            return e3;
        }

        if(explosionCounter == 4) {
            explosionCounter++;
            return e4;
        }

        if(explosionCounter == 5) {
            explosionCounter++;
            return e5;
        }

        if(explosionCounter == 6) {
            explosionCounter++;
            return e6;
        }

        if(explosionCounter == 7) {
            explosionCounter++;
            return e7;
        }

        if(explosionCounter == 8) {
            explosionCounter++;
            return e8;
        }

        if(explosionCounter == 9) {
            explosionCounter++;
            return e9;
        }

        explosionCounter=1;
        return e10;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
