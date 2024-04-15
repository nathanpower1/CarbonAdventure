package com.noname.carbonadventure.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.List;

public class Dialogue {

    private Stage stage;
    private Dialog dialog;
    private Skin skin;

    public Dialogue(Stage stage, String title, String message, List<String> busStops) {
        this.stage = stage;


        skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        // Create a dialog using the default skin
        dialog = new Dialog(title, skin);

        float dialogWidth = 200; // Width in pixels
        float dialogHeight = 100; // Height in pixels
        dialog.setSize(dialogWidth, dialogHeight);

        float dialogX = (stage.getWidth() - dialogWidth) / 2;
        float dialogY = (stage.getHeight() - dialogHeight) / 2;
        dialog.setPosition(dialogX, dialogY);
        dialog.text(message);

        // Add buttons to the dialog for each bus stop
        for (String stop : busStops) {
            dialog.button(stop, stop);
        }

        // listener to handle button clicks
        dialog.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                if (event.getTarget() instanceof TextButton) {
                    handleDialogResult(((TextButton) event.getTarget()).getText().toString());
                }
            }
        });

        dialog.setMovable(false);
        dialog.setResizable(false);

        dialog.show(stage);
    }

    public void closeDialog() {
        if (dialog != null) {
            dialog.hide();
            dialog.remove();
        }
    }

    private void handleDialogResult(String stop) {
        System.out.println("User selected: " + stop);
        closeDialog();
    }

    public void dispose() {
        if (skin != null) {
            skin.dispose();
        }
    }
}
