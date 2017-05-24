package com.example.hugo.estatefinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.hugo.estatefinder.API.Apicallback;
import com.example.hugo.estatefinder.API.User;
import com.example.hugo.estatefinder.API.apiCaller;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity {

    EditText emailField;
    EditText passwordField;
    Button registerButton;
    Button loginButton;
    public apiCaller LoginApI;
    public static User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LoginApI = apiCaller.getInstance(getApplicationContext());
        emailField  = (EditText)findViewById(R.id.emailField);
        passwordField = (EditText)findViewById(R.id.passwordField);
        registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proccedToRegister();
            }
        });

        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Carry out API call to login user
               if (passwordField.getText().length() > 0 && validateFields(emailField.getText().toString())) {
                    try {
                        String loginJson = "{email:" + emailField.getText()+", password:" + passwordField.getText() +"}";
                        JSONObject params = new JSONObject(loginJson);
                        JSONObject response = LoginApI.makePostRequest("Users/Login", params, new Apicallback() {
                            @Override
                            public JSONObject getApiResult(JSONObject response) {
                                System.out.println(response);
                                try {
                                     currentUser = new User(response.getString("email"),response.getString("password"));
                                     proccedToMain();
                                    }catch (JSONException je) {
                                       je.printStackTrace();
                                }
                                return null;
                            };

                            @Override
                            public String getApiError(JSONObject response) {
                                System.out.println(response);
                                // Make a Login failed message
                                try {
                                    String message = response.getString("message").toString();
                                    showDialog(message,"Login Failed, Incorrect password or username");
                                }catch (JSONException je){
                                    je.printStackTrace();
                                }

                                return response.toString();
                            }
                        });

                        //proccedToMain();

                    } catch (JSONException je) {
                        je.printStackTrace();
                        System.out.println("There is a error");
                    }
               } else {
                   showDialog("Please provide email and password","invalid fields");
               }
                //proccedToMain();
            }
        });


    }

    private void showDialog(String message, String title) {
         WarningDialog loginWarning = WarningDialog.newInstance(message,title,getApplicationContext());
          FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
          Fragment prev = getSupportFragmentManager().findFragmentByTag("login_dialog");

          if (prev != null) {
            ft.remove(prev);
          }
          ft.addToBackStack(null);

        // Create and show the dialog.
          loginWarning.show(ft,"login_dialog");
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

    // Transitions user to main menu after the validation is done
    private void proccedToMain (){

        Intent myIntent = new Intent(LoginActivity.this, MainMenu.class);
        LoginActivity.this.startActivity(myIntent);


    }
    private void proccedToRegister () {
        Intent myIntent = new Intent(LoginActivity.this, registerActivity.class);
        LoginActivity.this.startActivity(myIntent);

    }


}
