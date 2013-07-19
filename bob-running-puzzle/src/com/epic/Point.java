package com.epic;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;

public class Point<T> /*implements Vector<Vector2>*/ {
	private static IdentityHashMap<String, Point> map = new IdentityHashMap<String, Point>();
	
	public static class GenericIdentifier {
		private static Integer id = 0;
		public static String getNewIdentifier() {
			String str;
			for(;;id++){
				str = "[GenericIdentifier:" + id.toString() + "]";
				if(map.containsKey(str))
					break;
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
	
	private void newPoint(String Id, Vector2 v) throws Exception{
		if(this.map.containsKey(Id))
			throw new Exception(Id);
		this.Id = Id;
		this.position = v;
		Point.map.put(Id, this);
	}
	
	private ArrayList<T> lst;
	public static <T> boolean register(String Id,T t){
		return map.get(Id).register(t);
	}
	public static <T> boolean register(String Id,T t, int index){
		return map.get(Id).register(t, index);
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
		}else return false;
	}
	public T getOtherT(T notThisT){
		for (Iterator<T> iterator = this.lst.iterator(); iterator.hasNext();) {
			T el = (T) iterator.next();
			if(!el.equals(notThisT))
				return el;
		}
		return null;
	}
	
	public Point(String Id) throws Exception {
		this.newPoint(Id, new Vector2());
	}
	public Point(String Id,Vector2 v) throws Exception {
		this.newPoint(Id, v);
	}
	public Point(String Id, float x, float y) throws Exception {
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
