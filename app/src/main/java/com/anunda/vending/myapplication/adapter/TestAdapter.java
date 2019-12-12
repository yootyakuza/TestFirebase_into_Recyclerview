package com.anunda.vending.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anunda.vending.myapplication.R;
import com.anunda.vending.myapplication.model.ProductModel;

import java.util.ArrayList;
import java.util.List;


public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> implements Filterable {

    private Context context;//for use in future
    private List<ProductModel> productObjects;
    private List<ProductModel> productObjectsTotal;

    public TestAdapter(Context context, List<ProductModel> productObjects) {
        this.context = context;
        this.productObjects = productObjects;
        this.productObjectsTotal = new ArrayList<>(productObjects);
    }

    @NonNull
    @Override
    public TestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_test, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.ViewHolder holder, int position) {
        holder.c_column.setText(productObjects.get(position).getColumn());
        holder.c_des.setText(productObjects.get(position).getDescription());
        holder.c_name.setText(productObjects.get(position).getName());
        holder.c_pic.setText(productObjects.get(position).getPicture());
        holder.c_price.setText(productObjects.get(position).getPrice());
        holder.c_pX.setText(productObjects.get(position).getProductX());
        holder.c_pY.setText(productObjects.get(position).getProductY());
        holder.c_row.setText(productObjects.get(position).getRow());
        holder.c_shelf.setText(productObjects.get(position).getShelf());
        holder.c_type.setText(productObjects.get(position).getType());
        holder.c_weight.setText(productObjects.get(position).getWeight());
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        if (productObjects != null && !productObjects.isEmpty()) {
            return productObjects.size();
        }
        return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView c_column;
        private TextView c_des;
        private TextView c_name;
        private TextView c_pic;
        private TextView c_price;
        private TextView c_pX;
        private TextView c_pY;
        private TextView c_row;
        private TextView c_shelf;
        private TextView c_type;
        private TextView c_weight;

        ViewHolder(View itemView) {
            super(itemView);
            this.c_column = itemView.findViewById(R.id.c_column);
            this.c_des = itemView.findViewById(R.id.c_des);
            this.c_name = itemView.findViewById(R.id.c_name);
            this.c_pic = itemView.findViewById(R.id.c_pic);
            this.c_price = itemView.findViewById(R.id.c_price);
            this.c_pX = itemView.findViewById(R.id.c_pX);
            this.c_pY = itemView.findViewById(R.id.c_pY);
            this.c_row = itemView.findViewById(R.id.c_row);
            this.c_shelf = itemView.findViewById(R.id.c_shelf);
            this.c_type = itemView.findViewById(R.id.c_type);
            this.c_weight = itemView.findViewById(R.id.c_weight);
        }
    }

    @Override
    public Filter getFilter() {
        return productFilter;
    }

    private Filter productFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ProductModel> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(productObjectsTotal);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
               /*getType Search term*/
                for(ProductModel productItem : productObjectsTotal){
                    if(productItem.getType().toLowerCase().contains(filterPattern)){
                        filteredList.add(productItem);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            productObjects.clear();
            productObjects.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
}
