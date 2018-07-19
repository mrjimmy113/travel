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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class PlanActivity extends AppCompatActivity {
    AutoCompleteTextView location1, hotel1, place1, location2, hotel2, place2;
    Spinner sp, sp1,sp2;
    LinearLayout day1Infor, day2Infor;
    TextView day1Title, day2Title;
    CardView day1Ac2 ,day2acc1, day2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        day1Infor = findViewById(R.id.day1_infor);
        day1Title = findViewById(R.id.day1);
        day1Ac2 = findViewById(R.id.day1_activity2);
        day2Infor = findViewById(R.id.day2_infor);
        day2Title = findViewById(R.id.day2);
        sp2 = findViewById(R.id.spinner_currency_2);
        sp1 = findViewById(R.id.spinner_currency_1);
        day2acc1 = findViewById(R.id.day2_activity1);
        day2 = findViewById(R.id.card_day_2);

        location1 = findViewById(R.id.autoComp_location_1);
        location2 = findViewById(R.id.autoComp_location_2);
        ArrayAdapter<String> adapterLocation1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.location));
        location1.setAdapter(adapterLocation1);
        location2.setAdapter(adapterLocation1);

        hotel1 = findViewById(R.id.autoComp_hotel_1);
        hotel2 = findViewById(R.id.autoComp_hotel_2);
        ArrayAdapter<String> adapterHotel1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.hotel));
        hotel1.setAdapter(adapterHotel1);
        hotel2.setAdapter(adapterHotel1);

        place1 = findViewById(R.id.autoComp_place_1);
        place2 = findViewById(R.id.autoComp_place_2);
        ArrayAdapter<String> adapterPlace1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.place));
        place1.setAdapter(adapterPlace1);
        place2.setAdapter(adapterPlace1);

        sp = findViewById(R.id.spinner_currency);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency, R.layout.spinner_item);
        sp.setAdapter(adapter);
        sp1.setAdapter(adapter);
        sp2.setAdapter(adapter);
    }

    public void moveToBank(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("IsConfirm", true);
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



    }



    public void moveBack(View view) {
        this.finish();
    }

    public void moveToTypeChoose(View view) {
        Intent intent = new Intent(this, ActivityTypeActivity.class);
        startActivity(intent);
        day1Ac2.setVisibility(View.VISIBLE);

    }

    public void newDay(View view) {
        day2.setVisibility(View.VISIBLE);
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


    public void hideDay(View view) {
        if(day1Infor.getVisibility() == View.VISIBLE) {
            day1Title.setCompoundDrawablesRelativeWithIntrinsicBounds(getDrawable(R.drawable.ic_arrow_drop_down_black_24dp),null,null,null);
            day1Infor.setVisibility(View.GONE);
        }else{
            day1Title.setCompoundDrawablesRelativeWithIntrinsicBounds(getDrawable(R.drawable.ic_arrow_drop_up_black_24dp),null,null,null);
            day1Infor.setVisibility(View.VISIBLE);
        }
    }

    public void moveToTypeChoose2(View view) {
        Intent intent = new Intent(this, ActivityTypeActivity.class);
        startActivity(intent);
        day2acc1.setVisibility(View.VISIBLE);
    }

    public void hideDay2(View view) {
        if(day2Infor.getVisibility() == View.VISIBLE) {
            day2Title.setCompoundDrawablesRelativeWithIntrinsicBounds(getDrawable(R.drawable.ic_arrow_drop_down_black_24dp),null,null,null);
            day2Infor.setVisibility(View.GONE);
        }else{
            day2Title.setCompoundDrawablesRelativeWithIntrinsicBounds(getDrawable(R.drawable.ic_arrow_drop_up_black_24dp),null,null,null);
            day2Infor.setVisibility(View.VISIBLE);
        }
    }

    public void openDatePicker(final View v) {
        final Calendar c = Calendar.getInstance();
        Log.d("Mydebug",c.getTime().toString());
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                ((TextView)v).setText(year + "/" + month + "/" + dayOfMonth);
            }
        },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
