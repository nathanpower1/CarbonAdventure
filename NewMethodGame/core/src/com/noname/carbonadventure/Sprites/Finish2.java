package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Scenes.HUD;
import com.noname.carbonadventure.Screens.PlayScreen;

import static com.noname.carbonadventure.Play.player;

public class Finish2 extends InteractiveTileObject {
    private PlayScreen screen;

    public Finish2(PlayScreen screen, Rectangle rect) {
        super(screen, rect);
        this.screen = screen;
        fixture.setUserData(this);
        setCategoryFilter(Play.GEM_BIT);
    }

    @Override
    public void OnBodyHit() {
        HUD.resetGemIcons();
        Gdx.app.log("Finish", "Finish line has been triggered.");
        // 2730/Play.PPM,3180/Play.PPM)
        float destinationX = 2730/Play.PPM;// Change this to the desired X coordinate
        float destinationY = 3180/Play.PPM;// Change this to the desired Y coordinate

        // Teleport the player to the destination
        screen.teleportPlayer(player, destinationX, destinationY);
    }
}
