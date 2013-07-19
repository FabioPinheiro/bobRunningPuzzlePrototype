/**
 * 
 */
package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.epic.bobrunningpuzzle.BobRunningPuzzle;
import com.epic.bobrunningpuzzle.model.serializer.ModelJsonSerializer;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

/**
 * @author Fábio Pinheiro
 * 
 */
public class Gate implements ModelElement{//extends Surmountable{

	private Surmountable surmountable;
	private Gate pairGateA, pairGateB;
	private Vector2 position;
	
	
	/** Used only by de Serializer {@link ModelJsonSerializer}*/
	public Gate() {}
	
	public Gate(Surmountable surmountable, Vector2 position) {
		this.surmountable = surmountable;
		this.position = position;
		pairGateA = null;
		pairGateB = null;
	}
	
	/*FIXME I ()Fabio don't like this
	public Gate(Surmountable surmountable, Gate pairGateA) {
		this.surmountable = surmountable;
		this.pairGate = pairGateA;
		this.position = pairGateA.getPosition();
		pairGateA.setPairGate(this);
	}*/
	
	public boolean isActivated(){return pairGateA != null;}
	public boolean isComplex() {return this.pairGateB != null;}
	
	//get and set and is
	public Gate getPairGate() {
		if(this.isComplex() && Surmountable.getController().isTouching())
			return pairGateB;
		else return pairGateA;
	}
	public Gate getPairGateA() {return pairGateA;}
	public Gate getPairGateB() {return pairGateB;}
	public void setPairGate(Gate pairGate) 	{this.pairGateA = pairGate;}
	public void setPairGate(Gate pairGateA, Gate pairGateB) {
		this.pairGateA = pairGateA;
		this.pairGateB = pairGateB;
	}
	public Surmountable getSurmountable() 	{return surmountable;}
	public Vector2 getPosition() 			{return position;}
	
	public static void pairOfGates(Gate x, Gate a){
		x.setPairGate(a);
		a.setPairGate(x);
	}
	public static void pairOfGates(Gate x, Gate a, Gate b){
		x.setPairGate(a, b);
		a.setPairGate(x);
		b.setPairGate(x);
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
				"; pairGateA" + pairGateA;//FIXME
	}
}
