package com.example.daniel.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        MainActivity.linearLayout.removeAllViews();

        for(int count=0;count<MainActivity.buttons.size();count++){
           MainActivity.buttons.get(count).RemoveButtonFromScreen();
        }

        Calendar calendar = new GregorianCalendar(year,monthOfYear,dayOfMonth);
            for(int count=0;count<MainActivity.buttons.size();count++){
                if(MainActivity.buttons.get(count).GetIsEventToday(year,monthOfYear,dayOfMonth)){
                    MainActivity.buttons.get(count).AddButtonToScreen();
                    Log.d("found date","the button " + MainActivity.buttons.get(count).eventName + " was found");
                }
                else {
                    Log.d("why it wasn't found " , "year " + year + " month " + monthOfYear + " day " + dayOfMonth );
                }
            }
    }
}