package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Scenes.HUD;
import com.noname.carbonadventure.Screens.PlayScreen;

public class Gem2 extends InteractiveTileObject {
    private static int gemCount = 0; // Static variable to keep track of total gems

    public Gem2(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(Play.GEM_BIT);
        gemCount++;
    }

    @Override
    public void OnBodyHit() {
        Gdx.app.log("Gem Collision", "");
        Play.manager.get("audio/sounds/Gem_Collect.wav", Sound.class).play();
        setCategoryFilter(Play.DESTROYED_BIT);
        getCell().setTile(null);
        HUD.addGemIcon("img/yellow.png");
        gemCount--;

        // Check if all gems are collected
        if (gemCount == 0) {
            Barricade2.destroyAll(); // Destroy all Barricade objects
            System.out.println("All gems are collected!");
        }
    }
}
