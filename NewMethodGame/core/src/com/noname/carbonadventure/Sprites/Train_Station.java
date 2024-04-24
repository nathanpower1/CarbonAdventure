package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Scenes.HUD;
import com.noname.carbonadventure.Screens.PlayScreen;
import com.noname.carbonadventure.Scenes.Dialogue_Train;

import java.util.Arrays;
import java.util.List;

public class Train_Station extends InteractiveTileObject {
    private final PlayScreen screen;
    private float x, y;
    private Dialogue_Train currentDialogue;

    public Train_Station(PlayScreen screen, Rectangle bounds) {
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
        List<String> trainStops = Arrays.asList("N1","","E1","","S1","","Hub","","N2","","E2","","S2");

        Gdx.app.log("Train_Station", "Collision detected at x: " + x + ", y: " + y);


        Vector2 trainStopPosition = new Vector2(x, y);

        if (currentDialogue != null) {
            currentDialogue.dispose();
        }
        currentDialogue = new Dialogue_Train(screen, screen.getStage(), "", "Welcome to the Dublin Train!\n\nPlease choose a stop you would like to travel to:", trainStops, trainStopPosition);
        HUD.increaseCarbonMeter(10);
    }

    public void update(float delta) {
        if (currentDialogue != null) {
            currentDialogue.update(delta);
            Vector2 playerPosition = screen.getPlayer().getPosition();
            float distance = new Vector2(x, y).dst(playerPosition);

            if (distance > Dialogue_Train.distance_min) {
                currentDialogue.closeDialog();
                currentDialogue = null;
            }
        }
    }

    public Stage getStage() {
        return screen.getStage();
    }
}