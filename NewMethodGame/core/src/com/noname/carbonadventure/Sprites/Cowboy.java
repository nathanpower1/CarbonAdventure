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
import com.noname.carbonadventure.Scenes.Dialogue_Tutorial;
import com.noname.carbonadventure.Scenes.HUD;
import com.noname.carbonadventure.Screens.PlayScreen;

import java.util.Arrays;
import java.util.List;

import static com.noname.carbonadventure.Play.player;

public class Cowboy extends NPC {
    private float stateTime;
    private Animation<TextureRegion> NPCRun;
    private Animation <TextureRegion> NPCDown;
    private Animation <TextureRegion> NPCUp;
    private TextureRegion NPCStand;
    private Array<TextureRegion> frames;
    private boolean isSoundPlaying = false;

    public static boolean cowboyInteracted = false;

    public Cowboy(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 1; i <= 2; i++) {
            frames.add(screen.getCowboyAtlas().findRegion("cowboy_down", i));
        }
        NPCRun = new Animation<TextureRegion>(0.5f,frames);

        stateTime = 0;
        setBounds(getX(),getY(),18/ Play.PPM,16 /Play.PPM);
    }

    @Override
    public void update(float dt){
        stateTime += dt;
        setPosition(b2body.getPosition().x - getWidth()/2 , b2body.getPosition().y - getHeight()/2);
        setRegion(NPCRun.getKeyFrame(stateTime, true));
        b2body.setLinearVelocity(velocity);
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

    public void BodyHit() {
        // Check if the sound is not currently playing
        if (!isSoundPlaying) {
            // Log the collision
            Gdx.app.log("Cowboy Collision","");

            //Music
            Music music = Play.manager.get("audio/music/cowboyTrio.mp3", Music.class);
            Music music2 = Play.manager.get("audio/music/buckbumble.mp3", Music.class);
            Music music3 = Play.manager.get("audio/music/lasvegas.mp3", Music.class);


            if(!music2.isPlaying() && !music3.isPlaying()){
                music.play();}
            else if (music2.isPlaying()| music3.isPlaying()){
                music2.stop();
                music3.stop();
                music.play();
            }


            Play.manager.get("audio/sounds/best_shot.wav", Sound.class).play();
            Play.manager.get("audio/music/cowboyTrio.mp3", Music.class).play();
            // Play the sound
            //Play.manager.get("audio/sounds/best_shot.wav", Sound.class).play();

            // Set the flag to indicate that the sound is playing
            isSoundPlaying = true;

            // Start a timer to reset the flag after 10 seconds
            Timer.schedule(new Timer.Task(){
                @Override
                public void run() {
                    isSoundPlaying = false;
                }
            }, 10); // Reset the flag after 10 seconds
            float destinationX = 480/Play.PPM;// Change this to the desired X coordinate
            float destinationY = 1620/Play.PPM;// Change this to the desired Y coordinate
            screen.teleportPlayer(player, destinationX, destinationY);
            HUD.setWorldTimer(120);
            HUD.setCarbonMeter(0);
            screen.onPlayerTeleportedCowboy();
        }
    }
}
