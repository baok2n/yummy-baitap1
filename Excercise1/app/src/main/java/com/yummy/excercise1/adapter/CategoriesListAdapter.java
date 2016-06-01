package com.yummy.excercise1.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yummy.excercise1.R;
import com.yummy.excercise1.activity.ProductCategoriesActivity;
import com.yummy.excercise1.activity.ProductListByCategoryActivity;
import com.yummy.excercise1.model.ProductCategory;

import java.util.List;

/**
 * Created by Tri Nguyen on 5/30/2016.
 */
public class CategoriesListAdapter extends RecyclerView.Adapter {

    private List<ProductCategory> categoryList;
    private Context mContext;

    static final String PRE_DES_STRING = "Description: ";
    static final String PRE_QUAN_STRING = "Quantity: ";

    public CategoriesListAdapter(List<ProductCategory> categoryList, Context context) {
        this.categoryList = categoryList;
        this.mContext = context;
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        public TextView categoryNameTextView;
        public TextView categoryQuantityTextView;
        public TextView categoryDescriptionTextView;
        public ImageView categoryImageView;
        public CardView categoryView;

        public CategoryViewHolder(View itemView)
        {
            super(itemView);
            categoryView = (CardView) itemView.findViewById(R.id.cell_category);
            categoryNameTextView = (TextView) itemView.findViewById(R.id.cell_category_name);
            categoryQuantityTextView = (TextView) itemView.findViewById(R.id.cell_category_quantity);
            categoryDescriptionTextView = (TextView) itemView.findViewById(R.id.cell_category_description);
            categoryImageView = (ImageView) itemView.findViewById(R.id.cell_category_image);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_product_category,parent,false);
        CategoryViewHolder vh = new CategoryViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((CategoryViewHolder)holder).categoryNameTextView.setText(categoryList.get(position).getName());
        ((CategoryViewHolder)holder).categoryQuantityTextView.setText(PRE_QUAN_STRING + String.valueOf(categoryList.get(position).getCount()));
        ((CategoryViewHolder)holder).categoryDescriptionTextView.setText(PRE_DES_STRING + categoryList.get(position).getDescription());
//        Picasso.with(holder.itemView.getContext())
//                .load(categoryList.get(position).getImage())
//                .into(((CategoryViewHolder)holder).categoryImageView);
        Glide.with(mContext)
                .load(categoryList.get(position).getImage())
                .into(((CategoryViewHolder)holder).categoryImageView);

        ((CategoryViewHolder)holder).categoryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProductListByCategoryActivity.class);
                intent.putExtra(ProductCategoriesActivity.CATEGORY_NAME, categoryList.get(position).getName());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

}
