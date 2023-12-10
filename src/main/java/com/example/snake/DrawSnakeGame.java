package com.example.snake;

/*
 * Created by Angelo on 12/4/2023.
*/

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class DrawSnakeGame {
    public void draw(Canvas canvas, Paint paint, SnakeGame snakeGame) {
        if(snakeGame.mSurfaceHolder.getSurface().isValid()){
            canvas = snakeGame.mSurfaceHolder.lockCanvas();
            // Fill the screen with a color
            canvas.drawColor(Color.argb(255, 26, 128, 182));

            // Set the size and color of the paint for the text
            paint.setColor(Color.argb(255, 255, 255, 255));
            paint.setTextSize(120);

            // Draw the score
            canvas.drawText("" + snakeGame.mScore, 20, 120, paint);
            canvas.drawText("High Score:" + snakeGame.mHighScore, 1100, 120, paint);

            // Draw the apples and the snake
            snakeGame.mApple.draw(canvas, paint);
            // Only draw the gold apple if an instance of it does not exist yet
            if (snakeGame.singleton == null) {
                snakeGame.gApple.goldDraw(canvas, paint);
            }
            // Draw the bombs
            snakeGame.mBomb1.bombDraw(canvas, paint);
            snakeGame.mBomb2.bombDraw(canvas, paint);
            snakeGame.mBomb3.bombDraw(canvas, paint);
            snakeGame.mBomb4.bombDraw(canvas, paint);
            snakeGame.mBomb5.bombDraw(canvas, paint);
            snakeGame.mBomb6.bombDraw(canvas, paint);
            snakeGame.mBomb7.bombDraw(canvas, paint);
            snakeGame.mBomb8.bombDraw(canvas, paint);
            snakeGame.mBomb9.bombDraw(canvas, paint);
            snakeGame.mBomb10.bombDraw(canvas, paint);
            snakeGame.mBomb11.bombDraw(canvas, paint);
            snakeGame.mBomb12.bombDraw(canvas, paint);
            snakeGame.mBomb13.bombDraw(canvas, paint);
            snakeGame.mBomb14.bombDraw(canvas, paint);
            snakeGame.mBomb15.bombDraw(canvas, paint);
            snakeGame.mSnake.draw(canvas, paint);

            // Draw some text while paused
            if (snakeGame.mPaused) {
                // Set the size and color of the paint for the text
                paint.setColor(Color.argb(255, 255, 255, 255));
                paint.setTextSize(250);

                // Draw the message
                canvas.drawText("Tap To Play!", 400, 500, paint);
                paint.setTextSize(80);
                canvas.drawText("If you pressed 'Pause'", 700, 625, paint);
                canvas.drawText("Press resume to continue your current game", 300, 700, paint);
                paint.setTextSize(120);
                canvas.drawText("Resume", 20, 250, paint);
            }
            if (!snakeGame.mPaused) {
                // Draw "Pause" text
                canvas.drawText("Pause", 400, 120, paint);
            }

            // Unlock the canvas and reveal the graphics for this frame
            snakeGame.mSurfaceHolder.unlockCanvasAndPost(canvas);
         }
    }
}


