package ru.fisherman.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.fisherman.StartGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = StartGame.HEIGHT;
		config.width = StartGame.WIDTH;
		new LwjglApplication(new StartGame(), config);
	}
}
