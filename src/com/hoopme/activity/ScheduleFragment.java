package com.hoopme.activity;

import com.hoopme.activity.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CalendarView.OnDateChangeListener;

public class ScheduleFragment extends Fragment{

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    Bundle savedInstanceState) {
		
		Log.d("ScheduleFragment", "Creating view");
		View V = inflater.inflate(R.layout.fragment_schedule, container, false);

    	CalendarView calendar = (CalendarView) V.findViewById(R.id.calendar);
    	calendar.setShowWeekNumber(false);
    	Log.d("ScheduleFragment", "setShowWeekNumber: " + calendar.getShowWeekNumber());
    	calendar.setShownWeekCount(1);
    	Log.d("ScheduleFragment", "setShownWeekCount: " + calendar.getShownWeekCount());

    	calendar.setOnDateChangeListener(new OnDateChangeListener() {
    		public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
    			Toast.makeText(getActivity().getApplicationContext(), (month+1) + "/" + day + "/" + year, Toast.LENGTH_LONG).show();
    			// TODO: make database call for schedule for the day
    			TextView time_12pm = (TextView) getView().findViewById(R.id.time_12pm);
    			time_12pm.setText("12:00 PM - " + (day%3 + month%2) + " players");
    			TextView time_1pm = (TextView) getView().findViewById(R.id.time_1pm);
    			time_1pm.setText("1:00 PM - " + (day%3+1) + " players");
    		}
    	});
    	
    	
	    // Inflate the layout for this fragment
	    return V;
	}
	
}
