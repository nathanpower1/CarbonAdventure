package com.noname.carbonadventure.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.noname.carbonadventure.Play;

public class PlayerNameDisplay {
    private Label nameLabel;
    private Stage stage;

    public PlayerNameDisplay(Play game, Stage stage, Skin skin) {
        this.stage = stage;
        nameLabel = new Label("Player: " + game.getPlayerName(), skin);
        nameLabel.setPosition(20, 170); // Set the position as appropriate for your layout
        stage.addActor(nameLabel); // Add the label to the provided stage
        Gdx.app.log("PlayerNameDisplay", "Label added with text: " + nameLabel.getText());
    }



    public void updateName(String newName) {
        nameLabel.setText("Player: " + newName);
    }

    public void setPosition(float x, float y) {
        nameLabel.setPosition(x, y);
    }
}

