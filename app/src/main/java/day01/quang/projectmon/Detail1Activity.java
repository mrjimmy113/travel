package day01.quang.projectmon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Detail1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail1);
    }

    public void moveToFavorite(View view) {
        Intent intent = new Intent(Detail1Activity.this, FavoriteActivity.class);
        startActivity(intent);
    }
}
