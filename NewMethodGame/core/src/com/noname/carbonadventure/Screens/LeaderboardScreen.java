package com.noname.carbonadventure.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.models.LeaderboardEntry;
import com.badlogic.gdx.utils.Array;

public class LeaderboardScreen implements Screen {
    private Play game;
    private Stage stage;
    private Skin skin;

    public LeaderboardScreen(final Play game) {
        this.game = game;
        stage = new Stage(new FitViewport(Play.V_WIDTH, Play.V_HEIGHT), game.batch);
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Array<LeaderboardEntry> entries = game.getLeaderboardEntries();
        for (LeaderboardEntry entry : entries) {
            Label label = new Label(entry.getPlayerName() + " - " + entry.getScore(), skin);
            table.add(label).pad(10);
            table.row();
        }

        TextButton backButton = new TextButton("Back to Main Menu", skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        table.add(backButton).padTop(30);
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
