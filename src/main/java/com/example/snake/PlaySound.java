/*
 * Created by Angelo on 11/26/2023.
 */

package com.example.snake;

import android.media.SoundPool;


public class PlaySound implements AudioStrategy{
    @Override
    public int playSound(int soundID, SoundPool mSP){
        // return the sound playing
        return mSP.play(soundID, 1, 1, 0, 0, 1);
    }
}
