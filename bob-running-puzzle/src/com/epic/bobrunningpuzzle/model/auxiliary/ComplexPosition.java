package com.epic.bobrunningpuzzle.model.auxiliary;

import com.badlogic.gdx.math.Vector2;

public class ComplexPosition {
	Vector2 position;
	float angle;
	float inputLength;
	float outputLength;
	
	public Vector2 getPosition() {return position;}
	public float getAngle() {return angle;}
	public float getInputLength() {return inputLength;}
	public float getOutputLength() {return outputLength;}
	
	public Vector2 entryPoint(){
		Vector2 aux = new Vector2(- this.getInputLength(), 0);
		aux.rotate(this.getAngle());
		aux.add(getPosition());
		return aux;
	}
	
	public Vector2 centerPoint(){
		return this.getPosition();
	}
	
	public Vector2 exitPoint(){
		Vector2 aux = new Vector2(+ this.getOutputLength(), 0);
		aux.rotate(this.getAngle());
		aux.add(getPosition());
		return aux;
	}
	
	public ComplexPosition(Vector2 position, float angle, float inputLength, float outputLength) {
		this.position = position;
		this.angle = angle;
		this.inputLength = inputLength;
		this.outputLength = outputLength;
	}
	
	public ComplexPosition(Vector2 position, float angle, float length) {
		this(position, angle, length, length);
	}
}
