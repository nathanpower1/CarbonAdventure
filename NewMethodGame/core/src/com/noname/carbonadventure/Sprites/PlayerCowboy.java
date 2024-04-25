package com.noname.carbonadventure.Sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.noname.carbonadventure.Screens.PlayScreen;

public class PlayerCowboy extends Player {
    private Animation<TextureRegion> playerUp;
    private Animation<TextureRegion> playerDown;
    private Animation<TextureRegion> playerRight;
    private Animation<TextureRegion> playerLeft;

    public PlayerCowboy(PlayScreen screen) {
        super(screen);
        initializeAnimations();
    }

    private void initializeAnimations() {
        Array<TextureRegion> frames = new Array<>();

        playerStand = new TextureRegion(new Texture("img/pboy_down1.png"));


        // Animation for moving up
        frames.add(new TextureRegion(new Texture("img/pboy_up1.png")));
        frames.add(new TextureRegion(new Texture("img/pboy_up2.png")));
        playerUp = new Animation<>(0.1f, frames);
        frames.clear();

        // Animation for moving right
        frames.add(new TextureRegion(new Texture("img/pboy_right1.png")));
        frames.add(new TextureRegion(new Texture("img/pboy_right2.png")));
        playerRight = new Animation<>(0.1f, frames);
        frames.clear();

        // Animation for moving down
        frames.add(new TextureRegion(new Texture("img/pboy_down1.png")));
        frames.add(new TextureRegion(new Texture("img/pboy_down2.png")));
        playerDown = new Animation<>(0.1f, frames);
        frames.clear();

        // Animation for moving left
        frames.add(new TextureRegion(new Texture("img/pboy_left1.png")));
        frames.add(new TextureRegion(new Texture("img/pboy_left2.png")));
        playerLeft = new Animation<>(0.1f, frames);
        frames.clear();
    }

    @Override
    public TextureRegion getFrame(float dt) {
        currentState = getState();

        TextureRegion region;
        switch (currentState) {
            case UP:
                region = playerUp.getKeyFrame(getStateTimer(), true);
                break;
            case DOWN:
                region = playerDown.getKeyFrame(getStateTimer(), true);
                break;
            case RUNNING:
                if (isRunningRight()) {
                    region = playerRight.getKeyFrame(getStateTimer(), true);
                } else {
                    region = playerLeft.getKeyFrame(getStateTimer(), true);
                }
                break;
            default:
                region = playerStand;
                break;
        }

        updateTextureDirection(region);

        if (currentState == previousState) {
            setStateTimer(getStateTimer() + dt);
        } else {
            setStateTimer(0);
        }

        return region;
    }



    private void updateTextureDirection(TextureRegion region) {
        // Get the current horizontal velocity of the player
        float velocityX = b2body.getLinearVelocity().x;

        if (velocityX < 0 && !region.isFlipX()) {
            region.flip(true, false);
        } else if (velocityX > 0 && region.isFlipX()) {
            region.flip(true, false);
        }
    }
}


