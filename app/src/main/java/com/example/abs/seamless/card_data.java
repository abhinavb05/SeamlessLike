package com.example.abs.seamless;

import android.graphics.Bitmap;

/**
 * Created by Ab's on 23-02-2017.
 */

public class card_data {
    public String name;
    public String spl;
    public String logo;

    public card_data(){}
    public card_data(String logo,String name,String spl){
        this.name=name;
        this.spl=spl;
        this.logo=logo;
    }
}
