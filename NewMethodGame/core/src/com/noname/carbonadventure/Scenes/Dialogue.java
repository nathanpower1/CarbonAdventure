package com.noname.carbonadventure.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
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

    private Vector2 busStopPosition; // Add this field to store the bus stop's position

    public Dialogue(PlayScreen playScreen, Stage stage, String title, String message, List<String> busStops, Vector2 busStopPosition) {
        this.playScreen = playScreen;
        this.stage = stage;
        this.busStopPosition = busStopPosition;

        skin = new Skin(Gdx.files.internal("data/terra-mother-ui.json"));

        dialog = new Dialog(title, skin) {
            @Override
            protected void result(Object object) {
                handleDialogResult(object.toString());
            }
        };

        // Set dialog properties
        GlyphLayout layout = new GlyphLayout();
        float textWidth = layout.width;
        float textHeight = layout.height;

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
            destinationX = 6.00F;
            destinationY = 27.653332F;
        } else if (stop.equals("Stop 2")) {
            destinationX = 0.616f;
            destinationY = 24.468332f;
        } else if (stop.equals("Stop 3")) {
            destinationX = 9.12f;
            destinationY = 29.62667f;
        }

        // Call teleportPlayer method from nathan
        playScreen.teleportPlayer(Play.player, destinationX, destinationY);
    }

    public Vector2 getBusStopPosition() {
        return busStopPosition;
    }

    public Dialog getDialog() {
        return dialog;
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
