package com.example.hugo.estatefinder;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


// This is the general fragment that displays the fields related to a menu category
public class ServicesFragment extends Fragment {

    TabLayout tabs ;
    MainMenu.currentFrags selectedFrag;
    String type ;
    ViewPager tabpager;
    FragmentPager pager;
    public ServicesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment dataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ServicesFragment newInstance(MainMenu.currentFrags type) {

        ServicesFragment fragment = new ServicesFragment();
        fragment.selectedFrag = type;
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_services,container, false);
        tabs = (TabLayout)v.findViewById(R.id.tablayout);
        tabpager = (ViewPager)v.findViewById(R.id.view_pager);
        FragmentManager fragMan = getChildFragmentManager();
        this.pager = new FragmentPager(fragMan,this.selectedFrag,this.populateTabHeadings(this.selectedFrag));

        tabpager.setAdapter(pager);
        tabs.setupWithViewPager(tabpager);


        return v;
    }

    public boolean jumpToTab(){
        int currentIndex = this.tabpager.getCurrentItem();
        if (currentIndex == 0 ) {
            return false;
        }

        System.out.println(currentIndex);
        int newIndex = currentIndex -1 ;
        this.tabpager.setCurrentItem(newIndex);
        return true;

    }
    private ArrayList<categories> populateTabHeadings (MainMenu.currentFrags s) {
        // Take
        // s in the flag that determine which services tabhost to render

        switch (s) {
            case ESTATE:
                 categories[] estateTitles = {new categories("All", categories.cats.ALL),
                                              new categories("Buy/Rent", categories.cats.BUY),
                                              new categories("Sell",categories.cats.SELL)};
                 return new ArrayList<categories>(Arrays.asList(estateTitles));

            case RENOVATION:
                categories[] renoTitles =  {new categories("All", categories.cats.ALL),
                                              new categories("Repairs", categories.cats.REPAIRS),
                                             new categories("Improvements",categories.cats.IMPROVEMENTS),
                                            new categories("HandyMan", categories.cats.HANDYMAN),
                                            new categories("Landscaping", categories.cats.LANDSCAPE)};
                return new ArrayList<categories>(Arrays.asList(renoTitles));



        }
        return null;

    }

}
