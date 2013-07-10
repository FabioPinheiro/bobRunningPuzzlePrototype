/**
 * 
 */
package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.epic.bobrunningpuzzle.BobRunningPuzzle;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

/**
 * @author Fábio Pinheiro
 * 
 */
public class Gate implements ModelElement{//extends Surmountable{

	private final Surmountable surmountable;
	private Gate pairGate;
	private final Vector2 position;
	
	
	public Gate(Surmountable surmountable, Vector2 position) {
		this.surmountable = surmountable;
		this.position = position;
		pairGate = null;
	}
	
	/*FIXME I ()Fabio don't like this
	public Gate(Surmountable surmountable, Gate pairGate) {
		this.surmountable = surmountable;
		this.pairGate = pairGate;
		this.position = pairGate.getPosition();
		pairGate.setPairGate(this);
	}*/
	
	public boolean activated (){
		return pairGate != null;
	}
	
	//get and set
	public Gate getPairGate() 				{return pairGate;}
	public void setPairGate(Gate pairGate) 	{this.pairGate = pairGate;}
	public Surmountable getSurmountable() 	{return surmountable;}
	public Vector2 getPosition() 			{return position;}
	
	public static void pairOfGates(Gate a, Gate b){
		a.setPairGate(b);
		b.setPairGate(a);
	}

	@Override
	public void update(float delta) {
		//none
	}

	/*@Override
	public void updateBob(float delta, Bob bob) {
		Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#updateBOB#################");
		if (!this.activated()) {
			Gdx.app.error("ERROR!!", this.getClass().getName()+"#getPairGate");//FIXME
		}
		this.getSurmountable().updateBob(delta, bob);
	}*/

	@Override
	public void acceptRendererVisitor(RendererVisitor rendererVisitor) {
		rendererVisitor.draw(this);
	}

	@Override
	public String debugString() {
		return "Gate:: position:" + position.toString()+
				"; surmountable:" + surmountable.toString() +
				"; pairGate" + pairGate;//FIXME
	}
}
