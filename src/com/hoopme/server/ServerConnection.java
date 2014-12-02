package com.hoopme.server;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.hoopme.JSON.FromJSONFactory;
import com.hoopme.JSON.FromJSONUtility;
import com.hoopme.location.LocationStatus;
import com.hoopme.objects.Court;
import com.hoopme.objects.CourtDetails;
import com.hoopme.objects.PlayerDetails;
import com.hoopme.objects.Timeline;

public class ServerConnection implements ServerInterface {
	
	private static final String IP = "129.64.181.48";
	
	private final HttpClient httpclient;
	
	@SuppressLint("NewApi")
	public ServerConnection() {
		this.httpclient = new DefaultHttpClient();
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
	}
	

	@Override
	public CourtDetails getCourtDetails(int courtId) {
		String url = "http://" + IP + ":8080/HoopMe/CourtDetails?id=" + courtId;
		Log.d("Server", url);
		JSONObject json = doHttpGetRequest(url);
		if (json != null) {
			return (CourtDetails) FromJSONUtility.getCourtDetailsFacotry().fromJSON(json);
		}
		return null;
	}

	@Override
	public List<Court> getCourts() {
		String url = "http://" + IP + ":8080/HoopMe/CourtFinder";
		JSONObject json = doHttpGetRequest(url);
		if (json != null) {
			try {
				return FromJSONUtility.JSONArrayToList(FromJSONUtility.getCourtFacotry(), json.getJSONArray("courts"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
			return null;
	}
	
	@Override
	public Timeline getTimeline(DateTime date, int courtId) {
		// TODO: FIX URL
		String url = "http://" + IP + ":8080/HoopMe/Schedule?id=" + courtId + "date=" + date.toString();
		JSONObject json = doHttpGetRequest(url);
		if (json != null) {
			return (Timeline) FromJSONUtility.getTimelineFactory().fromJSON(json);
		}
		return null;
	}
	
	@Override
	public boolean createProfile(PlayerDetails player) {
		String url = "http://" + IP + ":8080/HoopMe/PlayerCreator";
		int status = doHttpPostRequest(url, player.toJSON());
		return status < 400;	
	}
	
	@Override
	public int getNewPlayerId() {
		String url = "http://" + IP + ":8080/HoopMe/PlayerCreator";
		JSONObject json = doHttpGetRequest(url);
		if (json != null) {
			try {
				return json.getInt("id");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return -1;
	} 
	
	@Override
	public boolean updateTimeline(Timeline timeline, DateTime dat, int courtId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean validateLogin(String username, String password) {
		String url = "http://" + IP + ":8080/HoopMe/ValidateUser?username=" + username + "&password=" + password;
		HttpGet httpget = new HttpGet(url);
			try {
				HttpResponse response = httpclient.execute(httpget);
				int validLogin = 201;
				return response.getStatusLine().getStatusCode() == validLogin;
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return false;
	}


	@Override
	public PlayerDetails getPlayerDetails(int playerId) {
		String url = "http://" + IP + ":8080/HoopMe/PlayerDetails?id=" + playerId;
		JSONObject json = doHttpGetRequest(url);
		if (json != null) {
			return (PlayerDetails) FromJSONUtility.getPlayerDetailsFactory().fromJSON(json);
		}
		return null;
	}


	@Override
	public boolean checkIn(int playerId, int courtId, double durationHours) {
		LocationStatus status = updateLocation(null, playerId, courtId, durationHours);
		return status == LocationStatus.CHECK_IN;
	}


	@Override
	public LocationStatus updateLocation(LatLng latlng, int playerId) {
		return updateLocation(latlng, playerId, -1, 0);
	}
	
	private LocationStatus updateLocation(LatLng latlng, int playerId, int courtId, double durationHours) {
		JSONObject json = new JSONObject();
		try {
			if (latlng == null) {
				json.put("lat", 1000);
				json.put("lng", 1000);
			} else {
				json.put("lat", latlng.latitude);
				json.put("lng", latlng.longitude);
			}
			json.put("playerId", playerId);
			json.put("courtId", courtId);
			json.put("duration", durationHours);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String url = "http://" + IP + ":8080/HoopMe/LocationUpdater";
		int statusCode = doHttpPostRequest(url, json);
		return LocationStatus.getLocationStatus(statusCode);
		
	}
	
	@Override
	public int getId(String username) {
		String url = "http://" + IP + ":8080/HoopMe/PlayerDetails?username=" + username;
		JSONObject json = doHttpGetRequest(url);
		try {
			return json.getInt("username");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return -1;
	}
		
	private JSONObject doHttpGetRequest(String url) {
		HttpGet httpget = new HttpGet(url);
		try {
			HttpResponse response = httpclient.execute(httpget);
			String out = new BasicResponseHandler().handleResponse(response);
			JSONObject json = null;
			try {
				json = new JSONObject(out);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			Log.d("Server", out);
			return json;
		} catch (ClientProtocolException e) {
			Log.e("Get fail", e.toString());
		} catch (IOException e) {
			Log.e("Get fail", e.toString());
		}
		return null;
	}
	
	
	
	
	private int doHttpPostRequest(String url, JSONObject json) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(url);
	    try {
	        StringEntity params =new StringEntity(json.toString());
	        httppost.addHeader("content-type", "application/x-www-form-urlencoded");
	        httppost.setEntity(params);
	        HttpResponse response = httpclient.execute(httppost);
	        return response.getStatusLine().getStatusCode();
	        
	    } catch (ClientProtocolException e) {
	        Log.e("Post fail", e.toString());
	    } catch (IOException e) {
	    	 Log.e("Post fail", e.toString());
	    }
	    return 400;
	}


	







	
}
