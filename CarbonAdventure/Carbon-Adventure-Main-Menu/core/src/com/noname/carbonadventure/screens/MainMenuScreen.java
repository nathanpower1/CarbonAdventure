package com.noname.carbonadventure.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.noname.carbonadventure.CarbonAdventure;
import com.badlogic.gdx.Screen;


public class MainMenuScreen implements Screen {

    private static final int LEADERBOARD_BUTTON_WIDTH = 300;
    private static final int LEADERBOARD_BUTTON_HEIGHT = 150;

    private static final int PLAY_BUTTON_WIDTH = 330;
    private static final int PLAY_BUTTON_HEIGHT= 150;
    private static final int LEADERBOARD_BUTTON_Y = 100;
    private static final int PLAY_BUTTON_Y = 300;

    CarbonAdventure game;

    Texture LeaderboardbuttonActive;
    Texture LeaderboardbuttonInactive;

    Texture PlaybuttonActive;
    Texture PlaybuttonInactive;

    Texture backgroundTexture;

    public MainMenuScreen (CarbonAdventure game) {
        this.game = game;
        PlaybuttonActive = new Texture("img/play01.png");
        PlaybuttonInactive = new Texture("img/play02.png");
        LeaderboardbuttonActive = new Texture("img/leaderboard01.png");
        LeaderboardbuttonInactive = new Texture("img/leaderboard02.png");
        backgroundTexture = new Texture("img/FbQJeV5WYAM_58i.png");

    }


    @Override
    public void show(){
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
        Gdx.gl.glClear(16384);
        Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);

        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0, CarbonAdventure.WIDTH, CarbonAdventure.HEIGHT);
        int x = CarbonAdventure.WIDTH /2 - LEADERBOARD_BUTTON_WIDTH /2 ;
        if (Gdx.input.getX() < x + LEADERBOARD_BUTTON_WIDTH && Gdx.input.getX() > x && CarbonAdventure.HEIGHT - Gdx.input.getY() < LEADERBOARD_BUTTON_Y + LEADERBOARD_BUTTON_HEIGHT && CarbonAdventure.HEIGHT - Gdx.input.getY() > LEADERBOARD_BUTTON_Y) {
            game.batch.draw(LeaderboardbuttonActive, x, LEADERBOARD_BUTTON_Y, LEADERBOARD_BUTTON_WIDTH, LEADERBOARD_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                // Transition to the Leaderboard screen
                this.dispose();
                game.setScreen(new LeaderboardScreen(game));
            }
        } else {
            game.batch.draw(LeaderboardbuttonInactive, x, LEADERBOARD_BUTTON_Y, LEADERBOARD_BUTTON_WIDTH, LEADERBOARD_BUTTON_HEIGHT);
        }


        x = CarbonAdventure.WIDTH /2 - PLAY_BUTTON_WIDTH/2;
        if (Gdx.input.getX() < x + PLAY_BUTTON_WIDTH &&  Gdx.input.getX() > x && CarbonAdventure.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && CarbonAdventure.HEIGHT -Gdx.input.getY() > PLAY_BUTTON_Y) {
            game.batch.draw(PlaybuttonActive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new Play(game));
            }
        }else {
            game.batch.draw(PlaybuttonInactive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        }
        game.batch.end();
        }
    @Override
    public void resize(int width, int height) {

    }
    @Override
    public void pause() {

    }
    @Override
    public void resume(){

    }
    @Override
    public void hide(){

    }
    @Override
    public void dispose(){

    }


}


