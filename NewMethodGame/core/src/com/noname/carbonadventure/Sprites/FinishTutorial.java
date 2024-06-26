package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Scenes.HUD;
import com.noname.carbonadventure.Screens.PlayScreen;

import static com.noname.carbonadventure.Play.player;

public class FinishTutorial extends InteractiveTileObject {
    private PlayScreen screen;


    public FinishTutorial(PlayScreen screen, Rectangle rect) {
        super(screen, rect);
        this.screen = screen;
        fixture.setUserData(this);
        setCategoryFilter(Play.GEM_BIT);

    }

    @Override
    public void OnBodyHit() {
        HUD.levelReset(120);
        HUD.resetScore();
        Gdx.app.log("Finish", "Finish line has been triggered.");
        Play.manager.get("audio/sounds/levelup.wav", Sound.class).play();
        // 490/Play.PPM,3180/Play.PPM
        float destinationX = 490/Play.PPM;// Change this to the desired X coordinate
        float destinationY = 3180/Play.PPM;// Change this to the desired Y coordinate

        // Teleport the player to the destination
        screen.teleportPlayer(player, destinationX, destinationY);
        screen.updateMiniMap("maps/Level_1.0.tmx");
        screen.onPlayerTeleported();
        screen.startGameTimer();
    }
}
