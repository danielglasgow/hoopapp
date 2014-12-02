package com.hoopme.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hoopme.activity.R;
import com.hoopme.objects.CourtDetails;
import com.hoopme.objects.PlayerDetails;
import com.hoopme.objects.PlayerView;
import com.hoopme.server.ServerConnectionProxy;
import com.hoopme.server.ServerInterface;

public class CourtDetailsPage extends ActionBarActivity {
	
	private int courtId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
    	Log.d("CourtDetails", "Creating view");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_court_details);
	    
		Log.d("CourtDetails", "Receiving intents");
    	Intent intent = getIntent();
    	courtId = intent.getIntExtra("com.hoopme.courtId", 0);

    	Log.d("CourtDetails", "Getting court details from server");
    	ServerInterface serverConnection = ServerConnectionProxy.getInstance();
    	CourtDetails courtDetails = serverConnection.getCourtDetails(courtId);

    	Log.d("CourtDetails", "Populating court name view");
    	TextView courtNameView = (TextView) findViewById(R.id.courtName);
    	courtNameView.setText(courtDetails.getName());
    	
    	Log.d("CourtDetails", "Populating players label");
       	TextView players_label = (TextView) findViewById(R.id.players_label);
       	List<PlayerView> players = courtDetails.getPlayersAtCourt();
       	String playersString = "";
       	if (players.size() == 0) {
       		playersString = "No players currently at court";
       	} else if (players.size() == 1) {
       		playersString = players.get(0).getName() + " is currently at court";
       	} else if (players.size() == 2) {
       		playersString = players.get(0).getName() + " and " +
       				players.get(1).getName() + " are currently at court";
       	} else {
       		playersString = players.get(0).getName() + ", " + players.get(1).getName() + 
       				" and " + players.size() + " other users are currently at court";
       	}
    	players_label.setText(playersString);
       	
       	// Clicking on players label bring to playersAtCourt activity
    	Log.d("CourtDetails", "Setting clickable for players label");
    	players_label.setOnClickListener(new View.OnClickListener() {
    	    @Override
    	    public void onClick(View v) {
    	    	// TODO: Link to correct page
    	    	Intent intent = new Intent(CourtDetailsPage.this, PlayersAtCourt.class);
    	    	intent.putExtra("com.hoopme.courtId", courtId);
    	    	startActivity(intent);
    	    }
    	});

    	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.court_details, menu);
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
		} else if (id == R.id.action_home) {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onViewSchedule(View view) {
		Intent intent = new Intent(this, CourtSchedule.class);
		intent.putExtra("com.hoopme.courtId", courtId);
		startActivity(intent);
	}
	
	public void onSubscribe(View view) {
		// TODO: Post call - update DB information
	}
}
