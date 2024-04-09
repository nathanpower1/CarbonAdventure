package com.noname.carbonadventure;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.noname.carbonadventure.Screens.MainMenuScreen;
import com.noname.carbonadventure.Screens.PlayScreen;


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
		 this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

}
