package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Scenes.Dialogue_Tutorial;
import com.noname.carbonadventure.Scenes.HUD;
import com.noname.carbonadventure.Screens.PlayScreen;

import java.util.Arrays;
import java.util.List;

public class Bus_Stop_Tutorial extends InteractiveTileObject {
    private final PlayScreen screen;
    private float x, y;

    public Bus_Stop_Tutorial(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        this.screen = screen;
        fixture.setUserData(this);
        setCategoryFilter(Play.GEM_BIT);

        this.x = bounds.x / Play.PPM;
        this.y = bounds.y / Play.PPM;
    }

    @Override
    public void OnBodyHit() {
        Play.manager.get("audio/sounds/bus_honk.wav", Sound.class).play();

        List<String> tutorialMessages = Arrays.asList(
                "Welcome to the Dublin Bus Tutorial!",
                "This tutorial will explain how you can use the bus system to travel between different stops efficiently.",
                "You can select any of the stops on the map to travel, and you will be teleported there!",
                "Each stop is located near a valuable gem, but watch out, cause its not free! Keep an eye an your carbon meter as you traverse the stations!",
                "Goodluck!\nEnjoy your journey!"
        );

        new Dialogue_Tutorial(screen.getStage(), "", tutorialMessages);
        HUD.increaseCarbonMeter(10);
    }

    public Stage getStage() {
        return screen.getStage();
    }
}