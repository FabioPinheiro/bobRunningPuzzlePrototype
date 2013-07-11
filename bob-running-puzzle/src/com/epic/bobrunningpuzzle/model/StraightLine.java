package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.math.Vector2;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public class StraightLine extends Road{

	public StraightLine(Vector2 positionA, Vector2 positionB, String debugID) {
		super(positionA, positionB, debugID);
	}
	public StraightLine(Gate gatePairA, Vector2 positionB, String debugID) {
		super(gatePairA.getPosition(), positionB, debugID);
		Gate.pairOfGates(gatePairA, this.getGateA());
	}
	public StraightLine(Gate gatePairA, Gate gatePairB, String debugID) {
		super(gatePairA.getPosition(), gatePairB.getPosition(), debugID);
		Gate.pairOfGates(gatePairA, this.getGateA());
		Gate.pairOfGates(gatePairB, this.getGateB());
	}
	
	@Override
	public void update(float delta) {
		//none
	}

	@Override
	public void updateTraveler(float delta, Traveler traveler) {
		super.updateTraveler(delta, traveler);
	}

	@Override
	public void acceptRendererVisitor(RendererVisitor rendererVisitor) {
		rendererVisitor.draw(this);
		super.acceptRendererVisitor(rendererVisitor);
	}
	@Override
	public void calculateAndUpdatePosition(Traveler traveler, Vector2 out) {
		if(traveler.getEntryGate().equals(this.getGateA())){
			out.set(this.getGateA().getPosition());
			out.lerp(this.getGateB().getPosition(), traveler.getT());
		}
		else{
			out.set(this.getGateB().getPosition());
			out.lerp(this.getGateA().getPosition(), (1-traveler.getT()));
		}
	}
	
	public final float getLength(){
		return this.getGateA().getPosition().dst(this.getGateB().getPosition());
	}
}
