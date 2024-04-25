package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.noname.carbonadventure.Scenes.TrainEnvironmentDialogue;
import com.noname.carbonadventure.Screens.PlayScreen;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Static_Train_Guy extends InteractiveTileObject {
    private final PlayScreen screen;
    private final List<String> trainEnvironmentalFacts = Arrays.asList(
            "Trains emit far less CO2 per passenger kilometre than cars, making them a greener option.",
            "Rail transport requires significantly less energy per passenger than road transport.",
            "High-speed trains can compete effectively with air travel over similar distances in terms of total travel time.",
            "Using trains helps reduce road congestion and lowers the demand for road expansions.",
            "Trains are among the most energy-efficient modes of transport, especially when powered by electricity from renewable sources.",
            "Rail networks effectively lower urban air pollution by reducing reliance on car commuting.",
            "A single train can replace over 300 cars on the road, significantly cutting down on traffic and emissions.",
            "Investments in rail infrastructure can encourage more sustainable urban and intercity transportation networks."
    );

    public Static_Train_Guy(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        this.screen = screen;
        fixture.setUserData(this);
    }

    @Override
    public void OnBodyHit() {
        Random random = new Random();
        String randomFact = trainEnvironmentalFacts.get(random.nextInt(trainEnvironmentalFacts.size()));
        new TrainEnvironmentDialogue(screen.getStage(), randomFact);
    }

    public Stage getStage() {
        return screen.getStage();
    }
}
