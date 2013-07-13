package com.epic.bobrunningpuzzle.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.epic.bobrunningpuzzle.BobRunningPuzzle;
import com.epic.bobrunningpuzzle.model.Alley;
import com.epic.bobrunningpuzzle.model.BezierCurve;
import com.epic.bobrunningpuzzle.model.Bob;
import com.epic.bobrunningpuzzle.model.Gate;
import com.epic.bobrunningpuzzle.model.Goal;
import com.epic.bobrunningpuzzle.model.Junction;
import com.epic.bobrunningpuzzle.model.Level;
import com.epic.bobrunningpuzzle.model.Road;
import com.epic.bobrunningpuzzle.model.Start;
import com.epic.bobrunningpuzzle.model.StraightLine;
import com.epic.bobrunningpuzzle.model.Surmountable;
import com.epic.bobrunningpuzzle.model.Traveler;

public class RendererDebugModels implements RendererVisitor{

	private ShapeRenderer debugShapeRenderer;
	
	public RendererDebugModels(ShapeRenderer debugShapeRenderer) {
		this.debugShapeRenderer = debugShapeRenderer;
	}
	
	public void drawGridLines() {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawGridLines");
		float maxX=100, maxY=100;
		for(float x = 0; x <maxX ; x+=1){
			debugShapeRenderer.line(x, 0, x, maxY, new Color(1, 0, 0, 1), new Color(1, 0, 0, 1));
		}
		for(float y = 0; y <maxY ; y+=1){
			debugShapeRenderer.line(0, y, maxX, y, new Color(0, 1, 0, 1), new Color(0, 1, 0, 1));
		}
	}
	
	@Override
	public void draw(Alley el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawAlley");
		debugShapeRenderer.setColor(new Color(0, 1, 1, 1));
		debugShapeRenderer.circle(
				el.getGate().getPosition().x, el.getGate().getPosition().y,
				el.getRadius());
	}
	
	@Override
	public void draw(BezierCurve el) {
		debugShapeRenderer.setColor(new Color(1, 1, 0, 1));
		debugShapeRenderer.line(
				el.getGateA().getPosition().x, el.getGateA().getPosition().y,
				el.getVectorA().x, el.getVectorA().y);
		debugShapeRenderer.line(
				el.getGateB().getPosition().x, el.getGateB().getPosition().y,
				el.getVectorB().x, el.getVectorB().y);
		//FIXME draw line to vertor's
		debugShapeRenderer.curve(
				el.getGateA().getPosition().x, el.getGateA().getPosition().y,
				el.getVectorA().x, el.getVectorA().y,
				el.getVectorB().x, el.getVectorB().y,
				el.getGateB().getPosition().x, el.getGateB().getPosition().y,
				BezierCurve.SEGMENTS);
	}
	
	@Override
	public void draw(Bob el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawBob");
		debugShapeRenderer.setColor(new Color(1f, .3f, .7f, 1));
		debugShapeRenderer.circle(
				el.getPosition().x, el.getPosition().y,
				0.6f, 20);
		debugShapeRenderer.circle(
				el.getPosition().x, el.getPosition().y,
				0.3f, 20);
		debugShapeRenderer.circle(
				el.getPosition().x, el.getPosition().y,
				0.1f, 20);
		debugShapeRenderer.setColor(new Color(0f, 1f, .2f, 1));
		debugShapeRenderer.line(
				el.getPosition().x, el.getPosition().y,
				el.getPosition().x + el.getDirection().x , el.getPosition().y + el.getDirection().y);
	}
	
	@Override
	public void draw(Gate el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawGate");
		debugShapeRenderer.setColor(new Color(1, 1, 1, 1));
		debugShapeRenderer.circle(
				el.getPosition().x, el.getPosition().y,
				0.3f);
	}

	@Override
	public void draw(Goal el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawGoal");
		debugShapeRenderer.setColor(new Color(0, 1, 0, 1));
		debugShapeRenderer.triangle(
				el.getGate().getPosition().x - 0.2f, el.getGate().getPosition().y - 0.2f,
				el.getGate().getPosition().x + 0.2f, el.getGate().getPosition().y - 0.2f,
				el.getGate().getPosition().x + 0f, el.getGate().getPosition().y + 0.5f);
	}
	
	@Override
	public void draw(Junction el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawJunction");
		if(Surmountable.getController().isTouching())
			debugShapeRenderer.setColor(new Color(1, 1, 1, 1));
		else
			debugShapeRenderer.setColor(new Color(0, 1, 0, 1));
		debugShapeRenderer.circle(
				el.getCenter().x, el.getCenter().y,
				el.getRadius(), 15);
	}
	
	@Override
	public void draw(Level level) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawLevel");
		Gdx.app.error("ERROR!!", "drawLevel- nunca devia chegar aqui!!!! devido ao visitor");
		//level.getWidth(); //FIXME
		//level.getHeight(); //FIXME
	}
	
	@Override
	public void draw(Road el) {
		Gdx.app.error("ERROR!!", "drawRoad- nunca devia chegar aqui!!!!");
	}
	
	@Override
	public void draw(Start el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawStart");
		debugShapeRenderer.setColor(new Color(1, 0, 0, 1));
		debugShapeRenderer.circle(
				el.getGate().getPosition().x, el.getGate().getPosition().y,
				0.4f,10);
	}
	
	@Override
	public void draw(StraightLine el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawRoad");
		debugShapeRenderer.line(
				el.getGateA().getPosition().x, el.getGateA().getPosition().y,
				el.getGateB().getPosition().x, el.getGateB().getPosition().y,
				new Color(0, 0, 1, 1), new Color(1, 0, 1, 1));
	}
	
	@Override
	public void draw(Surmountable el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, "ERROR!!!! " + this.getClass().getName() +" #drawSurmountable");
		Gdx.app.error("ERROR!!", "drawSurmountable- nunca devia chegar aqui!!!!");
	}

	@Override
	public void draw(Traveler el) {
		Gdx.app.error("ERROR!!", "drawTraveler- nunca devia chegar aqui!!!!");
	}
}
