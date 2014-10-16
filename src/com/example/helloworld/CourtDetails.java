package com.example.helloworld;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class CourtDetails extends ActionBarActivity {

	private ListView playerListView;  
	private ArrayAdapter<String> listAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_court_details);
		
		Intent i = getIntent();
		String courtId = i.getStringExtra("com.example.helloworld.courts");
		// TODO: db call for court name
		String courtName = courtId;
		TextView courtNameView = (TextView) findViewById(R.id.courtName);
		courtNameView.setText(courtName);
		
		// Find the ListView resource.   
	    playerListView = (ListView) findViewById(R.id.playerListView);
	    
		// TODO: db search for players at court
	    // Create and populate list
		ArrayList<String> players = new ArrayList<String>();
		players.add("Donald Glasgow");
		players.add("Tim Hickey");
		players.add("Justin Barash");
	  
	    // Create ArrayAdapter using the planet list.   
	    listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, players);  
	      
	    listAdapter.add("Karishma Pradhan");
	      
	    playerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView <? > arg0, View view, int position, long id) {
        		Intent intent = new Intent(CourtDetails.this, PlayerProfile.class);
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
