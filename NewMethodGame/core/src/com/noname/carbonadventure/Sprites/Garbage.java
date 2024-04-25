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
import com.noname.carbonadventure.Scenes.HUD;

public class Garbage extends InteractiveTileObject{
    public Garbage(PlayScreen screen, Rectangle bounds){
        super(screen,bounds);
        fixture.setUserData(this);
        setCategoryFilter(Play.GEM_BIT);


    }

    @Override
    public void OnBodyHit() {

        Gdx.app.log("Garbage Collision","");
        Play.manager.get("audio/sounds/Gem_Collect.wav", Sound.class).play();
        setCategoryFilter(Play.DESTROYED_BIT);
        getCell().setTile(null);
        // Update carbon meter
        HUD.increaseCarbonMeter(-5); // Decrease carbon meter by 5
        //HUD.addScore(1);
        if (HUD.getCarbonMeter() == 0);{
            HUD.addScore(50);
        }
    }
}
