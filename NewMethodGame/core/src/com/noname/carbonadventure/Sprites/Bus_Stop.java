package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Scenes.Dialogue;
import com.noname.carbonadventure.Screens.PlayScreen;

import java.util.Arrays;
import java.util.List;
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
        Play.manager.get("audio/sounds/bus_honk.wav", Sound.class).play();

        // List of bus stops
        List<String> busStops = Arrays.asList("Stop 1", "Stop 2", "Stop 3");

        // Create and show the dialogue box with the list of bus stops
        // Pass the stage from the PlayScreen to the Dialogue
        new Dialogue(screen.getStage(), "Welcome to Dublin Bus", "Please choose a stop you would like to travel to:", busStops);
    }

    public Stage getStage() {
        return screen.getStage();  // Assuming PlayScreen has a method to get the Stage
    }

        // Determine the destination coordinates for teleportation
        float destinationX = 3; // Change this to the desired X coordinate
        float destinationY = 3; // Change this to the desired Y coordinate

        // Teleport the player to the destination
        screen.teleportPlayer(player, destinationX, destinationY);

        // Update carbon meter
        HUD.increaseCarbonMeter(10); // Change the value as needed
}
