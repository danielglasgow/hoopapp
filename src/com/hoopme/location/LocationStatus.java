package com.hoopme.location;

import java.util.HashMap;
import java.util.Map;

public enum LocationStatus {

	NO_MATCH(203), AT_COURT(202), CHECK_IN(201), USER_ERROR(400), SERVER_ERROR(500), NO_CONNECTION(0);
	
	private final int statusCode;
	
	private LocationStatus(int statusCode) {
		this.statusCode = statusCode;
	}
	
	private static final Map<Integer, LocationStatus> LOCATION_STATUS_MAP = initializelocationStatusMap();

	private static Map<Integer, LocationStatus> initializelocationStatusMap() {
		Map<Integer, LocationStatus> locationStatusMap = new HashMap<Integer, LocationStatus>();
		for (LocationStatus position : LocationStatus.values()) {
			locationStatusMap.put(position.statusCode, position);
		}
		return locationStatusMap;
	}

	public static LocationStatus getLocationStatus(int statusCode) {
		return LOCATION_STATUS_MAP.get(statusCode);
	}
	
}
