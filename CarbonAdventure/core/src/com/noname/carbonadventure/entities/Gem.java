package com.noname.carbonadventure.entities;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Gem {
    private Vector2 position;
    private Texture texture;
    private boolean collected = false;

    public Gem(Texture texture, Vector2 position) {
        this.texture = texture;
        this.position = position;
    }

    public void draw(SpriteBatch batch) {
        if (!collected) {
            batch.draw(texture, position.x, position.y);
        }
    }

    public void collect() {
        collected = true;
    }

    public boolean isCollected() {
        return collected;
    }

    public Vector2 getPosition() {
        return position;
    }
}

