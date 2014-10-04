package com.example.helloworld;


import com.google.android.gms.maps.model.LatLng;

public enum Courts {
	GOSMAN(new LatLng(42.365086, -71.254830)), MASSEL(new LatLng(42.366783, -71.260259)), H_LOT(new LatLng(42.366244, -71.262791));
	
	private static final long serialVersionUID = 1L;
	
	public final LatLng location;
	
	private Courts(LatLng location) {
		this.location = location;
	}
}
