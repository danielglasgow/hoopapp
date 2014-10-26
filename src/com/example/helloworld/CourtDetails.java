package com.example.helloworld;

import java.util.ArrayList;
import java.util.List;

public class CourtDetails {

	public final List<Player> playersAtCourt;
	public final String name;
	
	public CourtDetails(String name, List<Player> playersAtCourt) {
		this.playersAtCourt = playersAtCourt;
		this.name = name;
	}
	
	public CourtDetails(String name) {
		this(name, new ArrayList<Player>());
	}
}
