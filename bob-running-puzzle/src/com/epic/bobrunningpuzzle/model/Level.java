package com.epic.bobrunningpuzzle.model;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.epic.bobrunningpuzzle.BobRunningPuzzle;
import com.epic.bobrunningpuzzle.model.auxiliary.LevelStructure;
import com.epic.bobrunningpuzzle.model.serializer.ModelJsonSerializer;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public class Level implements ModelElement{
	public enum GameState {START, INGAME, PAUSE, FINISH};
	
	private static GameState gameState = GameState.START; //FIXME não gosta!!!!!!!!!!!!!!!
	private static boolean win;
	private Bob bob;
	private LevelStructure levelStructure = new LevelStructure();
	
	public Bob getBob() {return bob;}
	/**{@link LevelStructure#getWidth()}*/
	public float getWidth() {return this.levelStructure.getWidth();}
	/**{@link LevelStructure#getHeight()}*/
	public float getHeight() {return this.levelStructure.getHeight();}
	/**{@link LevelStructure#getStartTimer()}*/
	public float getStartTimer() {return this.levelStructure.getStartTimer();}
	
	public static GameState getGameState() {return gameState;}
	public static boolean isWin() {return win;}
	
	public static void gameStart(){
		if(gameState == GameState.START)
			gameState = Level.GameState.INGAME;
	}
	public static void gameOver(boolean winner){
		win = winner;
		gameState = Level.GameState.FINISH;
	}
	public static void gamePause(){
		if(gameState == GameState.INGAME){
			gameState = Level.GameState.PAUSE;
			Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, "Level:"+"#gamePause : gameState = Level.GameState.PAUSE !!!!!!!!!!!!!!!!!!!! = " + getGameState().toString());
		}
		else if(gameState == GameState.PAUSE){
			gameState = Level.GameState.INGAME;
			Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, "Level:"+"#gamePause : gameState = Level.GameState.INGAME;!!!!!!!!!!!!!!!!!!!! = " + getGameState().toString());
		}
	}
	
	/** Used only FIXME by de Serializer {@link ModelJsonSerializer}*/
	public Level() {
		Gdx.app.log(BobRunningPuzzle.GAMELOG, "##########################################");
	}
	
	public void load(LevelStructure levelStructure){
		this.levelStructure = levelStructure;
		this.bob= new Bob(this.getStart().getGate());
	}
	
	public void update(float delta){
		bob.update(delta);
		Iterator<Surmountable> it =  this.levelStructure.getAllSurmountable().iterator();;
		while(it.hasNext()){
			Surmountable el = it.next();
			el.update(delta);
		}
	}

	@Override
	public void acceptRendererVisitor(RendererVisitor rendererVisitor) {
		Iterator<Surmountable> it =  this.levelStructure.getAllSurmountable().iterator(); //FIXME pouco eficiente
		while(it.hasNext()){
			Surmountable el = it.next();
			el.acceptRendererVisitor(rendererVisitor);
		}
		bob.acceptRendererVisitor(rendererVisitor);
		rendererVisitor.draw(this);
	}
	
	/**
	 * @return the strat of the level
	 * @see Strat#updateBob(float delta, Bob bob)
	 */
	public Start getStart(){return this.levelStructure.getStart(0/*FIXME*/);}

	@Override
	public String debugString() {
		String newString;
		newString = bob.toString() + "\n";
		Iterator<Surmountable> it =  this.levelStructure.getAllSurmountable().iterator();
		while(it.hasNext()){
			Surmountable el = it.next();
			newString += el.debugString() +"\n" ;
		}
		return newString;
	};
}
