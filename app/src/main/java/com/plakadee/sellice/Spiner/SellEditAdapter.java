package com.plakadee.sellice.Spiner;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.plakadee.sellice.DataObj.ProductPrice;
import com.plakadee.sellice.DataObj.SaleProduct;
import com.plakadee.sellice.DataObj.SaleProductEdit;
import com.plakadee.sellice.DataState.SaveState;
import com.plakadee.sellice.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SellEditAdapter extends RecyclerView.Adapter<SellEditAdapter.MyViewHolder> {
    private Context mContext;
    public List<SaleProductEdit> mData;
    SaleInterface saleInterface;

    public SellEditAdapter(Context mContext, List<SaleProductEdit> mData,SaleInterface saleInterface) {
        this.mContext = mContext;
        this.mData = mData;
        this.saleInterface = saleInterface;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.edit_product_card, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final SaleProductEdit data = mData.get(position);
        holder.edit_sell.setText(String.valueOf(data.getQty_edit()));
        holder.edit_price.setText(String.valueOf((data.getSale_price_edit()*data.getQty_edit())-data.getDiscount_edit()));
        holder.txt_name.setText(data.getProduct_name());
        holder.btn_sell.setText(String.valueOf(data.getQty()));
        holder.btn_price.setText(String.valueOf((data.getSale_price()*data.getQty())-data.getDiscount()));
        holder.edit_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.discount_state = false;
                holder.productPriceList = new ArrayList<>();
                holder.adapter = new ProductPriceAdapter(v.getContext(),holder.productPriceList,holder.selectPriceCallBack);
                LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
                llm.setOrientation(RecyclerView.HORIZONTAL);
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                LayoutInflater mInflater = LayoutInflater.from(v.getContext());
                View row = mInflater.inflate(R.layout.pad_dialog,null);
                holder.recyclerView = row.findViewById(R.id.recyclerview);
                holder.dis = row.findViewById(R.id.txt_discount_price);
                holder.txt_name = row.findViewById(R.id.txt_name);
                holder.txt_name.setText(data.getProduct_name());
                holder.txt_discount = row.findViewById(R.id.txt_discount_price);
                holder.txt_discount.setText(String.valueOf(data.getDiscount()));
                holder.txt_get_money = row.findViewById(R.id.txt_get_money);
                holder.txt_value= row.findViewById(R.id.txt_value);
                holder.txt_value.setText(String.valueOf(data.getQty()));
                holder.txt_get_money.setText(String.valueOf((data.getQty()*data.getSale_price())-data.getDiscount()));

                holder.dis.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.discount_state){
                            holder.dis.getBackground().setColorFilter(ContextCompat.getColor(mContext, R.color.colorTextTwilight), PorterDuff.Mode.MULTIPLY);

//                            holder.dis.setBackgroundColor(mContext.getResources().getColor(R.color.colorWarning));

                        }else {
                            holder.dis.getBackground().setColorFilter(ContextCompat.getColor(mContext, R.color.colorPositive), PorterDuff.Mode.MULTIPLY);

//                            holder.dis.getBackground()..setBackgroundColor(mContext.getResources().getColor(R.color.colorPositive));

                        }
                        holder.discount_state = !holder.discount_state;

                    }
                });
                holder.recyclerView.setLayoutManager(llm);
                holder.recyclerView.setAdapter(holder.adapter);
                get_data_product_price(holder,data);
                holder.txt_name.setText(data.getProduct_name());
                holder.one = row.findViewById(R.id.one);
                holder.one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.discount_state){
                            if (holder.discount.equals("0")){
                                holder.discount="1";
                            }else {
                                holder.discount = holder.discount+"1";
                            }
                            holder.txt_discount.setText(holder.discount);
                        }else {
                            if (holder.value.equals("0")){
                                holder.value="1";
                            }else {
                                holder.value = holder.value+"1";
                            }
                            holder.txt_value.setText(holder.value);
                        }


//                       holder.txt_sum_price.setText(String.valueOf(Double.parseDouble(holder.value)*Double.parseDouble(holder.price)));
                        holder.txt_get_money.setText(String.valueOf((Double.parseDouble(holder.value)*Double.parseDouble(holder.price))-Double.parseDouble(holder.discount)));

                    }
                });
                holder.two= row.findViewById(R.id.two);
                holder.two.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.discount_state){
                            if (holder.discount.equals("0")){
                                holder.discount="2";
                            }else {
                                holder.discount = holder.discount+"2";
                            }
                            holder.txt_discount.setText(holder.discount);
                        }else {
                            if (holder.value.equals("0")){
                                holder.value="2";
                            }else {
                                holder.value = holder.value+"2";
                            }
                            holder.txt_value.setText(holder.value);
                        }
//                        holder.txt_sum_price.setText(String.valueOf(Double.parseDouble(holder.value)*Double.parseDouble(holder.price)));
                        holder.txt_get_money.setText(String.valueOf((Double.parseDouble(holder.value)*Double.parseDouble(holder.price))-Double.parseDouble(holder.discount)));

                    }
                });
                holder.three= row.findViewById(R.id.three);
                holder.three.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.discount_state){
                            if (holder.discount.equals("0")){
                                holder.discount="3";
                            }else {
                                holder.discount = holder.discount+"3";
                            }
                            holder.txt_discount.setText(holder.discount);
                        }else {
                            if (holder.value.equals("0")){
                                holder.value="3";
                            }else {
                                holder.value = holder.value+"3";
                            }
                            holder.txt_value.setText(holder.value);
                        }


//                        holder.txt_sum_price.setText(String.valueOf(Double.parseDouble(holder.value)*Double.parseDouble(holder.price)));
                        holder.txt_get_money.setText(String.valueOf((Double.parseDouble(holder.value)*Double.parseDouble(holder.price))-Double.parseDouble(holder.discount)));

                    }
                });
                holder.four= row.findViewById(R.id.four);
                holder.four.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.discount_state){
                            if (holder.discount.equals("0")){
                                holder.discount="4";
                            }else {
                                holder.discount = holder.discount+"4";
                            }
                            holder.txt_discount.setText(holder.discount);
                        }else {
                            if (holder.value.equals("0")){
                                holder.value="4";
                            }else {
                                holder.value = holder.value+"4";
                            }
                            holder.txt_value.setText(holder.value);
                        }
//                        holder.txt_sum_price.setText(String.valueOf(Double.parseDouble(holder.value)*Double.parseDouble(holder.price)));
                        holder.txt_get_money.setText(String.valueOf((Double.parseDouble(holder.value)*Double.parseDouble(holder.price))-Double.parseDouble(holder.discount)));

                    }
                });
                holder.five= row.findViewById(R.id.five);
                holder.five.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.discount_state){
                            if (holder.discount.equals("0")){
                                holder.discount="5";
                            }else {
                                holder.discount = holder.discount+"5";
                            }
                            holder.txt_discount.setText(holder.discount);
                        }else {
                            if (holder.value.equals("0")){
                                holder.value="5";
                            }else {
                                holder.value = holder.value+"5";
                            }
                            holder.txt_value.setText(holder.value);
                        }
//                        holder.txt_sum_price.setText(String.valueOf(Double.parseDouble(holder.value)*Double.parseDouble(holder.price)));
                        holder.txt_get_money.setText(String.valueOf((Double.parseDouble(holder.value)*Double.parseDouble(holder.price))-Double.parseDouble(holder.discount)));

                    }
                });
                holder.six= row.findViewById(R.id.six);
                holder.six.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.discount_state){
                            if (holder.discount.equals("0")){
                                holder.discount="6";
                            }else {
                                holder.discount = holder.discount+"6";
                            }
                            holder.txt_discount.setText(holder.discount);
                        }else {
                            if (holder.value.equals("0")){
                                holder.value="6";
                            }else {
                                holder.value = holder.value+"6";
                            }
                            holder.txt_value.setText(holder.value);
                        }
//                        holder.txt_sum_price.setText(String.valueOf(Double.parseDouble(holder.value)*Double.parseDouble(holder.price)));
                        holder.txt_get_money.setText(String.valueOf((Double.parseDouble(holder.value)*Double.parseDouble(holder.price))-Double.parseDouble(holder.discount)));

                    }
                });
                holder.seven= row.findViewById(R.id.seven);
                holder.seven.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.discount_state){
                            if (holder.discount.equals("0")){
                                holder.discount="7";
                            }else {
                                holder.discount = holder.discount+"7";
                            }
                            holder.txt_discount.setText(holder.discount);
                        }else {
                            if (holder.value.equals("0")){
                                holder.value="7";
                            }else {
                                holder.value = holder.value+"7";
                            }
                            holder.txt_value.setText(holder.value);
                        }


//                        holder.txt_sum_price.setText(String.valueOf(Double.parseDouble(holder.value)*Double.parseDouble(holder.price)));
                        holder.txt_get_money.setText(String.valueOf((Double.parseDouble(holder.value)*Double.parseDouble(holder.price))-Double.parseDouble(holder.discount)));

                    }
                });
                holder.eight= row.findViewById(R.id.eight);
                holder.eight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.discount_state){
                            if (holder.discount.equals("0")){
                                holder.discount="8";
                            }else {
                                holder.discount = holder.discount+"8";
                            }
                            holder.txt_discount.setText(holder.discount);
                        }else {
                            if (holder.value.equals("0")){
                                holder.value="8";
                            }else {
                                holder.value = holder.value+"8";
                            }
                            holder.txt_value.setText(holder.value);
                        }


//                        holder.txt_sum_price.setText(String.valueOf(Double.parseDouble(holder.value)*Double.parseDouble(holder.price)));
                        holder.txt_get_money.setText(String.valueOf((Double.parseDouble(holder.value)*Double.parseDouble(holder.price))-Double.parseDouble(holder.discount)));

                    }
                });
                holder.nine= row.findViewById(R.id.nine);
                holder.nine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.discount_state){
                            if (holder.discount.equals("0")){
                                holder.discount="9";
                            }else {
                                holder.discount = holder.discount+"9";
                            }
                            holder.txt_discount.setText(holder.discount);
                        }else {
                            if (holder.value.equals("0")){
                                holder.value="9";
                            }else {
                                holder.value = holder.value+"9";
                            }
                            holder.txt_value.setText(holder.value);
                        }


//                        holder.txt_sum_price.setText(String.valueOf(Double.parseDouble(holder.value)*Double.parseDouble(holder.price)));
                        holder.txt_get_money.setText(String.valueOf((Double.parseDouble(holder.value)*Double.parseDouble(holder.price))-Double.parseDouble(holder.discount)));

                    }
                });
                holder.zero= row.findViewById(R.id.zero);
                holder.zero.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.discount_state){
                            if (holder.discount.equals("0")){
                                holder.discount="0";
                            }else {
                                holder.discount = holder.discount+"0";
                            }
                            holder.txt_discount.setText(holder.discount);
                        }else {
                            if (holder.value.equals("0")){
                                holder.value="0";
                            }else {
                                holder.value = holder.value+"0";
                            }
                            holder.txt_value.setText(holder.value);
                        }
//                        holder.txt_sum_price.setText(String.valueOf(Double.parseDouble(holder.value)*Double.parseDouble(holder.price)));
                        holder.txt_get_money.setText(String.valueOf((Double.parseDouble(holder.value)*Double.parseDouble(holder.price))-Double.parseDouble(holder.discount)));

                    }
                });
                holder.c= row.findViewById(R.id.c);
                holder.c.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.discount_state){
                            holder.discount="0";
                            holder.txt_discount.setText(holder.discount);
                        }else {
                            holder.value="0";
                            holder.txt_value.setText(holder.value);
                        }
//                        holder.txt_sum_price.setText(String.valueOf(Double.parseDouble(holder.value)*Double.parseDouble(holder.price)));
                        holder.txt_get_money.setText(String.valueOf((Double.parseDouble(holder.value)*Double.parseDouble(holder.price))-Double.parseDouble(holder.discount)));

                    }
                });
                holder.x= row.findViewById(R.id.x);
                holder.x.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.discount_state){
                            if (holder.discount.length()==1){
                                holder.discount = "0";
                            }else {
                                holder.discount = holder.discount.substring(0, holder.discount.length() - 1);
//                            holder.value.substring(0, holder.value.length()-1);
//                            holder.value.replaceFirst(".$","");
                            }
                            holder.txt_discount.setText(holder.discount);
                        }else {
                            if (holder.value.length()==1){
                                holder.value = "0";
                            }else {
                                holder.value = holder.value.substring(0, holder.value.length() - 1);
//                            holder.value.substring(0, holder.value.length()-1);
//                            holder.value.replaceFirst(".$","");
                            }
                            holder.txt_value.setText(holder.value);
                        }


//                        holder.txt_sum_price.setText(String.valueOf(Double.parseDouble(holder.value)*Double.parseDouble(holder.price)));
                        holder.txt_get_money.setText(String.valueOf((Double.parseDouble(holder.value)*Double.parseDouble(holder.price))-Double.parseDouble(holder.discount)));

                    }
                });
                holder.ok= row.findViewById(R.id.ok);
                holder.ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.dialog.dismiss();
                        set_value(data,holder.discount,holder.price,holder.value);
                    }
                });


//                holder.txt_sum_price= row.findViewById(R.id.txt_sum_price);

                builder.setView(row);
                holder.dialog = builder.create();
                holder.dialog.show();



            }
        });


    }

    private void set_value(SaleProductEdit data, String discount, String price,String value) {

        for (int i = 0; i < mData.size(); i++) {

            if (mData.get(i).getProduct_runno()==data.getProduct_runno()){
                mData.get(i).setDiscount_edit(Integer.parseInt(discount));
                mData.get(i).setQty_edit(Integer.parseInt(value));
                mData.get(i).setSale_price_edit((int) Math.round(Double.parseDouble(price)));
//                new AlertDialog.Builder(mContext).setMessage(String.valueOf(mData.get(i).getQty())+"=-="+String.valueOf(mData.get(i).getSale_price())).setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                }).show();
            }

        }
        saleInterface.calculate();
        notifyDataSetChanged();

    }

    private void get_data_product_price(final MyViewHolder holder, SaleProductEdit data) {
        String URL = SaveState.getURL()+"productprice?isactive=true&ordering=runno&product_runno="+data.getProduct_runno();
        RequestQueue queue = Volley.newRequestQueue(mContext);
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                ProductPrice dummy = new ProductPrice(jsonObject);
                                holder.productPriceList.add(dummy);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        holder.adapter.notifyDataSetChanged();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        );
        queue.add(getRequest);
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

    public static class MyViewHolder extends RecyclerView.ViewHolder implements SelectPriceCallBack  {
        TextView txt_name;
        TextView btn_sell;
        TextView btn_price;
        Button edit_price;
        Button edit_sell;
        AlertDialog dialog;
        Button one ,two,three,four,five,six,seven,eight,nine,zero,c,x,ok;
        TextView dis;
        TextView txt_value;
        TextView txt_sum_price;
        TextView txt_discount;
        TextView txt_get_money;
        String value = "0";
        String price = "0";
        String discount = "0";
        ProductPriceAdapter adapter;
        RecyclerView recyclerView;
        List<ProductPrice> productPriceList;
        SelectPriceCallBack selectPriceCallBack = this;
        boolean discount_state =  false;

        public MyViewHolder(final View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_name);
            btn_sell = itemView.findViewById(R.id.btn_sell);
            btn_price = itemView.findViewById(R.id.btn_price);
            edit_sell  = itemView.findViewById(R.id.edit_sell);
            edit_price  = itemView.findViewById(R.id.edit_price);



        }

        @Override
        public void selected(String number) {
            price = number;
            txt_value.setText(value);
            txt_get_money.setText(String.valueOf((Double.parseDouble(value)*Double.parseDouble(price))-Double.parseDouble(discount)));

//            txt_sum_price.setText(String.valueOf(Double.parseDouble(value)*Double.parseDouble(price)));
        }
    }

}