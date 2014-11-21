package com.hoopme.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hoopme.activity.R;
import com.hoopme.objects.CourtDetails;
import com.hoopme.objects.Player;
import com.hoopme.server.ServerConnectionProxy;
import com.hoopme.server.ServerInterface;

public class CourtDetailsFragment extends Fragment {

	private ListView playerListView;  
	private ArrayAdapter<String> listAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	Log.d("CourtDetailsFragment", "Creating view");

        View V =  inflater.inflate(R.layout.fragment_court_details, null);

        // TODO: get court name from activity
//    	Intent i = getIntent();
//    	int courtId = Integer.parseInt(i.getStringExtra("com.example.helloworld.courts"));
    	int courtId = 1;
    	ServerInterface serverConnection = ServerConnectionProxy.getInstance();
    	CourtDetails courtDetails = serverConnection.getCourtDetails(courtId);
    	// TODO: db call for court name
    	
    	Log.d("CourtDetailsFragment", "Populating court name");

    	String courtName = courtDetails.getName();
    	TextView courtNameView = (TextView) V.findViewById(R.id.courtName);
    	courtNameView.setText(courtName);
    	
    	Log.d("CourtDetailsFragment", "Populating times list");

    	// Find the ListView resource.   
    	playerListView = (ListView) V.findViewById(R.id.playerListView);

    	
    	// TODO: db search for players at court
    	// Create and populate list
    	ArrayList<String> players = new ArrayList<String>();
    	for (Player player : courtDetails.getPlayersAtCourt()) {
    		players.add(player.getName());
    	}
    	
    	TextView players_label = (TextView) V.findViewById(R.id.players_label);
    	players_label.setText("" + players.size() + " users currently at court:");

    	// Create ArrayAdapter using the planet list.   
    	listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.simplerow, players);
    	  
    	playerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    	    public void onItemClick(AdapterView <? > arg0, View view, int position, long id) {
    			Intent intent = new Intent(getActivity(), PlayerProfile.class);
    			intent.putExtra("com.example.helloworld.playername", ((TextView) view).getText());
    			startActivity(intent);
    		}
    	});
    	
    	// Set the ArrayAdapter as the ListView's adapter.
    	playerListView.setAdapter(listAdapter);
    	
    	return V;
    }    
}

