package com.hoopme.objects;


import org.json.JSONException;
import org.json.JSONObject;

import com.hoopme.JSON.FromJSON;
import com.hoopme.JSON.ToJSON;

public class Player implements FromJSON, ToJSON {

	public final int id;
	public final String name;
	public String password;
	public String bdate;
	public int skill;
	public String position;
	
	public Player(int id, String name, String bdate, String password, int skill, String position) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.bdate = bdate;
		this.skill = skill;
		this.position = position;
	}
	
	@Override
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("id", id);
			json.put("name", name);
			json.put("password", password);
			json.put("bdate", bdate);
			json.put("skill", skill);
			json.put("position", position);
			return json;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static FromJSON fromJSON(JSONObject json) {
		try {
			return new Player(json.getInt("id"), json.getString("name"), json.getString("password"), 
					json.getString("bdate"), json.getInt("skill"), json.getString("position"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
