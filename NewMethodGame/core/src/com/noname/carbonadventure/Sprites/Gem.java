package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Scenes.HUD;
import com.noname.carbonadventure.Screens.PlayScreen;

public class Gem extends InteractiveTileObject {
    private static int gemCount = 0; // Static variable to keep track of total gems

    private PlayScreen screen;

    public Gem(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        this.screen = screen;
        setCategoryFilter(Play.GEM_BIT);
        gemCount++;
        Gdx.app.log("Gem Collision Now", String.valueOf(gemCount));
    }

    @Override
    public void OnBodyHit() {
        Gdx.app.log("Gem Collision Before", String.valueOf(gemCount));

        // Play gem collection sound effect
        Play.manager.get("audio/sounds/Gem_Collect.wav", Sound.class).play();

        // Set category filter to DESTROYED_BIT to prevent further collisions
        setCategoryFilter(Play.DESTROYED_BIT);

        // Remove the tile representing the gem from the map
        getCell().setTile(null);

        // Update HUD to reflect gem collection
        HUD.addGemIcon(null);

        // Decrement gem count
        gemCount--;

        Gdx.app.log("Gem Collision Now", String.valueOf(gemCount));

        // Check if all gems are collected
        if (gemCount == 0) {
            screen.allGemsCollected();
            Barricade.destroyAll(); // Destroy all Barricade objects
            Gdx.app.log("All gems are collected!", "");
        }
    }
}
