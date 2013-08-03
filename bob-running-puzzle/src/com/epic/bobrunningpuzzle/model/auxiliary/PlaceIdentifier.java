package com.epic.bobrunningpuzzle.model.auxiliary;

import com.epic.bobrunningpuzzle.model.serializer.ModelJsonSerializer;

public class PlaceIdentifier {
	private String key;
	private String subKey;
	
	/** Used only by de Serializer {@link ModelJsonSerializer}*/
	public PlaceIdentifier() {}
	public PlaceIdentifier(String primary, String extension) {
		this.key = primary;
		this.subKey = extension;
	}
	public String getFullKey() {return key + "&" + subKey;}
	public String getKey() {return key;}
	public String getsubKey() {return subKey;}
}
