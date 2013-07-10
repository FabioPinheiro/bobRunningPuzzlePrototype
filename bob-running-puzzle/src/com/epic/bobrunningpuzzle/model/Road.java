package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.math.Vector2;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public abstract class Road extends Surmountable {
	
	private Gate gateA, gateB;
	
	/*public Road(String debugID) {
		super(debugID);
	}
	public Road(Gate gateA, Gate gateB) {
		this.gateA = gateA;
		this.gateB = gateB;		
	}*/
	public Road(Vector2 positionA, Vector2 positionB, String debugID) {
		super(debugID);
		this.gateA = new Gate(this, positionA);
		this.gateB = new Gate(this, positionB);
	}/*
	public Road(Gate gateA, Vector2 positionB, String debugID) {
		super(debugID);
		this.gateA = new Gate(this, gateA);
		this.gateB = new Gate(this, positionB);
	}
	public Road(Gate gateA, Gate gateB, String debugID) {
		super(debugID);
		this.gateA = new Gate(this, gateA);
		this.gateB = new Gate(this, gateB);
	}*/
	

	@Override
	public abstract void update(float delta);

	@Override
	public abstract void updateTraveler(float delta, Traveler traveler);

	@Override
	public abstract void acceptRendererVisitor(RendererVisitor rendererVisitor);
	
	//get's and set's
	public Gate getOtherGate(Gate gate)	{return gate.equals(gateA) ? gateB : gateA;}
	public Gate getGateA() 				{return gateA;}
	public void setGateA(Gate gateA) 	{this.gateA = gateA;}
	public Gate getGateB() 				{return gateB;}
	public void setGateB(Gate gateB) 	{this.gateB = gateB;}
	
	@Override
	public String debugString() { //FIXME implementaçao nas subClass
		return "Road::"+super.debugString() +"\n\t"+ getGateA().debugString() +"\n\t"+ getGateB().debugString();
	}
}
