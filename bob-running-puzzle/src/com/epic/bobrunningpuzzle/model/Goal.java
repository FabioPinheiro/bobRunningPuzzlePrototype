package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.math.Vector2;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public class Goal extends Surmountable {
	
	
	private final Gate gate;

	public Gate getGate() {return gate;}
	
	public Goal(Vector2 position) {
		this.gate = new Gate(this, position);
	}
	
	public Goal(Gate gatePair) {
		this.gate = new Gate(this, gatePair.getPosition());
		Gate.pairOfGates(this.gate, gatePair);
	}
	
	
	@Override
	public void update(float delta) {
		// none
	}

	@Override
	public void updateTraveler(float delta, Traveler traveler) {
		Level.gameOver(true);
	}
	
	@Override
	public void calculateAndUpdatePosition(Traveler traveler, Vector2 out) {
		// none
	}
	
	@Override
	public void acceptRendererVisitor(RendererVisitor rendererVisitor) {
		rendererVisitor.draw(this);
	}

}
