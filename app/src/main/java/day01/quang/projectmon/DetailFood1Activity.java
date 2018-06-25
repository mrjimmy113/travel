package day01.quang.projectmon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DetailFood1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food1);
    }

    public void moveToFavorite(View view) {
        Intent intent = new Intent(DetailFood1Activity.this, FavoriteActivity.class);
        startActivity(intent);
    }
}
