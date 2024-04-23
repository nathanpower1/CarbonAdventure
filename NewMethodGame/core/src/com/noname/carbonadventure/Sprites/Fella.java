package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Scenes.Dialogue_NPC;
import com.noname.carbonadventure.Screens.PlayScreen;

public class Fella extends NPC {
    private float stateTime;
    private Animation<TextureRegion> NPCRun;
    private Array<TextureRegion> frames;
    private boolean isSoundPlaying = false;

    public Fella(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<>();
        for (int i = 1; i <= 2; i++) {
            frames.add(screen.getFellaAtlas().findRegion("Fella_down", i));
        }
        NPCRun = new Animation<>(0.5f, frames);

        stateTime = 0;
        setBounds(getX(), getY(), 18 / Play.PPM, 16 / Play.PPM);
        defineNPC();
    }

    @Override
    public void update(float dt) {
        stateTime += dt;
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(NPCRun.getKeyFrame(stateTime, true));
        b2body.setLinearVelocity(velocity);
    }

    @Override
    protected void defineNPC() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        CircleShape shape = new CircleShape();
        shape.setRadius(10 / Play.PPM);

        FixtureDef fdef = new FixtureDef();
        fdef.filter.categoryBits = Play.NPC_BIT;
        fdef.filter.maskBits = Play.DEFAULT_BIT | Play.GEM_BIT | Play.NPC_BIT | Play.OBJECT_BIT | Play.PLAYER_BIT;
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }

    public void BodyHit() {
        if (!isSoundPlaying) {
            Gdx.app.log("NPC Collision", "");
            Play.manager.get("audio/sounds/hello.wav", Sound.class).play();
            isSoundPlaying = true;

            Vector2 npcPosition = new Vector2(b2body.getPosition().x, b2body.getPosition().y);
            Dialogue_NPC dialogue = new Dialogue_NPC(screen, screen.getStage(), "", "I said forget about it cuh", npcPosition);

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    dialogue.closeDialog();
                    isSoundPlaying = false;
                }
            }, 3);
        }
    }
}
