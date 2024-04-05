package com.noname.carbonadventure.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import com.noname.carbonadventure.CarbonAdventure;

public class LeaderboardScreen implements Screen {
    private CarbonAdventure game;

    public LeaderboardScreen(CarbonAdventure game) {
        this.game = game;
        // Initialize your leaderboard here (e.g., load scores)
    }

    @Override
    public void show() {
        // This method is called when this screen becomes the current screen.
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render your leaderboard here
        game.batch.begin();
        // Use your BitmapFont to draw the leaderboard entries
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // This method is called when the window is resized.
    }

    @Override
    public void pause() {
        // This method is called when the game is paused.
    }

    @Override
    public void resume() {
        // This method is called when the game resumes from a paused state.
    }

    @Override
    public void hide() {
        // This method is called when this screen is no longer the current screen.
        dispose(); // It's often good practice to dispose of screen resources when hiding it.
    }

    @Override
    public void dispose() {
        // Dispose of assets and resources. For example:
        // if (yourTexture != null) yourTexture.dispose();
    }
}
