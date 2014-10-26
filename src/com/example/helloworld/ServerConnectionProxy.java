package com.example.helloworld;

import java.util.ArrayList;
import java.util.List;

public class ServerConnectionProxy implements ServerInterface {

	@Override
	public CourtDetails getCourtDetails(int courtId) {
		CourtDetails courtDetails = new CourtDetails("Massel");
		courtDetails.playersAtCourt.add(new Player(1, "dani"));
		courtDetails.playersAtCourt.add(new Player(2, "danny"));
		return courtDetails;
	}

	@Override
	public List<Court> getCourts() {
		List<Court> courts = new ArrayList<Court>();
		courts.add(new Court("Massel", 1, Courts.MASSEL.location));
		courts.add(new Court("Gosman", 2, Courts.GOSMAN.location));
		courts.add(new Court("H-Lot", 3, Courts.H_LOT.location));
		return courts;
	}

}
