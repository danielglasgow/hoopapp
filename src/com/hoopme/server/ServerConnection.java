package com.hoopme.server;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import com.hoopme.JSON.FromJSONUtility;
import com.hoopme.objects.Court;
import com.hoopme.objects.CourtDetails;

public class ServerConnection implements ServerInterface {
	
	private static final String IP = "129.64.208.136";
	
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
			return (CourtDetails) FromJSONUtility.getCourtDetailsFacotry().fromJSON(json);
		} catch (ClientProtocolException e) {
			Log.e("Get fail", e.toString());
		} catch (IOException e) {
			Log.e("Get fail", e.toString());
		}
		return null;
	}

	@Override
	public List<Court> getCourts() {
		String url = "http://" + IP + ":8080/HoopMe/CourtFinder";
		HttpGet httpget = new HttpGet(url);
		try {
			HttpResponse response = httpclient.execute(httpget);
			String out = new BasicResponseHandler().handleResponse(response);
			JSONArray jsonArray = null;
			try {
				jsonArray = new JSONArray(out);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			Log.d("Server", out);
			return FromJSONUtility.JSONArrayToList(FromJSONUtility.getCourtFacotry(), jsonArray);
		} catch (ClientProtocolException e) {
			Log.e("Get fail", e.toString());
		} catch (IOException e) {
			Log.e("Get fail", e.toString());
		}
		return null;
	}
	
}
