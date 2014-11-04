package com.hoopme.server;

import java.util.List;

import com.hoopme.objects.Court;
import com.hoopme.objects.CourtDetails;

public interface ServerInterface {

	public CourtDetails getCourtDetails(int courtId);
	
	public List<Court> getCourts();
	
}
