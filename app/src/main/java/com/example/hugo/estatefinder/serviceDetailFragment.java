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
import android.widget.TextView;

// displays buisness details a
public class serviceDetailFragment extends Fragment {

    TextView phoneNum , description,email,address,price,hours,companyName;
    ImageView companyLogo;
    Button commentButton,saveFavButton,contactButton;


    public serviceDetailFragment() {
        // Required empty public constructor
    }


    public static serviceDetailFragment newInstance() {
        serviceDetailFragment fragment = new serviceDetailFragment();

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
        this.phoneNum = (TextView) v.findViewById(R.id.phoneNum);
        this.phoneNum.setClickable(true);
        this.phoneNum.setText("604-939-3233");
        this.phoneNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phoneContactIntent = new Intent (Intent.ACTION_DIAL);
                // set the phone number as the one used in the textField
                phoneContactIntent.setData(Uri.parse("tel:6049393233"));
                startActivity(phoneContactIntent);
            }
        });
        this.email = (TextView) v.findViewById(R.id.email);
        this.email.setClickable(true);
        this.email.setText("hugoc@evidentpoint.com");
        this.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent ( Intent.ACTION_SENDTO);
                emailIntent.putExtra(Intent.EXTRA_EMAIL ,email.getText());
                emailIntent.setData(Uri.parse("mailto:"));
                startActivity(emailIntent);

            }
        });

        this.address =(TextView) v. findViewById(R.id.maplocation);
        this.address.setText("1655 Charland Ave , Coquitlam BC, Canada");
        this.address.setClickable(true);
        this.address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri location = Uri.parse("geo:0,0?q=1655 charland ave, coquitlam BC, Canada");
                Intent mapIntent = new Intent( Intent.ACTION_VIEW,location);
                startActivity(mapIntent);

            }
        });

        return v;
    }


}
