package com.hoopme.activity;

import java.util.ArrayList;

import com.hoopme.activity.R;
import com.hoopme.objects.Player;
import com.hoopme.objects.Timeline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CalendarView.OnDateChangeListener;

public class ScheduleFragment extends Fragment{

	private ListView timesListView;
	private ArrayAdapter<String> listAdapter;
	View V;

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    Bundle savedInstanceState) {
		
		Log.d("ScheduleFragment", "Creating view");
		V = inflater.inflate(R.layout.fragment_schedule, container, false);

    	CalendarView calendar = (CalendarView) V.findViewById(R.id.calendar);
    	calendar.setShowWeekNumber(false);
    	Log.d("ScheduleFragment", "setShowWeekNumber: " + calendar.getShowWeekNumber());
    	calendar.setShownWeekCount(1);
    	Log.d("ScheduleFragment", "setShownWeekCount: " + calendar.getShownWeekCount());

    	// Find the ListView resource.  
		Log.d("ScheduleFragment", "Finding list view");
    	timesListView = (ListView) V.findViewById(R.id.timesListView);

    	// TODO: db search for players at court
		Log.d("ScheduleFragment", "Creating timeline");
		//"" + (month+1) + "/" + day + "/" + year
    	Timeline time = new Timeline();
    	
		Log.d("ScheduleFragment", "Populating times list");
    	ArrayList<String> times = new ArrayList<String>();
    	for(int i = 0; i < 24; i++) {
    		times.add(i, "" + i + ":00 - " + time.getNumPlayersAtTime(i) + " players");
    	}
    	
		Log.d("ScheduleFragment", "set listAdapter to times");

    	// Create ArrayAdapter using the times list.   
    	listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.simplerow, times);
    	
    	timesListView.setAdapter(listAdapter);

//    	timesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//    	    public void onItemClick(AdapterView <? > arg0, View view, int position, long id) {
//    	    	// TODO: onClick
//    		}
//    	});
    	
    	calendar.setOnDateChangeListener(new OnDateChangeListener() {
    		public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
    			Toast.makeText(getActivity().getApplicationContext(), (month+1) + "/" + day + "/" + year, Toast.LENGTH_LONG).show();
    			// TODO: make database call for schedule for the day
//    			TextView time_12pm = (TextView) getView().findViewById(R.id.time_12pm);
//    			time_12pm.setText("12:00 PM - " + (day%3 + month%2) + " players");
//    			TextView time_1pm = (TextView) getView().findViewById(R.id.time_1pm);
//    			time_1pm.setText("1:00 PM - " + (day%3+1) + " players");
    			

    		}
    	});
    	
    	
    	
	    // Inflate the layout for this fragment
	    return V;
	}
	
}
