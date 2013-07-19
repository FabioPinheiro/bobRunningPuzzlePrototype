package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.math.Vector2;
import com.epic.bobrunningpuzzle.controller.GameController;
import com.epic.bobrunningpuzzle.model.serializer.ModelJsonSerializer;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public abstract class Surmountable implements ModelElement{

	private static GameController controller;
	private String debugID = "none";
	
	public static GameController getController() {return controller;}
	public static void setController(GameController controller) {Surmountable.controller = controller;}
	private String getDebugID() {return debugID;}
	public String debugString() {return "(Surmountable::ID:"+getDebugID()+")";}
	
	/** Used only by de Serializer {@link ModelJsonSerializer}*/
	public Surmountable() {}

	public Surmountable(String debugID) {this.debugID = debugID;}
	
	public abstract void calculateAndUpdatePosition(Traveler traveler, Vector2 out);
	
	public abstract void update(float delta);
	
	/**
	 * @param delta time for last uptade(FIXME?frame?)
	 * @param bob
	 */
	public abstract void updateTraveler(float delta, Traveler traveler);
	
	public abstract void acceptRendererVisitor(RendererVisitor rendererVisitor);
}
