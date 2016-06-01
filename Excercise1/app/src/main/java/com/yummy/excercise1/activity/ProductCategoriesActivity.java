package com.yummy.excercise1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.yummy.excercise1.R;
import com.yummy.excercise1.adapter.CategoriesListAdapter;
import com.yummy.excercise1.model.ProductCategoriesResponse;
import com.yummy.excercise1.service.ServiceGenerator;
import com.yummy.excercise1.service.WooCommerceService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductCategoriesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CategoriesListAdapter categoriesListAdapter;
    RecyclerView.LayoutManager recyclerViewManager;

    Toolbar productCategoriesToolbar;
    public final static String CATEGORY_NAME = "com.yummy.excercise1.CATEGORY_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_list);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_category_list);
        recyclerView.setHasFixedSize(true);
        recyclerViewManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewManager);

        productCategoriesToolbar = (Toolbar) findViewById(R.id.tool_bar);
        productCategoriesToolbar.inflateMenu(R.menu.product_category_menu_item);
        productCategoriesToolbar.setTitle("Product Categories");
        setSupportActionBar(productCategoriesToolbar);

        WooCommerceService service =  ServiceGenerator.createService(WooCommerceService.class);
        Call<ProductCategoriesResponse> categoriesResponseCall = service.getListCategories();

        categoriesResponseCall.enqueue(new Callback<ProductCategoriesResponse>() {
            @Override
            public void onResponse(Call<ProductCategoriesResponse> call, Response<ProductCategoriesResponse> response) {
                ProductCategoriesResponse categoriesResponse = response.body();
                categoriesListAdapter = new CategoriesListAdapter(categoriesResponse.getProductCategories(), ProductCategoriesActivity.this);
                recyclerView.setAdapter(categoriesListAdapter);
            }
            @Override
            public void onFailure(Call<ProductCategoriesResponse> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });

    }
}
