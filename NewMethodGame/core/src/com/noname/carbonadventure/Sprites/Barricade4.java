package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Screens.PlayScreen;

import java.util.ArrayList;
import java.util.List;

public class Barricade4 extends InteractiveTileObject {
    private static List<Barricade4> barricades4 = new ArrayList<>();

    public Barricade4(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(Play.OBJECT_BIT);
        barricades4.add(this); // Add this Barricade object to the list
    }
    @Override
    public void OnBodyHit() {
        Gdx.app.log("Barricade Collision", "");
    }

    public static void destroyAll() {
        // Destroy all Barricade objects
        for (Barricade4 barricade4 : barricades4) {
            barricade4.destroy();
        }
    }

    public void destroy() {
        // Destroy the Barricade
        setCategoryFilter(Play.DESTROYED_BIT);
        getCell().setTile(null);
    }
}
