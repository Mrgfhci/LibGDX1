package com.triforce_release5;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Created by Matthew on 11/18/2015.
 */
public class SoundFiles {
    public static Music BackgroundMusic, FlapSound;
    public SoundFiles(){

    }
    public static void load(){
        BackgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("BackgroundMusic.mp3")); //Loads the file.
        FlapSound = Gdx.audio.newMusic(Gdx.files.internal("FlapSound.mp3"));
        FlapSound.setVolume(1.0f);
        BackgroundMusic.setLooping(true); //Loops the file.
        BackgroundMusic.play(); //Plays the file.
        BackgroundMusic.setVolume(1.0f); //Sets the volume.
    }
    }