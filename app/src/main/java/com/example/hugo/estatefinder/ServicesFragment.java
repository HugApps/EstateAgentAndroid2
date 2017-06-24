package com.example.hugo.estatefinder;

import android.accounts.NetworkErrorException;
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

import com.example.hugo.estatefinder.API.Apicallback;
import com.example.hugo.estatefinder.API.apiCaller;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// This is the general fragment that displays the fields related to a menu category
public class ServicesFragment extends Fragment {

    TabLayout tabs ;
    String type ;
    ViewPager tabpager;
    FragmentPager pager;
    private apiCaller apiCaller;
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
    public static ServicesFragment newInstance(String tag) {

        ServicesFragment fragment = new ServicesFragment();
        fragment.type=tag;
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


        // fetch child categories from this category type
        this.fetchCategories(this.type);



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
    private void setUpPager (FragmentPager pager ) {
        this.tabpager.setAdapter(pager);
        this.tabs.setupWithViewPager(tabpager);
    }

    private void fetchCategories  (String query) {
        final Gson gson = new Gson();
        final FragmentManager fragMan = getChildFragmentManager();
        apiCaller categoryAPI =  apiCaller.getInstance(getContext());
        categoryAPI.makeGetRequest("categories/children?name="+query, null, new Apicallback() {
                @Override
                public JSONObject getApiResult(JSONObject response) {

                    try {
                       String jsonString = response.get("data").toString();

                       System.out.println(response);
                       Categories[] catFromServer = gson.fromJson(jsonString,Categories[].class);
                       List<Categories> catsToDisplay = Arrays.asList(catFromServer);
                        pager = new FragmentPager(fragMan,catsToDisplay);
                        setUpPager(pager);

                    } catch (JsonSyntaxException jxe) {
                        jxe.printStackTrace();
                    } catch (JSONException je){
                        je.printStackTrace();
                    }

                    return null;
                }

                @Override
                public String getApiError(JSONObject response) {
                    System.out.println(response);
                    return null;
                }
            });

    }

}
