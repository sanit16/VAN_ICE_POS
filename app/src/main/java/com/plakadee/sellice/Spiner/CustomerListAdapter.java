package com.plakadee.sellice.Spiner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.plakadee.sellice.CustomerEdit;
import com.plakadee.sellice.CustomerList;
import com.plakadee.sellice.DataObj.Car;
import com.plakadee.sellice.DataObj.Customer;
import com.plakadee.sellice.DataState.SaveState;
import com.plakadee.sellice.R;
import com.plakadee.sellice.Selll;
import com.plakadee.sellice.Tools.DeleteDialog;

import java.util.List;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.MyViewHolder> {
    private Context mContext;
    public   List<Customer> mData;
    Activity activity;

    public CustomerListAdapter(Context mContext, List<Customer> mData, Activity activity) {
        this.mContext = mContext;
        this.mData = mData;
        this.activity = activity;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.customer_card_list, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Customer data = mData.get(position);
        holder.txt_header.setText(data.getCustomer_name());
        holder.txt_body.setText(data.getPhone_number());
        holder.rel_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveState.setCustomerRunno(mContext,data.getRunno());
                SaveState.setCustomer(mContext,String.valueOf(data));
                Intent intent = new Intent(mContext, Selll.class);
                mContext.startActivity(intent);
//                selectCallback.selected(data);

            }
        });
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DeleteDialog(mContext,data.getRunno(),data.getCustomer_name(),data.getPhone_number());
            }
        });
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveState.setCustomerRunno(mContext,data.getRunno());
                SaveState.setCustomerName(mContext,data.getCustomer_name());
                SaveState.setCustomerPhone(mContext,data.getPhone_number());
                SaveState.setCustomerLat(mContext,data.getLatitude());
                SaveState.setCustomerLon(mContext,data.getLongitude());
                Intent intent = new Intent(mContext, CustomerEdit.class);
                mContext.startActivity(intent);
                activity.finish();

            }
        });
        holder.img_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+data.getPhone_number()));
                mContext.startActivity(intent);
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
        Button btn_delete;
        Button btn_edit;
        ImageView img_call;

        public MyViewHolder(View itemView) {
            super(itemView);
            txt_header = itemView.findViewById(R.id.txt_header);
            txt_body = itemView.findViewById(R.id.txt_body);
            rel_layer = itemView.findViewById(R.id.rel_layer);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            btn_edit = itemView.findViewById(R.id.btn_edit);
            img_call = itemView.findViewById(R.id.img_call);



        }
    }
}