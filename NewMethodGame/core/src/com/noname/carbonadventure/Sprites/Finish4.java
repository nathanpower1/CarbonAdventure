package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Scenes.HUD;
import com.noname.carbonadventure.Screens.PlayScreen;
import com.noname.carbonadventure.Screens.*;


public class Finish4 extends InteractiveTileObject {
    private PlayScreen screen;
    private Play game;

    public static boolean playerFinish = false;

    private GameOverScreen gameoverscreen;

    public Finish4(PlayScreen screen, Rectangle rect) {
        super(screen, rect);
        this.screen = screen;
        fixture.setUserData(this);
        setCategoryFilter(Play.GEM_BIT);
    }

    @Override
    public void OnBodyHit() {
        int finalscore = HUD.getScore();
        System.out.println(finalscore);
        playerFinish = true;

        Gdx.app.log("Finish", "Finish line has been triggered.");
        Play.manager.get("audio/sounds/levelup.wav", Sound.class).play();
        Music music = Play.manager.get("audio/music/lasvegas.mp3", Music.class);
        Music music2 = Play.manager.get("audio/music/cowboy.mp3", Music.class);
        Music music3 = Play.manager.get("audio/music/finish.mp3", Music.class);



        if(!music.isPlaying() && !music2.isPlaying() && !music3.isPlaying() ){
            music3.play();}
        else if (music.isPlaying()| music2.isPlaying()| music3.isPlaying()){
            music2.stop();
            music.stop();
            music3.play();
        }

    }
}
