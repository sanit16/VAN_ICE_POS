package com.plakadee.sellice.Spiner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.plakadee.sellice.DataObj.Car;
import com.plakadee.sellice.Interface.TransferInterface;
import com.plakadee.sellice.R;

import java.util.List;
public class SendAdapter extends RecyclerView.Adapter<SendAdapter.MyViewHolder> {
    private Context mContext;
    List<Car> mData;
    TransferInterface transferInterface;
    RelativeLayout holder2;
    public String runno;

    public SendAdapter(Context mContext, List<Car> mData , TransferInterface transferInterface) {
        this.mContext = mContext;
        this.mData = mData;
        this.transferInterface = transferInterface;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.get_car_card, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Car data = mData.get(position);
        if (runno!=null){
            if (data.getRunno().equals(runno)){
                holder.rel_layer.setBackgroundColor(mContext.getColor(R.color.colorWarning2));


            }else {
                holder.rel_layer.setBackgroundColor(mContext.getColor(R.color.colorPositive3));

            }
        }
        holder.txt_header.setText(data.getCar_code());
        holder.txt_body.setText(data.getCar_no());
        holder.rel_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runno = data.getRunno();
                transferInterface.SelectCar(data);
                notifyDataSetChanged();
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

        public MyViewHolder(View itemView) {
            super(itemView);
            txt_header = itemView.findViewById(R.id.txt_header);
            txt_body = itemView.findViewById(R.id.txt_body);
            rel_layer = itemView.findViewById(R.id.rel_layer);


        }
    }
}