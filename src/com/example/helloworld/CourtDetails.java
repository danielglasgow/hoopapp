package com.example.helloworld;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CourtDetails extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_court_details);
		Intent i = getIntent();
		String courtId = i.getStringExtra("com.example.helloworld.courtdetail");
		
		// TODO: db call for court name
		String courtName = courtId;
		
		// Create text view
		TextView courtNameView = (TextView) findViewById(R.id.courtName);
		courtNameView.setText(courtName);
		
		// TODO: db search for players at court
		ArrayList<String> playersAtCourt = new ArrayList<String>();
		playersAtCourt.add("Donald Glasgow");
		playersAtCourt.add("Tim Hickey");
		playersAtCourt.add("Justin Ballsack");
		String players = "";
		
//		for(String player : playersAtCourt) {
//			players += player + "\n";
//		}
//		
//		TextView playersView = (TextView) findViewById(R.id.playerList);
//		playersView.setText(players);
		
		LinearLayout rl = (LinearLayout) findViewById(R.id.court_layout);
		
		// TODO: players need to be clickable to view profile
		for(String player : playersAtCourt) {
			TextView temp = new TextView(this);
			temp.setText(player);
			temp.setClickable(true);
			temp.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(CourtDetails.this, PlayerProfile.class);
					intent.putExtra("com.example.helloworld.playername", ((TextView) view).getText());
					startActivity(intent);
				}
			});
			rl.addView(temp);
		}
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
