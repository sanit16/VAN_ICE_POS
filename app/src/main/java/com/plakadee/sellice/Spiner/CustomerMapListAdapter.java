package com.plakadee.sellice.Spiner;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.plakadee.sellice.DataObj.Customer;
import com.plakadee.sellice.DataState.SaveState;
import com.plakadee.sellice.R;
import com.plakadee.sellice.Selll;

import org.w3c.dom.Text;

import java.util.List;

public class CustomerMapListAdapter extends RecyclerView.Adapter<CustomerMapListAdapter.MyViewHolder> {
    private Context mContext;
    List<Customer> mData;
    ShowMapCallback showMapCallback;
    public CustomerMapListAdapter(Context mContext, List<Customer> mData,ShowMapCallback showMapCallback) {
        this.mContext = mContext;
        this.mData = mData;
        this.showMapCallback = showMapCallback;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.customer_map_card_list, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Customer data = mData.get(position);
        holder.txt_header.setText(data.getCustomer_name());
        holder.txt_body.setText(data.getPhone_number());
        holder.txt_debt.setText(data.getDebt());
        holder.txt_status.setText(data.getStatus());
        holder.rel_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMapCallback.customer_click(new LatLng(Double.parseDouble(data.getLatitude()),Double.parseDouble(data.getLongitude())));
//                SaveState.setCustomerRunno(mContext,data.getRunno());
//                SaveState.setCustomer(mContext,String.valueOf(data));
//                Intent intent = new Intent(mContext, Selll.class);
//                mContext.startActivity(intent);
//                selectCallback.selected(data);

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
        TextView txt_header;
        TextView txt_body;
        RelativeLayout rel_layer;
        TextView txt_debt;
        TextView txt_status;

        public MyViewHolder(View itemView) {
            super(itemView);
            txt_header = itemView.findViewById(R.id.txt_header);
            txt_body = itemView.findViewById(R.id.txt_body);
            rel_layer = itemView.findViewById(R.id.rel_layer);
            txt_debt = itemView.findViewById(R.id.debt);
            txt_status = itemView.findViewById(R.id.status);


        }
    }
}