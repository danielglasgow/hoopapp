package com.hoopme.server;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.hoopme.location.LocationStatus;
import com.hoopme.logic.Position;
import com.hoopme.objects.Court;
import com.hoopme.objects.CourtDetails;
import com.hoopme.objects.PlayerDetails;
import com.hoopme.objects.PlayerView;
import com.hoopme.objects.Time;
import com.hoopme.objects.Timeline;


public class MockServerConnection implements ServerInterface {

	private static final PlayerDetails AVERAGE_JOE1 = new PlayerDetails(-1, "username_joe1", "Joe1 Average", "password", new DateTime(),  5, Position.POINT_GAURD);
	private static final PlayerDetails AVERAGE_JOE2 = new PlayerDetails(-2, "username_jo2", "Joe2, Average", "passowrd", new DateTime(), 5, Position.SHOOTING_GAURD);
	
	@Override
	public CourtDetails getCourtDetails(int courtId) {
		List<PlayerView> playersAtCourt = new ArrayList<PlayerView>();
		playersAtCourt.add(new PlayerView(AVERAGE_JOE1));
		playersAtCourt.add(new PlayerView(AVERAGE_JOE2));
		CourtDetails courtDetails = new CourtDetails("Massel", playersAtCourt);
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
		PlayerView p1 = new PlayerView(AVERAGE_JOE1);
		PlayerView p2 = new PlayerView(AVERAGE_JOE2);
		List<PlayerView> players = new ArrayList<PlayerView>();
		players.add(p1);
		players.add(p2);
		timeline.addAtTime(Time.AM_12 , players);
		timeline.addAtTime(Time.AM_03, players);
		return timeline;
	}

	@Override
	public boolean createProfile(PlayerDetails player) {
		return false;
	}

	@Override
	public int getNewPlayerId() {
		return -1;
	}

	@Override
	public boolean updateTimeline(Timeline timeline, DateTime dat, int courtId) {
		return false;
	}

	@Override
	public PlayerDetails getPlayerDetails(int playerId) {
		if (playerId == -1) {
			return AVERAGE_JOE1;
		}
		return AVERAGE_JOE2;
	}

	@Override
	public boolean checkIn(int playerId, int courtId, double durationHours) {
		return false;
	}

	@Override
	public LocationStatus updateLocation(LatLng latlng, int playerId) {
		return LocationStatus.NO_CONNECTION;
	}

	@Override
	public boolean validateLogin(String username, String password) {
		return true;
	}
	

}
