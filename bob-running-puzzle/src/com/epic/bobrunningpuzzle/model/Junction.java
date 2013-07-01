package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.math.Vector2;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public class Junction extends Surmountable{

	/**
	 * @author Fábio Pinheiro
	 * GateType will be used to define the way forward for this entry {@link Gate}
	 * l and L is for lower gate number
	 * h and H is for higher gate number
	 * uppercase - can NOT change the way
	 * lowercase - can change the way
	 */
	public enum GateType {
		l, h, L, H
	}
	
	private final GateType gateType1, gateType2, gateType3;
	private final Gate gate1, gate2, gate3;
	private final Vector2 position;
	private final float radius;
	
	/**
	 * 
	 * @param position center of the object; the {@link Vector2#angle()} is important to determine the position of the gates
	 * @param radius of the gate to the center
	 * @param gateType1
	 * @param gateType2
	 * @param gateType3
	 */
	public Junction(Vector2 position, float radius, GateType gateType1, GateType gateType2, GateType gateType3) {
		this.position = position;
		this.radius = radius;
		this.gateType1 = gateType1;
		this.gateType2 = gateType2;
		this.gateType3 = gateType3;
		this.gate1 = new Gate(this, position);//FIXME position the {@link Vector2#angle()} is important to determine the position of the gates
		this.gate2 = new Gate(this, position);//FIXME position the {@link Vector2#angle()} is important to determine the position of the gates
		this.gate3 = new Gate(this, position);//FIXME position the {@link Vector2#angle()} is important to determine the position of the gates
	}
	
	@Override
	public void update(float delta) {
		//none
	}

	@Override
	public void updateBob(float delta, Bob bob) {
		// TODO Auto-generated method stub
	}

	@Override
	public void acceptRendererVisitor(RendererVisitor rendererVisitor) {
		rendererVisitor.draw(this);
	}
	
	public GateType getGateType1() {return gateType1;}
	public GateType getGateType2() {return gateType2;}
	public GateType getGateType3() {return gateType3;}
	public Gate getGate1() {return gate1;}
	public Gate getGate2() {return gate2;}
	public Gate getGate3() {return gate3;}
}
