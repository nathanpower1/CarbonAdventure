package com.noname.carbonadventure.Screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Screens.PlayScreen;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.OrthographicCamera;




public class MainMenuScreen implements Screen {

    private Texture logoTexture;

    private static final int LEADERBOARD_BUTTON_WIDTH = 200;
    private static final int LEADERBOARD_BUTTON_HEIGHT = 200;

    private static final int PLAY_BUTTON_WIDTH = 200;
    private static final int PLAY_BUTTON_HEIGHT= 200;
    private static final int LEADERBOARD_BUTTON_Y = 100;
    private static final int PLAY_BUTTON_Y = 300;

    private OrthographicCamera camera;
    private Viewport viewport;

    Play game;

    Texture LeaderboardbuttonActive;
    Texture LeaderboardbuttonInactive;

    Texture PlaybuttonActive;
    Texture PlaybuttonInactive;

    Texture backgroundTexture;

    public MainMenuScreen (Play game) {
        this.game = game;
        logoTexture = new Texture("img/mainmenuimage2.png");
        PlaybuttonActive = new Texture("img/play01.png");
        PlaybuttonInactive = new Texture("img/play02.png");
        LeaderboardbuttonActive = new Texture("img/leaderboard01.png");
        LeaderboardbuttonInactive = new Texture("img/leaderboard02.png");
        backgroundTexture = new Texture("img/FbQJeV5WYAM_58i.png");


        camera = new OrthographicCamera();
        viewport = new StretchViewport(Play.WIDTH, Play.HEIGHT, camera);
        viewport.apply();
        camera.position.set(Play.WIDTH / 2, Play.HEIGHT / 2, 0);
    }




    @Override
    public void show(){
    }

    @Override
    public void render(float delta){

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
        Gdx.gl.glClear(true ? GL20.GL_COLOR_BUFFER_BIT : 0);

        game.batch.begin();
        float logoX = Play.WIDTH / 2 - logoTexture.getWidth() / 2;
        float logoY = Play.HEIGHT - logoTexture.getHeight() - 10; // 50 is an arbitrary padding from the top
        game.batch.draw(logoTexture, logoX, logoY);

        game.batch.draw(backgroundTexture, 0, 0, Play.WIDTH, Play.HEIGHT);

        // Get touch input as world coordinates
        Vector3 touchPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        viewport.unproject(touchPoint); // This now contains the touch coordinates in your game world

        // Use touchPoint.x and touchPoint.y instead of Gdx.input.getX() and Gdx.input.getY()
        int x = Play.WIDTH /2 - LEADERBOARD_BUTTON_WIDTH /2;
        if (touchPoint.x > x && touchPoint.x < x + LEADERBOARD_BUTTON_WIDTH && touchPoint.y > LEADERBOARD_BUTTON_Y && touchPoint.y < LEADERBOARD_BUTTON_Y + LEADERBOARD_BUTTON_HEIGHT) {
            game.batch.draw(LeaderboardbuttonActive, x, LEADERBOARD_BUTTON_Y, LEADERBOARD_BUTTON_WIDTH, LEADERBOARD_BUTTON_HEIGHT);
            if (Gdx.input.justTouched()) {
                // Transition to the Leaderboard screen
                game.setScreen(new LeaderboardScreen(game));
                return; // Avoid calling dispose here, let the screen manager handle it
            }
        } else {
            game.batch.draw(LeaderboardbuttonInactive, x, LEADERBOARD_BUTTON_Y, LEADERBOARD_BUTTON_WIDTH, LEADERBOARD_BUTTON_HEIGHT);
        }

        // Repeat for the Play button
        x = Play.WIDTH /2 - PLAY_BUTTON_WIDTH /2;
        if (touchPoint.x > x && touchPoint.x < x + PLAY_BUTTON_WIDTH && touchPoint.y > PLAY_BUTTON_Y && touchPoint.y < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT) {
            game.batch.draw(PlaybuttonActive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            if (Gdx.input.justTouched()){
                game.setScreen(new PlayScreen(this.game));
                return; // Same as above, don't dispose here
            }
        } else {
            game.batch.draw(PlaybuttonInactive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        }

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);


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