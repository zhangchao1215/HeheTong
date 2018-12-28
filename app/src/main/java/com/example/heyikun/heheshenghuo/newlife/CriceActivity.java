package com.example.heyikun.heheshenghuo.newlife;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.util.CriceView;

/**
 * Created by localadmin on 2018/1/14.
 */

public class CriceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crice);

        CriceView criceView = (CriceView) findViewById(R.id.crice_crice);

        criceView.setCriceColor(Color.parseColor("#21282D"));
        criceView.setCriceWidth(1);
        criceView.setCriceRadios(100);
        criceView.setStokeColor(Color.parseColor("#DB6958"));
        criceView.setStokeWidth(5);
        criceView.setPosition(50);
    }
}
