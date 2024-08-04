package com.gravityfalls.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.gravityfalls.game.GravityFalls;
import com.gravityfalls.game.utiles.Config;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Gravity Falls Game");
		config.setResizable(true);
		config.setWindowedMode(Config.ANCHO, Config.ALTO);
		new Lwjgl3Application(new GravityFalls(), config);
	}
}
