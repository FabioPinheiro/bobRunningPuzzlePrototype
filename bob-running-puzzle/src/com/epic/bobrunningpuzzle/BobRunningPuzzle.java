package com.epic.bobrunningpuzzle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epic.bobrunningpuzzle.sceens.Splash;

public class BobRunningPuzzle extends Game {
	
	public static final String TITLE = "Bob Running Puzzle", VERSTION = "0.0.0.1";
	public static final String GAMELOG = "BobRunningPuzzle-LOG", GAMELOG_RENDER = "BobRunningPuzzle-LOG_RENDER";
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	
	@Override
	public void create() {
		setScreen(new Splash());
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
