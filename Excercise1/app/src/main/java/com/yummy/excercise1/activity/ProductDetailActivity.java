package com.yummy.excercise1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yummy.excercise1.R;
import com.yummy.excercise1.model.Product;
import com.yummy.excercise1.service.ServiceGenerator;
import com.yummy.excercise1.service.WooCommerceService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        String productId = "99";

        WooCommerceService service = ServiceGenerator.createService(WooCommerceService.class);
        Call<Product> productCall = service.getProductById();
       // Call<ProductsResponse> productCall = service.getListProduct();
        productCall.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                System.out.println(response.body());
                Product product = response.body();
                System.out.println("Id: " + product.getId());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                System.err.println(t.getMessage());
            }
        });

    }
}
