package com.example.hugo.estatefinder;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.hugo.estatefinder.API.Apicallback;
import com.example.hugo.estatefinder.API.Services;
import com.example.hugo.estatefinder.API.apiCaller;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
   ListView tablayout;
   ArrayList<Services> servicesList;
   TextView backtoSubCategoryButton;
   TextView dialogButton;
    private Services[] services;

    public DataListFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static DataListFragment newInstance(Services[] services) {

        DataListFragment fragment = new DataListFragment();
        fragment.services = services;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // get Services from server
        if(this.services !=null && this.services.length>0 ) {
            //convert array to arrayList
            this.servicesList= new ArrayList(Arrays.asList(this.services));
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_data_list,container,false);
        this.tablayout = (ListView) v.findViewById(R.id.servicedatalist);

        tablayout.setAdapter(new DataListAdapter(getContext(),R.layout.fragment_services_data_row,this.servicesList));

        this.backtoSubCategoryButton = (TextView)v.findViewById(R.id.backtosubs);
        this.dialogButton = (TextView) v.findViewById(R.id.filtersort);
        this.dialogButton.setClickable(true);
        this.backtoSubCategoryButton.setClickable(true);
        /*if(this.currentCategory.equals("BROWSE") || this.currentCategory.equals("Favorite") ) {

            this.backtoSubCategoryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().onBackPressed();
                }
            });
        } else {
            this.backtoSubCategoryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   destroySelf();
                }
            });


        } */

        return v;
    }



    // populate rows with fragments
    private void destroySelf () {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }


}
