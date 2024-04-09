package com.noname.carbonadventure.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.noname.carbonadventure.Play;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenuScreen implements Screen {
    private Play game;
    private Stage stage;
    private Texture bgTexture;

    public MainMenuScreen(final Play game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Background
        bgTexture = new Texture(Gdx.files.internal("img/FbQJeV5WYAM_58i.png"));
        Image backgroundImage = new Image(bgTexture);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);

        // Buttons
        ImageButton playButton = createImageButton("img/play01.png", "img/play02.png", new Runnable() {
            @Override
            public void run() {
                game.setScreen(new PlayScreen(game));
            }
        });

        ImageButton leaderboardButton = createImageButton("img/leaderboard01.png", "img/leaderboard02.png", new Runnable() {
            @Override
            public void run() {
                // Switch to LeaderboardScreen
            }
        });

        ImageButton tutorialButton = createImageButton("img/option01.png", "img/option02.png", new Runnable() {
            @Override
            public void run() {
                // Switch to TutorialScreen
            }
        });

        ImageButton exitButton = createImageButton("img/restart01.png", "img/restart02.png", new Runnable() {
            @Override
            public void run() {
                Gdx.app.exit();
            }
        });

        int buttonSpacing = 10; // Adjust as needed for the spacing between buttons
        int initialY = 300; // Adjust as needed for the initial Y position of the first button

        playButton.setPosition(220, initialY);
        leaderboardButton.setPosition(220, initialY - (playButton.getHeight() + buttonSpacing));
        tutorialButton.setPosition(220, initialY - 2 * (playButton.getHeight() + buttonSpacing));
        exitButton.setPosition(220, initialY - 3 * (playButton.getHeight() + buttonSpacing));

        // Add buttons to stage
        stage.addActor(playButton);
        stage.addActor(leaderboardButton);
        stage.addActor(tutorialButton);
        stage.addActor(exitButton);
    }

    private ImageButton createImageButton(String upPath, String downPath, final Runnable onClick) {
        Texture upTexture = new Texture(Gdx.files.internal(upPath));
        Texture downTexture = new Texture(Gdx.files.internal(downPath));
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = new TextureRegionDrawable(upTexture);
        style.down = new TextureRegionDrawable(downTexture);

        ImageButton button = new ImageButton(style);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onClick.run();
            }
        });

        return button;
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        bgTexture.dispose();
        // Dispose of other textures if not managed by Skin
    }

    // Implement other required methods (pause, resume, hide) as empty
}
