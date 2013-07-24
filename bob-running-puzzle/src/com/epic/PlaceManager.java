package com.epic;

import com.badlogic.gdx.utils.ArrayMap;
import com.epic.Place;
import com.epic.bobrunningpuzzle.model.Gate;

public class PlaceManager {
	/** Class with private constructor is static.*/
	private PlaceManager() {}
	private static ArrayMap<String, Place> map = new ArrayMap<String, Place>();
	private static GenericIdentifier genericIdentifier = new GenericIdentifier();
	
	private static int size1(){return PlaceManager.map.size;}
	private static int size2(String key){return PlaceManager.map.get(key).size();}
	/*public Points() {
		Gdx.app.log("POINT", "TTTTTTTTTEEEEEEEESSSSSSSSSSSSSTTTTTTTTTEEEEEE");
		Gdx.app.log("POINT", "map.size" + map.size);
		for (int i = 0 ; i< map.size; i++) {
			Gdx.app.log("POINT", "map.getKeyAt(i): " + map.getKeyAt(i));
			Gdx.app.log("POINT", "map.getKeyAt(i): " + map.getValueAt(i).getLst().size());
		}
	}*/
	public static Place getPoint(String key){return PlaceManager.map.get(key);}
	
	public static String add(Place point) {
		String aux = PlaceManager.GenericIdentifier.newIdentifier();
		PlaceManager.map.put(aux, point);
		return aux;
	}
	
	public static PlaceIdentifier register(String key, Gate gate) {
		return PlaceManager.getPoint(key).register(gate);
	}
	
	public static class GenericIdentifier {
		private static String NAME = "ID";//"GenericIdentifier";
		public static String newIdentifier() {return "[" + NAME + ":" + map.size + "]";}
		public static String newSubIdentifier(String key) {return "[" + NAME + "_sub:" + PlaceManager.size2(key) + "]";}
		public static String newSubIdentifier(Place point) {return "[" + NAME + "_sub:" + point.size() + "]";}
	}
}
