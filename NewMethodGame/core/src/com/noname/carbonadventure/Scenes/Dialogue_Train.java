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

public class Dialogue_Train {
    private Stage stage;
    private Dialog dialog;
    private Skin skin;
    private PlayScreen playScreen;
    private Vector2 trainStopPosition;

    public static final float distance_min = 0.5f;


    public Dialogue_Train(PlayScreen playScreen, Stage stage, String title, String message, List<String> options, Vector2 trainStopPosition) {
        this.playScreen = playScreen;
        this.stage = stage;
        this.trainStopPosition = trainStopPosition;

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

        TextButton closeButton = new TextButton("exit", skin);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                closeDialog();
            }
        });
        dialog.getButtonTable().add(closeButton).padRight(10);

        dialog.show(stage);
        dialog.setPosition((stage.getWidth() - dialog.getWidth()) / 2, 10);
        dialog.toFront();
        stage.act();
        Gdx.input.setInputProcessor(stage);
    }

    private void handleDialogResult(String option) {
        closeDialog();
        teleportPlayerBasedOnStop(option);
    }

    private void teleportPlayerBasedOnStop(String stop) {
        float destinationX = 0;
        float destinationY = 0;

        switch (stop) {
            case "North Station":
                destinationX = 10;
                destinationY = 20;
                break;
            case "Central Station":
                destinationX = 15;
                destinationY = 25;
                break;
            case "South Station":
                destinationX = 5;
                destinationY = 10;
                break;
        }
        playScreen.teleportPlayer(Play.player, destinationX, destinationY);
    }

    public void update(float delta) {
        Vector2 playerPosition = playScreen.getPlayer().getPosition();
        float distance = trainStopPosition.dst(playerPosition);
        if (trainStopPosition.dst(playerPosition) > distance_min) {
            closeDialog();
        }
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