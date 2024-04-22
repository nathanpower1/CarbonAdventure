package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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

import static com.noname.carbonadventure.Play.player;

public class CowboyDuel extends NPC {
    private float stateTime;
    private Animation<TextureRegion> NPCRun;
    private Animation<TextureRegion> NPCDown;
    private Animation<TextureRegion> NPCUp;
    private TextureRegion NPCStand;
    private Array<TextureRegion> frames;
    private boolean isSoundPlaying = false;

    public CowboyDuel(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 1; i <= 2; i++) {
            frames.add(screen.getCowboyAtlas().findRegion("cowboy_up", i));
        }
        NPCRun = new Animation<TextureRegion>(0.5f, frames);

        stateTime = 0;
        setBounds(getX(), getY(), 18 / Play.PPM, 16 / Play.PPM);
    }

    @Override
    public void update(float dt) {
        stateTime += dt;
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(NPCRun.getKeyFrame(stateTime, true));
    }

    @Override
    protected void defineNPC() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(this.getX(), this.getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / Play.PPM);
        fdef.filter.categoryBits = Play.NPC_BIT;
        fdef.filter.maskBits = Play.DEFAULT_BIT | Play.GEM_BIT | Play.NPC_BIT | Play.OBJECT_BIT | Play.PLAYER_BIT;


        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);


    }
    public void BodyHit() {}
}
