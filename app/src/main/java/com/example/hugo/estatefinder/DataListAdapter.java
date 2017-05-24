package com.example.hugo.estatefinder;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hugo on 2017-03-05.
 */

public class DataListAdapter extends ArrayAdapter<String> {


    TextView serviceTitle;
    Button detailsButton;
    Button contactButton;
    RatingBar avgRatingBar;
    ArrayList<String> companyNames;
    RelativeLayout frame;
    Context application;
    public DataListAdapter (Context context, int resource , ArrayList<String> data){
        super(context,resource,data);
        this.companyNames = data;
        this.application = context;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_services_data_row, parent, false);
        }

        this.frame = (RelativeLayout)convertView.findViewById(R.id.detailframe) ;
        this.serviceTitle = (TextView) convertView.findViewById(R.id.servicesTitle);

        serviceTitle.setText(companyNames.get(position));

        this.detailsButton = (Button) convertView.findViewById(R.id.detailsButton);
        // set onClickListner to start a new fragment with the service detail
        this.detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction ft = ((FragmentActivity)application).getSupportFragmentManager().beginTransaction();
                Fragment detailFragment = new serviceDetailFragment();
                ft.replace(R.id.current_fragment,detailFragment,"tset");
                ft.addToBackStack("tset");
                ft.commit();
            }
        });

        this.contactButton = (Button) convertView.findViewById(R.id.contactButton);
        this.avgRatingBar = (RatingBar)convertView.findViewById(R.id.avgRating);
        this.avgRatingBar.setRating(3);

        return convertView;
    }


}