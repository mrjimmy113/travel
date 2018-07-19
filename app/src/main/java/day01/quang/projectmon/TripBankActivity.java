package day01.quang.projectmon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TripBankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_bank);
    }

    public void moveToConfirm(View view) {
        Intent intent = new Intent(this, ConfirmTripActivity.class);
        startActivity(intent);
    }

    public void moveBack(View view) {
        this.finish();
    }

    public void moveToEdit(View view) {
        Intent intent = new Intent(this, PlanEditActivity.class);
        startActivity(intent);
    }

    public void moveSummary(View view) {
        Intent intent = new Intent(this, SummaryDayActivity.class);
        startActivity(intent);
    }
}
