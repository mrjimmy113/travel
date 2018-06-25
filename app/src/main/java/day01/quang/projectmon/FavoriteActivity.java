package day01.quang.projectmon;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        toolbar = (Toolbar) findViewById(R.id.toolbarFavorite);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "Want to go");
        adapter.addFragment(new TwoFragment(), "Hotel");
        adapter.addFragment(new ThreeFragment(), "Food & Drink");
        viewPager.setAdapter(adapter);
    }

    public void moveToDetail(View view) {
        Intent intent = new Intent(FavoriteActivity.this, Detail1Activity.class);
        startActivity(intent);
    }

    public void moveToDetailDL(View view) {
        Intent intent = new Intent(FavoriteActivity.this, DetailDLActivity.class);
        startActivity(intent);
    }

    public void moveToDetailFood1(View view) {
        Intent intent = new Intent(FavoriteActivity.this, DetailFood2Activity.class);
        startActivity(intent);
    }

    public void moveToDetailFood(View view) {
        Intent intent = new Intent(FavoriteActivity.this, DetailFood1Activity.class);
        startActivity(intent);
    }

    public void moveToDetailHotel1(View view) {
        Intent intent = new Intent(FavoriteActivity.this, DetailHotel1Activity.class);
        startActivity(intent);
    }

    public void moveToDetailHotel2(View view) {
        Intent intent = new Intent(FavoriteActivity.this, DetailHotel2Activity.class);
        startActivity(intent);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
