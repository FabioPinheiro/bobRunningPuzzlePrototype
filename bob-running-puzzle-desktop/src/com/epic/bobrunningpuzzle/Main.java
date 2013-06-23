package com.epic.bobrunningpuzzle;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = BobRunningPuzzle.TITLE + " v" +  BobRunningPuzzle.VERSTION;
		cfg.vSyncEnabled = true; //use less CPU!!!!
		cfg.useGL20 = true;
		cfg.width = 800;
		cfg.height = 420;
		
		new LwjglApplication(new BobRunningPuzzle(), cfg);
	}
}
