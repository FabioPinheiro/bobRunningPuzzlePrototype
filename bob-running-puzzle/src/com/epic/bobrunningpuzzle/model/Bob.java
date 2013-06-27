package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.math.Vector2;

public class Bob {
	public enum State {
		A, B, C
	}
	
	private Surmountable local;//FIXME rename me plz
	//Vector2 	position = new Vector2(); no need
	Vector2 	velocity = new Vector2();
	State		state = State.A;

	
	public Bob(Surmountable local) {
		this.local = local;
		update(0);
	}
	
	public void update(float delta) {
		local.updateBob(delta, this); 
	}
	
	//get's and set's
	public State getState() {
		return state;
	}
	public void setState(State newState) {
		this.state = newState;
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}
}
