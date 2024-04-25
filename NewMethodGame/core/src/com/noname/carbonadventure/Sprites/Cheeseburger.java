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

public class Cheeseburger extends InteractiveTileObject {
    public static int burgerCount = 0; // Static variable to keep track of total burgers

    public Cheeseburger(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(Play.GEM_BIT);
    }

    @Override
    public void OnBodyHit() {


        // Play gem collection sound effect
        Play.manager.get("audio/sounds/Elvis_uh.mp3", Sound.class).play();
        burgerCount++;
        // Set category filter to DESTROYED_BIT to prevent further collisions
        setCategoryFilter(Play.DESTROYED_BIT);

        // Remove the tile representing the gem from the map
        getCell().setTile(null);
        }
    }
