package com.example.helloworld;

import java.util.ArrayList;
import com.hoopme.activity.R;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
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
		String username = i.getStringExtra("com.example.helloworld.username");
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
		List<Courts> courts = new ArrayList<Courts>();
		courts.add(Courts.GOSMAN);
		courts.add(Courts.H_LOT);
		courts.add(Courts.MASSEL);
		intent.putExtra("com.example.helloworld.courts", new CourtsTransferObject(courts));
		//intent.putExtra("com.example.helloworld.courts", getNearbyCourts();
		startActivity(intent);
		
	}
	

}
