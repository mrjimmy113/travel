package day01.quang.projectmon;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class NewExpenseActivity extends AppCompatActivity {
    List<View> listView = new ArrayList<>();
    Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense);
        sp = findViewById(R.id.spinner_currency);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency, R.layout.spinner_item);
        sp.setAdapter(adapter);
    }

    public void moveBack(View view) {
        this.finish();
    }

    public void createExpense(View view) {
        this.finish();
    }


    public void typeChoose(View view) {
        for (int i = 0 ; i < listView.size(); i++) {
            listView.get(i).setBackgroundColor(getResources().getColor(R.color.transparent));
        }
        view.setBackgroundColor(getResources().getColor(R.color.choosen_expense));
        if(!listView.contains(view)){
            listView.add(view);
        }
    }
}
