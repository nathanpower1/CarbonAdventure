package com.noname.carbonadventure.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.noname.carbonadventure.Play;
import com.badlogic.gdx.graphics.GL20;




public class PlayerNameScreen implements Screen {
    private Stage stage;
    private Play game; // Replace with your actual game class reference
    private TextField nameTextField;

    public PlayerNameScreen(final Play game) {
        this.game = game;
        stage = new Stage(); // Consider using an appropriate viewport
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json")); // Make sure you have this file
        nameTextField = new TextField("", skin);
        nameTextField.setMessageText("Enter Name");
        // Position the text field appropriately on the screen
        nameTextField.setPosition(100, 150);

        TextButton startGameButton = new TextButton("Start Game", skin);
        // Position the button appropriately on the screen
        startGameButton.setPosition(100, 100);
        startGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setPlayerName(nameTextField.getText());
                game.setScreen(new PlayScreen(game)); // This would transition to the actual game screen
                Gdx.app.log("Debug", "Name set to: " + game.getPlayerName());
            }
        });

        // Add actors to the stage
        stage.addActor(nameTextField);
        stage.addActor(startGameButton);
    }

    @Override
    public void show() {
        // Prepare the screen elements
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear the screen
        stage.act(delta); // Update the stage
        stage.draw(); // Draw the stage
    }

    @Override
    public void resize(int width, int height) {

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

    // Implement other required methods from the Screen interface

    @Override
    public void dispose() {
        stage.dispose();
        // Dispose of other resources if necessary
    }
}


