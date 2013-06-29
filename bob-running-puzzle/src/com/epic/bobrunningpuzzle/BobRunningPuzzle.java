package com.epic.bobrunningpuzzle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.epic.bobrunningpuzzle.sceens.SplashScreen;

public class BobRunningPuzzle extends Game {
	
	public static final String TITLE = "Bob Running Puzzle", VERSTION = "0.0.0.1";
	public static final String GAMELOG = "BobRunningPuzzle-LOG", GAMELOG_RENDER = "BobRunningPuzzle-LOG_RENDER";
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	
	@Override
	public void create() {
		Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"----START");
		Vector2 a = new Vector2(1, 2), b = new Vector2(2, 2);
		a.lerp(b, 0.5f);
		Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"----test---:" + a.toString());
		setScreen(new SplashScreen());
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
