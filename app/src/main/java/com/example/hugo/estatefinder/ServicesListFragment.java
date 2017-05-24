package com.example.hugo.estatefinder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServicesListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServicesListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private categories currentCategory;
    ListView servicesList;
    FrameLayout infoPane;
    TextView backButton;
    TextView filterSortButton;
    ArrayList<String> subCategories;
    public ServicesListFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ServicesListFragment newInstance(categories cattype) {

        ServicesListFragment fragment = new ServicesListFragment();
        fragment.currentCategory = cattype;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.subCategories = new ArrayList<String>();
        // Depending on the category we have different sub categories of services, for now we make up phoney ones
        this.subCategories.add("First category");
        this.subCategories.add("second category");
        this.subCategories.add("Third category");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_services_list, container, false);
        this.infoPane = (FrameLayout)v.findViewById(R.id.infopane);
        this.servicesList = (ListView) v.findViewById(R.id.serviceslist);
        this.servicesList.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,this.subCategories));
        this.servicesList.setClickable(true);
        this.backButton = (TextView) v.findViewById(R.id.backtosubs);
        this.filterSortButton = (TextView) v.findViewById(R.id.filtersort);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        this.servicesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // When clicked on a subcategory, intiate a DataListFragment
                LoadInfoPane(subCategories.get(i));
            }
        });
        return v;
    }

    private void LoadInfoPane (String subcategory){

        FragmentManager fm = getChildFragmentManager();
        DataListFragment datafrag = new DataListFragment().newInstance(subcategory);
        FragmentTransaction ft = fm.beginTransaction();
       // ft.add(datafrag,subcategory);
        ft.replace(this.infoPane.getId(),datafrag);
        ft.addToBackStack(subcategory);
        ft.commit();
    }






}
