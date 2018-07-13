package day01.quang.projectmon;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class TodayPlanFragment extends Fragment {

    ImageView tl1,tl2,tl3;
    FloatingActionButton fbtnTool;
    public TodayPlanFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_today_plan, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        tl1 = getView().findViewById(R.id.timeline1);
        tl2 = getView().findViewById(R.id.timeline2);
        tl3 = getView().findViewById(R.id.timeline3);
        fbtnTool = getView().findViewById(R.id.btn_float_trip_tool);
        fbtnTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).showTripTool(fbtnTool);
            }
        });
        tl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).showActivityMenu(tl1);
            }
        });
        tl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).showActivityMenu(tl2);
            }
        });
        tl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).showActivityMenu(tl3);
            }
        });
        super.onActivityCreated(savedInstanceState);
    }
}
