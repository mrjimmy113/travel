package day01.quang.projectmon;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class PlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

    }

    public void moveToBank(View view) {
        Intent intent = new Intent(this, TripBankActivity.class);
        startActivity(intent);
    }

    public void openTimePicker(View view) {

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);


            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                }

            }, hour, minute, false);

            timePickerDialog.show();
            Log.d("MyDebug", "DKM");

//
//        DatePickerDialog datePickerDialog = new DatePickerDialog(getApplicationContext(), new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//            }
//        },c.YEAR,c.MONTH,c.DAY_OF_MONTH);

    }

    public void openPlacePicker(View view) {
        Intent intent = new Intent(this, PlaceAroundActivity.class);
        startActivity(intent);
    }

    public void moveBack(View view) {
        this.finish();
    }

    public void moveToTypeChoose(View view) {
        Intent intent = new Intent(this, ActivityTypeActivity.class);
        startActivity(intent);
    }
}
