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

public class StaticNPC extends NPC {
    private float stateTime;
    private Animation<TextureRegion> NPCStand;
    private Array<TextureRegion> frames;
    private boolean isSoundPlaying = false;
    private int currentDialogueIndex = 0;
    private Array<Dialogue_NPC> dialogues;

    public StaticNPC(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();
        frames.add(screen.getNPCAtlas().findRegion("NPC_down", 1));
        NPCStand = new Animation<TextureRegion>(1f, frames);

        stateTime = 0;
        setBounds(getX(), getY(), 18 / Play.PPM, 16 / Play.PPM);

        // Initialize dialogues
        dialogues = new Array<Dialogue_NPC>();
        dialogues.add(new Dialogue_NPC(screen, screen.getStage(), "Dialogue 1", "Hello, how can I help you?", new Vector2(x, y)));
        dialogues.add(new Dialogue_NPC(screen, screen.getStage(), "Dialogue 2", "Leave me alone.", new Vector2(x, y)));
    }

    public void update(float dt) {
        stateTime += dt;
        setPosition(getX(), getY());
        setRegion(NPCStand.getKeyFrame(stateTime, true));
    }

    @Override
    protected void defineNPC() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(this.getX(), this.getY());
        bdef.type = BodyDef.BodyType.StaticBody; 
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / Play.PPM);
        fdef.filter.categoryBits = Play.NPC_BIT;
        fdef.filter.maskBits = Play.DEFAULT_BIT | Play.GEM_BIT | Play.NPC_BIT | Play.OBJECT_BIT | Play.PLAYER_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }

    public void BodyHit() {
        if (!isSoundPlaying) {
            Gdx.app.log("NPC Collision", "");
            Play.manager.get("audio/sounds/cuh.wav", Sound.class).play();
            isSoundPlaying = true;

            Dialogue_NPC currentDialogue = dialogues.get(currentDialogueIndex);
            currentDialogue.closeDialog();

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    currentDialogue.closeDialog();
                    isSoundPlaying = false;
                    // Optionally, cycle to the next dialogue or reset the index
                    currentDialogueIndex = (currentDialogueIndex + 1) % dialogues.size;
                }
            }, 1);
        }
    }
}