package com.example.helloworld;

import com.google.android.gms.maps.model.LatLng;


public class Court {
	
	public final String name;
	public final int id;
	public final LatLng location;
	
	public Court(String name, int id, LatLng location) {
		this.name = name;
		this.id = id;
		this.location = location;
	}
}
