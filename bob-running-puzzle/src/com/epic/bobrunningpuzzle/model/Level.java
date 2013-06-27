package com.epic.bobrunningpuzzle.model;

import java.util.Iterator;

import com.badlogic.gdx.utils.Array;
import com.epic.bobrunningpuzzle.view.WorldRenderer;

public class Level {
	private Array<Surmountable> map = new Array<Surmountable>();
	private Start start;
	public Level() {
		start = new Start();
		map.add(start);
		map.add(new Road());
		map.add(new Road());
		map.add(new Road());
	}

	/**
	 * 
	 * @return the strat of the level
	 * @see Strat#updateBob(float delta, Bob bob)
	 */
	public Surmountable getStart(){
		return start;
	};
	
	public void update(float delta){
		Iterator<Surmountable> it =  this.getIterator();
		while(it.hasNext()){
			Surmountable el = it.next();
			el.update(delta);
		}
	}
	/**
	 * the Iterator must must be released after use
	 * @return a Iterator of all {@link Surmountable} in the level;
	 * @see WorldRenderer#render()
	 */
	public Iterator<Surmountable> getIterator(){
		return map.iterator();
	} 
}
