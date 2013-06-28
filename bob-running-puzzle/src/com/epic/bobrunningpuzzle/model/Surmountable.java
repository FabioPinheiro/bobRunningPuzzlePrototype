package com.epic.bobrunningpuzzle.model;

import com.epic.bobrunningpuzzle.model.Bob.EntryExtremity;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public abstract class Surmountable implements ModelElement{

	public class Gate{
		
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
	}
	
	public abstract void update(float delta);
	public abstract void updateBob(float delta,Bob bob);
	public abstract void acceptRendererVisitor(RendererVisitor rendererVisitor);
}
