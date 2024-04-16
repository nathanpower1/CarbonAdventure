package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Screens.PlayScreen;

import static com.noname.carbonadventure.Play.player;

public class Finish extends InteractiveTileObject {
    private PlayScreen screen;

    public Finish(PlayScreen screen, Rectangle rect) {
        super(screen, rect);
        this.screen = screen;
        fixture.setUserData(this);
        setCategoryFilter(Play.GEM_BIT);
    }

    @Override
    public void OnBodyHit() {

        Gdx.app.log("Finish", "Finish line has been triggered.");
        float destinationX = 490/Play.PPM;// Change this to the desired X coordinate
        float destinationY = 940/Play.PPM;// Change this to the desired Y coordinate

        // Teleport the player to the destination
        screen.teleportPlayer(player, destinationX, destinationY);
    }
}


