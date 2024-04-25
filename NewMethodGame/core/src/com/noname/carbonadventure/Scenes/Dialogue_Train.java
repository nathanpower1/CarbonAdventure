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
import com.badlogic.gdx.utils.Timer;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Screens.PlayScreen;

import java.util.List;

public class Dialogue_Train {
    private Stage stage;
    private Dialog dialog;
    private Skin skin;
    private PlayScreen playScreen;
    private Vector2 trainStopPosition;
    private boolean isCooldown;
    private static final float COOLDOWN_TIME = 3;

    public static final float distance_min = 0.5f;

    public Dialogue_Train(PlayScreen playScreen, Stage stage, String title, String message, List<String> options, Vector2 trainStopPosition) {
        this.playScreen = playScreen;
        this.stage = stage;
        this.trainStopPosition = trainStopPosition;
        this.isCooldown = false;

        skin = new Skin(Gdx.files.internal("data/terra-mother-ui.json"));
        if (!isCooldown) {
            createDialogue(title, message, options);
            startCooldown();
        }
    }

    private void createDialogue(String title, String message, List<String> options) {
        dialog = new Dialog(title, skin) {
            @Override
            protected void result(Object object) {
                if (object != null) {
                    handleDialogResult(object.toString());
                }
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

        TextButton closeButton = new TextButton("Exit", skin);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dialog.cancel();
            }
        });
        dialog.getButtonTable().add(closeButton).padRight(10);

        dialog.show(stage);
        dialog.setPosition((stage.getWidth() - dialog.getWidth()) / 2, 10);
        dialog.toFront();
        stage.act();
        Gdx.input.setInputProcessor(stage);
    }

    public boolean isInCooldown() {
        return isCooldown;
    }

    private void startCooldown() {
        isCooldown = true;
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                isCooldown = false;
            }
        }, COOLDOWN_TIME);
    }

    private void handleDialogResult(String option) {
        if (!"Exit".equals(option)) {
            teleportPlayerBasedOnStop(option);
        }
        closeDialog();
    }

    private void teleportPlayerBasedOnStop(String stop) {
        float destinationX = 0;
        float destinationY = 0;

        switch (stop) {
            case "N1": destinationX = 17.4867f; destinationY = 14.897767f; break;
            case "S1": destinationX = 17.69f; destinationY = 0.9444673f; break;
            case "E1": destinationX = 17.603399f; destinationY = 8.939467f; break;
            case "Hub": destinationX = 24.0534f; destinationY = 8.649467f; break;
            case "N2": destinationX = 30.266699f; destinationY = 14.857767f; break;
            case "E2": destinationX = 30.238401f; destinationY = 8.644467f; break;
            case "S2": destinationX = 30.2534f; destinationY = 0.6444673f; break;
        }
        playScreen.teleportPlayer(Play.player, destinationX, destinationY);
        HUD.increaseCarbonMeter(10);
    }

    public void update(float delta) {
        if (isDialogOpen() && shouldClose()) {
            closeDialog();
        }
    }

    private boolean isDialogOpen() {
        return dialog != null && dialog.isVisible();
    }

    private boolean shouldClose() {
        Vector2 playerPosition = playScreen.getPlayer().getPosition();
        return trainStopPosition.dst(playerPosition) > distance_min;
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
