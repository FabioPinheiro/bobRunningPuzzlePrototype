package com.epic.bobrunningpuzzle.model;

import com.epic.bobrunningpuzzle.controller.GameController;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public abstract class Surmountable implements ModelElement{

	private static GameController controller;
	private String debugID = "none";
	
	public static GameController getController() {return controller;}
	public static void setController(GameController controller) {Surmountable.controller = controller;}
	private String getDebugID() {return debugID;}
	public String debugString() {return "(Surmountable::ID:"+getDebugID()+")";}
	
	public Surmountable() {
	}
	//public Surmountable(GameController controller) {
	//	this.controller = controller;
	//}
	public Surmountable(String debugID) {
		this.debugID = debugID;
	}
	/*public class Gate{
		
		private final Surmountable surmountableA;
		private final Surmountable surmountableB;
		private final EntryExtremity entryExtremityA;
		private final EntryExtremity entryExtremityB;
		
		public Gate(Surmountable surmountableA,  Surmountable surmountableB, EntryExtremity entryExtremityA, EntryExtremity entryExtremityB) {
			this.surmountableA = surmountableA;
			this.surmountableB = surmountableB;
			this.entryExtremityA = entryExtremityA;
			this.entryExtremityB = entryExtremityB;
		}
		public Surmountable nextSurmountable(Surmountable surmountableX) {
			return(surmountableX.equals(surmountableA) ? surmountableB : surmountableA);
		}
		public EntryExtremity nextEntryExtremity(Surmountable surmountableX) {
			return(surmountableX.equals(surmountableA) ? entryExtremityA : entryExtremityB);
		}
	}*/
	
	public abstract void update(float delta);
	
	/**
	 * @param delta time for last uptade(FIXME?frame?)
	 * @param bob
	 */
	public abstract void updateBob(float delta,Bob bob);
	
	public abstract void acceptRendererVisitor(RendererVisitor rendererVisitor);
}
