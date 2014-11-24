package com.hoopme.server;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.hoopme.objects.Court;
import com.hoopme.objects.CourtDetails;
import com.hoopme.objects.Player;
import com.hoopme.objects.PlayerView;
import com.hoopme.objects.Time;
import com.hoopme.objects.Timeline;


public class MockServerConnection implements ServerInterface {

	@Override
	public CourtDetails getCourtDetails(int courtId) {
		CourtDetails courtDetails = new CourtDetails("Massel");
		courtDetails.getPlayersAtCourt().add(new Player(1, "dani", "11/5/2014", "password", 5, "position"));
		courtDetails.getPlayersAtCourt().add(new Player(2, "danny", "11/5/2014", "password", 5, "position"));
		return courtDetails;
	}

	@Override
	public List<Court> getCourts() {
		List<Court> courts = new ArrayList<Court>();
		courts.add(new Court("Massel", 1, new LatLng(42.366783, -71.260259)));
		courts.add(new Court("Gosman", 2, new LatLng(42.365086, -71.254830)));
		courts.add(new Court("H-Lot", 3, new LatLng(42.366244, -71.262791)));
		return courts;
	}

	@Override
	public Timeline getTimeline(DateTime date, int courtId) {
		Log.d("Schedule", "Creating mock schedule");
		Timeline timeline = new Timeline(date, courtId);
		PlayerView p1 = new PlayerView(0, "Justin");
		PlayerView p2 = new PlayerView(1, "Daniel");
		List<PlayerView> players = new ArrayList<PlayerView>();
		players.add(p1);
		players.add(p2);
		timeline.addAtTime(Time.AM_12 , players);
		timeline.addAtTime(Time.AM_03, players);
		return timeline;
	}

	@Override
	public boolean createProfile(Player player) {
		return false;
	}

	@Override
	public int getNewPlayerId() {
		return -1;
	}
	

}
