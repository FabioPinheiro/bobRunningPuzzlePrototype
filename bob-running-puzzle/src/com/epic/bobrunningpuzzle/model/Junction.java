package com.epic.bobrunningpuzzle.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
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
	
	private final GateType gateType1, gateType2, gateType3;
	private final Gate gate1, gate2, gate3;
	private final Vector2 center;
	private final float radius, angle;
	
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
		this.gate1 = new Gate(this, new Vector2(center.x-radius, center.y+0));//FIXME angle is important to determine the position of the gates
		this.gate2 = new Gate(this, new Vector2(center.x+0, center.y+radius));//FIXME angle is important to determine the position of the gates
		this.gate3 = new Gate(this, new Vector2(center.x+radius, center.y+0));//FIXME angle is important to determine the position of the gates
	}
	
	@Override
	public void update(float delta) {
		//none
	}

	@Override
	public void updateBob(float delta, Bob bob) {
		// TODO Auto-generated method stub
	}

	@Override
	public void acceptRendererVisitor(RendererVisitor rendererVisitor) {
		rendererVisitor.draw(this);
		gate1.acceptRendererVisitor(rendererVisitor);
		gate2.acceptRendererVisitor(rendererVisitor);
		gate3.acceptRendererVisitor(rendererVisitor);
	}
	
	public GateType getGateType1() {return gateType1;}
	public GateType getGateType2() {return gateType2;}
	public GateType getGateType3() {return gateType3;}
	public Gate getGate1() {return gate1;}
	public Gate getGate2() {return gate2;}
	public Gate getGate3() {return gate3;}
	public Vector2 getCenter() {return center;}
	public float getRadius() {return radius;}
}
