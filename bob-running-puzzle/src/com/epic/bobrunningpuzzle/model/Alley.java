package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.math.Vector2;
import com.epic.bobrunningpuzzle.model.serializer.ModelJsonSerializer;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public class Alley extends Surmountable {

	private Gate gate;
	private float radius;
	
	/** Used only by de Serializer {@link ModelJsonSerializer}*/
	public Alley() {super();}
	
	public Alley(Vector2 position,  float radius) {
		this.gate = new Gate(this, position);
		this.radius = radius;
	}

	public Alley(Gate gatePair,  float radius) {
		this(gatePair.getPosition(), radius);
		Gate.pairOfGates(gatePair, this.getGate());
	}
	
	public Gate getGate() {return gate;}
	public float getRadius() {return radius;}
	
	@Override
	public void update(float delta) {
		//nome

	}

	@Override
	public void updateTraveler(float delta, Traveler traveler) {
		traveler.surmountableTransition(this.gate.getPairGate(),delta);
	}
	
	@Override
	public void calculateAndUpdatePosition(Traveler traveler, Vector2 out) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void acceptRendererVisitor(RendererVisitor rendererVisitor) {
		rendererVisitor.draw(this);
		gate.acceptRendererVisitor(rendererVisitor);
	}

}
