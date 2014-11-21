package com.hoopme.activity;


import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hoopme.activity.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hoopme.objects.Court;
import com.hoopme.server.ServerConnectionProxy;

public class MapActivity extends FragmentActivity implements
GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener,
OnMarkerClickListener{

	   /* Define a request code to send to Google Play services
	     * This code is returned in Activity.onActivityResult
	     */
	    private final static int
	            CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
	    private LocationClient mLocationClient;
	    private GoogleMap mMap;
	    private Map<Marker, Court> markerToCourt;
	
	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_map);
		MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
		this.mMap = mapFragment.getMap();
		this.mLocationClient= new LocationClient(this, this, this);
		markerToCourt = new HashMap<Marker, Court>();
		for (Court court : ServerConnectionProxy.getInstance().getCourts()) {
			Marker marker = mMap.addMarker(new MarkerOptions()
	        .position(court.location)
	        .title(court.name));
			if (marker != null && court != null) {
				markerToCourt.put(marker, court);
			}
		}
		mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
	        @Override
	        public void onInfoWindowClick(Marker marker) {
	        	Court court = markerToCourt.get(marker);
	        	courtClicked(court);
	        }
	    });
		
	}


	
	 @Override
	    protected void onStart() {
	        super.onStart();
	        // Connect the client.
	        mLocationClient.connect();
	    }
	 
	 @Override
	    protected void onStop() {
	        // Disconnecting the client invalidates it.
	        mLocationClient.disconnect();
	        super.onStop();
	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		/*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (result.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                result.startResolutionForResult(
                        this,
                        CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
            Log.e("Connection Error", "" + result.getErrorCode());
        }
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		// Display the connection status
        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
        Location currentLocation = mLocationClient.getLastLocation();
        
        while (currentLocation == null) {
        	mLocationClient.requestLocationUpdates(new LocationRequest(),
        		    new LocationListener(){

        	    @Override
        	    public void onLocationChanged(Location location) {
        	        //Some code
        	    }
        	});
        	currentLocation = mLocationClient.getLastLocation();
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), 12));
		//mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Courts.GOSMAN, 12));
	}

	@Override
	public void onDisconnected() {
		// Display the connection status
        Toast.makeText(this, "Disconnected. Please re-connect.",
                Toast.LENGTH_SHORT).show();
		
	}
	
    /*
     * Handle results returned to the FragmentActivity
     * by Google Play services
     */
    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        // Decide what to do based on the original request code
        switch (requestCode) {
           //add functionality
            case CONNECTION_FAILURE_RESOLUTION_REQUEST :
            /*
             * If the result code is Activity.RESULT_OK, try
             * to connect again
             */
                switch (resultCode) {
                    case Activity.RESULT_OK :
                    /*
                     * Try the request again
                     */
                    //add functionality
                    break;
                }
            //add functionality
        }
     }
	
	// Define a DialogFragment that displays the error dialog
    public static class ErrorDialogFragment extends DialogFragment {
        // Global field to contain the error dialog
        private Dialog mDialog;
        // Default constructor. Sets the dialog field to null
        public ErrorDialogFragment() {
            super();
            mDialog = null;
        }
        // Set the dialog to display
        public void setDialog(Dialog dialog) {
            mDialog = dialog;
        }
        // Return a Dialog to the DialogFragment.
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return mDialog;
        }
    }
    
    
	
	private void courtClicked(Court court) {
		Intent intent = new Intent(this, CourtDetailsPage.class);
		intent.putExtra("com.hoopme.activity.courtId", "" + court.id);
		startActivity(intent);
	}



	@Override
	public boolean onMarkerClick(Marker marker) {
		return false;
	}
}
