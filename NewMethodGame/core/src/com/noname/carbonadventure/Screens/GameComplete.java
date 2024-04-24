package com.noname.carbonadventure.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.noname.carbonadventure.Play;

import java.awt.*;

public class GameComplete implements Screen {
    private Viewport viewport;
    private Stage stage;
    private Game game;
    public GameComplete(Game game){
        this.game = game;
        Skin skin = new Skin(Gdx.files.internal("data/terra-mother-ui.json"));
        viewport = new FitViewport(Play.V_WIDTH,Play.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport,((Play) game).batch);

        Label.LabelStyle font =  new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameoverLabel = new Label("YOU WIN", font);
        table.add(gameoverLabel).expandX();

        TextButton leaderboardButton = new TextButton("View Leaderboard", skin);
        leaderboardButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Transition to the leaderboard screen
                ((Play)game).setScreen(new LeaderboardScreen((Play)game));
            }
        });

        // Add the button below the label
        table.row();
        table.add(leaderboardButton).padTop(10);
        stage.addActor(table);

    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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

    }
}
