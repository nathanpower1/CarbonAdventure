package com.noname.carbonadventure;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.noname.carbonadventure.screens.MainMenuScreen;
import com.noname.carbonadventure.screens.Play;

public class CarbonAdventure extends Game {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 780;

	public SpriteBatch  batch;
	Texture img;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		// Set the screen to your MainMenuScreen
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void resize (int width, int height) {
		super.resize(width,height);
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}

	@Override
	public void pause () {
		super.pause();
	}

	@Override
	public void resume () {
		super.resume();
	}
}
