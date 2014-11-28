package com.hoopme.activity;

import org.joda.time.DateTime;
import org.json.JSONObject;

import com.hoopme.activity.R;
import com.hoopme.logic.Position;
import com.hoopme.objects.PlayerDetails;
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
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CreateProfile extends ActionBarActivity {


	private String name;
	private String password;
	private String birthday;
	private int skillLevel;
	private SeekBar skillBar;
	private Spinner spinner;
	private String position;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_profile);
		
		// Seek bar for skill level
		Log.d("CreateProfile", "Creating skill bar");
		skillBar = (SeekBar) findViewById(R.id.skill_seekbar);
		skillLevel = 1;
		Log.d("CreateProfile", "Creating skill bar2");
		skillBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
				TextView skill_label = (TextView) findViewById(R.id.skill_label);
				skill_label.setText("Skill level: " + (progress+1));
				skillLevel = progress;
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				/**Toast.makeText(CreateProfile.this,"seek bar progress:"+progressChanged, 
						Toast.LENGTH_SHORT).show(); */
			}
		});
		
		// Spinner for preferred position
		Log.d("CreateProfile", "Creating position spinner");
		spinner = (Spinner) findViewById(R.id.position_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.positions, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
//		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//			     String position = parent.getItemAtPosition(pos).toString();
//		    }
//		    public void onNothingSelected(AdapterView<?> parent) {
//			}
//		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_profile, menu);
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
	
	// TODO: when user submits information - must validate and send to DB
	public void onSubmit(View view) {
		
		// Name
		EditText name_input = (EditText) findViewById(R.id.name_input);
		name = name_input.getText().toString();
		Log.d("CreateProfile", "Name: " + name);
	
		// Password
		EditText password_input = (EditText) findViewById(R.id.password_input);
		password = password_input.getText().toString();
		Log.d("CreateProfile", "Password: " + password);
			
		// Position
		position = spinner.getSelectedItem().toString();
		Log.d("CreateProfile", "Spinner position: " + position);
	
		// Birthday
		EditText birthday_input = (EditText) findViewById(R.id.birthday_input);
		birthday = birthday_input.getText().toString();
		Log.d("CreateProfile", "Birthday: " + birthday);
		
		// Skill Level
		Log.d("CreateProfile", "Skill level: " + skillLevel);
		
		
		// Note: add username
		ServerInterface server = ServerConnectionProxy.getInstance();
		int id = server.getNewPlayerId();
		PlayerDetails player = new PlayerDetails(id, "username", name, password, DateTime.now(), skillLevel, Position.getPosition(position));
		Log.d("CreatePorifle", "status: " + server.createProfile(player));
		
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("com.hoopme.activity.username", name);
		
		startActivity(intent);
		
	}
}
