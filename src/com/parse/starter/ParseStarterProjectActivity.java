package com.parse.starter;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.parse.ParseAnalytics;
import com.parse.ParseObject;

public class ParseStarterProjectActivity extends Activity {
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ParseAnalytics.trackAppOpenedInBackground(getIntent());
		/*	WORKED
		ParseObject testObject = new ParseObject("TestObject");
		testObject.put("foo", "bar");
		testObject.saveInBackground();
		*/


	}
	public void schedule(View view) {
		// tag == angelus || fasting
		String var = (String)view.getTag();
		Intent myIntent=new Intent(view.getContext(),SchedulingActivity.class);
		myIntent.putExtra("schedulingType",var);
		startActivity(myIntent);
	}
}
