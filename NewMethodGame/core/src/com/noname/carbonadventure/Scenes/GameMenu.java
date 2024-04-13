package com.noname.carbonadventure.Scenes;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Screens.MainMenuScreen;

public class GameMenu {
    private Stage stage;
    private float yOffsetFromHead = 52;
    private Play game;
    private ImageButton menuButton, quitButton, pauseButton;  // Declare buttons globally within the class

    public GameMenu(Play game) {
        this.game = game;
        this.stage = new Stage(new FitViewport(Play.V_WIDTH, Play.V_HEIGHT));
        Gdx.input.setInputProcessor(stage); // Set this stage as the input processor
        initButtons();
    }

    private void initButtons() {
        Texture menuTexture = new Texture(Gdx.files.internal("img/menubuttonmain.png"));
        Texture quitTexture = new Texture(Gdx.files.internal("img/exit.png"));
        Texture pauseTexture = new Texture(Gdx.files.internal("img/pause.png"));

        menuButton = new ImageButton(new TextureRegionDrawable(menuTexture));
        quitButton = new ImageButton(new TextureRegionDrawable(quitTexture));
        pauseButton = new ImageButton(new TextureRegionDrawable(pauseTexture));


        addListeners();
        stage.addActor(menuButton);
        stage.addActor(quitButton);
        stage.addActor(pauseButton);
    }

    private void addListeners() {
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        pauseButton.addListener(new ClickListener() {
            // pending lol
        });
    }

    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        updateButtonPositions(width, height);
    }

    public void updateButtonPositions(int width, int height) {
        float buttonWidth = menuButton.getWidth();
        float buttonHeight = menuButton.getHeight();

        // Calculate new Y position
        float buttonY = Play.V_HEIGHT - yOffsetFromHead; // Use your virtual screen height

        // Calculate X positions using virtual screen dimensions
        float menuButtonX = 4; // Distance from the left edge of the virtual screen
        float quitButtonX = menuButtonX + buttonWidth + 1; // Spacing between buttons
        float pauseButtonX = quitButtonX + buttonWidth + 1;

        // Update button positions
        menuButton.setPosition(menuButtonX, buttonY - buttonHeight); // Subtract the button height if needed
        quitButton.setPosition(quitButtonX, buttonY - buttonHeight);
        pauseButton.setPosition(pauseButtonX, buttonY - buttonHeight);
    }
    public void dispose() {
        stage.dispose();
        // dispose textures
        disposeButtonTexture(menuButton);
        disposeButtonTexture(quitButton);
        disposeButtonTexture(pauseButton);
    }


    private void disposeButtonTexture(ImageButton button) {
        Drawable drawable = button.getStyle().imageUp; // Assuming the texture is set as 'imageUp'
        if (drawable instanceof TextureRegionDrawable) {
            TextureRegion region = ((TextureRegionDrawable) drawable).getRegion();
            if (region != null && region.getTexture() != null) {
                region.getTexture().dispose();
            }
        }
    }

}