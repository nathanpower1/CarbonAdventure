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

    public Gem(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(Play.GEM_BIT);
        gemCount++;
        Gdx.app.log("Gem Collision Now", String.valueOf(gemCount));
    }

    @Override
    public void OnBodyHit() {
        Gdx.app.log("Gem Collision Before", String.valueOf(gemCount));
        Play.manager.get("audio/sounds/Gem_Collect.wav", Sound.class).play();
        setCategoryFilter(Play.DESTROYED_BIT);
        getCell().setTile(null);
        HUD.addGemIcon();
        gemCount--;
        Gdx.app.log("Gem Collision Now", String.valueOf(gemCount));

        // Check if all gems are collected
        if (gemCount == 0) {
            Barricade.destroyAll(); // Destroy all Barricade objects
            System.out.println("All gems are collected!");
        }
    }
}
