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
    private float yOffsetFromHead = 20;
    private Play game;
    private ImageButton menuButton, quitButton, pauseButton,toggleButton;  // Declare buttons globally within the class

    public GameMenu(Play game) {
        this.game = game;
        this.stage = new Stage(new FitViewport(Play.V_WIDTH, Play.V_HEIGHT));
        Gdx.input.setInputProcessor(stage); // Set this stage as the input processor
        initButtons();
    }

    private void initButtons() {
        Texture toggleTexture = new Texture(Gdx.files.internal("img/menuicon.png"));
        Texture menuTexture = new Texture(Gdx.files.internal("img/ingamainmenubutton.png"));
        Texture quitTexture = new Texture(Gdx.files.internal("img/QuitIngame.png"));
        Texture pauseTexture = new Texture(Gdx.files.internal("img/Resumegame.png"));

        toggleButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(toggleTexture)));
        menuButton = new ImageButton(new TextureRegionDrawable(menuTexture));
        quitButton = new ImageButton(new TextureRegionDrawable(quitTexture));
        pauseButton = new ImageButton(new TextureRegionDrawable(pauseTexture));

        menuButton.setVisible(false);
        quitButton.setVisible(false);
        pauseButton.setVisible(false);

        addListeners();
        stage.addActor(toggleButton);
        stage.addActor(menuButton);
        stage.addActor(quitButton);
        stage.addActor(pauseButton);
    }

    private void addListeners() {

        toggleButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean isVisible = menuButton.isVisible();
                menuButton.setVisible(!isVisible);
                quitButton.setVisible(!isVisible);
                pauseButton.setVisible(!isVisible);
            }
        });

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

        // Calculate new Y positions using virtual screen height, stacked vertically
        float baseY = Play.V_HEIGHT - yOffsetFromHead; // Use your virtual screen height

        // All buttons will have the same X position (right aligned)
        float buttonsX = Play.V_WIDTH - buttonWidth - 4; // Right margin of 4

        // Update button positions - align to the right, stacked vertically
        // Note that we're adjusting the Y position for each button based on the one above it
        toggleButton.setPosition(buttonsX, baseY);
        menuButton.setPosition(buttonsX, baseY - (buttonHeight + 1)); // Positioned 1 unit below the toggle button
        quitButton.setPosition(buttonsX, baseY - 2 * (buttonHeight + 1)); // Positioned 1 unit below the menu button
        pauseButton.setPosition(buttonsX, baseY - 3 * (buttonHeight + 1)); // Positioned 1 unit below the quit button
    }
    public void dispose() {
        stage.dispose();
        // dispose textures
        disposeButtonTexture(menuButton);
        disposeButtonTexture(quitButton);
        disposeButtonTexture(pauseButton);
        disposeButtonTexture(toggleButton);
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