package com.example.snake;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import java.util.Random;

public class GoldenApple {
    // The location of the  gold apple on the grid
    // Not in pixels
    private Point goldLocation = new Point();
    // The range of values we can choose from
    // to spawn a gold apple
    private Point mSpawnRange;
    private int mSize;
    private Bitmap mBitmapGoldenApple;
    GoldenApple(Context context, Point sr, int s){
        // Make a note of the passed in spawn range
        mSpawnRange = sr;
        // Make a note of the size of the gold apple
        mSize = s;
        // Hide the gold apple off-screen until the game starts
        goldLocation.x = -10;
        mBitmapGoldenApple = BitmapFactory.decodeResource(context.getResources(), R.drawable.gold);
        mBitmapGoldenApple = Bitmap.createScaledBitmap(mBitmapGoldenApple, s, s, false);
    }

    public void goldSpawn() {
        // Choose two random values and place the gold apple
        Random random = new Random();
        goldLocation.x = random.nextInt(mSpawnRange.x) + 1;
        goldLocation.y = random.nextInt(mSpawnRange.y - 1) + 1;
    }

    // Let SnakeGame know where the gold apple is
    // SnakeGame can share this with the snake
    public Point getGoldLocation(){
        return goldLocation;
    }

    // Draw the gold apple
    public void goldDraw(Canvas canvas, Paint paint){
        canvas.drawBitmap(mBitmapGoldenApple, goldLocation.x * mSize, goldLocation.y * mSize, paint);
    }
}
