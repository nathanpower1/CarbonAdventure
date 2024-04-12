package com.noname.carbonadventure.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.noname.carbonadventure.Play;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.sun.tools.javac.util.Name;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


import java.awt.*;

public class HUD implements Disposable {
    public static Stage stage;
    private Viewport viewport;
    private Integer worldTimer;
    private float timeCount;
    private static Integer score;
    private static Integer carbonMeter = 0;

    static Label carbonMeterLabel;

    Label countdownLabel;
    static Label scoreLabel;
    Label timelabel;

    Label levellabel;
    Label worldlabel;
    Label playerlabel;

    private static Array<Image> gemIcons = new Array<>();
    private static final int ICON_SIZE = 16;
    private static final int ICON_PADDING = 4;

    private Image charInfoImage;

    private TextureAtlas UIatlas;

    private Texture playerHeadTexture;
    private Image playerHeadImage;



    public HUD(SpriteBatch sb){
        this.UIatlas = new TextureAtlas("ui.atlas");
        this.playerHeadTexture = new Texture("img/Playerhead.png");

        this.playerHeadImage = new Image(playerHeadTexture);

        // Set position of the image on the HUD (modify x, y as needed)
        this.playerHeadImage.setPosition(2, Play.V_HEIGHT - 55 - playerHeadImage.getHeight());
        this.playerHeadImage.setSize(80, 80);

        // Option 2: Scale the image
        this.playerHeadImage.setScale(0.5f, 0.75f);

        //worldTimer = 3;
        //timeCount = 0;
        //score = null;

        viewport = new FitViewport(Play.V_WIDTH,Play.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport,sb);

        TextureAtlas.AtlasRegion charInfoRegion = UIatlas.findRegion("char_info");
        if (charInfoRegion != null) {
            this.charInfoImage = new Image(charInfoRegion);
            // Set position and size of the image as needed
            charInfoImage.setPosition(5, Play.V_HEIGHT - charInfoImage.getHeight() - 30);
            charInfoImage.setSize(100, 50); // Set size as needed, or use pack size

            // Add to the stage
            stage.addActor(charInfoImage);
            stage.addActor(playerHeadImage);
        }

        //Table table = new Table();
        //table.top();
        //table.setFillParent(true);

        //countdownLabel = new Label(String.format("%03d",worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //scoreLabel = new Label(String.format("%06d",score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //scoreLabel = new Label(String.format("%01d",score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //carbonMeterLabel = new Label(String.format("%07d", carbonMeter), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //timelabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //levellabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //worldlabel = new Label("Carbon Meter", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //worldlabel = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //playerlabel = new Label("GEM COUNTER", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        //table.add(playerlabel).expandX().padTop(10);
        //table.add(worldlabel).expandX().padTop(10);
        //table.add(timelabel).expandX().padTop(10);
        //table.row();
        //table.add(scoreLabel).expandX();
        //table.add(carbonMeterLabel).expandX();
        //table.add(levellabel).expandX();
        //table.add(countdownLabel).expandX();

        //stage.addActor(table);





    }

    public void update(float dt) {
        //timeCount += dt;
        //if (timeCount >= 1) {
            //if (worldTimer > 0) {
                //worldTimer--;
                //countdownLabel.setText(String.format("%03d", worldTimer));
            //}
            //timeCount = 0;
        //}
    }

    //public static void addScore(int value){
        //score += value;
        //scoreLabel.setText(String.format("%01d", score));
    //}

    public static void addGemIcon() {
        TextureRegion gemRegion = new TextureRegion(new Texture("img/gem.png")); // Consider loading this once, not every time a gem is collected.
        Image gemImage = new Image(gemRegion);
        // Calculate position based on the number of already collected gems
        int offset = gemIcons.size * (ICON_SIZE + ICON_PADDING);
        gemImage.setPosition(40 + offset, Play.V_HEIGHT - 50); // Adjust y-position according to your HUD layout
        gemImage.setSize(ICON_SIZE, ICON_SIZE);

        stage.addActor(gemImage);
        gemIcons.add(gemImage);

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

