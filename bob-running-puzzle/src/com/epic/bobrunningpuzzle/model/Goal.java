package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.math.Vector2;
import com.epic.bobrunningpuzzle.model.auxiliary.Place;
import com.epic.bobrunningpuzzle.model.serializer.ModelJsonSerializer;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public class Goal extends Surmountable {
	
	
	private Gate gate;

	public Gate getGate() {return gate;}
	
	/** Used only by de Serializer {@link ModelJsonSerializer}*/
	public Goal() {super();}
	
	public Goal(Place point) {
		this.gate = new Gate(this, point);
	}
	
	public Goal(Gate gatePair) {
		this.gate = new Gate(this, gatePair.getThisGatePlace());
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
