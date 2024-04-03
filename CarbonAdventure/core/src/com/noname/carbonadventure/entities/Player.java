package com.noname.carbonadventure.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite {
    private Vector2 velocity = new Vector2();
    private float speed = 60;

    public Player(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    public void update(float delta) {
        velocity.x = 0;
        velocity.y = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            velocity.x = -speed;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            velocity.x = speed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            velocity.y = speed;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            velocity.y = -speed;
        }

        setX(getX() + velocity.x * delta);
        setY(getY() + velocity.y * delta);
    }
}

