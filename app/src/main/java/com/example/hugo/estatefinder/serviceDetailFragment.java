package com.example.hugo.estatefinder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.hugo.estatefinder.API.Services;

// displays buisness details a
public class serviceDetailFragment extends Fragment {

    TextView phoneNum , description,email,address,price,hours,companyName;
    ImageView companyLogo;
    RatingBar avgRating;
    Button commentButton,saveFavButton,contactButton,rateButton;
    Services currentService;


    public serviceDetailFragment() {
        // Required empty public constructor
    }


    public static serviceDetailFragment newInstance(Services service) {
        serviceDetailFragment fragment = new serviceDetailFragment();
        fragment.currentService=service;
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
        View v = inflater.inflate(R.layout.fragment_service_detail, container,false);
        this.avgRating = (RatingBar) v.findViewById(R.id.ratingBar);
        this.rateButton = (Button) v.findViewById(R.id.rateButton);
        this.phoneNum = (TextView) v.findViewById(R.id.phoneNum);
        this.companyName = (TextView) v.findViewById(R.id.detailTitle);
        this.description= (TextView) v.findViewById(R.id.description);
        this.price = (TextView) v.findViewById(R.id.priceLabel);
        this.hours = (TextView) v.findViewById(R.id.hoursLabel);
        this.email = (TextView) v.findViewById(R.id.email);
        this.address =(TextView) v. findViewById(R.id.maplocation);

        bindValues();
        bindActions();

        return v;
    }


    private void bindValues (){
        this.companyName.setText(currentService.name);
        this.phoneNum.setText(currentService.phone);
        this.email.setText(currentService.email);
        this.address.setText(currentService.address);
        //this.avgRating.setRating(Integer.parseInt(currentService.avgRating));
        this.description.setText(currentService.description);
        this.price.setText(currentService.price);
        this.hours.setText(currentService.email);
    }

    private void bindActions (){


        this.avgRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

            }
        });
        this.email.setClickable(true);
        this.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent ( Intent.ACTION_SENDTO);
                emailIntent.putExtra(Intent.EXTRA_EMAIL ,email.getText());
                emailIntent.setData(Uri.parse("mailto:"));
                startActivity(emailIntent);

            }
        });

        this.phoneNum.setClickable(true);
        this.phoneNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phoneContactIntent = new Intent (Intent.ACTION_DIAL);
                // set the phone number as the one used in the textField
                phoneContactIntent.setData(Uri.parse("tel:" +currentService.phone));
                startActivity(phoneContactIntent);
            }
        });


        this.address.setClickable(true);
        this.address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Location object

                Uri location = Uri.parse("geo:0,0?z=21&q="+currentService.address);
                Intent mapIntent = new Intent( Intent.ACTION_VIEW,location);
                startActivity(mapIntent);

            }
        });

    }

}
