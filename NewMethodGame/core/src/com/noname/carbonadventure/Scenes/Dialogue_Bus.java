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

public class Dialogue_Bus {
    private Stage stage;
    private Dialog dialog;
    private Skin skin;
    private PlayScreen playScreen;
    private Vector2 busStopPosition;

    public static final float distance_min = 0.5f;

    public Dialogue_Bus(PlayScreen playScreen, Stage stage, String title, String message, List<String> options, Vector2 busStopPosition) {
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
        dialog.getButtonTable().add(closeButton).padRight(20);;

        dialog.show(stage);
        dialog.setPosition((stage.getWidth() - dialog.getWidth()) / 2, 10);
        dialog.toFront();
        stage.act();
        Gdx.input.setInputProcessor(stage);
    }

    public void update(float delta) {
        Vector2 playerPosition = playScreen.getPlayer().getPosition();
        float distance = busStopPosition.dst(playerPosition);
        if (busStopPosition.dst(playerPosition) > distance_min) {
            closeDialog();
        }
    }

    public boolean shouldClose() {
        Vector2 playerPosition = playScreen.getPlayer().getPosition();
        return busStopPosition.dst(playerPosition) > distance_min;
    }

    private void handleDialogResult(String option) {
        closeDialog();
        teleportPlayerBasedOnStop(option);
    }

    private void teleportPlayerBasedOnStop(String stop) {
        float destinationX = 0;
        float destinationY = 0;

        switch (stop) {
            case "Central":
                destinationX = 5.41833f;
                destinationY = 27.368333f;
                break;
            case "East":
                destinationX = 0.315f;
                destinationY = 24.978333f;
                break;
            case "West":
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
