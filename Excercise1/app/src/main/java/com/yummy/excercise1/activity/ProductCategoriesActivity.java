package com.yummy.excercise1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.yummy.excercise1.R;
import com.yummy.excercise1.adapter.CategoriesListAdapter;
import com.yummy.excercise1.adapter.RecyclerItemClickListener;
import com.yummy.excercise1.model.ProductCategoriesResponse;
import com.yummy.excercise1.model.ProductCategory;
import com.yummy.excercise1.service.ServiceGenerator;
import com.yummy.excercise1.service.WooCommerceService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductCategoriesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CategoriesListAdapter categoriesListAdapter;
    RecyclerView.LayoutManager recyclerViewManager;
    public final static String CATEGORY_NAME = "com.yummy.excercise1.CATEGORY_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_list);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_category_list);
        recyclerView.setHasFixedSize(true);
        recyclerViewManager = new LinearLayoutManager(this);

        WooCommerceService service =  ServiceGenerator.createService(WooCommerceService.class);
        Call<ProductCategoriesResponse> categoriesResponseCall = (Call<ProductCategoriesResponse>)service.getListCategories();

        categoriesResponseCall.enqueue(new Callback<ProductCategoriesResponse>() {
            @Override
            public void onResponse(Call<ProductCategoriesResponse> call, Response<ProductCategoriesResponse> response) {
                ProductCategoriesResponse categoriesResponse = response.body();
                categoriesListAdapter = new CategoriesListAdapter(categoriesResponse.getProductCategories());
                recyclerView.setLayoutManager(recyclerViewManager);
                recyclerView.setAdapter(categoriesListAdapter);
                Log.e("Debug", String.valueOf(categoriesResponse.getProductCategories().size()));
            }
            @Override
            public void onFailure(Call<ProductCategoriesResponse> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this.getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // Handle item click
                        ProductCategory productCategory = categoriesListAdapter.getaCategory().get(position);
                        Intent intent = new Intent(view.getContext(), ProductListByCategoryActivity.class);
                        intent.putExtra(CATEGORY_NAME, productCategory.getName());
                        startActivity(intent);
                    }
                })
        );
    }
}
