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

import com.example.hugo.estatefinder.API.Apicallback;
import com.example.hugo.estatefinder.API.Services;
import com.example.hugo.estatefinder.API.apiCaller;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServicesListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServicesListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private Categories currentCategory;
    ListView servicesList;
    FrameLayout infoPane;
    TextView backButton;
    TextView filterSortButton;
    List<Categories> subCategories;
    List<String> subCategoryNames;
    private com.example.hugo.estatefinder.API.apiCaller apiCaller;
    public ServicesListFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ServicesListFragment newInstance(Categories cattype) {

        ServicesListFragment fragment = new ServicesListFragment();
        fragment.currentCategory = cattype;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.fetchCategories(this.currentCategory.name);

    }

    private List<String> getNames(List<Categories> cats){
        List<String> names = new ArrayList<String>();
        for(Categories c : cats) {
            names.add(c.name);
        }
        return (List<String>)names;
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
                    Categories[] catFromServer = gson.fromJson(jsonString,Categories[].class);
                    List<Categories> catsToDisplay = Arrays.asList(catFromServer);
                    subCategories = catsToDisplay;
                    subCategoryNames = getNames(subCategories);
                    System.out.println(subCategories);
                    servicesList.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,subCategoryNames));

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


    private void fetchServices  (String query) {
        final Gson gson = new Gson();
        final FragmentManager fragMan = getChildFragmentManager();
        apiCaller categoryAPI =  apiCaller.getInstance(getContext());
        categoryAPI.makeGetRequest("categories/services?id="+query, null, new Apicallback() {
            @Override
            public JSONObject getApiResult(JSONObject response) {

                try {
                    String jsonString = response.get("data").toString();
                    System.out.println(jsonString);
                    Services[] servicesFromCat = gson.fromJson(jsonString,Services[].class);
                    LoadInfoPane(servicesFromCat);

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
                WarningDialog loginWarning = WarningDialog.newInstance("No services found","serverError",getContext());
                FragmentTransaction ft =fragMan.beginTransaction();
                Fragment prev =fragMan.findFragmentByTag("service_dialog");

                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);

                // Create and show the dialog.
                loginWarning.show(ft,"service_dialog");
                return null;
            }
        });

    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_services_list, container, false);
        this.infoPane = (FrameLayout)v.findViewById(R.id.infopane);
        this.servicesList = (ListView) v.findViewById(R.id.serviceslist);

        this.servicesList.setClickable(true);
        this.backButton = (TextView) v.findViewById(R.id.backtosubs);
        this.filterSortButton = (TextView) v.findViewById(R.id.filtersort);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        this.servicesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // When clicked on a subcategory, intiate a DataListFragment
                // LoadInfoPane should make server call to get list of services
                fetchServices(subCategories.get(i)._id);
            }
        });
        return v;
    }

    private void LoadInfoPane (Services[] services){

        FragmentManager fm = getChildFragmentManager();
        DataListFragment datafrag = new DataListFragment().newInstance(services);
        FragmentTransaction ft = fm.beginTransaction();
        //ft.add(datafrag,"subcategory");
        ft.replace(R.id.infopane,datafrag);

        ft.addToBackStack("subcategory");
        ft.commit();
    }






}
