package com.example.snake;

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
