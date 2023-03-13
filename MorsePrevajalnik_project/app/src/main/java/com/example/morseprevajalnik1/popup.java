package com.example.morseprevajalnik1;

import android.os.Bundle;
import android.util.DisplayMetrics;

public class popup extends MainActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_window);

        //dolocanje prikaznih lastnosti prikaznega okna za pomoč
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int visina = dm.heightPixels;
        int sirina = dm.widthPixels;

        getWindow().setLayout((int)(sirina *0.8),(int)(visina *0.45));
    }
}
