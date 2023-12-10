package com.example.snake;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class SnakeHead implements SnakeComponent{
    // A bitmap for each direction the head can face
    private Bitmap mBitmapHeadRight;
    private Bitmap mBitmapHeadLeft;
    private Bitmap mBitmapHeadUp;
    private Bitmap mBitmapHeadDown;
    // getters
    public Bitmap GetmBitmapHeadRight(){return mBitmapHeadRight;}
    public Bitmap GetmBitmapHeadLeft(){return mBitmapHeadLeft;}
    public Bitmap GetmBitmapHeadUp(){return mBitmapHeadUp;}
    public Bitmap GetmBitmapHeadDown(){return mBitmapHeadDown;}
    // setters
    public void SetmBitmapHeadRight(Bitmap mBitmapHeadRight){this.mBitmapHeadRight = mBitmapHeadRight;}
    public void SetmBitmapHeadLeft(Bitmap mBitmapHeadLeft){this.mBitmapHeadLeft = mBitmapHeadLeft;}
    public void SetmBitmapHeadUp(Bitmap mBitmapHeadUp){this.mBitmapHeadUp = mBitmapHeadUp;}
    public void SetmBitmapHeadDown(Bitmap mBitmapHeadDown){this.mBitmapHeadDown = mBitmapHeadDown;}

    @Override
    public void draw(Canvas canvas, Paint paint){
    }
}
