package com.noname.carbonadventure.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;

public class EnvironmentDialogue {
    private Stage stage;
    private Dialog dialog;
    private Skin skin;

    public EnvironmentDialogue(Stage stage, String facts) {
        this.stage = stage;

        skin = new Skin(Gdx.files.internal("data/terra-mother-ui.json"));
        dialog = new Dialog("", skin);
        dialog.setMovable(false);

        Label factsLabel = new Label(facts, skin, "default");
        factsLabel.setWrap(true);
        dialog.getContentTable().add(factsLabel).width(stage.getWidth() - 40).pad(5);

        dialog.button("OK", true);

        dialog.show(stage);
        dialog.setPosition((stage.getWidth() - dialog.getWidth()) / 2, 10);
        stage.act();
        Gdx.input.setInputProcessor(stage);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                closeDialog();
            }
        }, 5);
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