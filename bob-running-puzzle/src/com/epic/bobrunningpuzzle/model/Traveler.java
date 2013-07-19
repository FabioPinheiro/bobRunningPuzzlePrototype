package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.epic.bobrunningpuzzle.BobRunningPuzzle;
import com.epic.bobrunningpuzzle.model.serializer.ModelJsonSerializer;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public abstract class Traveler implements ModelElement{
	
	/** Used only by de Serializer {@link ModelJsonSerializer}*/
	public Traveler() {super();}
	
	public Traveler(Gate entryGate) {
		this.setEntryGate(entryGate);
		this.calculatePosition(); //XXX can i do this now?: ERROR!!: com.epic.bobrunningpuzzle.model.Start#calculateAndUpdatePosition- nunca devia chegar aqui!!!!
	}
	
	//LIXO public class TravelerState { 	private TravelerState travelerState;
	
	/**
	 * The t is {@link Surmountable} length traveled (ranging 0..1).
	 */
	private float t=0;
	private Gate entryGate;
	private Vector2 position = new Vector2(), oldPosition = new Vector2();
	
	//get's and set's and add
	public Gate getEntryGate() {return entryGate;}
	public void setEntryGate(Gate entryGate){
		this.entryGate = entryGate;
		this.t=0;
	} 
	public float getT() {return t;}
	public void setT(float t) {this.t = t;}
	//public void addT(float alpha) {this.t += alpha;}	
	public Vector2 getPosition() {return position;}
	public void setPosition(Vector2 position) {this.position = position;}
	public Vector2 getDirection() {
		return position.cpy().sub(oldPosition).clamp(1f, 1f);
	}
	public float getDirectionAngle() {
		return getDirection().angle();
	}
	
	public abstract float getSIZE();
	public abstract float getSPEED();
	
	public void calculatePosition(){
		this.oldPosition.set(this.getPosition());
		this.getEntryGate().getSurmountable().calculateAndUpdatePosition(this, this.getPosition());
	}

	
	/**
	 * @param surmountableLength
	 * @param delta
	 * @return remaining Delta return NOT 0 if reach the target
	 */
	public float updatesStateTraveler(float surmountableLength, float delta){
		//LIXO float timeToGo = (surmountableLength*(1-this.t))/this.getSPEED();
		float distanceToGo = surmountableLength*(1-getT());
		float distanceTraveled = delta*this.getSPEED();
		if(distanceToGo > distanceTraveled){
			this.setT(1-(distanceToGo-distanceTraveled)/surmountableLength);
			//Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#T= "+ this.getT());
			return 0f;
		}else{
			this.setT(1);
			return (distanceTraveled-distanceToGo)/this.getSPEED();
		}
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
		this.setEntryGate(entryGate);
		this.setT(0);
		entryGate.getSurmountable().updateTraveler(remainingDelta, this);
	}
	
	
	
	
	/*
	 * 
	 * @param target
	 * @param delta
	 * @return remaining Delta return NOT 0 if reach the target
	 *
	public float move(Vector2 target, float delta){
		float distanceToGo = position.dst(target);
		float timeToGo = distanceToGo/Bob.SPEED;
		if(timeToGo > delta){
			float alpha = delta/timeToGo;
			position.lerp(target, alpha);
			Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#move1-if; position=" + position.toString());
			return 0f;
		}else{
			//position.lerp(target, 1);//FIXME it is not necessary this line
			Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#move1-else; position=" + position.toString());
			return delta - timeToGo;
		}
	}*/


	public void update(float delta) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#update#################");
		this.getEntryGate().getSurmountable().updateTraveler(delta, this); 
		this.calculatePosition();
	}
	
	@Override
	public abstract void acceptRendererVisitor(RendererVisitor rendererVisitor);

}
