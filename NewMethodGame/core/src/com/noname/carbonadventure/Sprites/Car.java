package com.noname.carbonadventure.Sprites;

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

public class Car extends Sprite implements Box2DObject {
    private Texture upTexture, downTexture, leftTexture, rightTexture;
    public Body b2body;
    private World world;

    // Attributes specific to car movement
    private float maxSpeed = 2.0f;
    private float acceleration = 0.1f;

    public Car(World world) {
        this.world = world;
        upTexture = new Texture("img/car_up.PNG");
        downTexture = new Texture("img/car_down.PNG");
        leftTexture = new Texture("img/car_left.PNG");
        rightTexture = new Texture("img/car_right.PNG");

        defineCar();
        float scale = 2.5f;
        setRegion(downTexture);
        float width = 32 * scale / Play.PPM;
        float height = 32 * scale / Play.PPM;
        setBounds(0, 0, width, height);
    }

    private void defineCar() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(100 / Play.PPM, 100 / Play.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16 / Play.PPM, 16 / Play.PPM);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData("car");
    }

    public void update(float dt) {
        Vector2 velocity = b2body.getLinearVelocity();

        // Update position based on the body's position
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);

        // Determine and set the texture region based on velocity direction
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

        // Manage speed limit
        if (velocity.len() > maxSpeed) {
            velocity = velocity.nor().scl(maxSpeed);
            b2body.setLinearVelocity(velocity);
        }
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
}


