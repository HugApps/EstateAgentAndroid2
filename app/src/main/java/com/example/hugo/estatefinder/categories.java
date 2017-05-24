package com.example.hugo.estatefinder;

/**
 * Created by Hugo on 2017-02-19.
 */

public class categories {
    String label;
    cats type;
    public categories (String label, cats type ) {
        this.label=label;
        this.type=type;


    }

    public cats getCategory() {
        return this.type;
    }
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
