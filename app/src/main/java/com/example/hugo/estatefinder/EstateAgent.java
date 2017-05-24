package com.example.hugo.estatefinder;

import android.app.Application;

import com.example.hugo.estatefinder.API.User;
import com.example.hugo.estatefinder.API.apiCaller;



/**
 * Created by Hugo on 2017-04-20.
 */

public class EstateAgent extends Application {

   // test commit
   private static apiCaller API ;
    private static EstateAgent sInstance;
    public User currentUser;
    @Override
    public void onCreate() {
        super.onCreate();
        apiCaller API = apiCaller.getInstance(this);
        System.out.println("APPLICATION STARTED");
        sInstance = this;
    }



    public synchronized static EstateAgent getInstance() {
        return sInstance;
    }

    public apiCaller getAPICaller () {
        return API;
    }

    public User getLoggedInUser () {
        return currentUser;
    }

    public void AssignUser(User newuser ) {
        currentUser = newuser;
    }

    public void removeUser() {
        currentUser = null;
    }
}
