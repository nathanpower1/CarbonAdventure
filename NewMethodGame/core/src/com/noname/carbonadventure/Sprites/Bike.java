package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Scenes.HUD;

public class Bike extends Sprite implements Box2DObject {
    private Texture upTexture, downTexture, leftTexture, rightTexture, idleTexture;
    public Body b2body;
    private World world;

    // Attributes specific to car movement
    private float maxSpeed = 1.5f;
    private float acceleration = 0.1f;

    public Bike(World world) {
        this.world = world;
        upTexture = new Texture("img/skateboardUp1.PNG");
        downTexture = new Texture("img/skateboardDown1.PNG");
        leftTexture = new Texture("img/skateboardLeft.PNG");
        rightTexture = new Texture("img/skateboardRight.PNG");
        idleTexture = new Texture("img/skateboard.PNG");

        defineCar();
        float scale = .75f;
        setRegion(idleTexture);
        float width = 64 * scale / Play.PPM;
        float height = 32 * scale / Play.PPM;
        setBounds(0, 0, width, height);
    }

    private void defineCar() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(100 / Play.PPM, 100 / Play.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(5 / Play.PPM, 5 / Play.PPM);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData("bike");
    }

    public void update(float dt) {
        Vector2 velocity = b2body.getLinearVelocity();

        if (!isMoving()) {
            setRegion(new TextureRegion(idleTexture));  // Use the idle texture when not moving
            b2body.setLinearVelocity(0, 0); // Stop the bike by setting velocity to zero
        } else {
            // Handle direction and texture update based on the velocity direction
            if (Math.abs(velocity.x) > Math.abs(velocity.y)) {
                if (velocity.x > 0) {
                    setRegion(new TextureRegion(rightTexture));
                } else if (velocity.x < 0) {
                    setRegion(new TextureRegion(leftTexture));
                }
            } else if (Math.abs(velocity.y) > Math.abs(velocity.x)) {
                if (velocity.y > 0) {
                    setRegion(new TextureRegion(upTexture));
                } else if (velocity.y < 0) {
                    setRegion(new TextureRegion(downTexture));
                }
            }

            // Cap the velocity at the maximum speed
            if (velocity.len() > maxSpeed) {
                velocity = velocity.nor().scl(maxSpeed);
                b2body.setLinearVelocity(velocity);
            }
        }

        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }

    public void accelerate(Vector2 direction) {
        Vector2 currentVelocity = b2body.getLinearVelocity();
        Vector2 addedVelocity = direction.scl(acceleration);
        b2body.setLinearVelocity(currentVelocity.add(addedVelocity));
    }

    public Body getBody() {
        return b2body;
    }

    public void changeTexture(Direction direction) {
        switch (direction) {
            case UP:
                setRegion(new TextureRegion(upTexture));
                break;
            case DOWN:
                setRegion(new TextureRegion(downTexture));
                break;
            case LEFT:
                setRegion(new TextureRegion(leftTexture));
                break;
            case RIGHT:
                setRegion(new TextureRegion(rightTexture));
                break;
        }
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
    private boolean isMoving() {
        return Gdx.input.isKeyPressed(Input.Keys.UP) ||
                Gdx.input.isKeyPressed(Input.Keys.DOWN) ||
                Gdx.input.isKeyPressed(Input.Keys.LEFT) ||
                Gdx.input.isKeyPressed(Input.Keys.RIGHT);
    }

}

