package com.noname.carbonadventure.Scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.noname.carbonadventure.Play;

public class GameMenu {
    private Stage stage;
    private Play game;
    private Label.LabelStyle labelStyle;

    public GameMenu(Play game) {
        this.game = game;
        stage = new Stage(new FitViewport(Play.V_WIDTH, Play.V_HEIGHT));

        labelStyle = new Label.LabelStyle(new BitmapFont(), null);

        // Setup HUD layout
        Table table = new Table();
        table.bottom().left();
        table.setFillParent(true);

        // Adding Home Button
        Image homeButton = new Image(new Texture("img/home.png")); // Assume home.png is your icon
        Label homeLabel = new Label("1 - Home", labelStyle);

        // Adding Quit Button
        Image quitButton = new Image(new Texture("img/exit.png")); // Assume quit.png is your icon
        Label quitLabel = new Label("2 - Quit", labelStyle);

        // Setup table structure
        table.add(homeButton).padRight(10);
        table.add(homeLabel).padRight(30);
        table.row();
        table.add(quitButton).padRight(10);
        table.add(quitLabel).padRight(30);

        // Add table to stage
        stage.addActor(table);
    }

    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
        game.dispose();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}
