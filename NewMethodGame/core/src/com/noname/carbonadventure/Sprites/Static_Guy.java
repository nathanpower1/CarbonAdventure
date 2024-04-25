package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.noname.carbonadventure.Scenes.EnvironmentDialogue;
import com.noname.carbonadventure.Screens.PlayScreen;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Static_Guy extends InteractiveTileObject {
    private final PlayScreen screen;
    private final List<String> environmentalFacts = Arrays.asList(
            "Taking the bus over cars reduces CO2 emissions by a significant amount",
            "Buses use less fuel per passenger compared to cars, making them an eco-friendly option for commuting.",
            "Public transportation can save over 1.4 billion gallons of gasoline annually.",
            "One full bus can remove up to 40 vehicles from the road, reducing congestion and greenhouse gases.",
            "Did you know that all buses are named Frank in Ireland",
            "In 2023, Dublin Bus introduced over 200 hybrid-electric vehicles to their fleet",
            "Buses run on alternative energy sources like natural gas, biodiesel, or electricity, which emit fewer pollutants than conventional gasoline and diesel.",
            "Buses contribute to reducing noise pollution compared to the cumulative effect of many cars, especially in dense urban areas.",
            "Bus systems often integrate with other forms of transportation like cycling and walking, promoting a healthier and more sustainable lifestyle."
    );

    public Static_Guy(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        this.screen = screen;
        fixture.setUserData(this);
    }

    @Override
    public void OnBodyHit() {
        Random random = new Random();
        String randomFact = environmentalFacts.get(random.nextInt(environmentalFacts.size()));
        new EnvironmentDialogue(screen.getStage(), randomFact);
    }

    public Stage getStage() {
        return screen.getStage();
    }
}
