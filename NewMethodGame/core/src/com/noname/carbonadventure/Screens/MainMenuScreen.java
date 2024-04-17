package com.noname.carbonadventure.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.noname.carbonadventure.Play;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import java.util.List;
import java.util.Arrays;



public class MainMenuScreen implements Screen {
    private Play game;
    private Stage stage;
    private Texture bgTexture;
    private Music music;

    private Table table;

    private ImageButton playButton;
    private ImageButton leaderboardButton;
    private ImageButton tutorialButton;
    private ImageButton exitButton;


    public MainMenuScreen(final Play game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);



        // Background
        bgTexture = new Texture(Gdx.files.internal("img/FbQJeV5WYAM_58i.png"));
        Image backgroundImage = new Image(bgTexture);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);

        Table table = new Table();
        table.setFillParent(true); // The table will fill the stage
        stage.addActor(table);

        //Music
        Music music2 = Play.manager.get("audio/music/cowboy.mp3", Music.class);

        music = Play.manager.get("audio/music/buckbumble.mp3",Music.class);
        music.setLooping(true);
        if(!music2.isPlaying()){
            music.play();}
        else if (music2.isPlaying()){
                music2.stop();
                music.play();
        }




        // Buttons
        playButton = createImageButton("img/play01.png", "img/play02.png", () -> game.setScreen(new PlayerNameScreen(game)));
        leaderboardButton = createImageButton("img/leaderboard01.png", "img/leaderboard02.png", () -> {});
        tutorialButton = createImageButton("img/option01.png", "img/option02.png", () -> {});
        exitButton = createImageButton("img/restart01.png", "img/restart02.png", Gdx.app::exit);






        table.center();
        table.add(playButton).padBottom(10).row();
        table.add(leaderboardButton).padBottom(10).row();
        table.add(tutorialButton).padBottom(10).row();
        table.add(exitButton);

    }



    private ImageButton createImageButton(String upPath, String downPath, final Runnable onClick) {
        Texture upTexture = new Texture(Gdx.files.internal(upPath));
        Texture downTexture = new Texture(Gdx.files.internal(downPath));
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = new TextureRegionDrawable(upTexture);
        style.down = new TextureRegionDrawable(downTexture);

        ImageButton button = new ImageButton(style);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onClick.run();
            }
        });

        return button;
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);


        final int minWidth = 800;
        final int minHeight = 600;

        float scaleFactorWidth = (float) width / minWidth;
        float scaleFactorHeight = (float) height / minHeight;
        float scaleFactor = Math.min(scaleFactorWidth, scaleFactorHeight);


        float scalingModifier = 1.0f;
        if (width > minWidth || height > minHeight) {
            scalingModifier = 1.5f;
        }
        scaleFactor *= scalingModifier;


        updateButtonSizes(scaleFactor);
    }

    private void updateButtonSizes(float scaleFactor) {

        if (table == null) {
            return;
        }

        float baseSize = 72;

        table.clearChildren();
        for (ImageButton button : Arrays.asList(playButton, leaderboardButton, tutorialButton, exitButton)) {
            float newSize = baseSize * scaleFactor;
            button.setSize(newSize, newSize); // Apply new size
            table.add(button).size(newSize, newSize).padBottom(10 * scaleFactor).row();
        }

        table.pack();


        for (ImageButton button : Arrays.asList(playButton, leaderboardButton, tutorialButton, exitButton)) {

            button.setSize(baseSize * scaleFactor, baseSize * scaleFactor);

            button.getImage().setScale(scaleFactor);
        }


        table.clearChildren();
        table.add(playButton).size(playButton.getWidth(), playButton.getHeight()).padBottom(10 * scaleFactor).row();
        table.add(leaderboardButton).size(leaderboardButton.getWidth(), leaderboardButton.getHeight()).padBottom(10 * scaleFactor).row();
        table.add(tutorialButton).size(tutorialButton.getWidth(), tutorialButton.getHeight()).padBottom(10 * scaleFactor).row();
        table.add(exitButton).size(exitButton.getWidth(), exitButton.getHeight()).row();


        table.pack();
    }



    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        bgTexture.dispose();

    }


}