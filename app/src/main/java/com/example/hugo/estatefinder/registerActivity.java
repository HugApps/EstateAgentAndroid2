package com.example.hugo.estatefinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hugo.estatefinder.API.Apicallback;
import com.example.hugo.estatefinder.API.apiCaller;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registerActivity extends AppCompatActivity {
    EditText nameField;
    EditText emailField;
    EditText passwordField;
    EditText confirmPassword;
    Button register;
    apiCaller Api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Api = apiCaller.getInstance(getApplicationContext());
        nameField = (EditText)findViewById(R.id.displayName);
        emailField=(EditText)findViewById(R.id.new_email);
        passwordField= (EditText)findViewById(R.id.confirm_password);
        register = (Button)findViewById(R.id.register_button);
        confirmPassword = (EditText)findViewById(R.id.confirm_password);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newEmail = emailField.getText().toString();
                String newPassword = passwordField.getText().toString();
                String displayName = nameField.getText().toString();
                if (displayName.length() <= 0) {
                    System.out.println("missing username");
                    showDialog("Missing Field User name","Missing Field");
                    //warn user to add a valid display name;
                    return;
                }

                if ( ! validateFields(newEmail)) {
                    System.out.println("missing email");
                    showDialog("Missing Field Email","Missing Field");
                    // email is not valid
                    // warn user to add a valid email
                    return;
                }

                if(newPassword.length() <= 0 || !newPassword.equals(confirmPassword.getText().toString()) ){
                    System.out.println("missing password, passwords dont match");
                    showDialog("Passwords do not match","Password Mismatch");
                    //warn user password not valid
                    return;
                }

                //pass all cases we can create new user by a call
                String jsonParams = "{display_name:" + displayName +
                                    ", email:" + newEmail +
                                    ", password:" + newPassword + "}";
                System.out.println(jsonParams);
                try {
                    Api.makePostRequest("Users/register", new JSONObject(jsonParams), new Apicallback() {
                        @Override
                        public JSONObject getApiResult(JSONObject response) {
                            System.out.println(response);
                            Intent myIntent = new Intent(registerActivity.this, MainMenu.class);
                            registerActivity.this.startActivity(myIntent);
                            return null;
                        }
                        @Override
                        public String getApiError(JSONObject response) {
                            System.out.println(response);
                            showDialog("Registration failed","Login Failed, Incorrect password or username");
                            return null;
                        }
                    });
                }catch(JSONException jsE) {
                    jsE.printStackTrace();
                    return;
                }


            }
        });






        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    private void showDialog (String message, String title){
        WarningDialog loginWarning = WarningDialog.newInstance(message,title,getApplicationContext());
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("login_dialog");

        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        loginWarning.show(ft,"register_dialog");
        // loginWarning.show(getSupportFragmentManager(),"login_dialog");
    }
    private boolean validateFields (String email ) {

        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);

        if (email.length() < 0 || email == null){
            return false;
        } else if (!m.find()) {
            return false;
        } else { return true;}
    }

}
