/**
 * 
 */
package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.math.Vector2;
import com.epic.bobrunningpuzzle.model.auxiliary.Place;
import com.epic.bobrunningpuzzle.model.auxiliary.PlaceIdentifier;
import com.epic.bobrunningpuzzle.model.auxiliary.PlaceManager;
import com.epic.bobrunningpuzzle.model.serializer.ModelJsonSerializer;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

/**
 * @author Fábio Pinheiro
 * 
 */
public class Gate implements ModelElement{//extends Surmountable{

	private Surmountable surmountable;
	private PlaceIdentifier identifier;
	
	public String getKey() {return this.identifier.getKey();}
	
	public Place getThisGatePlace(){return PlaceManager.getPlace(this.getKey());}
	
	
	/** Used only by de Serializer {@link ModelJsonSerializer}*/
	public Gate() {}
	
	public Gate(Surmountable surmountable, String pointId) {
		this.surmountable = surmountable;
		this.identifier= PlaceManager.register(pointId, this);
	}
	public Gate(Surmountable surmountable, Gate gate) {
		this(surmountable, gate.getKey());
	}
	public Gate(Surmountable surmountable, Place point) {
		this(surmountable, point.getKey());
	}
	
	public boolean isActivated(){return this.getThisGatePlace().size() > 1;}
	public boolean isComplex() {return this.getThisGatePlace().size() > 2;}
	
	//get and set and is
	public Gate getPairGate() {
		if(this.isComplex() && Surmountable.getController().isTouching())
			return this.getPairGateB();
		else return this.getPairGateA();
	}
	public Gate getPairGateA() {return PlaceManager.getPlace(this.getKey()).getOtherGateIndex(this,0);}
	public Gate getPairGateB() {return PlaceManager.getPlace(this.getKey()).getOtherGateIndex(this,1);}
	public Surmountable getSurmountable() 	{return surmountable;}
	public Vector2 getPosition() 			{return this.getThisGatePlace().getPosition();}

	@Override
	public void update(float delta) {
		//none
	}

	/*@Override
	public void updateBob(float delta, Bob bob) {
		Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#updateBOB#################");
		if (!this.activated()) {
			Gdx.app.error("ERROR!!", this.getClass().getName()+"#getPairGate");//FIXME
		}
		this.getSurmountable().updateBob(delta, bob);
	}*/

	@Override
	public void acceptRendererVisitor(RendererVisitor rendererVisitor) {
		//FIXME rendererVisitor.draw(this);
	}

	@Override
	public String debugString() {
		return "Gate:: this.getKey():" + this.getKey() +
				"; point:" + getPosition().toString()+
				"; surmountable:" + surmountable.toString() +
				"; pairGateA" + getPairGateA();//FIXME + "; pairGateB" + getPairGateB();//FIXME
	}
	
	/*
	private Surmountable surmountable;
	private Gate pairGateA, pairGateB;
	private Vector2 point;
	
	
	** Used only by de Serializer {@link ModelJsonSerializer}*
	public Gate() {}
	
	public Gate(Surmountable surmountable, Vector2 point) {
		this.surmountable = surmountable;
		this.position = point;
		pairGateA = null;
		pairGateB = null;
	}
	
	//FIXME I ()Fabio don't like this
	//public Gate(Surmountable surmountable, Gate pairGateA) {
	//	this.surmountable = surmountable;
	//	this.pairGate = pairGateA;
	//	this.position = pairGateA.getPosition();
	//	pairGateA.setPairGate(this);
	//}
	
	public boolean isActivated(){return pairGateA != null;}
	public boolean isComplex() {return this.pairGateB != null;}
	
	//get and set and is
	public Gate getPairGate() {
		if(this.isComplex() && Surmountable.getController().isTouching())
			return pairGateB;
		else return pairGateA;
	}
	public Gate getPairGateA() {return pairGateA;}
	public Gate getPairGateB() {return pairGateB;}
	public void setPairGate(Gate pairGate) 	{this.pairGateA = pairGate;}
	public void setPairGate(Gate pairGateA, Gate pairGateB) {
		this.pairGateA = pairGateA;
		this.pairGateB = pairGateB;
	}
	public Surmountable getSurmountable() 	{return surmountable;}
	public Vector2 getPosition() 			{return point;}
	
	public static void pairOfGates(Gate x, Gate a){
		x.setPairGate(a);
		a.setPairGate(x);
	}
	public static void pairOfGates(Gate x, Gate a, Gate b){
		x.setPairGate(a, b);
		a.setPairGate(x);
		b.setPairGate(x);
	}

	@Override
	public void update(float delta) {
		//none
	}

	//@Override
	//public void updateBob(float delta, Bob bob) {
	//	Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#updateBOB#################");
	//	if (!this.activated()) {
	//		Gdx.app.error("ERROR!!", this.getClass().getName()+"#getPairGate");//FIXME
	//	}
	//	this.getSurmountable().updateBob(delta, bob);
	//}

	@Override
	public void acceptRendererVisitor(RendererVisitor rendererVisitor) {
		rendererVisitor.draw(this);
	}

	@Override
	public String debugString() {
		return "Gate:: point:" + point.toString()+
				"; surmountable:" + surmountable.toString() +
				"; pairGateA" + pairGateA;//FIXME
	}*/
}
