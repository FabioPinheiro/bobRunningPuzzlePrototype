package com.epic.bobrunningpuzzle.sceens;

//import com.me.myfirstgdxgame.controller.BobController;
//import com.me.myfirstgdxgame.model.World;
//import com.me.myfirstgdxgame.view.WorldRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.epic.bobrunningpuzzle.BobRunningPuzzle;
import com.epic.bobrunningpuzzle.controller.GameController;
import com.epic.bobrunningpuzzle.model.Level;
import com.epic.bobrunningpuzzle.model.Surmountable;
import com.epic.bobrunningpuzzle.view.WorldRenderer;

public class GameScreen implements Screen {

	//private World world;
	private Level level;
	private WorldRenderer worldRenderer;
	private GameController controller;
	//private BobController controller;

	private int width, height; // the width and height of the screen used by the Android touch events.
	
	public GameScreen() {
		controller = new GameController();
		Surmountable.setController(controller);
		level = new Level();
		worldRenderer = new WorldRenderer(level, true);
	}
	
	@Override
	public void render(float delta) {
		//Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1); FIXME
		//Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); FIXME

		//controller.update(delta);
		level.update(delta);
		worldRenderer.render();
	}
	
	@Override
	public void show(){
		Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#show()");
		//controller = new BobController(world);
		Gdx.input.setInputProcessor(controller);
	}

	@Override
	public void resize(int width, int height) {
		worldRenderer.setSize(width, height);
		this.width = width;
		this.height = height;
	}

	@Override
	public void hide() {
		this.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		worldRenderer.dispose();
		Gdx.input.setInputProcessor(null);
	}

	// * InputProcessor methods ***************************//
/*
	@Override
	public boolean keyDown(int keycode) { // triggered whenever a key is pressed on the physical keyboard.
		if (keycode == Keys.LEFT)
			controller.leftPressed();
		if (keycode == Keys.RIGHT)
			controller.rightPressed();
		if (keycode == Keys.Z)
			controller.jumpPressed();
		if (keycode == Keys.X)
			controller.firePressed();
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.LEFT)
			controller.leftReleased();
		if (keycode == Keys.RIGHT)
			controller.rightReleased();
		if (keycode == Keys.Z)
			controller.jumpReleased();
		if (keycode == Keys.X)
			controller.fireReleased();
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
*/
/*	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		//if (!Gdx.app.getType().equals(ApplicationType.Android))
			//return false;
		if (x < width / 2 && y > height / 2) {
			//controller.leftPressed();
		}
		if (x > width / 2 && y > height / 2) {
			//controller.rightPressed();
		}
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		//if (!Gdx.app.getType().equals(ApplicationType.Android))
			//return false;
		if (x < width / 2 && y > height / 2) {
			//controller.leftReleased();
		}
		if (x > width / 2 && y > height / 2) {
			//controller.rightReleased();
		}
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}*/
}
