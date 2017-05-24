package com.example.hugo.estatefinder;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Hugo on 2017-05-22.
 */

public  class WarningDialog extends android.support.v4.app.DialogFragment {
     String mMessage;
     String mTitle;
     TextView warningMessage ;
     TextView warningTitle;
     TextView confirmButton;
     Context currentActivity;
     static WarningDialog newInstance(String message, String title, Context context) {
         WarningDialog currentDialog = new WarningDialog();
         Bundle args = new Bundle();
         args.putString("title",title);
         args.putString("message",message);
         currentDialog.setArguments(args);
         currentDialog.currentActivity = context;
         return currentDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMessage = getArguments().getString("title");
        mTitle = getArguments().getString("message");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.warning_dialog,container,false);
        warningTitle = (TextView)v.findViewById(R.id.dialog_title);
        warningMessage = (TextView) v.findViewById(R.id.dialog_message);
        confirmButton = (TextView) v.findViewById(R.id.dialog_confirm);
        warningTitle.setText(this.mTitle);
        warningMessage.setText(this.mMessage);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dismiss();
            }
        });
        return v;//super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }
}
