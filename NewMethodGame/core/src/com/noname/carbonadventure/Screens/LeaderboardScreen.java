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

        // Creating a header table to hold only the leaderboard label
        Table headerTable = new Table();
        Label leaderboardLabel = new Label("LEADERBOARD", skin);
        leaderboardLabel.setFontScale(0.9f); // Smaller font scale
        headerTable.add(leaderboardLabel).expandX().center().padTop(3);

        // Add the header table to the main table
        mainTable.add(headerTable).fillX().expandX();
        mainTable.row(); // Move to the next row for the button

        // Button to go back to the main menu, positioned at the top left or center if desired
        TextButton backButton = new TextButton("< MAIN MENU >", skin, "default");
        backButton.getLabel().setFontScale(0.5f); // Reduce the font scale of the button text
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
        mainTable.add(backButton).padTop(3).center();

        mainTable.row(); // Move to the next row for the scrollable area

        // Table for leaderboard entries
        Table entriesTable = new Table();
        entriesTable.top();

// Define headers
        Label nameLabelHeader = new Label("Player", skin, "default");
        Label scoreLabelHeader = new Label("Score", skin, "default");
        Label timeLabelHeader = new Label("Time", skin, "default");

// Set font scale or other styling for headers if needed
        float headerFontScale = 0.6f;
        nameLabelHeader.setFontScale(headerFontScale);
        scoreLabelHeader.setFontScale(headerFontScale);
        timeLabelHeader.setFontScale(headerFontScale);

// Add headers to the table
        entriesTable.add(nameLabelHeader).expandX().fillX().pad(10);
        entriesTable.add(scoreLabelHeader).expandX().fillX().pad(10);
        entriesTable.add(timeLabelHeader).expandX().fillX().pad(10);
        entriesTable.row(); // Start a new row for actual entries

// ScrollPane setup
        ScrollPane scrollPane = new ScrollPane(entriesTable, skin);
        scrollPane.setScrollingDisabled(true, false); // Disable horizontal, enable vertical scrolling
        scrollPane.setFadeScrollBars(false);

// Add ScrollPane to the main table to occupy most of the screen
        mainTable.add(scrollPane).expand().fill().pad(10);

// Populate the leaderboard
        Array<LeaderboardEntry> entries = game.getLeaderboardEntries();
        float fontScale = 0.5f; // Adjust font scale for entries
        for (LeaderboardEntry entry : entries) {
            Label nameLabel = new Label(entry.getPlayerName(), skin, "default");
            Label scoreLabel = new Label(String.valueOf(entry.getScore()), skin, "default");
            Label timeLabel = new Label(String.format("%.2f s", entry.getTimeTaken()), skin, "default");

            nameLabel.setFontScale(fontScale);
            scoreLabel.setFontScale(fontScale);
            timeLabel.setFontScale(fontScale);

            entriesTable.add(nameLabel).expandX().fillX().left().padLeft(10);
            entriesTable.add(scoreLabel).expandX().fillX().center();
            entriesTable.add(timeLabel).expandX().fillX().right().padRight(10);
            entriesTable.row();
        }

// Add the main table to the stage
        stage.addActor(mainTable);}






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