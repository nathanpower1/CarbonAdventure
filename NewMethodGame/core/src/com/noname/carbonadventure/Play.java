package com.noname.carbonadventure;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.noname.carbonadventure.Screens.MainMenuScreen;
import com.noname.carbonadventure.Screens.PlayScreen;


public class Play extends Game {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	public static final float PPM = 100;

	public static final int WIDTH = 480;
	public static final int HEIGHT = 780;
	public static final short DEFAULT_BIT = 1;
	public static final short PLAYER_BIT = 2;
	public static final short GEM_BIT = 4;
	public static final short DESTROYED_BIT = 8;

	public static final short OBJECT_BIT = 16;
	public static final short NPC_BIT = 32;

	public SpriteBatch batch;

	public static AssetManager manager;


	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("audio/music/Wicked.mp3", Music.class);
		manager.load("audio/music/buckbumble.mp3", Music.class);
		manager.load("audio/sounds/Gem_Collect.wav", Sound.class);
		manager.finishLoading();
		 this.setScreen(new MainMenuScreen(this));

	}

	@Override
	public void render () {

		super.render();
	}

}
