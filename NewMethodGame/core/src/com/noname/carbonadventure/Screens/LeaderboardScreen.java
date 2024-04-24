package com.noname.carbonadventure.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
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
        skin = new Skin(Gdx.files.internal("data/terra-mother-ui.json"));


        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();



        // Table that will hold leaderboard entries and the back button
        Table entriesTable = new Table();
        entriesTable.top();

        Label leaderboardLabel = new Label("Leaderboard", skin);
        leaderboardLabel.setFontScale(0.8f); // Make the header larger
        entriesTable.add(leaderboardLabel).expandX().fillX().center().padTop(10).padBottom(10);
        entriesTable.row(); // Move to the next row for entries

        // ScrollPane for scrolling through leaderboard entries
        ScrollPane scrollPane = new ScrollPane(entriesTable, skin);
        scrollPane.setScrollingDisabled(true, false); // Disable horizontal scrolling, enable vertical
        scrollPane.setFadeScrollBars(false); 

        // Adding the ScrollPane to the main table
        mainTable.add(scrollPane).expand().fill().pad(10);

        // Leaderboard entries
        Array<LeaderboardEntry> entries = game.getLeaderboardEntries();
        float fontScale = 0.5f;
        for (LeaderboardEntry entry : entries) {
            Label nameLabel = new Label(entry.getPlayerName(), skin, "giygas");
            Label scoreLabel = new Label(String.valueOf(entry.getScore()), skin, "giygas");
            nameLabel.setFontScale(fontScale);
            scoreLabel.setFontScale(fontScale);
            entriesTable.add(nameLabel).expandX().fillX().left().padLeft(10);
            entriesTable.add(scoreLabel).right().pad(10);
            entriesTable.row();
        }

        // Button to go back to the main menu, added at the end inside the scrollable area
        TextButton backButton = new TextButton("Back to Main Menu", skin, "default");
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        entriesTable.add(backButton).fillX().padTop(30).padBottom(10);

        // add the main table to the stage
        stage.addActor(mainTable);
    }



    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);  // Set a custom clear color
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        stage.getCamera().position.set(Play.V_WIDTH / 2f, Play.V_HEIGHT / 2f, 0);
        stage.getCamera().update();
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
