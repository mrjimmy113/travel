package day01.quang.projectmon;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

    public void openTimePicker(final View view) {

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);


            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker v, int hourOfDay, int minute) {
                    ((EditText)view).setText(hourOfDay + ":" + minute);
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
        if(view.getTag().toString().equals("whereToGo")) {
            ((EditText)view).setText("Nha Trang");
        }
        if(view.getTag().toString().equals("whereHotel")) {
            ((EditText)view).setText("Vinpearl Hotel");
        }
        if(view.getTag().toString().equals("whereVisit")) {
            ((EditText)view).setText("Binh ba Island");
        }
    }

    public void moveBack(View view) {
        this.finish();
    }

    public void moveToTypeChoose(View view) {
        CardView cd = findViewById(R.id.activity_2);
        cd.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, ActivityTypeActivity.class);
        startActivity(intent);
    }

    public void newDay(View view) {
        CardView cd = findViewById(R.id.day_2);
        cd.setVisibility(View.VISIBLE);
    }

    public void deleteActivity(final View view) {
        final Dialog dialog =  new Dialog(this);
        dialog.setContentView(R.layout.dialog_confirm);
        Button btnConfirm = dialog.findViewById(R.id.btnConfirm);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CardView)(((view.getParent()).getParent())).getParent()).setVisibility(View.GONE);
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
