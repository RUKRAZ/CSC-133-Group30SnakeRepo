package com.example.snake;

// Singleton class to make a single instance of the gold apple
// This will make it to where the gold apple will only spawn once for an entire session
public class Singleton {
    private static Singleton instance;
    public GoldenApple goldenApple;
    private Singleton(GoldenApple goldenApple){
        this.goldenApple = goldenApple;
    }

    public static Singleton getInstance(GoldenApple goldenApple){
        if(instance == null){
            instance = new Singleton(goldenApple);
        }
        return instance;
    }

    public void gSpawn(){
        if(instance == null){
            goldenApple.goldSpawn();
        }
    }

}
