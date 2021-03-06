package com.yummy.excercise1.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yummy.excercise1.R;
import com.yummy.excercise1.adapter.ViewPagerAdapter;
import com.yummy.excercise1.library.ResuableFunctions;
import com.yummy.excercise1.model.Image;
import com.yummy.excercise1.model.Product;
import com.yummy.excercise1.model.ProductResponse;
import com.yummy.excercise1.service.ServiceGenerator;
import com.yummy.excercise1.service.WooCommerceService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity{

    private ViewPager viewPager;
    private FragmentStatePagerAdapter adapter;
    private LinearLayout thumbnailsContainer;

    private TextView textViewProductName;
    private RatingBar ratingBar;
    private TextView textViewNumberOfComment;
    private TextView textViewRegularPrice;
    private TextView textViewPrice;
    private TextView textViewDescription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        final Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.view_pager_product_detail);
        thumbnailsContainer = (LinearLayout) findViewById(R.id.container);

        textViewProductName = (TextView) findViewById(R.id.text_view_product_name_in_product_detail);
        ratingBar = (RatingBar) findViewById(R.id.rating_bar_in_product_detail);
        textViewNumberOfComment = (TextView) findViewById(R.id.text_view_number_of_comment);
        textViewRegularPrice = (TextView) findViewById(R.id.text_view_regular_price_in_product_detail);
        textViewPrice = (TextView) findViewById(R.id.text_view_price_in_product_detail);
        textViewDescription = (TextView) findViewById(R.id.text_view_description_in_product_detail);

        Intent intent = getIntent();
        int productId = intent.getIntExtra(ProductListByCategoryActivity.PRODUCT_ID, -1);

        WooCommerceService service = ServiceGenerator.createService(WooCommerceService.class);
        Call<ProductResponse> productsResponseCall = service.getProductById(productId);
        productsResponseCall.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    ProductResponse productResponse = response.body();
                    Product product = productResponse.getProduct();
                    myToolbar.setTitle(product.getTitle());
                    displayProduct(product);
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

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

    private void displayProduct(Product product) {
        // init viewpager adapter and attach
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), product.getImages());
        viewPager.setAdapter(adapter);
        inflateThumbnails(product.getImages());
        textViewProductName.setText(product.getTitle());
        ratingBar.setRating(Float.parseFloat(product.getAverageRating()));
        textViewNumberOfComment.setText("(" + product.getRatingCount() + " reviews)");
        textViewNumberOfComment.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        if (product.getSalePrice() != null) {
            textViewRegularPrice.setText("$" + product.getRegularPrice());
            textViewRegularPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }else {
            textViewRegularPrice.setText("");
        }
        textViewPrice.setText("$" + product.getPrice());
        textViewDescription.setText(ResuableFunctions.removeHtmlTag(product.getDescription()));
    }

    private void inflateThumbnails(List<Image> listImage) {
        for (int i = 0; i < listImage.size(); i++) {
            View imageLayout = getLayoutInflater().inflate(R.layout.item_image, null);
            ImageView imageView = (ImageView) imageLayout.findViewById(R.id.img_thumb);
            imageView.setOnClickListener(onChangePageClickListener(i));
            //Picasso.with(this.getBaseContext()).load(listImage.get(i).getSrc()).into(imageView);
            Glide.with(ProductDetailActivity.this).load(listImage.get(i).getSrc()).into(imageView);
            thumbnailsContainer.addView(imageLayout);
        }
    }

    private View.OnClickListener onChangePageClickListener(final int i) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(i);
            }
        };
    }

}
