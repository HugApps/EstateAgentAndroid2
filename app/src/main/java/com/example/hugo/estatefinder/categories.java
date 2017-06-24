package com.example.hugo.estatefinder;

import com.example.hugo.estatefinder.API.Services;

import java.util.List;

/**
 * Created by Hugo on 2017-02-19.
 */

public class Categories {
    String _id;
    String name;
    String parent;
    //Sub children returned as a list of ids;
    List<String> children;
    List<String> services;

    protected static enum cats {
            ALL,
            BUY,
            SELL,
            IMPROVEMENTS,
            REPAIRS,
            LANDSCAPE,
            HANDYMAN,
            FAVORITES,
        }





}
