package com.hoopme.activity;

import com.hoopme.activity.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent i = getIntent();
		String username = i.getStringExtra("com.hoopme.username");
		TextView usernameView = (TextView) findViewById(R.id.username_disp);
		usernameView.setText("Username: " + username);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//Test change
		getMenuInflater().inflate(R.menu.main, menu); //0 probably should not be 0
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
	

	public void findCourts(View view) {
		Intent intent = new Intent(this, MapActivity.class);
		startActivity(intent);
		
	}
	
	public void addNewCourt(View view) {
		// TODO: make a new class for dropping a pin on the map and storing it as a new court
		//Intent intent = new Intent(this, MapActivity.class);
		//startActivity(intent);		
	}
	

}
