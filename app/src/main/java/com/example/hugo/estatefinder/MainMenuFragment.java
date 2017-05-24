package com.example.hugo.estatefinder;import android.content.Context;import android.net.Uri;import android.os.Bundle;import android.support.v4.app.Fragment;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.ImageButton;/** * A simple {@link Fragment} subclass. * Activities that contain this fragment must implement the * {@link MainMenuFragment.OnFragmentInteractionListener} interface * to handle interaction events. * Use the {@link MainMenuFragment#newInstance} factory method to * create an instance of this fragment. */public class MainMenuFragment extends Fragment {    private OnFragmentInteractionListener mListener;    ImageButton favoritesButton,estatesButton,renovationsButton,allButton;    public MainMenuFragment() {        // Required empty public constructor    }    // TODO: Rename and change types and number of parameters    public static MainMenuFragment newInstance(String param1, String param2) {        MainMenuFragment fragment = new MainMenuFragment();        return fragment;    }    @Override    public void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);    }    @Override    public View onCreateView(LayoutInflater inflater, ViewGroup container,                             Bundle savedInstanceState) {        // Inflate the layout for this fragment        View v = inflater.inflate(R.layout.fragment_main_menu, container, false);        this.favoritesButton = (ImageButton)v.findViewById(R.id.favorite_btn);        this.estatesButton = (ImageButton) v.findViewById(R.id.estate_btn);        this.renovationsButton = (ImageButton) v.findViewById(R.id.renovations_btn);        this.allButton = (ImageButton) v.findViewById(R.id.browse_btn);        this.intiateMenuButtons();        return v;    }    // TODO: Rename method, update argument and hook method into UI event    public void intiateMenuButtons (){        this.favoritesButton.setOnClickListener( new navigateOnClickListener(getResources().getString(R.string.favs_menu_fragment)));        this.estatesButton.setOnClickListener( new navigateOnClickListener(getResources().getString(R.string.estate_menu_fragment)));        this.renovationsButton.setOnClickListener( new navigateOnClickListener(getResources().getString(R.string.renovation_menu_fragment)));        this.allButton.setOnClickListener( new navigateOnClickListener(getResources().getString(R.string.browse_menu_fragment)));    }    @Override    public void onAttach(Context context) {        super.onAttach(context);    }    @Override    public void onDetach() {        super.onDetach();        mListener = null;    }    /**     * This interface must be implemented by activities that contain this     * fragment to allow an interaction in this fragment to be communicated     * to the activity and potentially other fragments contained in that     * activity.     * <p>     * See the Android Training lesson <a href=     * "http://developer.android.com/training/basics/fragments/communicating.html"     * >Communicating with Other Fragments</a> for more information.     */    public interface OnFragmentInteractionListener {        // TODO: Update argument type and name        void onFragmentInteraction(Uri uri);    }    public class navigateOnClickListener implements View.OnClickListener{        String tag;        public navigateOnClickListener(String tag){            this.tag = tag;        }        @Override        public void onClick(View view) {            ( (MainMenu)getActivity()).LoadFragment(tag);        }    }}