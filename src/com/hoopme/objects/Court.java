package com.hoopme.objects;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.model.LatLng;
import com.hoopme.JSON.FromJSON;


public class Court implements FromJSON {
	
	public final String name;
	public final int id;
	public final LatLng location;
	
	public Court(String name, int id, LatLng location) {
		this.name = name;
		this.id = id;
		this.location = location;
	}
	
	public static Court fromJSON(JSONObject json) {
		try {
			LatLng latlng = new LatLng(json.getDouble("lat"), json.getDouble("lng"));
			return new Court(json.getString("name"), json.getInt("id"), latlng);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
