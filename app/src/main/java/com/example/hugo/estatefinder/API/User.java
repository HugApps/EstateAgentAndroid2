package com.example.hugo.estatefinder.API;

import java.util.ArrayList;

/**
 * Created by Hugo on 2017-04-03.
 */
// Represntation of user signing on
public  class User {

    String email;
    ArrayList<Services> favoriteList;
    String address;
    String password;



    // once authenticated we create a user object
    public User (String  email , String password) {
        this.email=email;
        this.password =password;


    }
}
