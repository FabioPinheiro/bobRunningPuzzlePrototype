package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.math.Vector2;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public class Bob implements ModelElement{
	/**
	 * @author Fábio Pinheiro
	 * @see Surmountable#updateBob(float, Bob)
	 */
	public enum EntryExtremity {
		A, B
	}
	
	private Surmountable local;//FIXME rename me plz
	EntryExtremity entryExtremity = EntryExtremity.A;
	//Vector2 position = new Vector2(); no need
	Vector2 velocity = new Vector2();

	
	public Bob(Surmountable local) {
		this.local = local;
		update(0);
		//surmountableTransition(newSurmountable, entryExtremity, remainingDelta)
	}
	
	/**
	 * 
	 * @param newSurmountable
	 * @param entryExtremity
	 * @param remainingDelta at the end of the previous {@link Surmountable}
	 * @see Surmountable#updateBob(float, Bob)
	 */
	public void surmountableTransition(Surmountable newSurmountable, EntryExtremity entryExtremity, float remainingDelta){
		this.local = newSurmountable;
		this.entryExtremity = entryExtremity;
		newSurmountable.updateBob(remainingDelta, this);
	}
	
	public void update(float delta) {
		local.updateBob(delta, this); 
	}
	
	//get's and set's
	/*public EntryExtremity getEntryExtremity() {
		return entryExtremity;
	}
	public void setEntryExtremity(EntryExtremity newEntryExtremity) {
		this.entryExtremity = newEntryExtremity;
	}*/
	
	public Vector2 getVelocity() {
		return velocity;
	}
	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	@Override
	public void acceptRendererVisitor(RendererVisitor rendererVisitor) {
		rendererVisitor.draw(this);
	}
}
