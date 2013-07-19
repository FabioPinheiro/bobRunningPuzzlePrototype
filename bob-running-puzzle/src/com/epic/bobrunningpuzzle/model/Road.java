package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.epic.bobrunningpuzzle.model.serializer.ModelJsonSerializer;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public class Road extends Surmountable {
	
	private Gate gateA, gateB;
	
	/** Used only by de Serializer {@link ModelJsonSerializer}*/
	public Road() {super();}
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
	}
	/*
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
	/**
	 * This constructor is used by {@link Junction}
	 * @param positionA coordinates of gatePairA
	 * @param gatePairB1 expected with equal coordinates of gatePairB2
	 * @param gatePairB2 expected with equal coordinates of gatePairB1
	 * @param debugID
	 */
	public Road(Vector2 positionA, Gate gatePairB1, Gate gatePairB2, String debugID) {
		super(debugID);
		this.gateA = new Gate(this, positionA);
		if(!gatePairB1.getPosition().equals(gatePairB2.getPosition()))
			Gdx.app.error("ERROR!!", this.getClass().getName()+"#Road: Gates with different coordinate");
		this.gateB = new Gate(this, gatePairB1.getPosition());
		Gate.pairOfGates(this.gateB, gatePairB1, gatePairB2);
	}
	

	@Override
	public void update(float delta) {
		//none
	}

	@Override
	public void updateTraveler(float delta, Traveler traveler) {
		float remainingDelta = traveler.updatesStateTraveler(this.getLength(), delta); //LIXO move(this.getOtherGate(traveler.getEntryGate()).getPosition(), delta);
		if(remainingDelta != 0f){
			traveler.surmountableTransition(this.getOtherGate(traveler.getEntryGate()).getPairGate(),remainingDelta);
		}
	}
	
	/**
	 * the metodo must be @Override
	 */
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
	
	@Override
	public void acceptRendererVisitor(RendererVisitor rendererVisitor){
		this.getGateA().acceptRendererVisitor(rendererVisitor);
		this.getGateB().acceptRendererVisitor(rendererVisitor);
	}
	
	//get's and set's
	public Gate getOtherGate(Gate gate)	{return gate.equals(gateA) ? gateB : gateA;}
	public Gate getGateA() 				{return gateA;}
	public void setGateA(Gate gateA) 	{this.gateA = gateA;}
	public Gate getGateB() 				{return gateB;}
	public void setGateB(Gate gateB) 	{this.gateB = gateB;}
	
	/**
	 * the metodo must be @Override
	 * @return 0
	 */
	public float getLength() {return 0;}
	
	@Override
	public String debugString() { //FIXME implementaçao nas subClass
		return "Road::"+super.debugString() +"\n\t"+ getGateA().debugString() +"\n\t"+ getGateB().debugString();
	}



}
