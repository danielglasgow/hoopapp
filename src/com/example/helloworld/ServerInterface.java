package com.example.helloworld;

import java.util.List;

public interface ServerInterface {

	public CourtDetails getCourtDetails(int courtId);
	
	public List<Court> getCourts();
	
}
