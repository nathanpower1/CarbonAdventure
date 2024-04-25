package com.noname.carbonadventure.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


import java.util.ArrayList;
import java.util.List;

public class Dialogue_Train_Tutorial {
    private Stage stage;
    private Dialog dialog;
    private Skin skin;
    private List<String> messages;
    private int currentMessageIndex = 0;
    private Label messageLabel;

    public Dialogue_Train_Tutorial(Stage stage, String title, List<String> messages) {
        this.stage = stage;
        this.messages = new ArrayList<>(messages);

        skin = new Skin(Gdx.files.internal("data/terra-mother-ui.json"));
        dialog = new Dialog(title, skin);
        dialog.setMovable(false);
        dialog.setResizable(false);

        messageLabel = new Label(messages.get(currentMessageIndex), skin, "default");
        messageLabel.setWrap(true);
        dialog.getContentTable().add(messageLabel).width(stage.getWidth() - 40).pad(10);

        TextButton nextButton = new TextButton("Next", skin);
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                advanceDialogue();
            }
        });

        TextButton closeButton = new TextButton("exit", skin);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                dialog.hide();
            }
        });

        dialog.getButtonTable().add(nextButton).padRight(10);
        dialog.getButtonTable().add(closeButton);

        dialog.show(stage);
        dialog.setPosition((stage.getWidth() - dialog.getWidth()) / 2, 10);
        dialog.toFront();
        stage.act();
        Gdx.input.setInputProcessor(stage);

    }

    private void advanceDialogue() {
        currentMessageIndex++;
        if (currentMessageIndex >= messages.size()) {
            dialog.hide();
        } else {
            messageLabel.setText(messages.get(currentMessageIndex));
            dialog.pack();
        }
    }

    public void dispose() {
        if (skin != null) {
            skin.dispose();
            skin = null;
        }

    }
}
