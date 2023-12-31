package com.example.snake;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import java.util.Random;

class Apple {

    // The location of the apples on the grid
    // Not in pixels
    private Point location = new Point();
    private Point location2 = new Point();
    private Point location3 = new Point();

    // The range of values we can choose from
    // to spawn the apples
    private Point mSpawnRange;
    private int mSize;

    // Images to represent the apples
    private Bitmap mBitmapApple;
    private Bitmap mBitmapPurpleApple;
    private Bitmap mBitmapGreenApple;


    /// Set up the apple in the constructor
    Apple(Context context, Point sr, int s){

        // Make a note of the passed in spawn range
        mSpawnRange = sr;
        // Make a note of the size of an apple
        mSize = s;
        // Hide the apples off-screen until the game starts
        location.x = -10;
        location2.x = -10;
        location3.x = -10;

        // Load the image to the bitmap
        mBitmapApple = BitmapFactory.decodeResource(context.getResources(), R.drawable.apple);
        mBitmapPurpleApple = BitmapFactory.decodeResource(context.getResources(), R.drawable.purple);
        mBitmapGreenApple = BitmapFactory.decodeResource(context.getResources(), R.drawable.green);

        // Resize the bitmap
        mBitmapApple = Bitmap.createScaledBitmap(mBitmapApple, s, s, false);
        mBitmapPurpleApple = Bitmap.createScaledBitmap(mBitmapPurpleApple, s, s, false);
        mBitmapGreenApple = Bitmap.createScaledBitmap(mBitmapGreenApple, s, s, false);
    }

    // This called everytime an apple is eaten
    public void spawn(){
        // Choose two random values and place the apples
        Random random = new Random();
        location.x = random.nextInt(mSpawnRange.x) + 1;
        location.y = random.nextInt(mSpawnRange.y - 1) + 1;
        location2.x = random.nextInt(mSpawnRange.x) + 1;
        location2.y = random.nextInt(mSpawnRange.y - 1) + 1;
        location3.x = random.nextInt(mSpawnRange.x) + 1;
        location3.y = random.nextInt(mSpawnRange.y - 1) + 1;

    }

    // Let SnakeGame know where the apples are
    // SnakeGame can share this with the snake
    public Point getLocation(){
        return location;
    }

    public Point getLocation2(){
        return location2;
    }

    public Point getLocation3(){
        return location3;
    }

    // Draw the apples
    public void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(mBitmapApple,
                location.x * mSize, location.y * mSize, paint);
        canvas.drawBitmap(mBitmapPurpleApple, location2.x * mSize, location2.y * mSize, paint);
        canvas.drawBitmap(mBitmapGreenApple, location3.x * mSize, location3.y * mSize, paint);
    }
}