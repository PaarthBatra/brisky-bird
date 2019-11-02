package com.versionpb.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import java.awt.Frame;

import javax.swing.JFrame;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.versionpb.game.BriskyBird;

public class DesktopLauncher {
	public static void main (String[] arg) {
		//JFrame frame = new JFrame("Pearson Warranty");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		///frame.setVisible(true);
		LwjglApplication frame = new LwjglApplication(new BriskyBird(), config);
		
		config.width = BriskyBird.WIDTH ;
		config.height = BriskyBird.HEIGHT;
		config.title = BriskyBird.TITLE;
		config.resizable = false;
		config.addIcon("briskybirdIcon.png", Files.FileType.Internal);
		//frame.setAlwaysOnTop(true);
		
	}
}
