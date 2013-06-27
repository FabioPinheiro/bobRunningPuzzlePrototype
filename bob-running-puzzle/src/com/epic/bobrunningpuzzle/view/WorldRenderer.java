package com.epic.bobrunningpuzzle.view;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.epic.bobrunningpuzzle.BobRunningPuzzle;
import com.epic.bobrunningpuzzle.model.Alley;
import com.epic.bobrunningpuzzle.model.Bob;
import com.epic.bobrunningpuzzle.model.Goal;
import com.epic.bobrunningpuzzle.model.Level;
import com.epic.bobrunningpuzzle.model.Road;
import com.epic.bobrunningpuzzle.model.Start;
import com.epic.bobrunningpuzzle.model.Surmountable;

public class WorldRenderer {

	Bob bob;
	Level level;
	public WorldRenderer(/*FIXME LEVEL frome Level selection!!!!*/) {
		Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+" <new>");
		level = new Level();
		bob= new Bob(level.getStart());
	}
	
	public void render() {
		Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#drawLevel");
		drawDebug();
		drawBob();
		drawLevel();
	}
	
	public void drawLevel(){
		Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawLevel");
		Iterator<Surmountable> it =  level.getIterator();
		while(it.hasNext()){
			Surmountable el = it.next();
			drawSurmountable(el);//TODO can be optimized, if outside screen does not need to be designed
		}
	}
	
	private void drawDebug() {
		Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawDebug");
	}
	
	private void drawBob() {
		Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawBob");
	}
	
	private void drawSurmountable(Surmountable el) {
		//FIXME LOG nunca devia chegar aqui!!!! ISTO FUNCIONA EM JAVA? ou é nessecario um visitor?
		Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, "ERROR!!!! " + this.getClass().getName() +" #drawSurmountable(Surmountable el)");
		Gdx.app.error("ERROR!!", "drawSurmountable:LOG nunca devia chegar aqui!!!!");
	}
	
	private void drawSurmountable(Alley el) {
		Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawSurmountable(Alley el)");
	}
	private void drawSurmountable(Goal el) {
		Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawSurmountable(Goal el)");
	}
	private void drawSurmountable(Road el) {
		Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawSurmountable(Road el)");
	}
	private void drawSurmountable(Start el) {
		Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawSurmountable(Start el)");
	}
	
}
