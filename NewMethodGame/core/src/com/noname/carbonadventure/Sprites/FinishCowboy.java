package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Scenes.HUD;
import com.noname.carbonadventure.Screens.PlayScreen;

import static com.noname.carbonadventure.Play.player;

public class FinishCowboy extends InteractiveTileObject {
    private PlayScreen screen;

    public FinishCowboy(PlayScreen screen, Rectangle rect) {
        super(screen, rect);
        this.screen = screen;
        fixture.setUserData(this);
        setCategoryFilter(Play.GEM_BIT);
    }

    @Override
    public void OnBodyHit() {
        HUD.addScore(2000);
        HUD.levelReset(120);
        Gdx.app.log("Finish", "Finish line has been triggered.");
        Play.manager.get("audio/sounds/levelup.wav", Sound.class).play();
        Music music = Play.manager.get("audio/music/cowboyTrio.mp3", Music.class);
        Music music2 = Play.manager.get("audio/music/buckbumble.mp3", Music.class);
        Music music3 = Play.manager.get("audio/music/lasvegas.mp3", Music.class);
        Music music4 = Play.manager.get("audio/music/cowboy.mp3", Music.class);



        if(!music.isPlaying() && !music2.isPlaying() && !music3.isPlaying() ){
            music4.play();}
        else if (music.isPlaying()| music2.isPlaying()| music3.isPlaying()){
            music2.stop();
            music3.stop();
            music.stop();
            music4.play();
        }
        // 2410/Play.PPM,1570/Play.PPM
        float destinationX = 2730/Play.PPM;// Change this to the desired X coordinate
        float destinationY = 3180/Play.PPM;// Change this to the desired Y coordinate

        // Teleport the player to the destination
        screen.teleportPlayer(player, destinationX, destinationY);
        screen.updateMiniMap("maps/Level_1.1.tmx");
        screen.onPlayerTeleportedCowboyDefeat();
        screen.setCowboyFinishTriggered(true);
    }
}
