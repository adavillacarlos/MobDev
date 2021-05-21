package com.example.villacarlos.crusher;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

public class Enemy {
    //bitmap for the enemy
    private Bitmap enemy1, enemy2, enemy3, enemy4, enemy5;

    // x and y coordinates
    private int x, y;

    //enemy speed;
    private int speed = 2;

    //min and max coordinates to keep the enemy inside the screen
    private int maxX, minX;
    private int maxY, minY;

    //animation counter
    int enemyCounter =1 ;

    //sizes
    int width, height;

    //creating rect object to detect collision
    private Rect detectCollision;

    public Enemy(Context context, int screenX, int screenY){
        //bitmap from the drawable resource
        enemy1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.frame0001);
        enemy2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.frame0002);
        enemy3 = BitmapFactory.decodeResource(context.getResources(),R.drawable.frame0003);
        enemy4 = BitmapFactory.decodeResource(context.getResources(),R.drawable.frame0004);
        enemy5 = BitmapFactory.decodeResource(context.getResources(),R.drawable.frame0005);

        //rescaling the sizes
        width = (int) (screenX / 10);
        height = (int) (screenX / 10);
        enemy1 = Bitmap.createScaledBitmap(enemy1,width,height,false);
        enemy2 = Bitmap.createScaledBitmap(enemy2,width,height,false);
        enemy3 = Bitmap.createScaledBitmap(enemy3,width,height,false);
        enemy4 = Bitmap.createScaledBitmap(enemy4,width,height,false);
        enemy5 = Bitmap.createScaledBitmap(enemy5,width,height,false);

        //initializing min and max coordinates
        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;

        //generating random coordinate to add enemy
        Random random = new Random();
        speed = random.nextInt(10) + 10;
        x = screenX;
        y = random.nextInt(maxY) - enemy1.getHeight();

        //initializing the rect object
        detectCollision = new Rect(x,y, enemy1.getWidth(), enemy1.getHeight());

    }

    public void update(int playerSpeed){
        //decreasing x coordinate so that enemy will move right to left
        //x -= playerSpeed;
        x -= speed;

        //if enemy reaches the left edge
        if(x < minX - enemy1.getWidth()){
            //adding enemy again to the right edge
            Random random = new Random();
            speed = random.nextInt(10) + 10;
            x = maxX;
            y = random.nextInt(maxY) - enemy1.getHeight();
        }

        //adding the top, left, bottom and right to the rect object
        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + enemy1.getWidth();
        detectCollision.bottom = y + enemy1.getHeight();

    }

    //adding setters to x coordinate so we chan change it after collision
    public void setX(int x){
        this.x = x;
    }

    //getters
    public Rect getDetectCollision(){
        return  detectCollision;
    }

    public Bitmap getEnemy(){
        if(enemyCounter == 1){
            enemyCounter++;
            return enemy1;
        }
        if(enemyCounter == 2){
            enemyCounter++;
            return enemy2;
        }
        if(enemyCounter == 3){
            enemyCounter++;
            return  enemy3;
        }
        if(enemyCounter==4){
            enemyCounter++;
            return enemy4;
        }
        enemyCounter=1;
        return enemy5;
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
