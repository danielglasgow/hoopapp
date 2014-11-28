package com.hoopme.objects;


import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;

import com.hoopme.JSON.FromJSON;
import com.hoopme.JSON.ToJSON;
import com.hoopme.logic.Position;

public class PlayerDetails implements FromJSON, ToJSON {

	private final int id;
	private final String name;
	private String password;
	private DateTime birthday;
	private int skill;
	private Position position;
	private String username;
	
	public PlayerDetails(int id, String username, String name, String password,  DateTime birthday, int skill, Position position) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.birthday = birthday;
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
			json.put("birthday", birthday);
			json.put("skill", skill);
			json.put("position", position.abbreviation);
			json.put("username", username);
			return json;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static FromJSON fromJSON(JSONObject json) {
		try {
			return new PlayerDetails(json.getInt("id"), json.getString("username"), json.getString("name"), json.getString("password"), 
					new DateTime(json.getString("birthday")), json.getInt("skill"), Position.getPosition(json.getString("position")));
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

	public DateTime getBdate() {
		return birthday;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = Position.getPosition(position);
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
