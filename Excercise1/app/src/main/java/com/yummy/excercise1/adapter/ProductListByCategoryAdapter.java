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
import com.yummy.excercise1.activity.ProductDetailActivity;
import com.yummy.excercise1.activity.ProductListByCategoryActivity;
import com.yummy.excercise1.library.ResuableFunctions;
import com.yummy.excercise1.model.Product;

import java.util.List;

public class ProductListByCategoryAdapter extends RecyclerView.Adapter {

    private List<Product> productList;
    private Context mContext;

    static final String PRE_DES_STRING = "Description: ";
    static final String PRE_NUMBER_OF_COMMENT_STRING = "No of comment(s): ";

    public List<Product> getProductList() {
        return productList;
    }

    public ProductListByCategoryAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.mContext = context;
    }

    public static class CategoryViewHolder extends  RecyclerView.ViewHolder {

        public TextView textViewProductName;
        public TextView textViewProductNumberOfComment;
        public TextView textViewProductDescription;
        public ImageView imageViewProductImage;
        public CardView productView;

        public CategoryViewHolder(View itemView)
        {
            super(itemView);
            textViewProductName = (TextView) itemView.findViewById(R.id.cell_product_name);
            textViewProductNumberOfComment = (TextView) itemView.findViewById(R.id.cell_number_of_comment);
            textViewProductDescription = (TextView) itemView.findViewById(R.id.cell_product_description);
            imageViewProductImage = (ImageView) itemView.findViewById(R.id.cell_product_image);
            productView = (CardView) itemView.findViewById(R.id.cell_product);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_product_list_by_category,parent,false);
        CategoryViewHolder vh = new CategoryViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((CategoryViewHolder)holder).textViewProductName.setText(productList.get(position).getTitle());
        ((CategoryViewHolder)holder).textViewProductNumberOfComment.setText(PRE_NUMBER_OF_COMMENT_STRING + String.valueOf(productList.get(position).getRatingCount()));
        ((CategoryViewHolder)holder).textViewProductDescription.setText(PRE_DES_STRING + ResuableFunctions.removeHtmlTag(productList.get(position).getShortDescription()));
//        Picasso.with(holder.itemView.getContext())
//                .load(productList.get(position).getImages().get(0).getSrc())
//                .into(((CategoryViewHolder)holder).imageViewProductImage);
        Glide.with(mContext)
                .load(productList.get(position).getImages().get(0).getSrc())
                .into(((CategoryViewHolder)holder).imageViewProductImage);

        ((CategoryViewHolder)holder).productView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProductDetailActivity.class);
                intent.putExtra(ProductListByCategoryActivity.PRODUCT_ID, productList.get(position).getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}
