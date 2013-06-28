package com.epic.bobrunningpuzzle.model;

import java.util.Iterator;

import com.badlogic.gdx.utils.Array;
import com.epic.bobrunningpuzzle.view.RendererVisitor;
import com.epic.bobrunningpuzzle.view.WorldRenderer;

public class Level implements ModelElement{
	
	private Array<Surmountable> map = new Array<Surmountable>();
	private Start start;
	private Bob bob;
	
	public Bob getBob() {
		return bob;
	}

	public Level() {
		this.start = new Start();
		this.bob= new Bob(this.getStart());
		
		this.map.add(start);
		this.map.add(new Road());
		this.map.add(new Road());
		this.map.add(new Road());
	}

	/**
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

	@Override
	public void acceptRendererVisitor(RendererVisitor rendererVisitor) {
		bob.acceptRendererVisitor(rendererVisitor);//drawSurmountable(bob);
		//rendererVisitor.drawSurmountable(this);//FIXME
		Iterator<Surmountable> it =  this.getIterator();
		while(it.hasNext()){
			Surmountable el = it.next();
			el.acceptRendererVisitor(rendererVisitor);
		}
	} 
}
