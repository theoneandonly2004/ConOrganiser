package com.example.daniel.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Daniel on 04/04/2016.
 */
public class ActionHandler extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Event event = (Event)intent.getSerializableExtra("event");
        Log.d("the listener event", "i have entered the event as " + event.eventName);
        Toast.makeText(null, "hi, i'm an event called " + event.eventName, Toast.LENGTH_SHORT).show();
    }
}
