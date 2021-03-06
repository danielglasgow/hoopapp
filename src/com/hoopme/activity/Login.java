package com.hoopme.activity;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Login extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("Login", "Open to login screen");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
	
	public void onLogin (View view) {
		// TODO: validate login credentials
		Intent intent = new Intent(this, MainActivity.class);
		EditText username_text = (EditText) findViewById(R.id.username);
		EditText password_text = (EditText) findViewById(R.id.password);
		Log.i("Login", "Username: " + username_text.getText().toString());
		Log.i("Login", "Password: " + password_text.getText().toString());
		intent.putExtra("com.hoopme.activity.username", username_text.getText().toString());
		startActivity(intent);
	}
	
	public void onCreateAccount (View view) {
		Log.i("Login", "Click to create an account");
		Intent intent = new Intent(this, CreateProfile.class);
		startActivity(intent);
	}
}