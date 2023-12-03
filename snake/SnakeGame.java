package com.example.snake;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;
import java.util.Random;

class SnakeGame extends SurfaceView implements Runnable{

    // Objects for the game loop/thread
    private Thread mThread = null;
    // Control pausing between updates
    private long mNextFrameTime;
    // Is the game currently playing and or paused?
    private volatile boolean mPlaying = false;
    private volatile boolean mPaused = true;

    // for playing sound effects
    private SoundPool mSP;
    private int mEat_ID = -1;
    private int mCrashID = -1;

    // The size in segments of the playable area
    private final int NUM_BLOCKS_WIDE = 40;
    private int mNumBlocksHigh;

    // How many points does the player have
    private int mScore;
    private int mHighScore = 0;

    // Objects for drawing
    private Canvas mCanvas;
    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;

    // A snake ssss
    private Snake mSnake;
    // And an apple
    private Apple mApple;
    private GoldenApple gApple;
    private Bomb mBomb1, mBomb2, mBomb3, mBomb4, mBomb5, mBomb6, mBomb7, mBomb8, mBomb9, mBomb10, mBomb11, mBomb12, mBomb13, mBomb14, mBomb15 ;
    Singleton singleton;


    // This is the constructor method that gets called
    // from SnakeActivity
    public SnakeGame(Context context, Point size) {
        super(context);

        // Work out how many pixels each block is
        int blockSize = size.x / NUM_BLOCKS_WIDE;
        // How many blocks of the same size will fit into the height
        mNumBlocksHigh = size.y / blockSize;

        // Initialize the SoundPool
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            mSP = new SoundPool.Builder()
                    .setMaxStreams(5)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            mSP = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }
        try {
            AssetManager assetManager = context.getAssets();
            AssetFileDescriptor descriptor;

            // Prepare the sounds in memory
            descriptor = assetManager.openFd("get_apple.ogg");
            mEat_ID = mSP.load(descriptor, 0);

            descriptor = assetManager.openFd("snake_death.ogg");
            mCrashID = mSP.load(descriptor, 0);

        } catch (IOException e) {
            // Error
        }

        // Initialize the drawing objects
        mSurfaceHolder = getHolder();
        mPaint = new Paint();

        // Call the constructors of our two game objects
        mApple = new Apple(context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize);
        gApple = new GoldenApple(context, new Point(NUM_BLOCKS_WIDE, mNumBlocksHigh), blockSize);

        mSnake = new Snake(context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize);

        mBomb1 = new Bomb(context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize);

        mBomb2 = new Bomb(context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize);

        mBomb3 = new Bomb(context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize);

        mBomb4 = new Bomb(context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize);

        mBomb5 = new Bomb (context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize);

        mBomb6 = new Bomb(context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize);

        mBomb7 = new Bomb(context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize);

        mBomb8 = new Bomb(context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize);

        mBomb9 = new Bomb(context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize);

        mBomb10 = new Bomb (context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize);

        mBomb11 = new Bomb(context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize);

        mBomb12 = new Bomb(context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize);

        mBomb13 = new Bomb(context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize);

        mBomb14 = new Bomb(context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize);

        mBomb15 = new Bomb (context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize);

    }


    // Called to start a new game
    public void newGame() {

        // reset the snake
        mSnake.reset(NUM_BLOCKS_WIDE, mNumBlocksHigh);

        // Get the apple ready for dinner
        // spawn the other apples too
        mApple.spawn();
        if(singleton == null){
            gApple.goldSpawn();
        }

        mBomb1.bombSpawn();
        mBomb2.bombSpawn();
        mBomb3.bombSpawn();
        mBomb4.bombSpawn();
        mBomb5.bombSpawn();
        mBomb6.bombSpawn();
        mBomb7.bombSpawn();
        mBomb8.bombSpawn();
        mBomb9.bombSpawn();
        mBomb10.bombSpawn();
        mBomb11.bombSpawn();
        mBomb12.bombSpawn();
        mBomb13.bombSpawn();
        mBomb14.bombSpawn();
        mBomb15.bombSpawn();

        // Reset the mScore
        mScore = 0;

        // Setup mNextFrameTime so an update can triggered
        mNextFrameTime = System.currentTimeMillis();
    }

    public void newGameFromPaused(){
        if(mScore <=0 || mSnake.detectDeath()){
            newGame();
        } else {
            // Setup mNextFrameTime so an update can triggered
            mNextFrameTime = System.currentTimeMillis();
        }
    }


    // Handles the game loop
    @Override
    public void run() {
        while (mPlaying) {
            if(!mPaused) {
                // Update 10 times a second
                if (updateRequired()) {
                    updateApples();
                    updateGoldenApple();
                    updateBombs();
                    updateSnake();
                }
            }
            draw();
        }
    }


    // Check to see if it is time for an update
    public boolean updateRequired() {

        // Run at 10 frames per second
        final long TARGET_FPS = 10;
        // There are 1000 milliseconds in a second
        final long MILLIS_PER_SECOND = 1000;

        // Are we due to update the frame
        if(mNextFrameTime <= System.currentTimeMillis()){
            // Tenth of a second has passed

            // Setup when the next update will be triggered
            mNextFrameTime =System.currentTimeMillis()
                    + MILLIS_PER_SECOND / TARGET_FPS;

            // Return true so that the update and draw
            // methods are executed
            return true;
        }

        return false;
    }

    // Update all the game objects
    // Update this so that it will also check and do the needed functions when a green, a purple,
    // and a golden apple are eaten
    public void updateApples(){
        Random random = new Random();
        // Move the snake
        mSnake.move();
        // Did the head of the snake eat the apple?
        if(mSnake.checkDinner(mApple.getLocation())){
            // This reminds me of Edge of Tomorrow.
            // One day the apple will be ready!
            mApple.spawn();

            // the score will be updated in each eat method in each apple class
            // Add to  mScore
            mScore = mScore + 1;
            if(mScore >= mHighScore){
                mHighScore = mScore;
            }

            // Play a sound
            mSnake.playSnakeSound(mEat_ID, mSP);
        }
        if(mSnake.checkDinner(mApple.getLocation3())){
            mApple.spawn();
            int r = random.nextInt(100);
            mScore = mScore + r;
            if(mScore >= mHighScore){
                mHighScore = mScore;
            }
            mSnake.move2();
            mSnake.playSnakeSound(mEat_ID, mSP);
        }
        if(mSnake.checkDinner(mApple.getLocation2())){
            mApple.spawn();
            mScore = mScore - random.nextInt(50);
            mSnake.reset2();
            if (mScore < 0) {
                mPaused = true;
            }
            mSnake.playSnakeSound(mEat_ID, mSP);
        }
    }

    public void updateGoldenApple(){
        if(singleton == null){
            if(mSnake.checkDinner(gApple.getGoldLocation())){
                singleton = Singleton.getInstance(gApple);
                singleton.gSpawn();
                mScore = mScore + 1000;
                if(mScore >= mHighScore){
                    mHighScore = mScore;
                }
                mSnake.playSnakeSound(mEat_ID, mSP);
            }
        }
    }

    public void updateBombs(){
        if(mSnake.checkDinner(mBomb1.getBombLocation()) || mSnake.checkDinner(mBomb2.getBombLocation())
                || mSnake.checkDinner(mBomb3.getBombLocation()) || mSnake.checkDinner(mBomb4.getBombLocation())
                || mSnake.checkDinner(mBomb5.getBombLocation()) || mSnake.checkDinner(mBomb6.getBombLocation())
                || mSnake.checkDinner(mBomb7.getBombLocation()) || mSnake.checkDinner(mBomb8.getBombLocation())
                || mSnake.checkDinner(mBomb9.getBombLocation()) || mSnake.checkDinner(mBomb10.getBombLocation())
                || mSnake.checkDinner(mBomb11.getBombLocation()) || mSnake.checkDinner(mBomb12.getBombLocation())
                || mSnake.checkDinner(mBomb14.getBombLocation()) || mSnake.checkDinner(mBomb15.getBombLocation())) {
            mBomb1.bombSpawn();
            mBomb2.bombSpawn();
            mBomb3.bombSpawn();
            mBomb4.bombSpawn();
            mBomb5.bombSpawn();
            mBomb6.bombSpawn();
            mBomb7.bombSpawn();
            mBomb8.bombSpawn();
            mBomb9.bombSpawn();
            mBomb10.bombSpawn();
            mBomb11.bombSpawn();
            mBomb12.bombSpawn();
            mBomb13.bombSpawn();
            mBomb14.bombSpawn();
            mBomb15.bombSpawn();

            mScore = mScore - 30;

            if (mScore < 0) {
                mPaused = true;
            }
            mSnake.playSnakeSound(mEat_ID, mSP);
        }
    }

    public void updateSnake(){
        if (mSnake.detectDeath()) {
            // Pause the game ready to start again
            mSnake.playSnakeSound(mCrashID, mSP);

            mPaused =true;
        }
    }

    // Do all the drawing
    public void draw() {
        // Get a lock on the mCanvas
        if (mSurfaceHolder.getSurface().isValid()) {
            mCanvas = mSurfaceHolder.lockCanvas();

            // Fill the screen with a color
            mCanvas.drawColor(Color.argb(255, 26, 128, 182));

            // Set the size and color of the mPaint for the text
            mPaint.setColor(Color.argb(255, 255, 255, 255));
            mPaint.setTextSize(120);

            // Draw the score
            mCanvas.drawText("" + mScore, 20, 120, mPaint);
            mCanvas.drawText("High Score:" + mHighScore, 1100, 120, mPaint);

            // Draw the apple and the snake
            // update this to also draw the green, purple, and golden apple
            mApple.draw(mCanvas, mPaint);
            if(singleton == null){
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
            if(mPaused){

                // Set the size and color of the mPaint for the text
                mPaint.setColor(Color.argb(255, 255, 255, 255));
                mPaint.setTextSize(250);

                // Draw the message
                // We will give this an international upgrade soon
                //mCanvas.drawText("Tap To Play!", 200, 700, mPaint);
                mCanvas.drawText("Tap To Play!",
                        400, 500, mPaint);
                mPaint.setTextSize(80);
                mCanvas.drawText("If you pressed 'Pause'", 700, 625, mPaint);
                mCanvas.drawText("Press resume to continue your current game", 300, 700, mPaint);
                mPaint.setTextSize(120);
                mCanvas.drawText("Resume", 20, 250, mPaint);
            }
            if(mPaused == false){
                // Draw "Pause" text
                mCanvas.drawText("Pause", 400, 120, mPaint);
            }


            // Unlock the mCanvas and reveal the graphics for this frame
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    //fix the pause button error
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                if(motionEvent.getX() >= 20 && motionEvent.getX() <= 370) {
                    if(motionEvent.getY() <= 250){
                        mPaused = false;
                        newGameFromPaused();
                        return true;
                    }
                }
                if(motionEvent.getX() >= 400 && motionEvent.getX() <= 750){
                    if(motionEvent.getY() <=120){
                        mPaused = true;
                        return true;
                    }
                } else {
                    if (mPaused) {
                        mPaused = false;
                        newGame();

                        // Don't want to process snake direction for this tap
                        return true;
                    }
                }

                // Let the Snake class handle the input
                mSnake.switchHeading(motionEvent);
                break;

            default:
                break;

        }
        return true;
    }
    
    // Stop the thread
    public void pause() {
        mPlaying = false;
        try {
            mThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }

    // Start the thread
    public void resume() {
        mPlaying = true;
        mThread = new Thread(this);
        mThread.start();
    }
}