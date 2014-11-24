package com.hoopme.objects;

import android.util.Log;

public class Time {

	
	public static final Time AM_12 = new Time(0, 0);
	public static final Time AM_01 = new Time(1, 0);
	public static final Time AM_02 = new Time(2, 0);
	public static final Time AM_03 = new Time(3, 0);
	public static final Time AM_04 = new Time(4, 0);
	public static final Time AM_05 = new Time(5, 0);
	public static final Time AM_06 = new Time(6, 0);
	public static final Time AM_07 = new Time(7, 0);
	public static final Time AM_08 = new Time(8, 0);
	public static final Time AM_09 = new Time(9, 0);
	public static final Time AM_10 = new Time(10, 0);
	public static final Time AM_11 = new Time(11, 0);
	public static final Time PM_01 = new Time(12, 0);
	public static final Time PM_02 = new Time(13, 0);
	public static final Time PM_03 = new Time(14, 0);
	public static final Time PM_04 = new Time(15, 0);
	public static final Time PM_05 = new Time(16, 0);
	public static final Time PM_06 = new Time(17, 0);
	public static final Time PM_07 = new Time(18, 0);
	public static final Time PM_08 = new Time(19, 0);
	public static final Time PM_09 = new Time(20, 0);
	public static final Time PM_10 = new Time(21, 0);
	public static final Time PM_11 = new Time(22, 0);
	public static final Time PM_12 = new Time(23, 0);
	
	private final int hour;
	private final int minute;
	
	public Time(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}

	public static Time fromString(String time) {
		return new Time(Integer.parseInt(time), 0);
	}
	
	@Override
	public boolean equals(Object obj) {
		Log.d("Schedule", "equality");
		if (obj == this) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (!(obj instanceof Time)) {
			return false;
		} else {
			Log.d("Schedule", "4th condition");
			Time t = (Time) obj;
			return (t.getHour() == getHour() && t.getMinute() == getMinute());
		}
	}
	
	public int hashCode() {
		return 1000 + getHour()*100 + getMinute();
	}
	
	public String toString() {
		int hour = this.getHour();
		int minute = this.getMinute();
		String minuteString = Integer.toString(minute);
		String ampm = "";
		if (this.getMinute() < 10) {
			minuteString += "0";
		}
		if (hour == 0) {
			hour = 12;
		}
		if (hour < 12) {
			ampm = "AM";
		} else {
			if (hour != 12) {
				hour -= 12;
			}
			ampm = "PM";
		}
		String hourString = Integer.toString(minute);
		return hourString + ":" + minuteString + " " + ampm;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}
	
}
