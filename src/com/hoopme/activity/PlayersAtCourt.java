package com.hoopme.activity;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.hoopme.objects.CourtDetails;
import com.hoopme.objects.Player;
import com.hoopme.server.ServerConnectionProxy;
import com.hoopme.server.ServerInterface;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PlayersAtCourt extends ActionBarActivity {

	private CourtDetails courtDetails;
	private ArrayAdapter<String> listAdapter;
	private ListView playerListView;
	private int courtId;
	private DateTime dateTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_players_at_court);
		
		Log.d("PlayersAtCourt", "Receiving intents");
    	Intent intent = getIntent();
    	courtId = intent.getIntExtra("com.hoopme.courtId", 0);
		
		Log.d("PlayersAtCourt", "Getting courtName view");
    	// String courtName = courtDetails.getName();
    	TextView courtNameView = (TextView) findViewById(R.id.courtName);
    	
		Log.d("PlayersAtCourt", "Populating courtDetails from server");
    	ServerInterface serverConnection = ServerConnectionProxy.getInstance();
    	courtDetails = serverConnection.getCourtDetails(courtId);
    	courtNameView.setText(courtDetails.getName());

    	
		Log.d("PlayersAtCourt", "Populating list of names from courtDetails");
    	// Find the ListView resource.   
    	playerListView = (ListView) findViewById(R.id.playerListView);
    	
    	// Convert from list of players to list of string
    	List<String> players = new ArrayList<String>();
    	for (Player player : courtDetails.getPlayersAtCourt()) {
    		players.add(player.getName());
    	}
    	
		Log.d("PlayersAtCourt", "Populating listView");
    	listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, players);
    	playerListView.setAdapter(listAdapter);
    	
		Log.d("PlayersAtCourt", "Setting onItemClick for listView");
    	playerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    	    public void onItemClick(AdapterView <? > arg0, View view, int position, long id) {
    			Intent intent = new Intent(PlayersAtCourt.this, PlayerProfile.class);
    			intent.putExtra("com.example.helloworld.playername", ((TextView) view).getText());
    			startActivity(intent);
    		}
    	});
    	
    	// Set the ArrayAdapter as the ListView's adapter.

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.players_at_court, menu);
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
}
