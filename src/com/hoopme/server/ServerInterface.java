package com.hoopme.server;

import java.util.List;

import org.joda.time.DateTime;

import com.google.android.gms.maps.model.LatLng;
import com.hoopme.location.LocationStatus;
import com.hoopme.objects.Court;
import com.hoopme.objects.CourtDetails;
import com.hoopme.objects.PlayerDetails;
import com.hoopme.objects.Timeline;

public interface ServerInterface {

	public CourtDetails getCourtDetails(int courtId);
	
	public List<Court> getCourts();
	
	public Timeline getTimeline(DateTime date, int courtId);
	
	public boolean updateTimeline(Timeline timeline, DateTime dat, int courtId);
	
	public boolean createProfile(PlayerDetails player);
	
	public int getNewPlayerId();
	
	public PlayerDetails getPlayerDetails(int playerId);
	
	public boolean checkIn(int playerId, int courtId, double durationHours);
	
	public LocationStatus updateLocation(LatLng latlng, int playerId);
	
	public boolean validateLogin(String username, String password);
	
	public int getId(String username);
	
	
	
}
