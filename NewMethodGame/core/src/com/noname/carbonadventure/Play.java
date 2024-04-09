package com.noname.carbonadventure;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.noname.carbonadventure.Screens.PlayScreen;
import com.noname.carbonadventure.Screens.MainMenuScreen;


public class Play extends Game {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	public static final float PPM = 100;

	public static final int WIDTH = 480;
	public static final int HEIGHT = 780;

	public SpriteBatch batch;


	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MainMenuScreen(this)); // Change this line
	}

	@Override
	public void render () {
		super.render();
	}

}
