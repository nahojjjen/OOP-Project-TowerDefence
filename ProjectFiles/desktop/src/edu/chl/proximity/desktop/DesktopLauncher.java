package edu.chl.proximity.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import edu.chl.proximity.Proximity;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		config.fullscreen = false;
		config.addIcon("assets/Icons/256.png", Files.FileType.Internal);
		config.addIcon("assets/Icons/64.png", Files.FileType.Internal);
		config.addIcon("assets/Icons/32.png", Files.FileType.Internal);
		new LwjglApplication(new Proximity(), config);
	}
}
