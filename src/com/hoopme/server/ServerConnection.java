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

import com.hoopme.JSON.FromJSONUtility;
import com.hoopme.objects.Court;
import com.hoopme.objects.CourtDetails;
import com.hoopme.objects.Player;
import com.hoopme.objects.Timeline;

public class ServerConnection implements ServerInterface {
	
	private static final String IP = "129.64.181.83";
	
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
		JSONObject json = doHttpRequest(url);
		if (json != null) {
			return (CourtDetails) FromJSONUtility.getCourtDetailsFacotry().fromJSON(json);
		}
		return null;
	}

	@Override
	public List<Court> getCourts() {
		String url = "http://" + IP + ":8080/HoopMe/CourtFinder";
		JSONObject json = doHttpRequest(url);
		if (json != null) {
			try {
				return FromJSONUtility.JSONArrayToList(FromJSONUtility.getCourtFacotry(), json.getJSONArray("courts"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
			return null;
	}
	

	public Timeline getTimeline(DateTime date, int courtId) {
		// TODO: FIX URL
		String url = "http://" + IP + ":8080/HoopMe/Schedule?id=" + courtId + "date=" + date.toString();
		JSONObject json = doHttpRequest(url);
		if (json != null) {
			return (Timeline) FromJSONUtility.getTimelineFactory().fromJSON(json);
		}
		return null;
	}
	
	@Override
	public boolean createProfile(Player player) {
		String url = "http://" + IP + ":8080/HoopMe/CreatePlayer";
		return postData(url, player.toJSON());	
	}
	
	@Override
	public int getNewPlayerId() {
		String url = "http://" + IP + ":8080/HoopMe/CreatePlayer";
		JSONObject json = doHttpRequest(url);
		if (json != null) {
			try {
				return json.getInt("id");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return -1;
	} 
		
	private JSONObject doHttpRequest(String url) {
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
	
	
	private boolean postData(String url, JSONObject json) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(url);
	    try {
	        StringEntity params =new StringEntity(json.toString());
	        httppost.addHeader("content-type", "application/x-www-form-urlencoded");
	        httppost.setEntity(params);
	        HttpResponse response = httpclient.execute(httppost);
	        return response.getStatusLine().getStatusCode() == 400;
	        
	    } catch (ClientProtocolException e) {
	        Log.e("Post fail", e.toString());
	    } catch (IOException e) {
	    	 Log.e("Post fail", e.toString());
	    }
	    return false;
	}



	
}
