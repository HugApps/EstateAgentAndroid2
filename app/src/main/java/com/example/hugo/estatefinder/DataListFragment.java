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

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
   ListView tablayout;
   ArrayList<String> servicesList;
   TextView backtoSubCategoryButton;
   TextView dialogButton;
    private String currentCategory;

    public DataListFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static DataListFragment newInstance(String cattype) {

        DataListFragment fragment = new DataListFragment();
        fragment.currentCategory = cattype;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.servicesList = new ArrayList<String>();
        servicesList.add("test service");
        servicesList.add("another services");
        servicesList.add("thats all for now");
        servicesList.add("a");
        servicesList.add("bob");



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_data_list,container,false);
        this.tablayout = (ListView) v.findViewById(R.id.servicedatalist);
        tablayout.setAdapter(new DataListAdapter(getContext(),R.layout.fragment_services_data_row,servicesList));

        this.backtoSubCategoryButton = (TextView)v.findViewById(R.id.backtosubs);
        this.dialogButton = (TextView) v.findViewById(R.id.filtersort);
        this.dialogButton.setClickable(true);
        this.backtoSubCategoryButton.setClickable(true);
        if(this.currentCategory.equals("BROWSE") || this.currentCategory.equals("Favorite") ) {

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


        }

        return v;
    }



    // populate rows with fragments
    private void destroySelf () {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }


}
