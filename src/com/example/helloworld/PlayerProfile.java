package com.example.helloworld;

import java.util.ArrayList;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PlayerProfile extends ActionBarActivity {

	private ListView courtListView;  
	private ArrayAdapter<String> listAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_profile);
		
		// Set title equal to name
		Intent i = getIntent();
		String pName = i.getStringExtra("com.example.helloworld.playername");
		TextView textView = (TextView) findViewById(R.id.pname);
		textView.setText(pName);
		
		// Find the ListView resource.   
	    courtListView = (ListView) findViewById(R.id.courtListView);  
	  
	    // Create and populate list
		ArrayList<String> courts = new ArrayList<String>();
		courts.add("Massell");
		courts.add("Gosman");
		courts.add("H-lot");
	    
	    // Create ArrayAdapter using the planet list.   
	    listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, courts);  
	      
	    listAdapter.add("Example Court");
	    
	    courtListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView <? > arg0, View view, int position, long id) {
        		Intent intent = new Intent(PlayerProfile.this, CourtDetailsPage.class);
        		intent.putExtra("com.example.helloworld.courts", ((TextView) view).getText());
        		startActivity(intent);
        	}
        });
	    
	    // Set the ArrayAdapter as the ListView's adapter.  
	    courtListView.setAdapter(listAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.player_profile, menu);
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
