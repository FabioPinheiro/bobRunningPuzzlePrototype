package com.epic.bobrunningpuzzle.model;

import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.epic.Place;
import com.epic.bobrunningpuzzle.model.serializer.ModelJsonSerializer;

public class LevelStructure {
	
	
	private Array<Surmountable> surmountableArray = new Array<Surmountable>();
	private Array<Start> surmountableStartsArray = new Array<Start>();

	public Array<Surmountable> getAllSurmountable() {
		Array<Surmountable> aux =  new Array<Surmountable>(surmountableStartsArray);
		aux.addAll(surmountableArray);
		return aux;
	}
	
	public Array<Surmountable> getSurmountableArray() {return surmountableArray;}
	//public void setSurmountableArray(Array<Surmountable> surmountableArray) {this.surmountableArray = surmountableArray;}
	public Array<Start> getSurmountableStartsArray() {return surmountableStartsArray;}
	public Start getStart(int index) {return surmountableStartsArray.get(index);}
	//public void setSurmountableStartsArray(Array<Start> surmountableStartsArray) {this.surmountableStartsArray = surmountableStartsArray;}


	/** width in Metros*/
	private float width;
	/** height in Metros*/
	private float height;
	/** startTimer mast be biger them 3f.*/
	private float startTimer;
	
	/**@return width in Metros*/
	public float getWidth() {return width;}
	/**@return height in Metros*/
	public float getHeight() {return height;}
	/**@return startTimer in seconds*/
	public float getStartTimer() {return startTimer;}


	/**
	 * @param startTimer mast be biger them 3f.
	 * @param width of the surmountableArray in Metros.
	 * @param height of the surmountableArray in Metros.
	 */
	public void setWindowSizeAndTimer(float startTimer, float width, float height) {
		this.startTimer = startTimer;
		this.width = width;
		this.height = height;
	}

	/** Used only by de Serializer {@link ModelJsonSerializer}*/
	public LevelStructure() {
		this.setWindowSizeAndTimer(6f, 10f, 10f);
		
		Start start = new Start(new Place(0,0));
		this.surmountableStartsArray.add(start);
		
		Road road1= new StraightLine(start.getGate(),new Place(1,1),"pointA");
		Road road2= new StraightLine(road1.getGateB(),new Place(2,-1),"pointB");
		Road road3= new StraightLine(road2.getGateB(),new Place(5,2),"C");
		
		BezierCurve curve1 = new BezierCurve(road3.getGateB(), new Vector2(5,10), new Vector2(2,-5), new Place(2,2), "curve1");
		
		this.surmountableArray.add(road1);
		this.surmountableArray.add(road2);
		this.surmountableArray.add(road3);
		this.surmountableArray.add(curve1);
		
		Junction junctuin1 = new Junction(new Vector2(1, 6), 0.7f, 0f, "l", "l", "l");
		Junction junctuin2 = new Junction(new Vector2(5, 6), 1f, 180f, "l", "H", "h");
		this.surmountableArray.add(junctuin1);
		this.surmountableArray.add(junctuin2);
		
		Road road5= new StraightLine(curve1.getGateB(), junctuin2.getGateB(),"road5");
		Road road6= new StraightLine(junctuin2.getGateC(), junctuin1.getGateC(),"road6");
		this.surmountableArray.add(road5);
		this.surmountableArray.add(road6);
		
		Goal goal1 = new Goal(junctuin1.getGateA());
		this.surmountableArray.add(goal1);
	}
	


	public String debugString() {
		String newString = new String();
		Iterator<Start> it1 =  this.surmountableStartsArray.iterator();
		while(it1.hasNext()){
			Start el = it1.next();
			newString += el.debugString() +"\n" ;
		}
		Iterator<Surmountable> it2 =  this.surmountableArray.iterator();
		while(it2.hasNext()){
			Surmountable el = it2.next();
			newString += el.debugString() +"\n" ;
		}
		return newString;
	};
}
