package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.math.Vector2;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public class Road extends Surmountable {
	
	private Gate gateA, gateB;
	
	/*public Road(Gate gateA, Gate gateB) {
		this.gateA = gateA;
		this.gateB = gateB;		
	}*/
	public Road(Vector2 positionA, Vector2 positionB, String debugID) {
		super(debugID);
		this.gateA = new Gate(this, positionA);
		this.gateB = new Gate(this, positionB);
	}
	public Road(Gate gateA, Vector2 positionB, String debugID) {
		super(debugID);
		this.gateA = new Gate(this, gateA);
		this.gateB = new Gate(this, positionB);
	}
	public Road(Gate gateA, Gate gateB, String debugID) {
		super(debugID);
		this.gateA = new Gate(this, gateA);
		this.gateB = new Gate(this, gateB);
	}
	

	@Override
	public void update(float delta) {
		//none
	}

	@Override
	public void updateBob(float delta, Bob bob) {
		float remainingDelta = bob.move(this.getOtherGate(bob.getEntryGate()).getPosition(), delta);
		if(remainingDelta != 0f){
			bob.surmountableTransition(this.getOtherGate(bob.getEntryGate()).getPairGate(),remainingDelta);
		}
		/*LIXO
		float distanceToGo = gateA.getPosition().dst2(gateB.getPosition()) - bob.getTraveledDistance();
		if(Bob.SPEED * delta < distanceToGo){
			float remainingDelta = delta - (distanceToGo/Bob.SPEED);
			bob.surmountableTransition(this.getOtherGate(bob.getEntryGate()),remainingDelta);
		}else{
			bob.addTraveledDistance(Bob.SPEED*delta);
		}*/
	}

	@Override
	public void acceptRendererVisitor(RendererVisitor rendererVisitor) {
		rendererVisitor.draw(this);
		gateA.acceptRendererVisitor(rendererVisitor);
		gateB.acceptRendererVisitor(rendererVisitor);
	}
	
	//get's and set's
	public Gate getOtherGate(Gate gate){
		return gate.equals(gateA) ? gateB : gateA;
	}
	
	public Gate getGateA() 				{return gateA;}
	public void setGateA(Gate gateA) 	{this.gateA = gateA;}
	public Gate getGateB() 				{return gateB;}
	public void setGateB(Gate gateB) 	{this.gateB = gateB;}
	@Override
	public String debugString() {
		return "Road::"+super.debugString() +"\n\t"+ getGateA().debugString() +"\n\t"+ getGateB().debugString();
	}
}
