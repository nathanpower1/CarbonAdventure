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
import com.noname.carbonadventure.Screens.PlayScreen;

public class Dialogue_NPC {
    private Stage stage;
    public Dialog dialog;
    private Skin skin;
    private PlayScreen playScreen;
    private Vector2 npcPosition;

    public static final float distance_min = 0.2f;

    public Dialogue_NPC(PlayScreen playScreen, Stage stage, String title, String message, Vector2 npcPosition) {
        this.playScreen = playScreen;
        this.stage = stage;
        this.npcPosition = npcPosition;

        skin = new Skin(Gdx.files.internal("data/terra-mother-ui.json"));

        dialog = new Dialog(title, skin) {
            @Override
            protected void result(Object object) {
                closeDialog();
            }
        };

        dialog.setMovable(false);
        Label label = new Label(message, skin, "default");
        label.setWrap(true);
        dialog.getContentTable().add(label).width(stage.getWidth() - 40).pad(10);

        TextButton closeButton = new TextButton("Exit", skin);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dialog.hide();
            }
        });
        dialog.button(closeButton);

        dialog.show(stage);
        dialog.setPosition((stage.getWidth() - dialog.getWidth()) / 2, 10);
        dialog.toFront();
        stage.act();
        Gdx.input.setInputProcessor(stage);
    }

    public void update(float delta) {
        Vector2 playerPosition = playScreen.getPlayer().getPosition();
        if (npcPosition.dst(playerPosition) > distance_min) {
            closeDialog();
        }
    }

    public boolean shouldClose() {
        Vector2 playerPosition = playScreen.getPlayer().getPosition();
        return npcPosition.dst(playerPosition) > distance_min;
    }

    private void handleDialogResult(String option) {
        closeDialog();
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
