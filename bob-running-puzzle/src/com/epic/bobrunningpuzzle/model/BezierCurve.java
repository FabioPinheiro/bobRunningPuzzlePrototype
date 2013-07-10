package com.epic.bobrunningpuzzle.model;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector2;
import com.epic.bobrunningpuzzle.BobRunningPuzzle;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public class BezierCurve extends Road{

	public static final int SEGMENTS = 100;
	
	private Bezier<Vector2> bezierCurve;
	private Vector2 vectorA, vectorB;
	private float curveLength;
	
	
	public static int getSegments() {return SEGMENTS;}
	public float getCurveLength() {return curveLength;}
	public void setCurveLength(float curveLength) {this.curveLength = curveLength;}
	public Bezier<Vector2> getBezierCurve() {return bezierCurve;}
	public Vector2 getVectorA() {return vectorA;}
	public Vector2 getVectorB() {return vectorB;}
	
	/*private BezierCurve(String debugID, final Vector2... points){
		super(debugID);
		this.bezierCurve = new Bezier<Vector2>(points);
	}*/
	public BezierCurve(Vector2 positionA, Vector2 vectorA, Vector2 vectorB, Vector2 positionB, String debugID){
		super(positionA, positionB, debugID);
		this.vectorA = vectorA;
		this.vectorB = vectorB;
		Vector2 pointsAux[] = {this.getGateA().getPosition(),this.getVectorA(), this.getVectorB(), this.getGateB().getPosition()};
		this.bezierCurve = new Bezier<Vector2>(pointsAux);
		this.calculateLength();
	}
	public BezierCurve(Gate pairGateA, Vector2 vectorA, Vector2 vectorB, Vector2 positionB, String debugID){
		this(pairGateA.getPosition(), vectorA, vectorB, positionB, debugID);
		Gate.pairOfGates(pairGateA, this.getGateA());
	}
	public BezierCurve(Gate gatePairA, Vector2 vectorA, Vector2 vectorB, Gate gatePairB, String debugID){
		this(gatePairA.getPosition(), vectorA, vectorB, gatePairB.getPosition(), debugID);
		Gate.pairOfGates(gatePairA, this.getGateA());
		Gate.pairOfGates(gatePairB, this.getGateB());
	}
	
	private void calculateLength(){
		Vector2 aux1 = new Vector2(), aux2 = new Vector2();
		float sum=0;
		aux1.set(getGateA().getPosition());
		for(int i=1;i<=this.SEGMENTS;i++ ){
			aux2.set(aux1);
			this.bezierCurve.valueAt(aux1, (float) i/this.SEGMENTS);
			sum+=aux2.dst(aux1);
			//LIXO Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#sum= "+ sum + " dst=" +aux2.dst(aux1) + " I=" + (float) i/this.SEGMENTS);
		}
		this.setCurveLength(sum);
		//LIXO Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#CurveLength= "+ this.getCurveLength());
	}
	
	
	@Override
	public void update(float delta) {
		//none
	}

	@Override
	public void updateTraveler(float delta, Traveler traveler) {
		float remainingDelta = traveler.updatesStateTraveler(this.getCurveLength(), delta); //LIXO move(this.getOtherGate(traveler.getEntryGate()).getPosition(), delta);
		if(remainingDelta != 0f){
			traveler.surmountableTransition(this.getOtherGate(traveler.getEntryGate()).getPairGate(),remainingDelta);
		}
	}

	@Override
	public void acceptRendererVisitor(RendererVisitor rendererVisitor) {
		rendererVisitor.draw(this);
		this.getGateA().acceptRendererVisitor(rendererVisitor);
		this.getGateB().acceptRendererVisitor(rendererVisitor);
	}
	@Override
	public void calculateAndUpdatePosition(Traveler traveler, Vector2 out) {
		if(traveler.getEntryGate().equals(this.getGateA()))
			bezierCurve.valueAt(out, traveler.getT());
		else
			bezierCurve.valueAt(out, 1-traveler.getT());
	}

}
