package com.hoopme.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
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

public class CourtDetailsPage extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_court_details);
	    
        // Create a new Fragment to be placed in the activity layout
        Fragment courtDetailsFragment = new CourtDetailsFragment();
        Fragment scheduleFragment = new ScheduleFragment();
        
        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments
        // fragment.setArguments(getIntent().getExtras());
        FragmentTransaction transaction = 
                getSupportFragmentManager().beginTransaction();

        // Add the fragment to the 'fragment_container' FrameLayout
        transaction.add(R.id.court_details_container, courtDetailsFragment);
        transaction.add(R.id.schedule_container, scheduleFragment);     
        transaction.commit();
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
