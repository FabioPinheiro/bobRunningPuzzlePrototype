package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.epic.bobrunningpuzzle.BobRunningPuzzle;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public class Start extends Surmountable {

	private final Gate gate;

	public Start(Vector2 position) {
		gate = new Gate(this, position);
	}
	
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBob(float delta, Bob bob) {
		bob.surmountableTransition(this.getGate().getPairGate(), delta);
	}

	@Override
	public void acceptRendererVisitor(RendererVisitor rendererVisitor) {
		rendererVisitor.draw(this);
	}

	public Gate getGate() {return gate;}

	@Override
	public String debugString() {
		return "Start:: "+ gate.debugString();
	}
}
