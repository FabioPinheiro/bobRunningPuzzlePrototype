package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.epic.bobrunningpuzzle.model.auxiliary.Place;
import com.epic.bobrunningpuzzle.model.serializer.ModelJsonSerializer;
import com.epic.bobrunningpuzzle.view.RendererVisitor;

public class Junction extends Surmountable{

	/**
	 * @author Fábio Pinheiro
	 * GateType will be used to define the way forward for this entry {@link Gate}
	 * l and L is for lower gate number
	 * h and H is for higher gate number
	 * uppercase - can NOT change the way
	 * lowercase - can change the way
	 */
	public enum GateType {
		l, h, L, H
	}
	
	private GateType gateType1, gateType2, gateType3;
	private Vector2 center;
	private float radius;
	private BezierCurve curveAB, curveBC, curveCA;
	private Road roadA, roadB, roadC;
	
	
	/** Used only by de Serializer {@link ModelJsonSerializer}*/
	public Junction() {super();}
	
	/**
	 * @param center center of the object
	 * @param radius of the gate to the center
	 * @param angle is important to determine the position of the gates
	 * @param gateType1 in String ("l";"L";"h";"H")
	 * @param gateType2 in String ("l";"L";"h";"H")
	 * @param gateType3 in String ("l";"L";"h";"H")
	 */
	public Junction(Vector2 center, float radius, float angle, String gateType1, String gateType2, String gateType3) {
		this(center, radius, angle,
				Junction.GateType.valueOf(gateType1),
				Junction.GateType.valueOf(gateType2),
				Junction.GateType.valueOf(gateType3));
	}
	
	
	/**
	 * @param center center of the object
	 * @param radius of the gate to the center
	 * @param angle is important to determine the position of the gates
	 * @param gateType1 in {@link Junction.GateType}
	 * @param gateType2 in {@link Junction.GateType}
	 * @param gateType3 in {@link Junction.GateType}
	 */
	public Junction(Vector2 center, float radius, float angle, GateType gateType1, GateType gateType2, GateType gateType3) {
		this.center = center;
		this.radius = radius;
		
		this.gateType1 = gateType1;
		this.gateType2 = gateType2;
		this.gateType3 = gateType3;
		
		Place vecAux1 = new Place(-radius, 0);
		Place vecAux2 = new Place(0, +radius);
		Place vecAux3 = new Place(+radius, 0);
		
		vecAux1.getPosition().rotate(angle);
		vecAux2.getPosition().rotate(angle);
		vecAux3.getPosition().rotate(angle);
		vecAux1.getPosition().add(center);
		vecAux2.getPosition().add(center);
		vecAux3.getPosition().add(center);
		
		Place vecAuxA = new Place(vecAux1.getPosition());
		Place vecAuxB = new Place(vecAux2.getPosition());
		Place vecAuxC = new Place(vecAux3.getPosition());
		
		this.roadA = new Road(vecAux1, vecAuxA, "roadA");
		this.roadB = new Road(vecAux2, vecAuxB, "roadB");
		this.roadC = new Road(vecAux3, vecAuxC, "roadC");

		this.curveAB = new BezierCurve(vecAuxA, vecAux1.getPosition().cpy().lerp(center, 0.5f), vecAux2.getPosition().cpy().lerp(center, 0.5f), vecAuxB, "curveAB");
		this.curveBC = new BezierCurve(vecAuxB, vecAux2.getPosition().cpy().lerp(center, 0.5f), vecAux3.getPosition().cpy().lerp(center, 0.5f), vecAuxC, "curveBC");
		this.curveCA = new BezierCurve(vecAuxC, vecAux1.getPosition().cpy().lerp(center, 0.5f), vecAux3.getPosition().cpy().lerp(center, 0.5f), vecAuxA, "curveAC");
		
	}
	
	@Override
	public void update(float delta) {
		//none
	}

	@Override
	public void updateTraveler(float delta, Traveler traveler) {
		Gdx.app.error("ERROR!!",  this.getClass().getName()+"#updateTraveler- nunca devia chegar aqui!!!!");
	}

	@Override
	public void calculateAndUpdatePosition(Traveler traveler, Vector2 out) {
		Gdx.app.error("ERROR!!",  this.getClass().getName()+"#calculateAndUpdatePosition- nunca devia chegar aqui!!!!");
	}

	@Override
	public void acceptRendererVisitor(RendererVisitor rendererVisitor) {
		rendererVisitor.draw(this);
		curveAB.acceptRendererVisitor(rendererVisitor);
		curveBC.acceptRendererVisitor(rendererVisitor);
		curveCA.acceptRendererVisitor(rendererVisitor);
	}
	
	public GateType getGateType1() {return gateType1;}
	public GateType getGateType2() {return gateType2;}
	public GateType getGateType3() {return gateType3;}
	public Gate getGateA() {return roadA.getGateA();}
	public Gate getGateB() {return roadB.getGateA();}
	public Gate getGateC() {return roadC.getGateA();}
	public Vector2 getCenter() {return center;}
	public float getRadius() {return radius;}
	
	
	@SuppressWarnings("unused")//FIXME
	private GateType convertStringInGateType(String srt){
		//Implementation in JDK 7
		/*switch (srt) {
		case "l": return Junction.GateType.l; break;
		case "L": return Junction.GateType.L; break;
		case "h": return Junction.GateType.h; break;
		case "H": return Junction.GateType.H; break;
		default:
			Gdx.app.error("ERROR!!", this.getClass().getName()+"#convertStringInGateType: default:srt=\"" + "\"");
			return Junction.GateType.l;
			break;
		}*/
		
		//Before JDK 7     FABIO-I dont have JDK 7 sry!!!!
		return Junction.GateType.valueOf(srt); //XD nice
	}
}
