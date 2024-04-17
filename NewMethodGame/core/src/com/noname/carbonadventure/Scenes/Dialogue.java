package com.noname.carbonadventure.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Screens.PlayScreen;

import java.util.List;


public class Dialogue {
    private Stage stage;
    private Dialog dialog;
    private Skin skin;
    private PlayScreen playScreen;

    public Dialogue(PlayScreen playScreen, Stage stage, String title, String message, List<String> busStops) {
        this.playScreen = playScreen;
        this.stage = stage;

        skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        dialog = new Dialog(title, skin) {
            @Override
            protected void result(Object object) {
                handleDialogResult(object.toString());
            }
        };

        // Set dialog properties
        dialog.setMovable(false);
        dialog.setResizable(false);
        dialog.text(message);

        // Buttons for each stop to click
        for (String stop : busStops) {
            TextButton button = new TextButton(stop, skin);
            button.setTouchable(Touchable.enabled);
            dialog.button(button, stop);
        }

        // dialogue box size
        float dialogWidth = stage.getWidth() - 20;
        float dialogHeight = 80;

        // set the dialogue position on the screen
        dialog.setSize(dialogWidth, dialogHeight);
        dialog.setPosition(10, 10);

        dialog.show(stage);
        dialog.toFront();

        stage.act();
        dialog.setPosition((stage.getWidth() - dialogWidth) / 2, 10);

        Gdx.input.setInputProcessor(stage);
    }

    private void handleDialogResult(String stop) {
        System.out.println("User selected: " + stop);
        closeDialog();

        // Teleport the player bitch!
        float destinationX = 0;
        float destinationY = 0;

        // change x and y to bus stops on map
        if (stop.equals("Stop 1")) {
            destinationX = 10;
            destinationY = 10;
        } else if (stop.equals("Stop 2")) {
            destinationX = 20;
            destinationY = 20;
        } else if (stop.equals("Stop 3")) {
            destinationX = 30;
            destinationY = 30;
        }

        // Call teleportPlayer method from nathan
        playScreen.teleportPlayer(Play.player, destinationX, destinationY);
    }

    public void closeDialog() {
        if (dialog != null) {
            dialog.hide();
            dialog.remove();
        }
    }

    public void dispose() {
        if (skin != null) {
            skin.dispose();
            skin = null;
        }
    }
}
