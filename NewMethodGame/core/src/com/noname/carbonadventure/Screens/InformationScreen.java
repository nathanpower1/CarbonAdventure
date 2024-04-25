package com.noname.carbonadventure.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.noname.carbonadventure.Play;

public class InformationScreen implements Screen {
    private Play game;
    private Stage stage;
    private Skin skin;
   

    public InformationScreen(final Play game) {
        this.game = game;
        stage = new Stage(new FitViewport(Play.V_WIDTH, Play.V_HEIGHT), game.batch);
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("data/terra-mother-ui.json"));


        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();

        // Header for Controls
        Label controlsHeader = new Label("CONTROLS", skin);
        controlsHeader.setFontScale(0.9f);
        mainTable.add(controlsHeader).expandX().center().padTop(10);
        mainTable.row();

        // Back Button
        TextButton backButton = new TextButton("< MAIN MENU >", skin, "default");
        backButton.getLabel().setFontScale(0.5f);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
        mainTable.add(backButton).center().padTop(5);
        mainTable.row();

        // Text area for the controls
        String controlsText = "Arrow Keys - Move\nB - Skateboard\nC - Car\nM - Mini-Map";
        Label controlsLabel = new Label(controlsText, skin);
        controlsLabel.setWrap(true);

        ScrollPane scrollPane = new ScrollPane(controlsLabel, skin);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setFadeScrollBars(false);

        mainTable.add(scrollPane).expand().fill().pad(10);

        stage.addActor(mainTable);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        if (Gdx.input.getInputProcessor() == stage) {
            Gdx.input.setInputProcessor(null);
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

