package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Scenes.Dialogue;
import com.noname.carbonadventure.Scenes.HUD;
import com.noname.carbonadventure.Screens.PlayScreen;

import java.util.Arrays;
import java.util.List;

public class Bus_Stop extends InteractiveTileObject {
    private final PlayScreen screen;
    private float x, y;

    public Bus_Stop(PlayScreen screen, Rectangle bounds) {
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
        List<String> busStops = Arrays.asList("Stop 1","","", "Stop 2","","", "Stop 3");

        Vector2 busStopPosition = new Vector2(x, y);

        new Dialogue(screen, screen.getStage(), "", "Welcome to the Dublin Bus!\n\nPlease choose a stop you would like to travel to:", busStops, true, busStopPosition);
        HUD.increaseCarbonMeter(10);
    }

    public Stage getStage() {
        return screen.getStage();
    }
}
