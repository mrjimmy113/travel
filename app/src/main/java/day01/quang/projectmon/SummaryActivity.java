package day01.quang.projectmon;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.LargeValueFormatter;


import java.util.ArrayList;
import java.util.List;


public class SummaryActivity extends AppCompatActivity {
    List<Integer> listColor;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();






        PieChart chart = (PieChart) findViewById(R.id.chart);
        List<PieEntry> entries = new ArrayList<PieEntry>();


        entries.add(new PieEntry(1000000, getResources().getDrawable(R.drawable.ic_main_restaurant)));
        entries.add(new PieEntry(200000, getResources().getDrawable(R.drawable.ic_main_car)));
        entries.add(new PieEntry(1000000, getResources().getDrawable(R.drawable.ic_main_shopping)));
//        entries.add(new PieEntry(40, "Restaurant"));
//        entries.add(new PieEntry(20, "Transportation"));
//        entries.add(new PieEntry(40, "Shopping"));

        listColor = new ArrayList<>();
        listColor.add(getResources().getColor(R.color.yellow));
        listColor.add(getResources().getColor(R.color.ic_new_trip));
        listColor.add(getResources().getColor(R.color.red));
        listColor.add(getResources().getColor(R.color.yellow));
        listColor.add(getResources().getColor(R.color.brown));
        PieDataSet dataSet = new PieDataSet(entries, null);
        dataSet.setColors(listColor);
        dataSet.setValueFormatter(new LargeValueFormatter());
        dataSet.setValueTextSize(14);
        dataSet.setValueTextColor(getResources().getColor(R.color.colorPrimary));
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setValueLinePart2Length(0.8f);
        PieData lineData = new PieData(dataSet);
        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);
        chart.setCenterText("1.200.000 đ");
        chart.setCenterTextSize(15);
        chart.setCenterTextColor(getResources().getColor(R.color.colorPrimary));
        chart.setData(lineData);

        chart.invalidate(); // refres
    }

    public void moveBack(View view) {
        this.finish();
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new tabItemTransFragment(), "");
        adapter.addFragment(new tabItemResFragment(), "");
        adapter.addFragment(new tabItemShopFragment(), "");
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

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.ic_main_car));
        tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.ic_main_restaurant));
        tabLayout.getTabAt(2).setIcon(getResources().getDrawable(R.drawable.ic_main_shopping));
    }
}
