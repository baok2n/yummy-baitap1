package com.yummy.excercise1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yummy.excercise1.R;

public class MainActivity extends AppCompatActivity {

    public final static String PRODUCT_ID = "com.yummy.excercise1.PRODUCT_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra(PRODUCT_ID, "96");
        startActivity(intent);
    }
}
