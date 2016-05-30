package com.yummy.excercise1.service;

import com.yummy.excercise1.model.Product;
import com.yummy.excercise1.model.ProductsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WooCommerceService {
    @GET("/wc-api/v3/products/99/")
    Call<Product> getProductById();

    @GET("products/")
    Call<ProductsResponse> getListProduct();
}
