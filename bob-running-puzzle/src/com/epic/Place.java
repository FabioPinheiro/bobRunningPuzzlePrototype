package com.epic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ArrayMap;
import com.epic.bobrunningpuzzle.model.Gate;

public class Place{
	private Vector2 position;
	private String key;
	ArrayMap<String, Gate> collection = new ArrayMap<String, Gate>();
	
	public Vector2 getPosition() {return position;}
	public int size(){ return this.collection.size;}
	public String getKey() {return this.key;}
	public Gate getGate(int index) {return this.collection.getValueAt(index);}
	public PlaceIdentifier register(Gate gate) {
		String aux = PlaceManager.GenericIdentifier.newSubIdentifier(this);
		this.collection.put(aux, gate);
		return new PlaceIdentifier(this.key, aux);
	}
	
	
	private void newPoint(Vector2 v){
		this.position = v;
		this.key = PlaceManager.add(this);
	}
	
	public Place() {
		this.newPoint(new Vector2());
	}
	public Place(Vector2 v) {
		this.newPoint( v.cpy());
	}
	public Place(float x, float y) {
		this.newPoint(new Vector2(x,y));
	}
	
	public Gate getOtherGateIndex(Gate notThisGate, int index){
		for(int i=0; i < this.size(); i++){
			Gate el = this.getGate(i);
			if(!el.equals(notThisGate)){
				if(index == 0)
					return el;
				else index--;
			}
		}
		Gdx.app.error("ERROR!!", "EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEERRRRRRRRRRRRRRRRRRRRRRRROOOOOOOOOOOOOOORRRRRRRRRRRRRRRR");
		return null;
	}
}
