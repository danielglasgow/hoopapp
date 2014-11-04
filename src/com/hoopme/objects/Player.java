package com.hoopme.objects;

import org.json.JSONException;
import org.json.JSONObject;

import com.hoopme.JSON.FromJSON;

public class Player implements FromJSON {

	public final int id;
	public final String name;
	
	public Player(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public static FromJSON fromJSON(JSONObject json) {
		try {
			return new Player(json.getInt("id"), json.getString("name"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
