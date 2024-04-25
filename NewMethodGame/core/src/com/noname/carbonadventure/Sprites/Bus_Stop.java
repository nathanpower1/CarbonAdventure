package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Scenes.Dialogue_Bus;
import com.noname.carbonadventure.Screens.PlayScreen;

import java.util.Arrays;
import java.util.List;

public class Bus_Stop extends InteractiveTileObject {
    private final PlayScreen screen;
    private float x, y;
    private Dialogue_Bus currentDialogue;

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
        List<String> busStops = Arrays.asList("East Lake","","Central Park","","West Side");
        Vector2 busStopPosition = new Vector2(x, y);

        if (currentDialogue != null) {
            currentDialogue.dispose();
        }
        currentDialogue = new Dialogue_Bus(screen, screen.getStage(), "", "Please choose a location you would like to travel to:", busStops, busStopPosition);
    }

    public void update(float delta) {
        if (currentDialogue != null) {
            Vector2 playerPosition = screen.getPlayer().getPosition();
            float distance = new Vector2(x, y).dst(playerPosition);

            if (distance > Dialogue_Bus.distance_min) {
                currentDialogue.closeDialog();
                currentDialogue = null;
            }
        }
    }

    public Stage getStage() {
        return screen.getStage();
    }
}
