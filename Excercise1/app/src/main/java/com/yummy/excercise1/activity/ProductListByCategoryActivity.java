package com.yummy.excercise1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.yummy.excercise1.R;
import com.yummy.excercise1.adapter.ProductListByCategoryAdapter;
import com.yummy.excercise1.model.ProductsResponse;
import com.yummy.excercise1.service.ServiceGenerator;
import com.yummy.excercise1.service.WooCommerceService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListByCategoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductListByCategoryAdapter productListByCategoryAdapter;
    RecyclerView.LayoutManager recyclerViewManager;
    public final static String PRODUCT_ID = "com.yummy.excercise1.PRODUCT_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list_by_category);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar_in_product_by_category);
        setSupportActionBar(myToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String categoryName = intent.getStringExtra(ProductCategoriesActivity.CATEGORY_NAME);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_product_list_by_category);
        recyclerView.setHasFixedSize(true);
        recyclerViewManager = new LinearLayoutManager(this);

        WooCommerceService service =  ServiceGenerator.createService(WooCommerceService.class);
        Call<ProductsResponse> productsResponseCall = service.getListProductByCategory(categoryName);

        productsResponseCall.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                ProductsResponse productsResponse = response.body();
                productListByCategoryAdapter = new ProductListByCategoryAdapter(productsResponse.getProducts(), ProductListByCategoryActivity.this);
                recyclerView.setLayoutManager(recyclerViewManager);
                recyclerView.setAdapter(productListByCategoryAdapter);
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return true;
    }
}
