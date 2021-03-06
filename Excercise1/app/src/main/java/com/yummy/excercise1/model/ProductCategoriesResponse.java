
package com.yummy.excercise1.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductCategoriesResponse {

    @SerializedName("product_categories")
    @Expose
    private List<ProductCategory> productCategories = new ArrayList<ProductCategory>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public ProductCategoriesResponse() {
    }

    /**
     * 
     * @param productCategories
     */
    public ProductCategoriesResponse(List<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }

    /**
     * 
     * @return
     *     The productCategories
     */
    public List<ProductCategory> getProductCategories() {
        return productCategories;
    }

    /**
     * 
     * @param productCategories
     *     The product_categories
     */
    public void setProductCategories(List<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }

}
