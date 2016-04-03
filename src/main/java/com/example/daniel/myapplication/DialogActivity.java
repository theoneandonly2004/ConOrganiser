package com.example.daniel.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Daniel on 03/04/2016.
 */
public class DialogActivity extends Activity
{
    public static Event currentEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
       // builder.setMessage("Event:" + eventName + "\n Host:" + eventHost + "\n Location: " + eventLocation + "\n When: " + dateOutput);
        builder.setMessage("yay it worked for the event " + currentEvent.eventName);
        builder.setTitle("do you wish to add this event to your watch list?");

        if(!FileManager.eventList.contains(currentEvent)) {
            builder.setPositiveButton("add interest", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    FileManager.writeToFile(currentEvent,getBaseContext());
                    FileManager.readFromFile(getBaseContext());
                }
            });
        }
        else if(FileManager.eventList.contains(currentEvent)) {
            builder.setPositiveButton("remove interest", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    FileManager.eventList.remove(currentEvent);
                    FileManager.writeToFile(currentEvent,getBaseContext());
                    FileManager.readFromFile(getBaseContext());
                }
            });
        }

        builder.setPositiveButton("apple", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
