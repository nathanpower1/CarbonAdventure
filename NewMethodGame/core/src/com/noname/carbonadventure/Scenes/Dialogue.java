package com.noname.carbonadventure.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Screens.PlayScreen;

import java.util.List;

public class Dialogue {
    private Stage stage;
    private Dialog dialog;
    private Skin skin;
    private PlayScreen playScreen;
    private Vector2 npcPosition;
    private boolean isBusStopDialogue;
    public static final float distance_min = 0.5f;
    private boolean shouldClose = false;

    private Vector2 playerPosition; // Ensure this is updated with the player's current position
    private Vector2 busStopPosition; // Ensure this is set to the bus stop's position

    public Dialogue(PlayScreen playScreen, Stage stage, String title, String message, List<String> options, boolean isBusStopDialogue, Vector2 npcPosition) {
        this.playScreen = playScreen;
        this.stage = stage;
        this.isBusStopDialogue = isBusStopDialogue;
        this.npcPosition = npcPosition;

        skin = new Skin(Gdx.files.internal("data/terra-mother-ui.json"));

        dialog = new Dialog(title, skin) {
            @Override
            protected void result(Object object) {
                handleDialogResult(object.toString());
            }
        };

        dialog.setMovable(false);
        Label label = new Label(message, skin, "default");
        label.setWrap(true);
        dialog.getContentTable().add(label).width(stage.getWidth() - 40).pad(10);

        for (String option : options) {
            TextButton optionButton = new TextButton(option, skin);
            dialog.button(optionButton, option);
        }

        TextButton closeButton = new TextButton("Close", skin);
        dialog.getButtonTable().add(closeButton).pad(10);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                closeDialog();
            }
        });

        dialog.show(stage);
        dialog.setPosition((stage.getWidth() - dialog.getWidth()) / 2, 10);
        dialog.toFront();
        stage.act();
        Gdx.input.setInputProcessor(stage);
    }

    public void update(float dt) {
        if (npcPosition != null && playScreen.getPlayer() != null) {
            float distance = npcPosition.dst(playScreen.getPlayer().getBody().getPosition());
            if (distance > distance_min) {
                shouldClose = true;
                closeDialog();
            }
        }
    }

    public boolean shouldClose() {
        return shouldClose;
    }

    private void handleDialogResult(String option) {
        closeDialog();
        switch (option) {
            case "Tell me more":
                new Dialogue(playScreen, stage, "Details", "Here are more details.", List.of("Continue"), false, npcPosition);
                break;
            case "Enough":
                break;
            default:
                if (isBusStopDialogue) {
                    teleportPlayerBasedOnStop(option);
                }
                break;
        }
    }

    private void teleportPlayerBasedOnStop(String stop) {
        float destinationX = 0;
        float destinationY = 0;

        switch (stop) {
            case "Stop 1":
                destinationX = 5.41833f;
                destinationY = 27.368333f;
                break;
            case "Stop 2":
                destinationX = 0.315f;
                destinationY = 24.978333f;
                break;
            case "Stop 3":
                destinationX = 9.11167f;
                destinationY = 28.395002f;
                break;
        }
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
