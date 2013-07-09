package com.epic.bobrunningpuzzle.model;

import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.epic.bobrunningpuzzle.controller.GameController;
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
		
		this.start = new Start(new Vector2(0,0));
		this.bob= new Bob(this.getStart().getGate());
		
		Road road1= new Road(start.getGate(),new Vector2(1,1),"A");
		Road road2= new Road(road1.getGateB(),new Vector2(2,-1),"B");
		Road road3= new Road(road2.getGateB(),new Vector2(10,10),"C");
		Road road4= new Road(road3.getGateB(), road1.getGateA(),"D");
		
		this.map.add(start);
		this.map.add(road1);
		this.map.add(road2);
		this.map.add(road3);
		this.map.add(road4);
		
		Junction junctuin1 = new Junction(new Vector2(2, 6), 0.7f, 0f, "l", "l", "l");
		Junction junctuin2 = new Junction(new Vector2(4, 6), 1f, 180f, "l", "H", "h");
		this.map.add(junctuin1);
		this.map.add(junctuin2);
	}
	
	public void update(float delta){
		bob.update(delta);
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
		bob.acceptRendererVisitor(rendererVisitor);
		Iterator<Surmountable> it =  this.getIterator();
		while(it.hasNext()){
			Surmountable el = it.next();
			el.acceptRendererVisitor(rendererVisitor);
		}
	}
	
	/**
	 * @return the strat of the level
	 * @see Strat#updateBob(float delta, Bob bob)
	 */
	public Start getStart(){return start;}

	@Override
	public String debugString() {
		String newString;
		newString = bob.toString() + "\n";
		Iterator<Surmountable> it =  this.getIterator();
		while(it.hasNext()){
			Surmountable el = it.next();
			newString += el.debugString() +"\n" ;
		}
		return newString;
	};
}
