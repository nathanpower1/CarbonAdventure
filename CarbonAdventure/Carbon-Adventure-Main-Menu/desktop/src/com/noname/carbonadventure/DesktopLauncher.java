package com.noname.carbonadventure;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.noname.carbonadventure.CarbonAdventure;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("CarbonAdventure");
		config.setWindowedMode(CarbonAdventure.WIDTH, CarbonAdventure.HEIGHT);
		new Lwjgl3Application(new CarbonAdventure(), config);
	}
}
