package com.hoopme.server;

import java.util.List;

import org.joda.time.DateTime;

import com.hoopme.objects.Court;
import com.hoopme.objects.CourtDetails;
import com.hoopme.objects.Player;
import com.hoopme.objects.Timeline;

public interface ServerInterface {

	public CourtDetails getCourtDetails(int courtId);
	
	public List<Court> getCourts();
	
	public Timeline getTimeline(DateTime date, int courtId);
	
	public boolean createProfile(Player player);
	
	public int getNewPlayerId();
	
}
