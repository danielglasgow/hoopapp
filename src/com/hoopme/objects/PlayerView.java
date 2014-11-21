package com.hoopme.objects;

import org.json.JSONException;
import org.json.JSONObject;

import com.hoopme.JSON.FromJSON;

public class PlayerView implements FromJSON { 
	
	public final int id;
	public final String name;
	
	public PlayerView(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public static FromJSON fromJSON(JSONObject json) {
		try {
			int id = json.getInt("id");
			String name = json.getString("name");
			return new PlayerView(id, name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}	
}
