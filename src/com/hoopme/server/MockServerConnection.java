package com.hoopme.server;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;
import com.hoopme.objects.Court;
import com.hoopme.objects.CourtDetails;
import com.hoopme.objects.Player;


public class MockServerConnection implements ServerInterface {

	@Override
	public CourtDetails getCourtDetails(int courtId) {
		CourtDetails courtDetails = new CourtDetails("Massel");
		courtDetails.playersAtCourt.add(new Player(1, "dani", "11/5/2014", "password", 5, "position"));
		courtDetails.playersAtCourt.add(new Player(2, "danny", "11/5/2014", "password", 5, "position"));
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
	

}
