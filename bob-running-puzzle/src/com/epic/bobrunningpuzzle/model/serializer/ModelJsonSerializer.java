package com.epic.bobrunningpuzzle.model.serializer;


import java.io.File;
import java.io.FileNotFoundException;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ArrayMap.Entries;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.epic.Place;
import com.epic.PlaceManager;
import com.epic.PlaceManager.GenericIdentifier;
import com.epic.bobrunningpuzzle.model.Gate;
import com.epic.bobrunningpuzzle.model.LevelStructure;
/**
 * 
 * @author Fábio Pinheiro
 */
public class ModelJsonSerializer {
	//Alley BezierCurve Bob Gate Goal Junction Level Road Start StraightLine Surmountable Traveler
	
	public static String VERSTION = "VERSTION:0.0.10"; //FIXME use this
	
	public Json json;
	
	
	public static void main(String[] args) throws FileNotFoundException {
		//ModelJsonSerializer x = new ModelJsonSerializer();
		System.out.println("test ModelJsonSerializer");
		//FileHandle file = Gdx.files.internal("ui/generalSkin.json");
		File file = new File("E:/git/bobRunningPuzzle/bob-running-puzzle-android/assets/levels/level1.json");
		System.out.println(file.getAbsolutePath());
		System.out.println("file.exists()" + file.exists());
		System.out.println("file.canRead()" + file.canRead());
//		try (BufferedReader in = new BufferedReader(new FileReader(strFilename))) {
//			String inLine;
//			while ((inLine = in.readLine()) != null) {  // exclude newline
//				System.out.println(inLine);
//			}
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
		
		
		//Alley alley = new Alley(new Place(1,2), 0f);
		//StraightLine road1= new StraightLine(new Place(1,1),new Place(2,2),"pointA");
		//System.out.println(x.json.prettyPrint(road1));
		//System.out.println("###################### toJson");
		//String str = x.json.toJson(new LevelStructure(), Object.class);
		//System.out.println(str);
		//System.out.println(x.json.prettyPrint(new LevelStructure()));
		//PlaceManager.getMap().clear();
		//System.out.println(x.json.prettyPrint(road1));
//		System.out.println("###################### fromJson 1");
//		PlaceManager aux = x.json.fromJson(PlaceManager.class, str);
//		System.out.println("###################### fromJson 2");
//		System.out.println("A-"+ x.json.toJson(aux));
//		System.out.println("B-"+ aux.getClass().getName());
//		System.out.println("C-"+ x.json.prettyPrint(aux));
		
	}
	
	public ModelJsonSerializer() {
		this.json = new Json();
		setSerializaser(json);
	}
	
	
	private void setSerializaser(Json json) {
		json.setOutputType(com.badlogic.gdx.utils.JsonWriter.OutputType.minimal);
		json.setSerializer(PlaceManager.class, new Json.Serializer<PlaceManager>(){
			@Override
			public void write(Json json, PlaceManager object, @SuppressWarnings("rawtypes") Class knownType) {
				json.writeObjectStart();
					json.writeType(PlaceManager.class);
					json.writeValue("map.size", PlaceManager.sizeOfMap());
					json.writeValue("genericIdentifier.NAME", GenericIdentifier.getNAME());
					//json.writeType(PlaceManager.getMap().entries().getClass());
					json.writeArrayStart("MAP");
						Entries<String, Place> entries = PlaceManager.getMap().entries();
						for (com.badlogic.gdx.utils.ObjectMap.Entry<String, Place> entry : entries) {
							json.writeValue(entry);
						}
					json.writeArrayEnd();
				json.writeObjectEnd();
			}
			@Override
			public PlaceManager read(Json json, JsonValue jsonData, @SuppressWarnings("rawtypes") Class type) {
				PlaceManager.GenericIdentifier.setNAME(json.readValue("genericIdentifier.NAME", String.class , jsonData));
				JsonValue jsonValueAux = jsonData.getChild("MAP");
				ArrayMap<String, Place> mapAux = new ArrayMap<String, Place>();
				while (jsonValueAux != null){
					String key = json.readValue("value", String.class, jsonValueAux.get("key"));
					Place value = json.readValue(Place.class, jsonValueAux.get("value"));
					mapAux.put(key, value);
					jsonValueAux= jsonValueAux.next();
				}
				PlaceManager.setMap(mapAux);
				return new PlaceManager();
			}
		});
		
		
		json.setSerializer(Place.class, new Json.Serializer<Place>(){
			@Override
			public void write(Json json, Place object, @SuppressWarnings("rawtypes") Class knownType) {
				json.writeObjectStart();
				json.writeType(Place.class);
				json.writeField(object, "key");
				json.writeField(object, "position");
				json.writeValue("collection.size", object.size());
				//json.writeField(object, "collection");
				json.writeObjectEnd();
			}
			@Override
			public Place read(Json json, JsonValue jsonData, @SuppressWarnings("rawtypes") Class type) {
				Place object = new Place();
				json.readField(object, "key", jsonData);
				json.readField(object, "position", jsonData);
				int capacity= json.readValue("collection.size",int.class, jsonData);
				object.setCollection(new ArrayMap<String, Gate>(capacity));
				//json.readField(object, "collection", jsonData);
				return object;
			}
		});
		
		
		json.setSerializer(Vector2.class, new Json.Serializer<Vector2>() {
			public void write(Json json, Vector2 object, @SuppressWarnings("rawtypes") Class knownType) {
				json.writeObjectStart();
				json.writeValue("x", object.x);
				json.writeValue("y", object.y);
				json.writeObjectEnd();
			};
			public Vector2 read(Json json, com.badlogic.gdx.utils.JsonValue jsonData, @SuppressWarnings("rawtypes") Class type) {
				float x = json.readValue("x", float.class , jsonData);
				float y = json.readValue("y", float.class , jsonData);
				return new Vector2(x,y);
			};
		});

		json.setSerializer(Gate.class, new Json.Serializer<Gate>(){
			@Override
			public void write(Json json, Gate object, @SuppressWarnings("rawtypes") Class knownType) {
				json.writeObjectStart();
				json.writeField(object, "identifier");
				json.writeObjectEnd();
			}
			@Override
			public Gate read(Json json, JsonValue jsonData, @SuppressWarnings("rawtypes") Class type) {
				Gate object = new Gate();
				json.readField(object, "identifier", jsonData);
				return object;
			}
		});
		
		json.setSerializer(LevelStructure.class, new Json.Serializer<LevelStructure>(){
			@Override
			public void write(Json json, LevelStructure object, @SuppressWarnings("rawtypes") Class knownType) {
				json.writeObjectStart();
				json.writeType(object.getClass());
				json.writeValue("ModelJsonSerializer.VERSTION", ModelJsonSerializer.VERSTION);
				json.writeValue("PlaceManager", new PlaceManager());
				json.writeFields(object);
				json.writeObjectEnd();
			}
			@Override
			public LevelStructure read(Json json, JsonValue jsonData, @SuppressWarnings("rawtypes") Class type) {
				LevelStructure object = new LevelStructure();
				String verstionAux = json.readValue("ModelJsonSerializer.VERSTION", String.class, jsonData);
				if (ModelJsonSerializer.VERSTION != verstionAux)
					throw new RuntimeException("ModelJsonSerializer.VERSTION this:" +ModelJsonSerializer.VERSTION + " != json:" +verstionAux);//FIXME make a new CLASS
				json.readValue("PlaceManager", PlaceManager.class, jsonData);
				json.readFields(object, jsonData);
				return object;
			}
		});
	}
}