package com.noname.carbonadventure.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.noname.carbonadventure.screens.AnimationManager;
import com.badlogic.gdx.graphics.Texture;

public class Player {
    public Vector2 position = new Vector2();
    public Vector2 velocity = new Vector2();
    private float speed = 60;
    private PlayerState currentState;
    private float stateTime = 0;
    private AnimationManager animationManager;

    private Texture texture;

    public Player(Vector2 startPosition, AnimationManager animationManager) {
        this.position.set(startPosition);
        this.currentState = PlayerState.IDLE;
        this.animationManager = animationManager;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return 0;
    }

    public float getHeight() {
        return 0;
    }

    public Texture getTexture() {
        return texture;
    }

    public void dispose() {
        texture.dispose();
    }

    public void update(float delta) {
        handleInput();
        position.add(velocity.x * delta, velocity.y * delta);
        stateTime += delta;
        velocity.set(0, 0);
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            velocity.x = -speed;
            currentState = PlayerState.WALKING_LEFT;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            velocity.x = speed;
            currentState = PlayerState.WALKING_RIGHT;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            velocity.y = speed;
            currentState = PlayerState.WALKING_UP;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            velocity.y = -speed;
            currentState = PlayerState.WALKING_DOWN;
        } else {
            currentState = PlayerState.IDLE;
        }
    }

    public void draw(SpriteBatch batch) {

        TextureRegion currentFrame = animationManager.getFrame(currentState, stateTime);
        batch.draw(currentFrame, position.x, position.y);
    }
}

