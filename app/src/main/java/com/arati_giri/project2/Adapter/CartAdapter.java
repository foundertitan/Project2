package com.arati_giri.project2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arati_giri.project2.Domain.ItemsDomain;
import com.arati_giri.project2.Helper.ChangeNumberItemsListener;
import com.arati_giri.project2.Helper.ManagmentCart;
import com.arati_giri.project2.databinding.ViewholderCartBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Viewholder> {

    ArrayList<ItemsDomain> listItemSelected;
    ChangeNumberItemsListener changeNumberItemsListener;
    private ManagmentCart managmentCart;

    public CartAdapter(ArrayList<ItemsDomain> listItemSelected, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.listItemSelected = listItemSelected;
        this.changeNumberItemsListener = changeNumberItemsListener;
        managmentCart = new ManagmentCart(context);
    }

    @NonNull
    @Override
    public CartAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewholderCartBinding binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.Viewholder holder, int position) {
        holder.binding.titleTxt.setText(listItemSelected.get(position).getTitle());
        holder.binding.feeEachItem.setText("$" +listItemSelected.get(position).getPrice());
        holder.binding.totalEachItem.setText("$"+ Math.round(listItemSelected.get(position).getNumberinCart()*listItemSelected.get(position).getPrice()));
        holder.binding.numberItemTxt.setText(String.valueOf(listItemSelected.get(position).getNumberinCart()));

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transform(new CenterCrop());

        Glide.with(holder.itemView.getContext())
                .load(listItemSelected.get(position).getPicUrl().get(0))
                .apply(requestOptions)
                .into(holder.binding.pic);

        holder.binding.plusCartBtn.setOnClickListener(v -> managmentCart.plusItem(listItemSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.changed();

        }));

        holder.binding.minusCartBtn.setOnClickListener(v -> managmentCart.minusItem(listItemSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        }));

    }

    @Override
    public int getItemCount() {
        return listItemSelected.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        ViewholderCartBinding binding;
        public Viewholder(ViewholderCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
