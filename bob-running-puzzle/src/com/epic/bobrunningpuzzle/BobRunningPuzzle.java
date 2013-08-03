package com.epic.bobrunningpuzzle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.epic.bobrunningpuzzle.sceens.GameScreenTest;
import com.epic.bobrunningpuzzle.sceens.SplashScreen;

public class BobRunningPuzzle extends Game {
	
	public static final String TITLE = "Bob Running Puzzle", VERSTION = "0.0.0.1";
	public static final String GAMELOG = "BobRunningPuzzle-LOG", GAMELOG_RENDER = "BobRunningPuzzle-LOG_RENDER";
	
	@Override
	public void create() {
		Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"----START");
		setScreen(new SplashScreen());
		//setScreen(new GameScreenTest());
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
