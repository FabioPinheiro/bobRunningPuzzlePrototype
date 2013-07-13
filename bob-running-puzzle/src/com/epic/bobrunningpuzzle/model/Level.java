package com.epic.bobrunningpuzzle.model;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.epic.bobrunningpuzzle.BobRunningPuzzle;
import com.epic.bobrunningpuzzle.view.RendererVisitor;
import com.epic.bobrunningpuzzle.view.WorldRenderer;

public class Level implements ModelElement{
	
	public enum GameState {START, INGAME, PAUSE, FINISH};
	
	private static GameState gameState = GameState.START;
	private static boolean win;
	
	public static GameState getGameState() {return gameState;}
	public static boolean isWin() {return win;}
	
	public static void gameStart(){
		if(gameState == GameState.START)
			gameState = Level.GameState.INGAME;
	}
	public static void gameOver(boolean winner){
		win = win;
		gameState = Level.GameState.FINISH;
	}
	public static void gamePause(){
		if(gameState == GameState.INGAME)
			gameState = Level.GameState.PAUSE;
		else if(gameState == GameState.PAUSE)
			gameState = Level.GameState.INGAME;
		Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, "Level:"+"#gamePause !!!!!!!!!!!!!!!!!!!! = " + getGameState().toString());
	}
	
	/** width in Metros*/
	private final float width;
	/** height in Metros*/
	private final float height;
	
	private Array<Surmountable> map = new Array<Surmountable>();
	private Start start;
	private Bob bob;
	private float startTimer;
	
	/**@return width in Metros*/
	public float getWidth() {return width;}
	/**@return height in Metros*/
	public float getHeight() {return height;}
	
	public float getStartTimer() {return startTimer;}
	public Bob getBob() {return bob;}


	/**
	 * @param startTimer mast be biger them 3f.
	 * @param width of the map in Metros.
	 * @param height of the map in Metros.
	 */
	public Level(float startTimer, float width, float height) {
		this.startTimer = startTimer;
		this.width = width;
		this.height = height;
		
		this.start = new Start(new Vector2(0,0));
		this.bob= new Bob(this.getStart().getGate());
		
		Road road1= new StraightLine(start.getGate(),new Vector2(1,1),"pointA");
		Road road2= new StraightLine(road1.getGateB(),new Vector2(2,-1),"pointB");
		Road road3= new StraightLine(road2.getGateB(),new Vector2(5,2),"C");
		//Road road4= new StraightLine(road3.getGateB(), road1.getGateA(),"D");
		
		BezierCurve curve1 = new BezierCurve(road3.getGateB(), new Vector2(5,10), new Vector2(2,-5), new Vector2(2,2), "curve1");
		
		
		this.map.add(start);
		this.map.add(road1);
		this.map.add(road2);
		this.map.add(road3);
		this.map.add(curve1);
		//this.map.add(road4);
		
		Junction junctuin1 = new Junction(new Vector2(1, 6), 0.7f, 0f, "l", "l", "l");
		Junction junctuin2 = new Junction(new Vector2(5, 6), 1f, 180f, "l", "H", "h");
		this.map.add(junctuin1);
		this.map.add(junctuin2);
		
		Road road5= new StraightLine(curve1.getGateB(), junctuin2.getGateB(),"road5");
		Road road6= new StraightLine(junctuin2.getGateC(), junctuin1.getGateC(),"road6");
		this.map.add(road5);
		this.map.add(road6);
		
		Goal goal1 = new Goal(junctuin1.getGateA());
		this.map.add(goal1);
		

	}
	
	public void update(float delta){
		bob.update(delta);
		Iterator<Surmountable> it =  this.getIterator();
		while(it.hasNext()){
			Surmountable el = it.next();
			el.update(delta);
		}
	}
	/**
	 * the Iterator must must be released after use
	 * @return a Iterator of all {@link Surmountable} in the level;
	 * @see WorldRenderer#render()
	 */
	public Iterator<Surmountable> getIterator(){
		return map.iterator();
	}

	@Override
	public void acceptRendererVisitor(RendererVisitor rendererVisitor) {
		Iterator<Surmountable> it =  this.getIterator();
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
	public Start getStart(){return start;}

	@Override
	public String debugString() {
		String newString;
		newString = bob.toString() + "\n";
		Iterator<Surmountable> it =  this.getIterator();
		while(it.hasNext()){
			Surmountable el = it.next();
			newString += el.debugString() +"\n" ;
		}
		return newString;
	};
}
