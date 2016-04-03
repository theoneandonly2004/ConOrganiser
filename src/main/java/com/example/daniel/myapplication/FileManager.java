package com.example.daniel.myapplication;

import android.content.Context;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Daniel on 28/03/2016.
 */
public class FileManager {
    public static ArrayList<Event>eventList = new ArrayList<Event>();

    public static boolean compare(Event pEvent){
        for(int count=0;count<eventList.size();count++){
            if(pEvent.eventDate.equals(eventList.get(count).eventDate)){
                if(pEvent.eventName.equals(eventList.get(count).eventName)) {
                    if(pEvent.eventLocation.equals(eventList.get(count).eventLocation)){
                        if(pEvent.eventHost.equals(eventList.get(count).eventHost)){
                            Log.d("was found","wtf the event " + pEvent.eventName + " is identical to what's on the list");
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public static void clearDuplicates(Event pEvent){
        for(int count=0;count<eventList.size();count++){
            if(pEvent.eventDate.equals(eventList.get(count).eventDate)){
                if(pEvent.eventName.equals(eventList.get(count).eventName)) {
                    if(pEvent.eventLocation.equals(eventList.get(count).eventLocation)){
                        if(pEvent.eventHost.equals(eventList.get(count).eventHost)){
                            eventList.remove(count);

                        }
                    }
                }
            }
        }
    }

    public static void updateFile(Context pContext){
        try {
            pContext.openFileOutput("userEvents", pContext.MODE_PRIVATE).flush();
            FileOutputStream fos = pContext.openFileOutput("userEvents", pContext.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);



            for(int count=0;count < eventList.size();count++){
                os.writeObject(eventList.get(count));
            }



            os.close();
            fos.close();

            for(int count=0;count < eventList.size();count++){
                Log.d("event list " , eventList.get(count).eventName);
            }
        }
        catch(Exception ex)
        {
            Log.w("exception writing out",ex.getMessage());
        }
    }

    public static void writeToFile(Event pEvent,Context pContext) {
        try {
            FileOutputStream fos = pContext.openFileOutput("userEvents", pContext.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            if(!compare(pEvent)) {
                eventList.add(pEvent);
            }

            for(int count=0;count < eventList.size();count++){
                   os.writeObject(eventList.get(count));
               }



            os.close();
            fos.close();
        }
        catch(Exception ex)
        {
            Log.w("exception writing out",ex.getMessage());
        }

    }

    public static void readFromFile(Context pContext){

        eventList.clear();
        boolean isRunning = true;

        try {
            FileInputStream fis = pContext.openFileInput("userEvents");


            ObjectInputStream is = new ObjectInputStream(fis);
            while(isRunning) {
                try {
                    Event event = (Event) is.readObject();
                    eventList.add(event);
                }
                catch(EOFException ex)
                {
                    isRunning = false;
                }
            }
            for(int count =0; count < eventList.size();count++){
                Log.d("read input","event " + count + " is " + eventList.get(count).eventName);
            }

            is.close();
            fis.close();
        }
        catch(Exception ex){
            Log.w("exception reading in",ex.getMessage());
        }
    }

}
