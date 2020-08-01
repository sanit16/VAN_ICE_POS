package com.plakadee.sellice.Spiner;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.plakadee.sellice.DataObj.OrderDeatail;
import com.plakadee.sellice.DataObj.OrderDetailEdit;
import com.plakadee.sellice.R;

import java.util.List;

public class GetIceEditAdapter extends RecyclerView.Adapter<GetIceEditAdapter.MyViewHolder> {
    private Context mContext;
    public List<OrderDetailEdit> mData;
    GetIceInterface getIceInterface;

    public GetIceEditAdapter(Context mContext, List<OrderDetailEdit> mData, GetIceInterface getIceInterface) {
        this.mContext = mContext;
        this.mData = mData;
        this.getIceInterface = getIceInterface;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.get_product_card, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final OrderDetailEdit data = mData.get(position);
        holder.txt_name.setText(data.getProduct_name());
        holder.btn_sell.setText(String.valueOf(data.getOrder_qty1()));
        holder.btn_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
                llm.setOrientation(RecyclerView.HORIZONTAL);
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                LayoutInflater mInflater = LayoutInflater.from(v.getContext());
                View row = mInflater.inflate(R.layout.pad_get_dialog,null);
                holder.txt_value= row.findViewById(R.id.txt_value);
                holder.txt_name = row.findViewById(R.id.txt_name);
                holder.txt_name.setText(data.getProduct_name());
                holder.txt_value.setText(String.valueOf(data.getOrder_qty1()));
                holder.one = row.findViewById(R.id.one);
                holder.one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (holder.value.equals("0")){
                            holder.value="1";
                        }else {
                            holder.value = holder.value+"1";
                        }
                        holder.txt_value.setText(holder.value);




                    }
                });
                holder.two= row.findViewById(R.id.two);
                holder.two.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (holder.value.equals("0")){
                            holder.value="2";
                        }else {
                            holder.value = holder.value+"2";
                        }
                        holder.txt_value.setText(holder.value);

                    }
                });
                holder.three= row.findViewById(R.id.three);
                holder.three.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (holder.value.equals("0")){
                            holder.value="3";
                        }else {
                            holder.value = holder.value+"3";
                        }
                        holder.txt_value.setText(holder.value);




                    }
                });
                holder.four= row.findViewById(R.id.four);
                holder.four.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.value.equals("0")){
                            holder.value="4";
                        }else {
                            holder.value = holder.value+"4";
                        }
                        holder.txt_value.setText(holder.value);

                    }
                });
                holder.five= row.findViewById(R.id.five);
                holder.five.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (holder.value.equals("0")){
                            holder.value="5";
                        }else {
                            holder.value = holder.value+"5";
                        }
                        holder.txt_value.setText(holder.value);

                    }
                });
                holder.six= row.findViewById(R.id.six);
                holder.six.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (holder.value.equals("0")){
                            holder.value="6";
                        }else {
                            holder.value = holder.value+"6";
                        }
                        holder.txt_value.setText(holder.value);

                    }
                });
                holder.seven= row.findViewById(R.id.seven);
                holder.seven.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (holder.value.equals("0")){
                            holder.value="7";
                        }else {
                            holder.value = holder.value+"7";
                        }
                        holder.txt_value.setText(holder.value);

                    }
                });
                holder.eight= row.findViewById(R.id.eight);
                holder.eight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (holder.value.equals("0")){
                            holder.value="8";
                        }else {
                            holder.value = holder.value+"8";
                        }
                        holder.txt_value.setText(holder.value);



                    }
                });
                holder.nine= row.findViewById(R.id.nine);
                holder.nine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (holder.value.equals("0")){
                            holder.value="9";
                        }else {
                            holder.value = holder.value+"9";
                        }
                        holder.txt_value.setText(holder.value);

                    }
                });
                holder.zero= row.findViewById(R.id.zero);
                holder.zero.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (holder.value.equals("0")){
                            holder.value="0";
                        }else {
                            holder.value = holder.value+"0";
                        }
                        holder.txt_value.setText(holder.value);

                    }
                });
                holder.c= row.findViewById(R.id.c);
                holder.c.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        holder.value="0";
                        holder.txt_value.setText(holder.value);

                    }
                });
                holder.x= row.findViewById(R.id.x);
                holder.x.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (holder.value.length()==1){
                            holder.value = "0";
                        }else {
                            holder.value = holder.value.substring(0, holder.value.length() - 1);
//                            holder.value.substring(0, holder.value.length()-1);
//                            holder.value.replaceFirst(".$","");
                        }
                        holder.txt_value.setText(holder.value);



//
                    }
                });
                holder.ok= row.findViewById(R.id.ok);
                holder.ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.dialog.dismiss();
                        set_value(data,holder.value);
                    }
                });
                builder.setView(row);
                holder.dialog = builder.create();
                holder.dialog.show();



            }
        });


    }

    private void set_value(OrderDetailEdit data  ,String value) {
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).getProduct_runno()==data.getProduct_runno()){
                mData.get(i).setOrder_qty1(Integer.parseInt(value));
            }

        }
        notifyDataSetChanged();
        getIceInterface.calculate();
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements SelectPriceCallBack  {
        TextView txt_name;
        Button btn_sell;
        Button btn_price;
        AlertDialog dialog;
        Button one ,two,three,four,five,six,seven,eight,nine,zero,c,x,ok,dis;
        TextView txt_value;
        String value = "0";

        public MyViewHolder(final View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_name);
            btn_sell = itemView.findViewById(R.id.btn_sell);
            btn_price = itemView.findViewById(R.id.btn_price);



        }

        @Override
        public void selected(String number) {
            txt_value.setText(value);

//            txt_sum_price.setText(String.valueOf(Double.parseDouble(value)*Double.parseDouble(price)));
        }
    }

}