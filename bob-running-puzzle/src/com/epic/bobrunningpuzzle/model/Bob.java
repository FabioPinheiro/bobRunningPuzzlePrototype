package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.epic.bobrunningpuzzle.BobRunningPuzzle;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public class Bob implements ModelElement{
	
	public enum Stade {
		A,B,C
	}
	
	public static final float SIZE = 1f;
	
	/**
	 * @author Fábio Pinheiro
	 * @see Surmountable#updateBob(float, Bob)
	 */
	public final static float SPEED = 1f;
	
	private Gate entryGate;
	private Vector2 position = new Vector2(); 
	private Vector2 velocity = new Vector2();
	private Stade stade;
	//private float traveledDistance;
	
	/**
	 * 
	 * @param target
	 * @param delta
	 * @return remaining Delta return NOT 0 if reach the target
	 */
	public float move(Vector2 target, float delta){
		float distanceToGo = position.dst(target);
		float timeToGo = distanceToGo/Bob.SPEED;
		if(timeToGo > delta){
			float alpha = delta/timeToGo;
			position.lerp(target, alpha);
			Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#move-if; position=" + position.toString());
			return 0f;
		}else{
			//position.lerp(target, 1);//FIXME it is not necessary this line
			Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#move-else; position=" + position.toString());
			return delta - timeToGo;
		}
	}
	public Bob(Gate entryGate) {
		this.entryGate = entryGate;
		//update(0);
		//this.traveledDistance = 0;
		//surmountableTransition(newSurmountable, entryExtremity, remainingDelta)
	}
	
	/**
	 * 
	 * @param newSurmountable
	 * @param entryExtremity
	 * @param remainingDelta at the end of the previous {@link Surmountable}
	 * @see Surmountable#updateBob(float, Bob)
	 */
	public void surmountableTransition(Gate entryGate, float remainingDelta){
		Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#surmountableTransition"+ "--entryGate="+ entryGate.debugString());
		this.entryGate = entryGate;
		this.position.set(entryGate.getPosition());
		//this.traveledDistance = 0;
		entryGate.getSurmountable().updateBob(remainingDelta, this);
	}

	public void update(float delta) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#update#################");
		entryGate.getSurmountable().updateBob(delta, this); 
	}
	
	//get's and set's and add
	/*public EntryExtremity getEntryExtremity() {return entryExtremity;}
	public void setEntryExtremity(EntryExtremity newEntryExtremity) {this.entryExtremity = newEntryExtremity;}*/
	public Vector2 getVelocity() 									{return velocity;}
	public void setVelocity(Vector2 velocity) 						{this.velocity = velocity;}
	
	public Gate getEntryGate() 										{return entryGate;}
	public void setEntryGate(Gate entryGate) 						{this.entryGate = entryGate;}
	
	public Stade getStade() {return stade;}
	public void setStade(Stade stade) {this.stade = stade;}

	//public float getTraveledDistance() 								{return traveledDistance;}
	//public void setTraveledDistance(float traveledDistance) 		{this.traveledDistance = traveledDistance;}
	//public void addTraveledDistance(float addedTraveledDistance) 	{this.traveledDistance += addedTraveledDistance;}
	
	public Vector2 getPosition() {return position;}
	//public void setPositionXY(float x, float y) {this.position.set(x, y);}
	//public void addPositionXY(float x, float y) {this.position.add(x, y);}
	
	
	@Override
	public void acceptRendererVisitor(RendererVisitor rendererVisitor) {
		rendererVisitor.draw(this);
	}
	@Override
	public String debugString() {
		return "Bob:: position" + position.toString() + "; entryGate:"+entryGate.toString();
	}
}
