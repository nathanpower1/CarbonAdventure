package com.noname.carbonadventure.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.noname.carbonadventure.Play;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.sun.tools.javac.util.Name;

import java.awt.*;

public class HUD implements Disposable {
    public Stage stage;
    private Viewport viewport;
    private Integer worldTimer;
    private float timeCount;
    private static Integer score;

    Label countdownLabel;
    static Label scoreLabel;
    Label timelabel;

    Label levellabel;
    Label worldlabel;
    Label playerlabel;
    public HUD(SpriteBatch sb){

        worldTimer = 3;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(Play.V_WIDTH,Play.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport,sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countdownLabel = new Label(String.format("%03d",worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //scoreLabel = new Label(String.format("%06d",score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%01d",score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timelabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //levellabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
       // worldlabel = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        playerlabel = new Label("GEM COUNTER", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(playerlabel).expandX().padTop(10);
        table.add(worldlabel).expandX().padTop(10);
        table.add(timelabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levellabel).expandX();
        table.add(countdownLabel).expandX();

        stage.addActor(table);





    }

    public void update(float dt) {
        timeCount += dt;
        if (timeCount >= 1) {
            if (worldTimer > 0) {
                worldTimer--;
                countdownLabel.setText(String.format("%03d", worldTimer));
            }
            timeCount = 0;
        }
    }

    public static void addScore(int value){
        score += value;
        scoreLabel.setText(String.format("%01d", score));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

