package com.hoopme.activity;

import java.util.ArrayList;
import java.util.Calendar;

import org.joda.time.DateTime;

import com.hoopme.activity.R;
import com.hoopme.objects.CourtDetails;
import com.hoopme.objects.PlayerDetails;
import com.hoopme.objects.Time;
import com.hoopme.objects.Timeline;
import com.hoopme.server.ServerConnectionProxy;
import com.hoopme.server.ServerInterface;

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
    	
    	// Get current date, set schedule to show
    	DateTime currentDate = new DateTime();
    	Log.d("Calendar", "Date: " + currentDate.getMonthOfYear() + "/" + currentDate.getDayOfMonth() + "/" + currentDate.getYear());
		setListViewByDate(timesListView, currentDate);
    	
    	calendar.setOnDateChangeListener(new OnDateChangeListener() {
    		public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
    			Toast.makeText(getActivity().getApplicationContext(), (month+1) + "/" + day + "/" + year, Toast.LENGTH_LONG).show();
    			// TODO: make database call for schedule for the day
    			// String dateString = ""+ (month+1) + "/" + day + "/" + year;
    			DateTime date = new DateTime(year, (month+1), day, 0, 0);
    			setListViewByDate(timesListView, date);
    		}
    	});
    	
    	
    	
	    // Inflate the layout for this fragment
	    return V;
	}
	
	
	/** Sets the listView to display the schedule at a certain date */
	public void setListViewByDate(ListView timesListView, DateTime date) {
    	
		Log.d("ScheduleFragment", "Creating timeline for " + date.toString());

		// TODO: get courtId
		int courtId = 0;
		
    	ServerInterface serverConnection = ServerConnectionProxy.getInstance();
    	
    	Log.d("ScheduleFragment", "Populating timeline");
    	Timeline times = serverConnection.getTimeline(date, courtId);
		Log.d("ScheduleFragment", "Creating schedule from timeline");
		ArrayList<String> schedule = new ArrayList<String>();
		Log.d("ScheduleFragment", "Timeline: " + times.toString());
		
		for(int i = 0; i < 24; i ++) {
			Time time = new Time(i, 0);
			// Log.d("ScheduleFragment", "" + i + ":00 - " + times.getNumberPlayersAtTime(time) + " players");
			schedule.add("" + i + ":00 - " + times.getNumberPlayersAtTime(time) + " players");
		}
		
    	listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.simplerow, schedule);
    	timesListView.setAdapter(listAdapter);

    	Log.d("ScheduleFragment", "Setting onClick for listview");
    	timesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    		public void onItemClick(AdapterView <? > arg0, View view, int position, long id) {
    	    	// TODO: onClick
    		}
    	});
	}
		
}
