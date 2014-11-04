package com.hoopme.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hoopme.activity.R;
import com.hoopme.objects.CourtDetails;
import com.hoopme.objects.Player;
import com.hoopme.server.ServerConnectionProxy;
import com.hoopme.server.ServerInterface;

public class CourtDetailsPage extends ActionBarActivity {

	private ListView playerListView;  
	private ArrayAdapter<String> listAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_court_details);
		
		Intent i = getIntent();
		int courtId = Integer.parseInt(i.getStringExtra("com.example.helloworld.courts"));
		ServerInterface serverConnection = ServerConnectionProxy.getInstance();
		CourtDetails courtDetails = serverConnection.getCourtDetails(courtId);
		// TODO: db call for court name
		String courtName = courtDetails.name;
		TextView courtNameView = (TextView) findViewById(R.id.courtName);
		courtNameView.setText(courtName);
		
		// Find the ListView resource.   
	    playerListView = (ListView) findViewById(R.id.playerListView);
	    
		// TODO: db search for players at court
	    // Create and populate list
		ArrayList<String> players = new ArrayList<String>();
		for (Player player : courtDetails.playersAtCourt) {
			players.add(player.name);
		}
	  
	    // Create ArrayAdapter using the planet list.   
	    listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, players);
	      
	    playerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView <? > arg0, View view, int position, long id) {
        		Intent intent = new Intent(CourtDetailsPage.this, PlayerProfile.class);
        		intent.putExtra("com.example.helloworld.playername", ((TextView) view).getText());
        		startActivity(intent);
        	}
        });
	    
	    // Set the ArrayAdapter as the ListView's adapter.
	    playerListView.setAdapter(listAdapter);
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
		}
		return super.onOptionsItemSelected(item);
	}
}
