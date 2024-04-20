package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Screens.PlayScreen;

public class Player extends Sprite {
    public enum State { UP, DOWN, RUNNING, STANDING, DEAD}
    public State currentState;
    public State previousState;
    private Animation <TextureRegion> playerRun;
    private Animation <TextureRegion> playerDown;
    private Animation <TextureRegion> playerUp;
    public World world;
    public Body b2body;
    private TextureRegion playerStand;
    private float stateTimer;

    private boolean runningRight;
    private boolean playerIsDead;
    private boolean hasPlayedBooSound = false;

    public Player(PlayScreen screen){
        //super(screen.getAtlas().findRegion("boy_down"));
        super(new Texture("player.png"));
        this.world = screen.getWorld();
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 4; i <6; i++)
            frames.add(new TextureRegion(getTexture(), i*16, 0 ,16, 16));
        playerRun = new Animation<TextureRegion>(.5f,frames);
        frames.clear();

        for(int i = 0; i <2; i++)
            frames.add(new TextureRegion(getTexture(), i*16, 0 ,16, 16));
        playerDown = new Animation<TextureRegion>(.5f,frames);
        frames.clear();

        for(int i = 6; i <8; i++)
            frames.add(new TextureRegion(getTexture(), i*16, 0 ,16, 16));
        playerUp = new Animation<TextureRegion>(.5f,frames);
        frames.clear();


        // definePlayer(490/Play.PPM,3180/Play.PPM);= First Map Spawn
        // definePlayer(490/Play.PPM,940/Play.PPM); = Bottom left map spawn
        // definePlayer(2730/Play.PPM,3180/Play.PPM); = Top Right map spawn
        // definePlayer(2410/Play.PPM,1570/Play.PPM); = Bottom Right Map Spawn
        definePlayer(490/Play.PPM,3180/Play.PPM);
        playerStand = new TextureRegion(getTexture(),0, 0, 16, 16);
        setBounds(0,0,16/Play.PPM,16/Play.PPM);
        setRegion(playerStand);

    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() /2 , b2body.getPosition().y - getHeight()/2);
        setRegion(getFrame(dt));
        // Reset state timer when state changes
        if (currentState != previousState) {
            stateTimer = 0;
            previousState = currentState;
        }
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();

        TextureRegion region;
        switch (currentState) {
            case DEAD:
                region = playerStand;
                break;
            case UP:
                region = playerUp.getKeyFrame(stateTimer,true);
                break;
            case RUNNING:
                region = playerRun.getKeyFrame(stateTimer, true);
                break;
            case DOWN:
                region = playerDown.getKeyFrame(stateTimer,true);
                break;
            default:
                region = playerStand;
                break;
        }
        if((b2body.getLinearVelocity().x <0 || !runningRight) && !region.isFlipX()){
            region.flip(true,false);
            runningRight = false;
    }
        else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true,false);
            runningRight = true;
        }
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        return region;
        }

    public State getState(){
        if(playerIsDead)
            return State.DEAD;
        else if(b2body.getLinearVelocity().y>0)
            return State.UP;
        else if(b2body.getLinearVelocity().y<0)
            return State.DOWN;
        else if (b2body.getLinearVelocity().x !=0)
            return State.RUNNING;
        else
            return State.STANDING;


        }


    public void definePlayer(float x, float y) {
        BodyDef bdef = new BodyDef();
        bdef.position.set(x, y); // Set the initial position to the teleport destination
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(8 / Play.PPM);
        fdef.filter.categoryBits = Play.PLAYER_BIT;
        fdef.filter.maskBits = Play.DEFAULT_BIT | Play.GEM_BIT | Play.OBJECT_BIT | Play.NPC_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData("Player_body");

        // Update the player's sprite position to match the new body position
        setPosition(x - getWidth() / 2, y - getHeight() / 2);
    }


    public void dead() {
        // Stop the music
        Music music = Play.manager.get("audio/music/buckbumble.mp3", Music.class);
        Music music2 = Play.manager.get("audio/music/cowboy.mp3", Music.class);

        if (music.isPlaying() | music2.isPlaying()) {
            music.stop();
            music2.stop();
        }

        // Check if the "boo" sound asset is loaded before playing it
        if (Play.manager.isLoaded("audio/sounds/booo.wav", Sound.class)) {
            // Only play the sound if it hasn't been played before
            if (!hasPlayedBooSound) {
                // Play the "boo" sound once without looping
                Sound booSound = Play.manager.get("audio/sounds/booo.wav", Sound.class);
                Play.manager.get("audio/sounds/wompwomp.mp3", Sound.class).play();
                booSound.play(1.0f); // Volume set to 1.0f to ensure it's not muted
                hasPlayedBooSound = true; // Update the flag to indicate that the sound has been played
            }
        } else {
            Gdx.app.error("AssetNotLoaded", "booo.wav is not loaded");
        }

        playerIsDead = true;
    }

    public boolean isDead() {
        return playerIsDead;
    }

    public float getStateTimer(){
        return stateTimer;
    }

    public Body getBody() {
        return this.b2body;
    }





}
