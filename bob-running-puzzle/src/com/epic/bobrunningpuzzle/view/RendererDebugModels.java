package com.epic.bobrunningpuzzle.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.epic.bobrunningpuzzle.BobRunningPuzzle;
import com.epic.bobrunningpuzzle.model.Alley;
import com.epic.bobrunningpuzzle.model.Bob;
import com.epic.bobrunningpuzzle.model.Gate;
import com.epic.bobrunningpuzzle.model.Goal;
import com.epic.bobrunningpuzzle.model.Junction;
import com.epic.bobrunningpuzzle.model.Level;
import com.epic.bobrunningpuzzle.model.Road;
import com.epic.bobrunningpuzzle.model.Start;
import com.epic.bobrunningpuzzle.model.Surmountable;

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
		
		/*for (Block block : world.getDrawableBlocks((int)CAMERA_WIDTH, (int)CAMERA_HEIGHT)) {
			Rectangle rect = block.getBounds();
			float x1 = block.getPosition().x + rect.x;
			float y1 = block.getPosition().y + rect.y;
			debugRenderer.setColor(new Color(1, 0, 0, 1));
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}
		// render Bob
		Bob bob = world.getBob();
		Rectangle rect = bob.getBounds();
		float x1 = bob.getPosition().x + rect.x;
		float y1 = bob.getPosition().y + rect.y;
		debugRenderer.setColor(new Color(0, 1, 0, 1));
		debugRenderer.rect(x1, y1, rect.width, rect.height);*/
	}
	
	
	
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
	}
	public void draw(Level level) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawLevel");
		Gdx.app.error("ERROR!!", "drawLevel- nunca devia chegar aqui!!!! devido ao visitor");
		//level.getWidth(); //FIXME
		//level.getHeight(); //FIXME
	}
	
	public void draw(Surmountable el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, "ERROR!!!! " + this.getClass().getName() +" #drawSurmountable");
		Gdx.app.error("ERROR!!", "drawSurmountable- nunca devia chegar aqui!!!!");
	}
	
	public void draw(Alley el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawAlley");
		debugShapeRenderer.setColor(new Color(0, 1, 1, 1));
		debugShapeRenderer.circle(
				el.getGate().getPosition().x, el.getGate().getPosition().y,
				el.getRadius());
	}
	@Override
	public void draw(Gate el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawGate");
		debugShapeRenderer.setColor(new Color(1, 1, 1, 1));
		debugShapeRenderer.circle(
				el.getPosition().x, el.getPosition().y,
				0.3f);
	}
	public void draw(Goal el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawGoal");
	}
	public void draw(Junction el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawJunction");
		debugShapeRenderer.setColor(new Color(0, 1, 0, 1));
		debugShapeRenderer.circle(
				el.getCenter().x, el.getCenter().y,
				el.getRadius(), 15);
	}
	public void draw(Road el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawRoad");
		debugShapeRenderer.line(
				el.getGateA().getPosition().x, el.getGateA().getPosition().y,
				el.getGateB().getPosition().x, el.getGateB().getPosition().y,
				new Color(0, 0, 1, 1), new Color(1, 0, 1, 1));
	}
	public void draw(Start el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawStart");
		debugShapeRenderer.setColor(new Color(1, 0, 0, 1));
		debugShapeRenderer.circle(
				el.getGate().getPosition().x, el.getGate().getPosition().y,
				0.4f,10);
	}
	
}
