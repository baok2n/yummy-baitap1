package com.yummy.excercise1.service;

import com.yummy.excercise1.model.ProductCategoriesResponse;
import com.yummy.excercise1.model.ProductCategory;
import com.yummy.excercise1.model.ProductResponse;
import com.yummy.excercise1.model.ProductsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WooCommerceService {
    @GET("products/{id}")
    Call<ProductResponse> getProductById(@Path("id") int productId);

    @GET("products?filter[limit]=-1")
    Call<ProductsResponse> getListProduct();

    @GET("products/categories")
    Call<ProductCategoriesResponse> getListCategories();

    @GET("products/categories/{id}")
    Call<ProductCategory> getCategoryByID(@Path("id") int id);

    @GET("products?filter[category]=category_name")
    Call<ProductsResponse> getListProductByCategory(
            @Query("filter[category]") String categoryName
    );
}

