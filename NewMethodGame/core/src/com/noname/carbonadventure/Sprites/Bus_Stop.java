package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Scenes.Dialogue;
import com.noname.carbonadventure.Scenes.HUD;
import com.noname.carbonadventure.Screens.PlayScreen;

import java.util.Arrays;
import java.util.List;

import static com.noname.carbonadventure.Play.player;

public class Bus_Stop extends InteractiveTileObject {
    private final PlayScreen screen;

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

        List<String> busStops = Arrays.asList("Stop 1", "Stop 2", "Stop 3");

        new Dialogue(screen.getStage(), "Welcome to Dublin Bus", "Please choose a stop you would like to travel to:", busStops);

        float destinationX = 3;// Change this to the desired X coordinate
        float destinationY = 3;// Change this to the desired Y coordinate

        // Teleport the player to the destination
        screen.teleportPlayer(player, destinationX, destinationY);

        // Update carbon meter
        HUD.increaseCarbonMeter(10);
    }

    public Stage getStage() {
        return screen.getStage();  

}
