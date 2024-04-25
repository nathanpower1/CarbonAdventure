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
import com.badlogic.gdx.utils.Timer;

import java.util.List;

public class Bus_Stop_Level3_Dialogue {
    private Stage stage;
    private Dialog dialog;
    private Skin skin;
    private PlayScreen playScreen;
    private Vector2 busStopPosition;

    public static final float distance_min = 0.5f;

    public Bus_Stop_Level3_Dialogue(PlayScreen playScreen, Stage stage, String title, String message, List<String> options, Vector2 busStopPosition) {
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
        dialog.getContentTable().add(label).width(stage.getWidth() - 40).pad(5);

        for (String option : options) {
            TextButton optionButton = new TextButton(option, skin);
            dialog.button(optionButton, option);
        }

        TextButton closeButton = new TextButton("Exit", skin);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                closeDialog();
            }
        });
        dialog.getButtonTable().add(closeButton).padLeft(20).padRight(10);

        dialog.show(stage);
        dialog.setPosition((stage.getWidth() - dialog.getWidth()) / 2, 10);
        dialog.toFront();
        stage.act();
        Gdx.input.setInputProcessor(stage);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                closeDialog();
            }
        }, 7);
    }

    public void update(float delta) {
        if (isDialogOpen() && shouldClose()) {
            closeDialog();
        }
    }

    private boolean isDialogOpen() {
        return dialog != null && dialog.isVisible();
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
            case "Park":
                destinationX = 23.8133f;
                destinationY = 28.89333f;
                break;
            case "Town":
                destinationX = 29.74f;
                destinationY = 30.093332f;
                break;
            case "Park ext":
                destinationX = 27.533302f;
                destinationY = 25.77333f;
                break;
            case "South St":
                destinationX = 23.966698f;
                destinationY = 23.680002f;
                break;

        }
        playScreen.teleportPlayer(Play.player, destinationX, destinationY);
        HUD.increaseCarbonMeter(10);

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