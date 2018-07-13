package day01.quang.projectmon;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ImageButton btnDayAction, btnActivityAction;
    private ImageButton tl1,tl2,tl3;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);



        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // switch to new view
                        switch (menuItem.getItemId()) {
                            case R.id.nav_favorite:
                                Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_setting:
                                Intent intentSetting = new Intent(MainActivity.this, SettingActivity.class);
                                startActivity(intentSetting);
                                break;
                        }

                        if(menuItem.getItemId() == R.id.nav_camera) {
                            Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
                            startActivity(intent);
                        }

                        return true;
                    }
                });

        if(getIntent().hasExtra("IsConfirm")) {

        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.nav_favorite:
                Intent intent = new Intent(this, FavoriteActivity.class);
                this.startActivity(intent);
               break;
            default: return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void switchScreen(View view) {
        Intent intent = new Intent(this, PlanActivity.class);
        startActivity(intent);
    }

    public void moveToTripBank(View view) {
        Intent intent = new Intent(this, TripBankActivity.class);
        startActivity(intent);
    }

    public void moveToMap() {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    @SuppressLint("RestrictedApi")
    public void showDayMenu() {

        @SuppressLint("RestrictedApi") MenuBuilder menuBuilder =new MenuBuilder(this);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.trip_day_action, menuBuilder);
        @SuppressLint("RestrictedApi") MenuPopupHelper optionsMenu = new MenuPopupHelper(this, menuBuilder, btnDayAction);
        optionsMenu.setForceShowIcon(true);
        menuBuilder.setCallback(new MenuBuilder.Callback() {
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                switch (item.getItemId())  {
                    case R.id.menu_day_direction: {
                        moveToMap();
                        break;
                    }
                    case R.id.menu_budget_update: {
                        budgetDialog();
                        break;
                    }

                }
                return false;
            }

            @Override
            public void onMenuModeChange(MenuBuilder menu) {}
        });
        optionsMenu.show();
    }

    @SuppressLint("RestrictedApi")
    public void showActivityMenu(View v) {
        @SuppressLint("RestrictedApi") MenuBuilder menuBuilder =new MenuBuilder(this);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.trip_activity_action, menuBuilder);
        @SuppressLint("RestrictedApi") MenuPopupHelper optionsMenu = new MenuPopupHelper(this, menuBuilder, v);
        optionsMenu.setForceShowIcon(true);
        menuBuilder.setCallback(new MenuBuilder.Callback() {
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                switch (item.getItemId())  {
                    case R.id.menu_activity_direction: {
                        moveToMap();
                        break;
                    }
                }
                return false;
            }

            @Override
            public void onMenuModeChange(MenuBuilder menu) {}
        });
        optionsMenu.show();
    }

    @SuppressLint("RestrictedApi")
    public void showTripTool(View view) {
        @SuppressLint("RestrictedApi") MenuBuilder menuBuilder =new MenuBuilder(this);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.trip_tool, menuBuilder);
        @SuppressLint("RestrictedApi") MenuPopupHelper optionsMenu = new MenuPopupHelper(this, menuBuilder, view);
        optionsMenu.setForceShowIcon(true);
        menuBuilder.setCallback(new MenuBuilder.Callback() {
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                switch (item.getItemId())  {
                    case R.id.menu_day_edit_trip: {
                        Intent intent = new Intent(getApplicationContext(), PlanEditActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.menu_day_partner: {
                        Intent intent = new Intent(getApplicationContext(), PartnerActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.menu_budget_report: {
                        Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.menu_day_direction: {
                        moveToMap();
                        break;
                    }
                    case R.id.menu_budget_update: {
                        budgetDialog();
                        break;
                    }
                    case R.id.menu_day_finish_trip:{
                        Intent intent = new Intent(getApplicationContext(), SummaryActivity.class);
                        startActivity(intent);
                        break;
                    }
                }
                return false;
            }

            @Override
            public void onMenuModeChange(MenuBuilder menu) {}
        });
        optionsMenu.show();
    }

    public void budgetDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_budget_update);
        dialog.show();
    }


    public void activityMenu1(View view) {
        showActivityMenu(tl1);
    }

    public void activityMenu3(View view) {
        showActivityMenu(tl3);
    }

    public void activityMenu2(View view) {
        showActivityMenu(tl2);
    }

    public void moveToExplore(View view) {
        Intent intent = new Intent(this, FavoriteActivity.class);
        startActivity(intent);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TodayPlanFragment(), "PLAN");
        adapter.addFragment(new TodayBudgeFragment(), "EXPENSE");
        viewPager.setAdapter(adapter);
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
