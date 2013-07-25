package com.epic.bobrunningpuzzle.model;

import com.epic.bobrunningpuzzle.model.serializer.ModelJsonSerializer;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public class Bob extends Traveler{
	
	/**
	 * @author Fábio Pinheiro
	 * @see Surmountable#updateBob(float, Bob)
	 */
	public static final float SPEED = 1f;
	public static final float SIZE = 1f;
	
	/** Used only by de Serializer {@link ModelJsonSerializer}*/
	public Bob() {super();}
	
	public Bob(Gate entryGate) {
		super(entryGate);
	}
	
	@Override
	public void acceptRendererVisitor(RendererVisitor rendererVisitor) {
		rendererVisitor.draw(this);
	}
	
	
	@Override
	public String debugString() {
		return "Bob:: position" + getPosition().toString() + "; entryGate:"+ getEntryGate().toString();
	}

	@Override public float getSIZE() {return SIZE;}
	@Override public float getSPEED() {return SPEED;}
}
