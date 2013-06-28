package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.math.Vector2;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public class Junction extends Surmountable{

	/**
	 * @author Fábio Pinheiro
	 * GateType will be used to define the way forward for this entry gate
	 * l and L is for lower gate number
	 * h and H is for higher gate number
	 * uppercase - can NOT change the way
	 * lowercase - can change the way
	 */
	public enum GateType {
		l, h, L, H
	}
	
	private GateType gate1, gate2, gate3;
	Vector2 	position = new Vector2();
	
	public Junction(Vector2 position) {
		this.position = position;
		this.gate1 = GateType.l;
		this.gate2 = GateType.l;
		this.gate3 = GateType.l;
	}
	public Junction(Vector2 position, GateType gate1, GateType gate2, GateType gate3) {
		this.position = position;
		this.gate1 = gate1;
		this.gate2 = gate2;
		this.gate3 = gate3;
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

}
