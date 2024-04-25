package com.noname.carbonadventure;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.noname.carbonadventure.Screens.IntroText;
import com.noname.carbonadventure.Sprites.Player;
import com.noname.carbonadventure.models.LeaderboardEntry;

public class Play extends Game {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	public static final float PPM = 100;

	public static final int WIDTH = 480;
	public static final int HEIGHT = 780;
	public static final short NOTHING_BIT = 0;

	public static final short DEFAULT_BIT = 1;
	public static final short PLAYER_BIT = 2;
	public static final short GEM_BIT = 4;
	public static final short DESTROYED_BIT = 8;

	public static final short OBJECT_BIT = 16;
	public static final short NPC_BIT = 32;

	public static final short FINISH_BIT = 64;
	public static final short PATH_BIT = 128;

	public static Player player;

	public SpriteBatch batch;

	public static AssetManager manager;
	public String playerName;


	private Json json = new Json();

	private Preferences prefs;

	private Array<LeaderboardEntry> leaderboardEntries = new Array<>();


	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("audio/music/Wicked.mp3", Music.class);
		manager.load("audio/music/lasvegas.mp3", Music.class);
		manager.load("audio/music/buckbumble.mp3", Music.class);
		manager.load("audio/music/cowboy.mp3", Music.class);
		manager.load("audio/sounds/Gem_Collect.wav", Sound.class);
		manager.load("audio/sounds/cuh.wav", Sound.class);
		manager.load("audio/sounds/booo.wav", Sound.class);
		manager.load("audio/sounds/bus_honk.wav", Sound.class);
		manager.load("audio/sounds/hello.wav", Sound.class);
		manager.load("audio/sounds/car_horn.wav", Sound.class);
		manager.load("audio/sounds/alright.mp3", Sound.class);
		manager.load("audio/sounds/best_shot.wav", Sound.class);
		manager.load("audio/sounds/wompwomp.mp3", Sound.class);
		manager.load("audio/sounds/Elvis.wav", Sound.class);
		manager.load("audio/music/cowboyTrio.mp3", Music.class);
		manager.load("audio/sounds/gunshot.mp3", Sound.class);


		manager.finishLoading();
		setScreen(new IntroText(this));
		prefs = Gdx.app.getPreferences("MyGamePreferences");
		loadLeaderboard();


		// Log loading status for cuh.wav
		if (manager.isLoaded("audio/sounds/cuh.wav", Sound.class)) {
			Gdx.app.log("AssetLoaded", "cuh.wav is loaded successfully");
		} else {
			Gdx.app.error("AssetNotLoaded", "cuh.wav failed to load");
		}
	}

@Override
	public void render () {

		super.render();
	}

	public void setPlayerName(String name) {

		prefs.putString("playerName", name);
		prefs.flush();
	}

	public String getPlayerName() {

		return prefs.getString("playerName", "");
	}

	private void saveLeaderboard() {
		String leaderboardJson = json.toJson(leaderboardEntries, Array.class, LeaderboardEntry.class); // Ensure correct type handling
		prefs.putString("leaderboard", leaderboardJson);
		prefs.flush();
	}

	private void loadLeaderboard() {
		String leaderboardJson = prefs.getString("leaderboard", "");
		if (!leaderboardJson.isEmpty()) {
			// We specify LeaderboardEntry.class in the second argument to ensure type correctness
			Array<LeaderboardEntry> loadedEntries = json.fromJson(Array.class, LeaderboardEntry.class, leaderboardJson);
			if (loadedEntries != null) {
				leaderboardEntries = loadedEntries;
				leaderboardEntries.sort();
			}
		}
	}
	public void addLeaderboardEntry(String playerName, int score, float timeTaken) {
		LeaderboardEntry entry = new LeaderboardEntry(playerName, score, timeTaken);
		leaderboardEntries.add(entry);
		leaderboardEntries.sort();
		saveLeaderboard(); // Save the updated leaderboard after adding a new entry
	}

	public Array<LeaderboardEntry> getLeaderboardEntries() {
		return leaderboardEntries;
	}

}
