package com.example.helloworld;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.StrictMode;
import android.util.Log;

public class ServerConnection implements ServerInterface {

	@Override
	public CourtDetails getCourtDetails(int courtId) {
		String ip = "129.64.181.63";
		String url = "http://" + ip + ":8080/HoopMe/CourtDetails?id=" + courtId;
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		
		HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			String out = new BasicResponseHandler().handleResponse(response);
			Log.d("Server", out);
		} catch (ClientProtocolException e) {
			Log.e("Get fail", e.toString());
		} catch (IOException e) {
			Log.e("Get fail", e.toString());
		}
		
		// TODO: implement response parsing
		ServerInterface proxy = new ServerConnectionProxy();
		return proxy.getCourtDetails(courtId);

	}

	@Override
	public List<Court> getCourts() {
		
		// TODO: Remove proxy and write real connection like above
		ServerInterface proxy = new ServerConnectionProxy();
		return proxy.getCourts();
	}
	
	public String readStream(InputStream in) {
		Log.d("Server", "reading stream");
		BufferedReader rd = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while((line = rd.readLine()) != null) {
				sb.append(line + "\n");
				System.out.println(line);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String json = sb.toString();
		Log.d("test", "this is a test");
		return json;
		
	}

}
