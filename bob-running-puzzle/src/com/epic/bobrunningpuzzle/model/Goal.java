package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.math.Vector2;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public class Goal extends Surmountable {

	@Override
	public void update(float delta) {
		// none
	}

	@Override
	public void updateTraveler(float delta, Traveler traveler) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void calculateAndUpdatePosition(Traveler traveler, Vector2 out) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void acceptRendererVisitor(RendererVisitor rendererVisitor) {
		rendererVisitor.draw(this);
	}

}
