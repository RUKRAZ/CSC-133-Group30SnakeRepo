package com.example.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;


/**
 * Created by Angelo on 12/4/2023.
 */

public class DrawSnakeGame{

    private volatile boolean mPaused;
    private int mScore;
    private int mHighScore;
    private Canvas mCanvas;
    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;
    private Snake mSnake;
    private Apple mApple;
    private GoldenApple gApple;
    private Singleton singleton;
    private Bomb mBomb1, mBomb2, mBomb3, mBomb4, mBomb5,
            mBomb6, mBomb7, mBomb8, mBomb9, mBomb10,
            mBomb11, mBomb12, mBomb13, mBomb14, mBomb15;


    public DrawSnakeGame(boolean mPaused, int mScore, int mHighScore,
                         Canvas mCanvas, SurfaceHolder mSurfaceHolder, Paint mPaint,
                         Snake mSnake, Apple mApple, GoldenApple gApple, Singleton singleton,
                         Bomb mBomb1, Bomb mBomb2, Bomb mBomb3, Bomb mBomb4, Bomb mBomb5,
                         Bomb mBomb6, Bomb mBomb7, Bomb mBomb8, Bomb mBomb9, Bomb mBomb10,
                         Bomb mBomb11, Bomb mBomb12, Bomb mBomb13, Bomb mBomb14, Bomb mBomb15){
        this.mPaused = mPaused;

        this.mScore = mScore;
        this.mHighScore = mHighScore;

        this.mCanvas = mCanvas;
        this.mSurfaceHolder = mSurfaceHolder;
        this.mPaint = mPaint;

        this.mSnake = mSnake;
        this.mApple = mApple;
        this.gApple = gApple;
        this.singleton = singleton;

        this.mBomb1 = mBomb1;
        this.mBomb2 = mBomb2;
        this.mBomb3 = mBomb3;
        this.mBomb4 = mBomb4;
        this.mBomb5 = mBomb5;
        this.mBomb6 = mBomb6;
        this.mBomb7 = mBomb7;
        this.mBomb8 = mBomb8;
        this.mBomb9 = mBomb9;
        this.mBomb10 = mBomb10;
        this.mBomb11 = mBomb11;
        this.mBomb12 = mBomb12;
        this.mBomb13 = mBomb13;
        this.mBomb14 = mBomb14;
        this.mBomb15 = mBomb15;
    }

    public void draw() {
        if (mSurfaceHolder.getSurface().isValid()) {
            mCanvas = mSurfaceHolder.lockCanvas();

            // Fill the screen with a color
            mCanvas.drawColor(Color.argb(255, 26, 128, 182));

            // Set the size and color of the paint for the text
            mPaint.setColor(Color.argb(255, 255, 255, 255));
            mPaint.setTextSize(120);

            // Draw the score
            mCanvas.drawText("" + mScore, 20, 120, mPaint);
            mCanvas.drawText("High Score:" + mHighScore, 1100, 120, mPaint);

            // Draw the apple and the snake
            mApple.draw(mCanvas, mPaint);
            if (singleton == null) {
                gApple.goldDraw(mCanvas, mPaint);
            }
            mBomb1.bombDraw(mCanvas, mPaint);
            mBomb2.bombDraw(mCanvas, mPaint);
            mBomb3.bombDraw(mCanvas, mPaint);
            mBomb4.bombDraw(mCanvas, mPaint);
            mBomb5.bombDraw(mCanvas, mPaint);
            mBomb6.bombDraw(mCanvas, mPaint);
            mBomb7.bombDraw(mCanvas, mPaint);
            mBomb8.bombDraw(mCanvas, mPaint);
            mBomb9.bombDraw(mCanvas, mPaint);
            mBomb10.bombDraw(mCanvas, mPaint);
            mBomb11.bombDraw(mCanvas, mPaint);
            mBomb12.bombDraw(mCanvas, mPaint);
            mBomb13.bombDraw(mCanvas, mPaint);
            mBomb14.bombDraw(mCanvas, mPaint);
            mBomb15.bombDraw(mCanvas, mPaint);
            mSnake.draw(mCanvas, mPaint);

            // Draw some text while paused
            if (mPaused) {
                // Set the size and color of the paint for the text
                mPaint.setColor(Color.argb(255, 255, 255, 255));
                mPaint.setTextSize(250);

                // Draw the message
                mCanvas.drawText("Tap To Play!", 400, 500, mPaint);
                mPaint.setTextSize(80);
                mCanvas.drawText("If you pressed 'Pause'", 700, 625, mPaint);
                mCanvas.drawText("Press resume to continue your current game", 300, 700, mPaint);
                mPaint.setTextSize(120);
                mCanvas.drawText("Resume", 20, 250, mPaint);
            }
            //if (!mPaused) {
                // Draw "Pause" text
              //  mCanvas.drawText("Pause", 400, 120, mPaint);
           // }

            // Unlock the canvas and reveal the graphics for this frame
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }

}
