package com.hoopme.logic;

import java.util.HashMap;
import java.util.Map;

public enum Position {
	CENTER("Center", "C"), SMALL_FORWARD("Small Forward", "SF"), POWER_FORWARD("Power Forward", "PF"), 
	SHOOTING_GAURD("Shooting Gaurd", "SG"), POINT_GAURD("Point Gaurd", "PG");

	public final String name;
	public final String abbreviation;
	
	private Position(String name, String abbreviation) {
		this.name = name;
		this.abbreviation = abbreviation;
	}
	
	private static final Map<String, Position> POSITION_MAP = initializePositionMap();

	private static Map<String, Position> initializePositionMap() {
		Map<String, Position> positionMap = new HashMap<String, Position>();
		for (Position position : Position.values()) {
			positionMap.put(position.abbreviation, position);
			positionMap.put(position.name, position);
		}
		return positionMap;
	}

	public static Position getPosition(String position) {
		return POSITION_MAP.get(position);
	}
}
