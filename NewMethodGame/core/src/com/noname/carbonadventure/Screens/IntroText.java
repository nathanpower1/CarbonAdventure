package com.noname.carbonadventure.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.noname.carbonadventure.Play;

public class IntroText implements Screen {
    private Stage stage;
    private Game game;
    private float timer = 0;
    private final float displayTime = 3.0f;

    public IntroText(Game game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("data/terra-mother-ui.json"));

        Label titleLabel = new Label("Welcome to Carbon Adventure", skin, "default");
        Label subtitleLabel = new Label("Created By No Name Gaming", skin, "default");

        titleLabel.setFontScale(2.5f);
        subtitleLabel.setFontScale(1.5f);

        Table table = new Table();
        table.setFillParent(true);
        table.center();

        table.add(titleLabel).padBottom(10);
        table.row();
        table.add(subtitleLabel);

        stage.addActor(table);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        timer += delta;
        if (timer >= displayTime) {
            game.setScreen(new MainMenuScreen((Play) game));
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }
}
