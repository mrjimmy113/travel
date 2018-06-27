package day01.quang.projectmon;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
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

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ImageButton btnDayAction, btnActivityAction;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDayAction = findViewById(R.id.btnDayMenuAction);
        btnActivityAction = findViewById(R.id.btnActivityMenuAction);
        fab = findViewById(R.id.btnTripAction);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

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

        btnActivityAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showActivityMenu();
            }
        });
        btnDayAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDayMenu();
            }
        });
        if(getIntent().hasExtra("IsConfirm")) {
            LinearLayout no_data = (LinearLayout) findViewById(R.id.layout_no_data);
            LinearLayout yourTrip = (LinearLayout) findViewById(R.id.layout_yourTrip);
            LinearLayout budget= (LinearLayout) findViewById(R.id.layout_budget);
            CardView card = (CardView) findViewById(R.id.layout_CardView);
            no_data.setVisibility(View.GONE);
            yourTrip.setVisibility(View.VISIBLE);
            card.setVisibility(View.VISIBLE);
            budget.setVisibility(View.VISIBLE);
            fab.setVisibility(View.VISIBLE);
        }

        fab.setOnTouchListener(new View.OnTouchListener() {
            float startX;
            float startRawX;
            float distanceX;
            int lastAction;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = view.getX() - event.getRawX();
                        startRawX = event.getRawX();
                        lastAction = MotionEvent.ACTION_DOWN;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        view.setX(event.getRawX() + startX);
                        view.setY(event.getRawY() + startX);

                        lastAction = MotionEvent.ACTION_MOVE;
                        break;

                    case MotionEvent.ACTION_UP:
                        distanceX = event.getRawX()-startRawX;
                        if (Math.abs(distanceX)< 10){
                            showTripTool();
                        }
                        break;
                    case MotionEvent.ACTION_BUTTON_PRESS:

                    default:
                        return false;
                }
                return true;
            }
        });
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
    public void showActivityMenu() {
        @SuppressLint("RestrictedApi") MenuBuilder menuBuilder =new MenuBuilder(this);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.trip_activity_action, menuBuilder);
        @SuppressLint("RestrictedApi") MenuPopupHelper optionsMenu = new MenuPopupHelper(this, menuBuilder, btnActivityAction);
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
    public void showTripTool() {
        @SuppressLint("RestrictedApi") MenuBuilder menuBuilder =new MenuBuilder(this);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.trip_tool, menuBuilder);
        @SuppressLint("RestrictedApi") MenuPopupHelper optionsMenu = new MenuPopupHelper(this, menuBuilder, fab);
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



}
