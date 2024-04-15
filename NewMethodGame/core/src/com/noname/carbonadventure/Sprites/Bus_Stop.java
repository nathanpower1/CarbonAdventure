package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Screens.PlayScreen;

import static com.noname.carbonadventure.Play.player;
import com.noname.carbonadventure.Scenes.HUD;

public class Bus_Stop extends InteractiveTileObject {
    private PlayScreen screen;

    public Bus_Stop(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        this.screen = screen;
        fixture.setUserData(this);
        setCategoryFilter(Play.GEM_BIT);
    }

    @Override
    public void OnBodyHit() {
        Gdx.app.log("Bus Stop Collision", "");

        // Determine the destination coordinates for teleportation
        float destinationX = 3; // Change this to the desired X coordinate
        float destinationY = 3; // Change this to the desired Y coordinate

        // Teleport the player to the destination
        screen.teleportPlayer(player, destinationX, destinationY);

        // Update carbon meter
        HUD.increaseCarbonMeter(10); // Change the value as needed
    }
}