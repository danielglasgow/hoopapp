package com.hoopme.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hoopme.JSON.FromJSON;
import com.hoopme.JSON.FromJSONFactory;
import com.hoopme.JSON.FromJSONUtility;

import android.util.Log;

public class Timeline implements FromJSON{

	private final Map<Time, List<PlayerView>> times;
	private final DateTime date;
	private final int courtId;
	
	private static final String TIMELINE_KEY = "timeline";
	
	public Timeline(DateTime date, int courtId) {
		times = new HashMap<Time, List<PlayerView>>();
		this.date = date;
		this.courtId = courtId;
	}
	
	public List<PlayerView> getPlayersAtTime(Time time) {
		return times.get(time);
	}
	
	public int getNumberPlayersAtTime(Time time) {
		if(times.get(time) == null) {
			Log.d("Schedule", "" + time.getHour() +  " 0");
			return 0;
		} else {
			return times.get(time).size();
		}
	}
	
	private void addTimes(List<PlayersAtTime> playersAtTime) {
		for (PlayersAtTime players : playersAtTime) {
			times.put(players.time, players.players);
		}
	}
	
	public Map<Time, List<PlayerView>> getTimes() {
		return this.times;
	}
	
	public void addAtTime(Time time, List<PlayerView> players) {
		if (this.getTimes().containsKey(time)) {
			for (PlayerView player : players) {
				this.getTimes().get(time).add(player);
			}
		} else {
			this.getTimes().put(time, players);
		}

	}

	
	public static final class PlayersAtTime implements FromJSON {
		public final List<PlayerView> players;
		public final Time time;
		
		private PlayersAtTime(List<PlayerView> players, Time time) {
			this.players = players;
			this.time = time;
		}
		
		public static FromJSON fromJSON(JSONObject json) {
			try {
				Time time = Time.fromString(json.getString("time"));
				FromJSONFactory factory = FromJSONUtility.getPlayerViewFactory();
				List<PlayerView> players = FromJSONUtility.JSONArrayToList(factory, json.getJSONArray("players"));
				return new PlayersAtTime(players, time);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public List<PlayerView> getPlayers() {
			return this.players;
		}
		
		public Time getTime() {
			return this.time;
		}
	}

	public static FromJSON fromJSON(JSONObject json) {
		try {
			DateTime date = DateTime.parse(json.getString("date"));
			int courtId = json.getInt("courtId");
			JSONArray times = json.getJSONArray(TIMELINE_KEY);
			FromJSONFactory factory = FromJSONUtility.getPlayersAtTimeFactory();
			List<PlayersAtTime> playersAtTime = FromJSONUtility.JSONArrayToList(factory,times);
			Timeline timeline = new Timeline(date, courtId);
			timeline.addTimes(playersAtTime);
			return timeline;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

    public String toString(){
    	String res = "";
    	for(Time time : this.getTimes().keySet()) {
            res += "[" + time+"==>"+ this.getTimes().get(time).size() + "]";
        }
		return res;
    }
	
	
}
