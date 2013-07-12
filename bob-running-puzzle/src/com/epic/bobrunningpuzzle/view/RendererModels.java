package com.epic.bobrunningpuzzle.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

public class RendererModels implements RendererVisitor{
	
	private SpriteBatch spriteBatch;
	private Texture bobTexture;
	
	public RendererModels(SpriteBatch spriteBatch, Texture bobTexture) {
		this.spriteBatch = spriteBatch;
		this.bobTexture = bobTexture;
	}


	@Override
	public void draw(Alley el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawAlley");
	}
	@Override
	public void draw(BezierCurve el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#BezierCurve");
	}
	@Override
	public void draw(Bob el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawBob");
		spriteBatch.draw(bobTexture, el.getPosition().x-Bob.SIZE/2, el.getPosition().y-Bob.SIZE/2, Bob.SIZE/2, Bob.SIZE/2,Bob.SIZE,Bob.SIZE, 1f, 1f, el.getDirectionAngle(), 0, 0, bobTexture.getWidth(), bobTexture.getHeight(), false, false);
	}
	@Override
	public void draw(Gate el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawGate");
	}
	@Override
	public void draw(Goal el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawGoal");
	}
	@Override
	public void draw(Junction el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawJunction");
	}
	@Override
	public void draw(Level level) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawLevel");
		Gdx.app.error("ERROR!!", "drawLevel- nunca devia chegar aqui!!!! devido ao visitor");//FIXME
	}
	@Override
	public void draw(Road el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawRoad");
		Gdx.app.error("ERROR!!", "drawRoad- nunca devia chegar aqui!!!!");
	}
	@Override
	public void draw(Start el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawStart");
	}
	@Override
	public void draw(StraightLine el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#StraightLine");
	}
	@Override
	public void draw(Surmountable el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, "ERROR!!!! " + this.getClass().getName() +" #drawSurmountable");
		Gdx.app.error("ERROR!!", "drawSurmountable- nunca devia chegar aqui!!!!");
	}
	@Override
	public void draw(Traveler el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, "ERROR!!!! " + this.getClass().getName() +" #drawTraveler");
		Gdx.app.error("ERROR!!", "drawTraveler- nunca devia chegar aqui!!!!");
	}
}
