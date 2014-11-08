package com.hoopme.objects;

import java.util.ArrayList;

import android.util.Log;

public class Timeline {

	ArrayList<ArrayList<String>> playersAtTimes;
	
	public Timeline() {
		// TODO: DB call to fill array for the date
		playersAtTimes = new ArrayList<ArrayList<String>>(24);
		for(int i = 0; i < 24; i++) {
			playersAtTimes.add(null);
		}
		// To test
		ArrayList<String> players3 = new ArrayList<String>();
		players3.add("Daniel");
		players3.add("Dani");
		players3.add("Justin");
		ArrayList<String> players7 = new ArrayList<String>();
		players7.add("Peter");
		players7.add("Ronald");
		Log.d("Schedule", "3");
		playersAtTimes.set(3, players3);
		Log.d("Schedule", "7");
		playersAtTimes.set(7, players7);
	}
	
	public ArrayList<String> getPlayersAtTime(int hour) {
		return playersAtTimes.get(hour);
	}
	
	public int getNumPlayersAtTime(int hour) {
		if(playersAtTimes.get(hour) == null) {
			return 0;
		} else {
			return playersAtTimes.get(hour).size();
		}
	}
	
	
}
