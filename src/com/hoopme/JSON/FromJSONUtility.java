package com.hoopme.JSON;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hoopme.objects.Court;
import com.hoopme.objects.CourtDetails;
import com.hoopme.objects.Player;

public class FromJSONUtility {

	public static <T> List<T> JSONArrayToList(FromJSONFactory factory, JSONArray jsonArray) {
		List<FromJSON> list = new ArrayList<FromJSON>();
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				list.add(factory.fromJSON(jsonArray.getJSONObject(i)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return castList(list);
	}
	
	private static <T> List<T> castList(List<FromJSON> fromJSONList) {
		List<T> list = new ArrayList<T>();
		for (FromJSON e : fromJSONList) {
			@SuppressWarnings("unchecked")
			T t = (T) e;
			list.add(t);
		}
		return list;
	}
	
	public static FromJSONFactory getPlayerFactory() {
		return new FromJSONFactory() {
			@Override
			public FromJSON fromJSON(JSONObject json) {
				return Player.fromJSON(json);
			}	
		};
	}
	
	public static FromJSONFactory getCourtDetailsFacotry() {
		return new FromJSONFactory() {
			@Override
			public FromJSON fromJSON(JSONObject json) {
				return CourtDetails.fromJSON(json);
			}	
		};
	}

	public static FromJSONFactory getCourtFacotry() {
		return new FromJSONFactory() {
			@Override
			public FromJSON fromJSON(JSONObject json) {
				return Court.fromJSON(json);
			}	
		};
	}
	
}
