package com.yummy.excercise1.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yummy.excercise1.R;
import com.yummy.excercise1.model.ProductCategory;

import java.util.List;

/**
 * Created by Tri Nguyen on 5/30/2016.
 */
public class CategoriesListAdapter extends Adapter {
    private List<ProductCategory> aCategory;
    static final String PRE_DES_STRING = "Description: ";
    static final String PRE_QUAN_STRING = "Quantity: ";

    public CategoriesListAdapter(List<ProductCategory> aCategory)
    {
        this.aCategory = aCategory;
    }

    public static class CategoryViewHolder extends  RecyclerView.ViewHolder{

        TextView categoryNameTextView;
        TextView categoryQuantityTextView;
        TextView categoryDescriptionTextView;
        ImageView categoryImageView;
        CardView categoryView;

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CategoryViewHolder)holder).categoryNameTextView.setText(aCategory.get(position).getName());
        ((CategoryViewHolder)holder).categoryQuantityTextView.setText(PRE_QUAN_STRING + String.valueOf(aCategory.get(position).getCount()));
        ((CategoryViewHolder)holder).categoryDescriptionTextView.setText(PRE_DES_STRING + aCategory.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return aCategory.size();
    }
}
