/*
 * Created by Angelo on 11/26/2023.
 */

package com.example.snake;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
public class SnakeBody implements SnakeComponent{
    // bitmap for the snake's body
    private Bitmap mBitmapBody;

    // setter
    public void SetmBitmapBody(Bitmap mBitmapBody){
        this.mBitmapBody = mBitmapBody;
    }
    // getter
    public Bitmap GetmBitmapBody(){
        return mBitmapBody;
    }

    @Override
    public void draw(Canvas canvas, Paint paint){
    }
}
