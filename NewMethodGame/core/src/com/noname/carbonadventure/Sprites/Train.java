package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Screens.PlayScreen;

public class Train extends NPC {
    private float stateTime;
    private Animation <TextureRegion> trainDown;
    private Texture trainUp;
    private boolean isSoundPlaying = false;
    public Vector2 trainVelocity;


    public Train(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        trainUp = new Texture("img/train.png");

        trainVelocity = new Vector2(0,.1f);

        stateTime = 0;
        float scale = 5f;
        float width = 2 * scale / Play.PPM;
        float height = 14 * scale / Play.PPM;
        setBounds(0, 0, width, height);
    }

    @Override
    public void update(float dt){
        stateTime += dt;
        setPosition(b2body.getPosition().x - getWidth()/2 , b2body.getPosition().y - getHeight()/2);
        setRegion(trainUp);
        b2body.setLinearVelocity(trainVelocity);
    }

    @Override
    protected void defineNPC() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(this.getX(), this.getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(3 / Play.PPM, (14 * 3) / Play.PPM);

        FixtureDef fdef = new FixtureDef();
        fdef.filter.categoryBits = Play.NPC_BIT;
        fdef.filter.maskBits = Play.DEFAULT_BIT | Play.GEM_BIT | Play.NPC_BIT | Play.OBJECT_BIT | Play.PLAYER_BIT;

        fdef.shape = shape;
        // Change this line in defineNPC() method
        b2body.createFixture(fdef).setUserData(this);



    }

    public void BodyHit() {

        }

    public void trainReverseVelocity(boolean y) {
        if (y) {
            trainVelocity.y = -trainVelocity.y;
        }
    }
    }
