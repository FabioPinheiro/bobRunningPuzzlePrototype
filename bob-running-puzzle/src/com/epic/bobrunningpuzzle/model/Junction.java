package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.epic.Point;
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
	private float radius, angle;
	private BezierCurve curveAB, curveBC, curveAC;
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
		this.angle = angle;
		
		this.gateType1 = gateType1;
		this.gateType2 = gateType2;
		this.gateType3 = gateType3;
		
		Point<Gate> vecAux1 = new Point<Gate>(Point.GENERIC, -radius, 0);
		Point<Gate> vecAux2 = new Point<Gate>(Point.GENERIC, 0, +radius);
		Point<Gate> vecAux3 = new Point<Gate>(Point.GENERIC, +radius, 0);
		vecAux1.getPosition().rotate(angle);
		vecAux2.getPosition().rotate(angle);
		vecAux3.getPosition().rotate(angle);
		vecAux1.getPosition().add(center);
		vecAux2.getPosition().add(center);
		vecAux3.getPosition().add(center);
		
		Point<Gate> vecAux1Cpy = new Point<Gate>(Point.GENERIC, vecAux1.getPosition());
		Point<Gate> vecAux2Cpy = new Point<Gate>(Point.GENERIC, vecAux2.getPosition());
		Point<Gate> vecAux3Cpy = new Point<Gate>(Point.GENERIC, vecAux3.getPosition());
		

		curveAB = new BezierCurve(vecAux1Cpy, vecAux1.getPosition().cpy().lerp(center, 0.5f), vecAux2.getPosition().cpy().lerp(center, 0.5f), vecAux2Cpy, "curveAB");
		curveBC = new BezierCurve(vecAux2Cpy, vecAux2.getPosition().cpy().lerp(center, 0.5f), vecAux3.getPosition().cpy().lerp(center, 0.5f), vecAux3Cpy, "curveBC");
		curveAC = new BezierCurve(vecAux1Cpy, vecAux1.getPosition().cpy().lerp(center, 0.5f), vecAux3.getPosition().cpy().lerp(center, 0.5f), vecAux3Cpy, "curveAC");
		
		roadA = new Road(vecAux1, curveAB.getGateA().getPoint(), curveAC.getGateA().getPoint(), "roadA");
		roadB = new Road(vecAux2, curveBC.getGateA().getPoint(), curveAB.getGateB().getPoint(), "roadB");
		roadC = new Road(vecAux3, curveAC.getGateB().getPoint(), curveBC.getGateB().getPoint(), "roadC");
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
		curveAC.acceptRendererVisitor(rendererVisitor);
	}
	
	public GateType getGateType1() {return gateType1;}
	public GateType getGateType2() {return gateType2;}
	public GateType getGateType3() {return gateType3;}
	public Gate getGateA() {return roadA.getGateA();}
	public Gate getGateB() {return roadB.getGateA();}
	public Gate getGateC() {return roadC.getGateA();}
	public Vector2 getCenter() {return center;}
	public float getRadius() {return radius;}
	
	
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
