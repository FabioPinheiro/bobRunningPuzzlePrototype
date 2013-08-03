package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.epic.bobrunningpuzzle.model.auxiliary.Place;
import com.epic.bobrunningpuzzle.model.serializer.ModelJsonSerializer;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public class BezierCurve extends Road{
	
	public class BezierAux {
		private Bezier<Vector2> bezierCurve;
		private float curveLength;
		
		public Bezier<Vector2> getBezierCurve() {return bezierCurve;}
		public float getLength() {return curveLength;}
		private void setLength(float curveLength) {this.curveLength = curveLength;}
		
		public BezierAux(final Vector2... points) {
			this.bezierCurve = new Bezier<Vector2>(points);
			calculateLength();
			curveTotalLength += getLength();
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
	}
	
	public static final int SEGMENTS = 100;
	
	private Array<BezierAux> bezierArray = new Array<BezierCurve.BezierAux>();
	private float curveTotalLength;
	
	public static int getSegments() {return SEGMENTS;}
	public Array<BezierAux> getBezierArray() {return bezierArray;}
	public float getCurveTotalLength() {return curveTotalLength;}
	public float getLength() {return this.getCurveTotalLength();}
	//public Bezier<Vector2> getBezierCurve() {return bezierCurve;}
	
	/** Used only by de Serializer {@link ModelJsonSerializer}*/
	public BezierCurve() {super();}
	
	/*private BezierCurve(String debugID, final Vector2... points){
		super(debugID);
		this.bezierCurve = new Bezier<Vector2>(points);
	}*/
	public BezierCurve(Place pointA, Vector2 vectorA, Vector2 vectorB, Place pointB, String debugID){
		super(pointA, pointB, debugID);
		Vector2 pointsAux[] = {this.getGateA().getPosition(),vectorA, vectorB, this.getGateB().getPosition()};
		this.bezierArray.add(new BezierAux(pointsAux));
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
	
	/**
	 * Constructor for multiple curves
	 * @param pointA FIXME the position of this point must be equal to the positons of the first vectors element
	 * @param vectors must be multiplo of 4 {@link Vector2}, by the  respective order, starting point, direction of departure, direction of arrival, arrival point
	 * @param pointB FIXME the position of this point must be equal to the positons of the last vectors element
	 * @param debugID
	 */
	public BezierCurve(Place pointA, Array<Vector2> vectors, Place pointB, String debugID){
		super(pointA, pointB, debugID);
		if(!(vectors.size % 4 == 0) || !vectors.first().equals(pointA.getPosition()) || !vectors.get(vectors.size-1).equals(pointB.getPosition()))
			throw new RuntimeException();
		for(int index = 0; index < vectors.size; index +=4){
			Vector2 pointsAux[] = {vectors.get(index), vectors.get(index+1), vectors.get(index+2) ,vectors.get(index+3)};
			this.bezierArray.add(new BezierAux(pointsAux));
		}
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
		float tAux = traveler.getT();
		for(int index = 0; index< bezierArray.size; index++){
			if(traveler.getEntryGate().equals(this.getGateA())){
					if(tAux > bezierArray.get(index).getLength())
						tAux -= bezierArray.get(index).getLength() / this.getCurveTotalLength();
					else bezierArray.get(index).getBezierCurve().valueAt(out, tAux);
			}
			else{
					if(tAux > bezierArray.get(1-index).getLength())
						tAux -= bezierArray.get(1-index).getLength() / this.getCurveTotalLength();
					else bezierArray.get(1-index).getBezierCurve().valueAt(out, 1-tAux);
			}
		}
	}

}
