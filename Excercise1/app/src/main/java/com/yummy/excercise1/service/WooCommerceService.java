package com.yummy.excercise1.service;

import com.yummy.excercise1.model.Product;
import com.yummy.excercise1.model.ProductsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WooCommerceService {
    @GET("products/{id}")
    Call<Product> getProductById(@Path("id") String productId);

    @GET("products")
    Call<ProductsResponse> getListProduct();
}
