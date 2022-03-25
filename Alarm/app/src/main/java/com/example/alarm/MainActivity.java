package com.example.alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
Button btnStartTime;
    Button btnStartBudilnik;
EditText editSeconds;
Calendar Time = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStartTime = findViewById(R.id.btnStartTime);
        editSeconds = findViewById(R.id.editSeconds);
btnStartBudilnik= findViewById(R.id.btnStartBudilnik);
        TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Time.set(Calendar.HOUR_OF_DAY,hourOfDay);
                Time.set(Calendar.MINUTE,minute);
                editSeconds.setText(DateUtils.formatDateTime(getApplicationContext(),
                        Time.getTimeInMillis(),DateUtils.FORMAT_SHOW_TIME
                ));
            }
        };
        btnStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(MainActivity.this,t,
                        Time.get(Calendar.HOUR_OF_DAY),
                        Time.get(Calendar.MINUTE),
                        true).show();
            }
        });
        btnStartBudilnik.setOnClickListener(view->
        {
            int seconds = Time.get(Calendar.HOUR_OF_DAY)*60*60+Time.get(Calendar.MINUTE)*60+Time.get(Calendar.SECOND);
            Intent intent =new Intent(MainActivity.this,Alarm.class);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP,Time.getTimeInMillis(), PendingIntent.getBroadcast(getApplicationContext(),0,intent,0));
        });
    }
}