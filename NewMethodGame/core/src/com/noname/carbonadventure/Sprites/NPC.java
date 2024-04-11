package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.noname.carbonadventure.Screens.PlayScreen;

public abstract class NPC extends Sprite {
    protected World world;
    protected PlayScreen screen;
    public Body b2body;

    public NPC(PlayScreen screen, float x, float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x,y);
        defineNPC();
    }

    protected abstract void defineNPC();

    public abstract void BodyHit();


}
