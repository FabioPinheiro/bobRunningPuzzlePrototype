package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.epic.Place;
import com.epic.bobrunningpuzzle.model.serializer.ModelJsonSerializer;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public class Start extends Surmountable {
	
	/** Used only by de Serializer {@link ModelJsonSerializer}*/
	public Start() {super();}
	
	private Gate gate;
	
	public Gate getGate() {return gate;}
	
	public Start(Place point) {
		gate = new Gate(this, point);
	}
	
	@Override
	public void update(float delta) {
		//none
	}

	@Override
	public void updateTraveler(float delta, Traveler traveler) {
		traveler.surmountableTransition(this.getGate().getPairGate(), delta);
	}

	@Override
	public void calculateAndUpdatePosition(Traveler traveler, Vector2 out) {
		Gdx.app.error("ERROR!!", this.getClass().getName()+"#calculateAndUpdatePosition- nunca devia chegar aqui!!!!");
	}
	
	@Override
	public void acceptRendererVisitor(RendererVisitor rendererVisitor) {
		rendererVisitor.draw(this);
	}

	
	@Override
	public String debugString() {
		return "Start:: "+ gate.debugString();
	}
}
