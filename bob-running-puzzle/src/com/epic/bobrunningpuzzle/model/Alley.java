package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.math.Vector2;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public class Alley extends Surmountable {

	private final Gate gate;
	private final float radius;
	
	public Alley(Vector2 position,  float radius) {
		this.gate = new Gate(this, position);
		this.radius = radius;
	}

	public Alley(Gate gate,  float radius) {
		this.gate = new Gate(this, gate);
		this.radius = radius;
	}
	
	public Gate getGate() {return gate;}
	public float getRadius() {return radius;}
	
	@Override
	public void update(float delta) {
		//nome

	}

	@Override
	public void updateBob(float delta, Bob bob) {
		bob.surmountableTransition(this.gate.getPairGate(),delta);
	}

	@Override
	public void acceptRendererVisitor(RendererVisitor rendererVisitor) {
		rendererVisitor.draw(this);
		gate.acceptRendererVisitor(rendererVisitor);
	}

}
