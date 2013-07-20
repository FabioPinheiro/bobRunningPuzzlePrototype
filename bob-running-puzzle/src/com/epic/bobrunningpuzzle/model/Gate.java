/**
 * 
 */
package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.epic.Point;
import com.epic.bobrunningpuzzle.BobRunningPuzzle;
import com.epic.bobrunningpuzzle.model.serializer.ModelJsonSerializer;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

/**
 * @author Fábio Pinheiro
 * 
 */
public class Gate implements ModelElement{//extends Surmountable{

	private Surmountable surmountable;
	//private Gate pairGateA, pairGateB;
	private Point<Gate> point; //FIXME point
	private String pointId = null;
	private int priority = -1;
	
	/** Used only by de Serializer {@link ModelJsonSerializer}*/
	public Gate(String pointId, int priority) {
		this.pointId = pointId;
		this.priority = priority;
	}
	
	/**
	 * 
	 * @param surmountable
	 * @param point
	 * @param priority '0' is the most priority, the smaller the higher the priority 
	 */
	public Gate(Surmountable surmountable, Point<Gate> point, int priority) {
		this.surmountable = surmountable;
		this.point = point;
		this.point.register(this, priority);
	}
	/**
	 * lower priority
	 * @param surmountable
	 * @param point
	 */
	public Gate(Surmountable surmountable, Point<Gate> point) {
		this.surmountable = surmountable;
		this.point = point;
		this.point.register(this);
	}
	
	public boolean isActivated(){return this.point.sizeOfTheCollection() > 1;}
	public boolean isComplex() {return this.point.sizeOfTheCollection() > 2;}
	
	//get and set and is
	public Gate getPairGate() {
		if(this.isComplex() && Surmountable.getController().isTouching())
			return this.getPairGateB();
		else return this.getPairGateA();
	}
	public Gate getPairGateA() {return (Gate) Point.getPoint(this.point.getId()).getOtherT(this,0);}
	public Gate getPairGateB() {return (Gate) Point.getPoint(this.point.getId()).getOtherT(this,1);}
	public Surmountable getSurmountable() 	{return surmountable;}
	public Point<Gate> getPoint() 			{return point;}
	public Vector2 getPosition() 			{return point.getPosition();}

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
		return "Gate:: Point.id:" + point.getId() +
				"; point:" + getPosition().toString()+
				"; surmountable:" + surmountable.toString() +
				"; pairGateA" + getPairGateA() +//FIXME
				"; pairGateB" + getPairGateB();//FIXME
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
