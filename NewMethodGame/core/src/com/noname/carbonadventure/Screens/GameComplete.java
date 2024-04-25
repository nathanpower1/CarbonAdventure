package com.noname.carbonadventure.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.noname.carbonadventure.Play;

public class GameComplete implements Screen {
    private Viewport viewport;
    private Stage stage;
    private Game game;

    public GameComplete(Game game) {
        this.game = game;
        Skin skin = new Skin(Gdx.files.internal("data/terra-mother-ui.json"));
        viewport = new FitViewport(Play.V_WIDTH, Play.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Play) game).batch);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameCompleteLabel = new Label("YOU WIN!", skin);
        gameCompleteLabel.setFontScale(2);  // Optional: Increase the font size for visibility
        table.add(gameCompleteLabel).expandX();

        stage.addActor(table);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        // Schedule a transition to the LeaderboardScreen after a 5-second delay
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                ((Play)game).setScreen(new LeaderboardScreen((Play)game));
            }
        }, 5); // Delay in seconds
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
