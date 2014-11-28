package com.hoopme.objects;

import org.json.JSONException;
import org.json.JSONObject;

import com.hoopme.JSON.FromJSON;

public class PlayerView implements FromJSON { 
	
	private final int id;
	private final String username;
	private final String name;
	
	public PlayerView(int id, String username, String name) {
		this.id = id;
		this.username = username;
		this.name = name;
	}
	
	public PlayerView(PlayerDetails player) {
		this(player.getId(), player.getUsername(), player.getName());
	}

	public static FromJSON fromJSON(JSONObject json) {
		try {
			int id = json.getInt("id");
			String name = json.getString("name");
			String username = json.getString("username");
			return new PlayerView(id, username, name);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getName() {
		return name;
	}	
}
