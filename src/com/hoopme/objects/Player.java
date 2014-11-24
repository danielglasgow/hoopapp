package com.hoopme.objects;


import org.json.JSONException;
import org.json.JSONObject;

import com.hoopme.JSON.FromJSON;
import com.hoopme.JSON.ToJSON;

public class Player implements FromJSON, ToJSON {

	private final int id;
	private final String name;
	private String password;
	private String bdate;
	private int skill;
	private String position;
	private String username;
	
	public Player(int id, String name, String bdate, String password, int skill, String position, String username) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.bdate = bdate;
		this.skill = skill;
		this.position = position;
		this.username = username;
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
			json.put("username", username);
			return json;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static FromJSON fromJSON(JSONObject json) {
		try {
			return new Player(json.getInt("id"), json.getString("name"), json.getString("password"), 
					json.getString("bdate"), json.getInt("skill"), json.getString("position"),
					json.getString("username"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public int getSkill() {
		return skill;
	}

	public void setSkill(int skill) {
		this.skill = skill;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getUsername() {
		return username;
	}
}
