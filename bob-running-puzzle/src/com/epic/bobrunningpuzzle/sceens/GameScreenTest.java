package com.epic.bobrunningpuzzle.sceens;

//import com.me.myfirstgdxgame.controller.BobController;
//import com.me.myfirstgdxgame.model.World;
//import com.me.myfirstgdxgame.view.WorldRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.epic.bobrunningpuzzle.BobRunningPuzzle;
import com.epic.bobrunningpuzzle.controller.GameController;
import com.epic.bobrunningpuzzle.model.Level;
import com.epic.bobrunningpuzzle.model.Level.GameState;
import com.epic.bobrunningpuzzle.model.Surmountable;
import com.epic.bobrunningpuzzle.model.auxiliary.LevelStructure;
import com.epic.bobrunningpuzzle.view.WorldRenderer;

public class GameScreenTest implements Screen {
	
	public static final float StartToZoomInTheTraveler = 3f;

	//private World world;
	private Level level;
	private WorldRenderer worldRenderer;
	private GameController controller;
	private float timer;
	//private BobController controller;
	
	public GameScreenTest() {
		controller = new GameController();
		Surmountable.setController(controller);
		level = new Level();
		LevelStructure levelStructureAux = new LevelStructure();
		levelStructureAux.chargeLevel2();
		level.load(levelStructureAux);
		
		worldRenderer = new WorldRenderer(level, true);
		this.timer = level.getStartTimer();
	}
	
	@Override
	public void render(float delta) {
		//Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1); FIXME
		//Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); FIXME

		//controller.update(delta);
		if(Level.getGameState() == GameState.INGAME){
			timer += delta;
			level.update(delta);
		}else if(Level.getGameState() == GameState.PAUSE){
			//FIXME show PAUSE
		}else if(Level.getGameState() == GameState.START){
			//FIXME $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
			//timer -=delta;
			if(timer <= 0){
				timer = 0;
				Level.gameStart();
				worldRenderer.zoomInTheTraveler(1);
			}
			else if(timer < StartToZoomInTheTraveler){
				worldRenderer.zoomInTheTraveler(1-timer/StartToZoomInTheTraveler);
			};
		}else if(Level.getGameState() == GameState.FINISH){
			//FIXME show END
		}
		
			
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
		//FIXME worldRenderer.calculateSize();
		//this.width = width;
		//this.height = height;
	}

	@Override
	public void hide() {
		this.dispose();
	}

	@Override
	public void pause() {
		Level.gamePause();
	}

	@Override
	public void resume() {
		Level.gamePause();
	}

	@Override
	public void dispose() {
		worldRenderer.dispose();
		Gdx.input.setInputProcessor(null);
	}
}
