package com.noname.carbonadventure.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.EnumMap;
import java.util.Map;
import java.util.ArrayList;
import com.noname.carbonadventure.entities.PlayerState;
import java.util.List;

public class AnimationManager {
    private final Map<PlayerState, Animation<TextureRegion>> animations = new EnumMap<>(PlayerState.class);
    private final List<Texture> loadedTextures = new ArrayList<>();

    public AnimationManager() {
        initializeAnimations();
    }

    private void initializeAnimations() {
        animations.put(PlayerState.WALKING_LEFT, createAnimation(0.1f, "img/boy_left_1.png", "img/boy_left_2.png"));
        animations.put(PlayerState.WALKING_RIGHT, createAnimation(0.1f, "img/boy_right_1.png", "img/boy_right_2.png"));
        animations.put(PlayerState.WALKING_UP, createAnimation(0.1f, "img/boy_up_1.png", "img/boy_up_2.png"));
        animations.put(PlayerState.WALKING_DOWN, createAnimation(0.1f, "img/player.png", "img/boy_down_2.png"));
        animations.put(PlayerState.IDLE, createAnimation(1f, "img/player.png"));
    }

    private Animation<TextureRegion> createAnimation(float frameDuration, String... fileNames) {
        TextureRegion[] frames = new TextureRegion[fileNames.length];
        for (int i = 0; i < fileNames.length; i++) {
            Texture texture = new Texture(Gdx.files.internal(fileNames[i]));
            loadedTextures.add(texture);
            frames[i] = new TextureRegion(texture);
        }
        return new Animation<>(frameDuration, frames);
    }

    public TextureRegion getFrame(PlayerState state, float stateTime) {
        Animation<TextureRegion> animation = animations.get(state);
        if (animation == null) {
            return null;
        }
        return animation.getKeyFrame(stateTime, true);
    }

    public void dispose() {
        for (Texture texture : loadedTextures) {
            texture.dispose();
        }
    }
}

