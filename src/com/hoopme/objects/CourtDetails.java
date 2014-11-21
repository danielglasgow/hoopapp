package com.hoopme.objects;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hoopme.JSON.FromJSON;
import com.hoopme.JSON.FromJSONFactory;
import com.hoopme.JSON.FromJSONUtility;

public class CourtDetails implements FromJSON {

	private final List<Player> playersAtCourt;
	private final String name;
	
	public CourtDetails(String name, List<Player> playersAtCourt) {
		this.playersAtCourt = playersAtCourt;
		this.name = name;
	}
	
	public CourtDetails(String name) {
		this(name, new ArrayList<Player>());
	}
	
	public static CourtDetails fromJSON(JSONObject json) {
		JSONArray jsonArray = null;
		String name = null;
		try {
			jsonArray = (JSONArray) json.get("players");
			name = json.getString("name");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		FromJSONFactory playerFactory = FromJSONUtility.getPlayerFactory();
		List<Player> players = FromJSONUtility.JSONArrayToList(playerFactory, jsonArray);
		return new CourtDetails(name, players);
		
	}

	public List<Player> getPlayersAtCourt() {
		return playersAtCourt;
	}

	public String getName() {
		return name;
	}
}
