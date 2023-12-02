/*
 * Created by Angelo Ventura on 11/19/2023.
 * Purpose: implement audio into code using Strategy Design Pattern
 */

package com.example.snake;

import android.media.SoundPool;
public interface AudioStrategy {
    int playSound(int soundID, SoundPool mSP);
}
