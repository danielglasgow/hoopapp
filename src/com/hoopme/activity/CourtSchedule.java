package com.hoopme.activity;

import java.util.ArrayList;

import org.joda.time.DateTime;

import com.hoopme.objects.Time;
import com.hoopme.objects.Timeline;
import com.hoopme.server.ServerConnectionProxy;
import com.hoopme.server.ServerInterface;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.CalendarView.OnDateChangeListener;

public class CourtSchedule extends ActionBarActivity {

	private ListView timesListView;
	private ArrayAdapter<String> listAdapter;
	private int courtId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("Schedule", "Creating view");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_court_schedule);
		courtId = this.getIntent().getIntExtra("com.hoopme.courtId", 0);
    	
		CalendarView calendar = (CalendarView) findViewById(R.id.calendar);
    	calendar.setShowWeekNumber(false);
    	Log.d("Schedule", "setShowWeekNumber: " + calendar.getShowWeekNumber());

    	// Find the ListView resource.  
		Log.d("Schedule", "Finding list view");
		
    	timesListView = (ListView) findViewById(R.id.timesListView);
    	
    	// Set schedule to show current date
    	DateTime currentDate = new DateTime();
    	Log.d("Calendar", "Date: " + currentDate.getMonthOfYear() + "/" + currentDate.getDayOfMonth() + "/" + currentDate.getYear());
		setListViewByDate(timesListView, currentDate);
    	
		// Update schedule when the selected date changes
    	calendar.setOnDateChangeListener(new OnDateChangeListener() {
    		public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
    			Toast.makeText(getApplicationContext(), (month+1) + "/" + day + "/" + year, Toast.LENGTH_LONG).show();
    			DateTime date = new DateTime(year, (month+1), day, 0, 0);
    			setListViewByDate(timesListView, date);
    		}
    	});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.court_schedule, menu);
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
		} else if (id == R.id.action_home) {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
	
	/** Sets the listView to display the schedule at a certain date */
	public void setListViewByDate(ListView timesListView, DateTime date) {
    	
		Log.d("Schedule", "Creating timeline for " + date.toString());
    	ServerInterface serverConnection = ServerConnectionProxy.getInstance();
    	
    	Log.d("Schedule", "Populating timeline");
    	Timeline times = serverConnection.getTimeline(date, courtId);
		Log.d("Schedule", "Creating schedule from timeline");
		ArrayList<String> schedule = new ArrayList<String>();
		Log.d("Schedule", "Timeline: " + times.toString());
		
		Log.d("Schedule", "Preparing schedule");
		for(int i = 0; i < 24; i ++) {
			Time time = new Time(i, 0);
			// int numPlayers = times.getNumberPlayersAtTime(time);
			schedule.add(time.toString() + " - " + times.getNumberPlayersAtTime(time) + " players");
		}
		
    	listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, schedule);
    	timesListView.setAdapter(listAdapter);

    	Log.d("Schedule", "Setting onClick for listview");
    	timesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    		public void onItemClick(AdapterView <? > arg0, View view, int position, long id) {
    	    	// TODO: onClick
    		}
    	});
    	
    	timesListView.setSelection(14);
	}
}
