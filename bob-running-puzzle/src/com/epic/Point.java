package com.epic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ArrayMap;
import com.epic.bobrunningpuzzle.BobRunningPuzzle;
import com.epic.bobrunningpuzzle.model.Gate;

public class Point<T> /*implements Vector<Vector2>*/ {
	public static String GENERIC = "GENERIC";
	
	private static ArrayMap<String, Point> map = new ArrayMap<String, Point>();
	

	public Point() {
		Gdx.app.log("POINT", "TTTTTTTTTEEEEEEEESSSSSSSSSSSSSTTTTTTTTTEEEEEE");
		Gdx.app.log("POINT", "map.size" + map.size);
		for (int i = 0 ; i< map.size; i++) {
			Gdx.app.log("POINT", "map.getKeyAt(i): " + map.getKeyAt(i));
			Gdx.app.log("POINT", "map.getKeyAt(i): " + map.getValueAt(i).getLst().size());
		}
	}
	
	
	public static class GenericIdentifier {
		private static Integer id = 0;
		public static String getNewIdentifier() {
			//LIXO Gdx.app.log(BobRunningPuzzle.GAMELOG, "E E E E E EE E E E E E E E E E E E E E E E ");
			String str;
			for(;;id++){
				str = "[GenericIdentifier:" + id.toString() + "]";
				if(!map.containsKey(str)){
					//LIXO Gdx.app.log(BobRunningPuzzle.GAMELOG, "breakbreakbreakbreakbreakbreakbreakbreak");
					break;
				}
			}
			return str;
		}
	}
	public static Point getPoint(String Id){
		return map.get(Id);
	}
	
	private String Id;
	private Vector2 position;
	public String getId() {return Id;}
	public void setId(String Id) {this.Id = Id;}
	public Vector2 getPosition() {return position;}
	public void setPosition(Vector2 v) {this.position = v;}
	private ArrayList<T> lst = new ArrayList<T>();
	
	public ArrayList<T> getLst() {return lst;}
	
	public int sizeOfTheCollection() {return lst.size();}
	
	private void newPoint(String Id, Vector2 v){
		//LIXO Gdx.app.log(BobRunningPuzzle.GAMELOG, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		if(Id.equals(Point.GENERIC))
			this.Id = Point.GenericIdentifier.getNewIdentifier();
		if(Point.map.containsKey(this.getId())){
			try {
				//LIXO Gdx.app.log(BobRunningPuzzle.GAMELOG, "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
				throw new Exception(this.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//LIXO Gdx.app.log(BobRunningPuzzle.GAMELOG, "DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
				e.printStackTrace();
			}
		}
		this.position = v;
		//LIXO Gdx.app.log(BobRunningPuzzle.GAMELOG, "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
		Point.map.put(this.getId(), this);
		//LIXO Gdx.app.log(BobRunningPuzzle.GAMELOG, "GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
	}
	
	public static <T> boolean register(String Id,T t){
		return Point.map.get(Id).register(t);
	}
	public static <T> boolean register(String Id,T t, int index){
		return Point.map.get(Id).register(t, index);
	}
	public boolean register(T gate){
		if(!lst.contains(gate)){
			lst.add(gate);
			return true;
		}else return false;
	}
	public boolean register(T gate, int index){
		if(!lst.contains(gate)){
			lst.add(index, gate);
			return true;
		}else {
			return false;
		}
	}
	public T getOtherT(T notThisT, int index){
		for(int i=0; i < this.lst.size(); i++){
			T el = this.lst.get(i);
			if(!el.equals(notThisT)){
				if(index == 0)
					return el;
				else index--;
			}
		}
		Gdx.app.error("ERROR!!", "EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEERRRRRRRRRRRRRRRRRRRRRRRROOOOOOOOOOOOOOORRRRRRRRRRRRRRRR");
		return null;
	}
	
	public Point(String Id) {
		this.newPoint(Id, new Vector2());
	}
	public Point(String Id,Vector2 v) {
		this.newPoint(Id, v);
	}
	public Point(String Id, float x, float y) {
		this.newPoint(Id, new Vector2(x,y));
	}
	
	
	/*
	@Override
	public Vector2 cpy() {
		return this.v.cpy();
	}
	@Override
	public float len() {
		return this.v.len();
	}
	@Override
	public float len2() {
		return this.v.len2();
	}
	@Override
	public Vector2 limit(float limit) {
		return this.v.limit(limit);
	}
	@Override
	public Vector2 clamp(float min, float max) {
		return this.v.clamp(min, max);
	}
	@Override
	public Vector2 set(Vector2 position) {
		return this.v.set(position);
	}
	@Override
	public Vector2 sub(Vector2 position) {
		return this.v.sub(position);
	}
	@Override
	public Vector2 nor() {
		return this.v.nor();
	}
	@Override
	public Vector2 add(Vector2 position) {
		return this.v.add(position);
	}
	@Override
	public float dot(Vector2 position) {
		return this.v.dot(position);
	}
	@Override
	public Vector2 scl(float scalar) {
		return this.v.scl(scalar);
	}
	@Override
	public Vector2 scl(Vector2 position) {
		return this.v.scl(position);
	}
	@Override
	public float dst(Vector2 position) {
		return this.v. dst(position);
	}
	@Override
	public float dst2(Vector2 position) {
		return this.v.dst2(position);
	}
	@Override
	public Vector2 lerp(Vector2 target, float alpha) {
		return this.v.lerp(target, alpha);
	}*/
}
