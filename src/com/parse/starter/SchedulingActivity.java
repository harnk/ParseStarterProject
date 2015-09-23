package com.parse.starter;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TimePicker;

import java.util.Calendar;


public class SchedulingActivity extends Activity {
    public String schedulingType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduling);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            schedulingType = extras.getString("schedulingType");
        }
        if (schedulingType.equals("angelus")) {
            checkAllBoxes();
            TimePicker tp = (TimePicker) findViewById(R.id.timePicker);
            tp.setCurrentHour(12);
            tp.setCurrentMinute(0);
        } else if (schedulingType.equals("fasting")) {
            CheckBox box5 = (CheckBox) findViewById(R.id.fridayCheck);
            box5.setChecked(true);
            TimePicker tp = (TimePicker) findViewById(R.id.timePicker);
            tp.setCurrentHour(8);
            tp.setCurrentMinute(0);
        } else {
            System.out.println("ERRORNOTWORKING");
        }
    }

    public void done(View view) {
        TimePicker tp = (TimePicker) findViewById(R.id.timePicker);
        int hr = tp.getCurrentHour();
        int min = tp.getCurrentMinute();

        boolean[] isChecked = new boolean[7];
        isChecked = checkIfBoxesAreChecked(isChecked);

        /*
        SET NOTIFICATION REMINDER
         */
        for (int i = 0; i < 7; i++) {
            if (isChecked[i] == true) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, i + 1);   // 1 == Calendar.SUNDAY , 7 == saturday
                calendar.set(Calendar.HOUR_OF_DAY, hr);
                calendar.set(Calendar.MINUTE, min);

                Intent myIntent = new Intent(this, MyReceiver.class);
                myIntent.putExtra("schedulingType", schedulingType);
                final int _id = (int) System.currentTimeMillis();
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this, _id, myIntent, 0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), 1000 * 60 * 60 * 24 * 7, pendingIntent); // weekly
            }
        }
        Intent myIntent2 = new Intent(view.getContext(), ParseStarterProjectActivity.class);
        startActivity(myIntent2);
        finish();
    }

    private boolean[] checkIfBoxesAreChecked(boolean[] isChecked) {
        CheckBox box0 = (CheckBox) findViewById(R.id.sundayCheck);
        isChecked[0] = box0.isChecked();
        CheckBox box = (CheckBox) findViewById(R.id.mondayCheck);
        isChecked[1] = box.isChecked();
        CheckBox box2 = (CheckBox) findViewById(R.id.tuesdayCheck);
        isChecked[2] = box2.isChecked();
        CheckBox box3 = (CheckBox) findViewById(R.id.wednesdayCheck);
        isChecked[3] = box3.isChecked();
        CheckBox box4 = (CheckBox) findViewById(R.id.thursdayCheck);
        isChecked[4] = box4.isChecked();
        CheckBox box5 = (CheckBox) findViewById(R.id.fridayCheck);
        isChecked[5] = box5.isChecked();
        CheckBox box6 = (CheckBox) findViewById(R.id.saturdayCheck);
        isChecked[6] = box6.isChecked();

        return isChecked;
    }

    @Override
    public void onBackPressed() {
        // do something here and don't write super.onBackPressed()
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scheduling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void checkAllBoxes() {
        CheckBox box = (CheckBox) findViewById(R.id.mondayCheck);
        box.setChecked(true);
        CheckBox box2 = (CheckBox) findViewById(R.id.tuesdayCheck);
        box2.setChecked(true);
        CheckBox box3 = (CheckBox) findViewById(R.id.wednesdayCheck);
        box3.setChecked(true);
        CheckBox box4 = (CheckBox) findViewById(R.id.thursdayCheck);
        box4.setChecked(true);
        CheckBox box5 = (CheckBox) findViewById(R.id.fridayCheck);
        box5.setChecked(true);
        CheckBox box6 = (CheckBox) findViewById(R.id.saturdayCheck);
        box6.setChecked(true);
        CheckBox box7 = (CheckBox) findViewById(R.id.sundayCheck);
        box7.setChecked(true);
    }
}
