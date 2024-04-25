package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Screens.PlayScreen;

public class Dude extends NPC{
    private float stateTime;
    private Animation <TextureRegion> NPCRun;
    private Animation <TextureRegion> NPCDown;
    private Animation <TextureRegion> NPCUp;
    private TextureRegion NPCStand;
    private Array<TextureRegion> frames;
    private boolean isSoundPlaying = false;
    public Dude(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 1; i <= 2; i++) {
            frames.add(screen.getNPCAtlas().findRegion("NPC_down", i));
        }
        NPCRun = new Animation<TextureRegion>(0.5f,frames);

         stateTime = 0;
        setBounds(getX(),getY(),18/ Play.PPM,16 /Play.PPM);

    }

    public void update(float dt){
        stateTime += dt;
        setPosition(b2body.getPosition().x - getWidth()/2 , b2body.getPosition().y - getHeight()/2);
        setRegion(NPCRun.getKeyFrame(stateTime, true));
        b2body.setLinearVelocity(velocity);
    }

    @Override
    protected void defineNPC() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(this.getX(),this.getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / Play.PPM);
        fdef.filter.categoryBits = Play.NPC_BIT;
        fdef.filter.maskBits = Play.DEFAULT_BIT | Play.GEM_BIT | Play.NPC_BIT | Play.OBJECT_BIT | Play.PLAYER_BIT;




        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);;
        /*
        PolygonShape NPCbody = new PolygonShape();
        Vector2[] vertex = new Vector2[4];
        vertex[0] = new Vector2(-8,-8).scl(1/Play.PPM);
        vertex[1] = new Vector2(8,8).scl(1/Play.PPM);
        vertex[2] = new Vector2(8,-8).scl(1/Play.PPM);
        vertex[3] = new Vector2(-8,8).scl(1/Play.PPM);
        NPCbody.set(vertex);


        fdef.shape = NPCbody;
        fdef.filter.categoryBits = Play.NPC_BODY_BIT;*/


    }


   // @Override
    //public void BodyHit() {
    //    Gdx.app.log("NPC Collision","");
    //    Play.manager.get("audio/sounds/cuh.wav", Sound.class).play();
  //  }
    public void BodyHit() {
        // Check if the sound is not currently playing
        if (!isSoundPlaying) {
            Gdx.app.log("NPC Collision","");
            Play.manager.get("audio/sounds/cuh.wav", Sound.class).play();
            isSoundPlaying = true;



            Timer.schedule(new Timer.Task(){
                @Override
                public void run() {
                    isSoundPlaying = false;
                }
            }, 1);
        }
    }
}
