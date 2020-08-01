package com.plakadee.sellice.Spiner;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.plakadee.sellice.DataObj.Customer;
import com.plakadee.sellice.DataObj.ProductPrice;
import com.plakadee.sellice.DataState.SaveState;
import com.plakadee.sellice.R;
import com.plakadee.sellice.Selll;

import java.util.List;


public class ProductPriceAdapter extends RecyclerView.Adapter<ProductPriceAdapter.MyViewHolder> {
    private Context mContext;
    List<ProductPrice> mData;
    MyViewHolder last_holer;
    SelectPriceCallBack callBack;

    public ProductPriceAdapter(Context mContext, List<ProductPrice> mData,SelectPriceCallBack callBack) {
        this.mContext = mContext;
        this.mData = mData;
        this.callBack = callBack;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.product_price_card, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ProductPrice data = mData.get(position);
        holder.txt_price.setText(data.getProduct_price());
        holder.txt_name.setText(data.getProduct_price_name());
        holder.rel_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                holder.card_layer.setCardBackgroundColor(v.getContext().getResources().getColor(R.color.colorWarning));

//                Toast.makeText(mContext, "click", Toast.LENGTH_SHORT).show();
                if (last_holer==null){
                    last_holer = holder;
                }
               last_holer.rel_layer.setBackgroundColor(mContext.getResources().getColor(R.color.colorTextLight));
                holder.rel_layer.setBackgroundColor(mContext.getResources().getColor(R.color.colorWarning));
                last_holer = holder;
                notifyDataSetChanged();
                callBack.selected(data.getProduct_price());
            }
        });


    }


    private ViewGroup.LayoutParams setparams(ViewGroup view, int l, int t, int r, int b) {
        float dpRatio = mContext.getResources().getDisplayMetrics().density;
        int pixelForDp = t * (int) dpRatio;
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        params.setMargins(0, pixelForDp, 0, 0);
        return params;

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_price;
        TextView txt_name;
        RelativeLayout rel_layer;
        CardView card_layer;

        public MyViewHolder(View itemView) {
            super(itemView);
            txt_price = itemView.findViewById(R.id.txt_price);
            txt_name = itemView.findViewById(R.id.txt_name);
            rel_layer = itemView.findViewById(R.id.rel_layer);
            card_layer = itemView.findViewById(R.id.card_layer);


        }
    }
}