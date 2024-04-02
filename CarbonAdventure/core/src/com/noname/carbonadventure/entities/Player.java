package com.noname.carbonadventure.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite{
    /** movement velocity*/
    private Vector2 velocity = new Vector2();
    private float speed = 60 * 2;

    public  Player(Sprite sprite){
        super(sprite);
    }

    @Override
    public void draw(Batch Batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(Batch);

    }

    public void update(float delta){

        setX(getX() + velocity.x * delta);
        setY(getY() + velocity.y * delta);

    }
}
