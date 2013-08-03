package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector2;
import com.epic.bobrunningpuzzle.model.auxiliary.Place;
import com.epic.bobrunningpuzzle.model.serializer.ModelJsonSerializer;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public class BezierCurve extends Road{

	public static final int SEGMENTS = 100;
	
	private Bezier<Vector2> bezierCurve;
	private Vector2 vectorA, vectorB;
	private float curveLength;
	
	
	public static int getSegments() {return SEGMENTS;}
	public float getLength() {return curveLength;}
	public void setLength(float curveLength) {this.curveLength = curveLength;}
	public Bezier<Vector2> getBezierCurve() {return bezierCurve;}
	public Vector2 getVectorA() {return vectorA;}
	public Vector2 getVectorB() {return vectorB;}
	
	/** Used only by de Serializer {@link ModelJsonSerializer}*/
	public BezierCurve() {super();}
	
	/*private BezierCurve(String debugID, final Vector2... points){
		super(debugID);
		this.bezierCurve = new Bezier<Vector2>(points);
	}*/
	public BezierCurve(Place pointA, Vector2 vectorA, Vector2 vectorB, Place pointB, String debugID){
		super(pointA, pointB, debugID);
		this.vectorA = vectorA;
		this.vectorB = vectorB;
		Vector2 pointsAux[] = {this.getGateA().getPosition(),this.getVectorA(), this.getVectorB(), this.getGateB().getPosition()};
		this.bezierCurve = new Bezier<Vector2>(pointsAux);
		this.calculateLength();
	}
	/*public BezierCurve(Vector2 positionA, Vector2 vector, Vector2 positionB, String debugID){
		this(positionA, vector, vector, positionB, debugID);
	}*/
	public BezierCurve(Gate pairGateA, Vector2 vectorA, Vector2 vectorB, Place point, String debugID){
		this(pairGateA.getThisGatePoint(), vectorA, vectorB, point, debugID);
	}
	public BezierCurve(Gate gatePairA, Vector2 vectorA, Vector2 vectorB, Gate gatePairB, String debugID){
		this(gatePairA.getThisGatePoint(), vectorA, vectorB, gatePairB.getThisGatePoint(), debugID);
	}
	
	private void calculateLength(){
		Vector2 aux1 = new Vector2(), aux2 = new Vector2();
		float sum=0;
		aux1.set(getGateA().getPosition());
		for(int i=1;i<=BezierCurve.SEGMENTS;i++ ){
			aux2.set(aux1);
			this.bezierCurve.valueAt(aux1, (float) i/BezierCurve.SEGMENTS);
			sum+=aux2.dst(aux1);
			//LIXO Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#sum= "+ sum + " dst=" +aux2.dst(aux1) + " I=" + (float) i/this.SEGMENTS);
		}
		this.setLength(sum);
		//LIXO Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#CurveLength= "+ this.getCurveLength());
	}
	
	
	@Override
	public void update(float delta) {
		//none
	}

	@Override
	public void updateTraveler(float delta, Traveler traveler) {
		super.updateTraveler(delta, traveler);
	}

	@Override
	public void acceptRendererVisitor(RendererVisitor rendererVisitor) {
		rendererVisitor.draw(this);
		super.acceptRendererVisitor(rendererVisitor);
	}
	@Override
	public void calculateAndUpdatePosition(Traveler traveler, Vector2 out) {
		if(traveler.getEntryGate().equals(this.getGateA()))
			bezierCurve.valueAt(out, traveler.getT());
		else
			bezierCurve.valueAt(out, 1-traveler.getT());
	}

}
