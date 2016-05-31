package com.yummy.excercise1.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yummy.excercise1.R;
import com.yummy.excercise1.model.Product;

import java.util.List;

public class ProductListByCategoryAdapter extends Adapter {

    private List<Product> productList;
    static final String PRE_DES_STRING = "Description: ";
    static final String PRE_NUMBER_OF_COMMENT_STRING = "No of comment(s): ";

    public List<Product> getProductList() {
        return productList;
    }

    public ProductListByCategoryAdapter(List<Product> productList)
    {
        this.productList = productList;
    }

    public List<Product> listProductByCategory() {
        return productList;
    }

    public static class CategoryViewHolder extends  RecyclerView.ViewHolder {

        public TextView textViewProductName;
        public TextView textViewProductNumberOfComment;
        public TextView textViewProductDescription;
        public ImageView imageViewProductImage;

        public CategoryViewHolder(View itemView)
        {
            super(itemView);
            textViewProductName = (TextView) itemView.findViewById(R.id.cell_product_name);
            textViewProductNumberOfComment = (TextView) itemView.findViewById(R.id.cell_number_of_comment);
            textViewProductDescription = (TextView) itemView.findViewById(R.id.cell_product_description);
            imageViewProductImage = (ImageView) itemView.findViewById(R.id.cell_product_image);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_product_list_by_category,parent,false);
        CategoryViewHolder vh = new CategoryViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CategoryViewHolder)holder).textViewProductName.setText(productList.get(position).getTitle());
        ((CategoryViewHolder)holder).textViewProductNumberOfComment.setText(PRE_NUMBER_OF_COMMENT_STRING + String.valueOf(productList.get(position).getRatingCount()));
        ((CategoryViewHolder)holder).textViewProductDescription.setText(PRE_DES_STRING + productList.get(position).getDescription());
        Picasso.with(holder.itemView.getContext())
                .load(productList.get(position).getImages().get(0).getSrc())
                .into(((CategoryViewHolder)holder).imageViewProductImage);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}
