package com.parse.starter;

/**
 * Created by Patrick on 8/18/2015.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        String schedulingType = (String) extras.get("schedulingType");
        Intent service1 = new Intent(context, MyAlarmService.class);
        service1.putExtra("schedulingType", schedulingType);
        context.startService(service1);

    }
}