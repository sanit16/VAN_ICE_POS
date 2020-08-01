package com.plakadee.sellice.Spiner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
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
import com.plakadee.sellice.DataObj.Customer;
import com.plakadee.sellice.DataObj.ProductGet;
import com.plakadee.sellice.DataObj.ProductPrice;
import com.plakadee.sellice.DataObj.SaleProduct;
import com.plakadee.sellice.DataObj.SaleProductEdit;
import com.plakadee.sellice.DataState.SaveState;
import com.plakadee.sellice.R;
import com.plakadee.sellice.Selll;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class SellAdapter extends RecyclerView.Adapter<SellAdapter.MyViewHolder> {
    private Context mContext;
    public List<SaleProduct> mData;
    SaleInterface saleInterface;

    public SellAdapter(Context mContext, List<SaleProduct> mData,SaleInterface saleInterface) {
        this.mContext = mContext;
        this.mData = mData;
        this.saleInterface = saleInterface;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.sell_product_card, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final SaleProduct data = mData.get(position);
        holder.txt_name.setText(data.getProduct_name());
        holder.btn_sell.setText(String.valueOf(data.getQty()));
        holder.btn_price.setText(String.valueOf((data.getSale_price()*data.getQty())-data.getDiscount()));
        holder.btn_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.discount_state = 0;
                holder.productPriceList = new ArrayList<>();
                holder.adapter = new ProductPriceAdapter(v.getContext(),holder.productPriceList,holder.selectPriceCallBack);
                LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
                llm.setOrientation(RecyclerView.HORIZONTAL);
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                LayoutInflater mInflater = LayoutInflater.from(v.getContext());
                View row = mInflater.inflate(R.layout.pad_dialog,null);
                holder.recyclerView = row.findViewById(R.id.recyclerview);
                holder.txt_name = row.findViewById(R.id.txt_name);
                holder.txt_name.setText(data.getProduct_name());
                holder.txt_discount = row.findViewById(R.id.txt_discount_price);
                holder.txt_discount.setText(String.valueOf(data.getDiscount()));
                holder.txt_get_money = row.findViewById(R.id.txt_get_money);
                holder.txt_value= row.findViewById(R.id.txt_value);
                holder.txt_value.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.discount_state=0;
                        holder.txt_value.setBackgroundColor(v.getContext().getResources().getColor(R.color.colorNegative2));
                        holder.txt_discount.setBackgroundColor(v.getContext().getResources().getColor(R.color.colorPositive2));
                        holder.txt_free.setBackgroundColor(v.getContext().getResources().getColor(R.color.colorPositive2));

                    }
                });
                holder.txt_discount.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.discount_state=1;
                        holder.txt_value.setBackgroundColor(v.getContext().getResources().getColor(R.color.colorPositive2));
                        holder.txt_discount.setBackgroundColor(v.getContext().getResources().getColor(R.color.colorNegative2));
                        holder.txt_free.setBackgroundColor(v.getContext().getResources().getColor(R.color.colorPositive2));
                    }
                });
                holder.txt_free = row.findViewById(R.id.txt_free);
                holder.txt_free.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.discount_state=2;
                        holder.txt_value.setBackgroundColor(v.getContext().getResources().getColor(R.color.colorPositive2));
                        holder.txt_discount.setBackgroundColor(v.getContext().getResources().getColor(R.color.colorPositive2));
                        holder.txt_free.setBackgroundColor(v.getContext().getResources().getColor(R.color.colorNegative2));
                    }
                });
                holder.txt_value.setText(String.valueOf(data.getQty()));
                holder.txt_get_money.setText(String.valueOf((data.getQty()*data.getSale_price())-data.getDiscount()));
                holder.recyclerView.setLayoutManager(llm);
                holder.recyclerView.setAdapter(holder.adapter);
                get_data_product_price(holder,data);
                holder.txt_name.setText(data.getProduct_name());
                holder.one = row.findViewById(R.id.one);
                holder.one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        set_pad_click(holder,"1");

                    }
                });
                holder.two= row.findViewById(R.id.two);
                holder.two.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        set_pad_click(holder,"2");

                    }
                });
                holder.three= row.findViewById(R.id.three);
                holder.three.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        set_pad_click(holder,"3");

                    }
                });
                holder.four= row.findViewById(R.id.four);
                holder.four.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        set_pad_click(holder,"4");

                    }
                });
                holder.five= row.findViewById(R.id.five);
                holder.five.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        set_pad_click(holder,"5");

                    }
                });
                holder.six= row.findViewById(R.id.six);
                holder.six.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        set_pad_click(holder,"6");

                    }
                });
                holder.seven= row.findViewById(R.id.seven);
                holder.seven.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        set_pad_click(holder,"7");

                    }
                });
                holder.eight= row.findViewById(R.id.eight);
                holder.eight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        set_pad_click(holder,"8");

                    }
                });
                holder.nine= row.findViewById(R.id.nine);
                holder.nine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        set_pad_click(holder,"9");

                    }
                });
                holder.zero= row.findViewById(R.id.zero);
                holder.zero.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        set_pad_click(holder,"0");

                    }
                });
                holder.c= row.findViewById(R.id.c);
                holder.c.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (holder.discount_state){
                            case 0:
                                holder.value="0";
                                holder.txt_value.setText(holder.value);
                                break;
                            case 1:
                                holder.discount="0";
                                holder.txt_discount.setText(holder.discount);
                                break;
                            case 2:
                                holder.free="0";
                                holder.txt_free.setText(holder.free);
                                break;
                            default:
                                break;
                        }

//                        holder.txt_sum_price.setText(String.valueOf(Double.parseDouble(holder.value)*Double.parseDouble(holder.price)));
                        holder.txt_get_money.setText(String.valueOf((Double.parseDouble(holder.value)*Double.parseDouble(holder.price))-Double.parseDouble(holder.discount)));

                    }
                });
                holder.x= row.findViewById(R.id.x);
                holder.x.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (holder.discount_state){
                            case 0:
                                if (holder.value.length()==1){
                                    holder.value = "0";
                                }else {
                                    holder.value = holder.value.substring(0, holder.value.length() - 1);
//                            holder.value.substring(0, holder.value.length()-1);
//                            holder.value.replaceFirst(".$","");
                                }
                                holder.txt_value.setText(holder.value);
                                break;
                            case 1:
                                if (holder.discount.length()==1){
                                    holder.discount = "0";
                                }else {
                                    holder.discount = holder.discount.substring(0, holder.discount.length() - 1);
//                            holder.value.substring(0, holder.value.length()-1);
//                            holder.value.replaceFirst(".$","");
                                }
                                holder.txt_discount.setText(holder.discount);
                                break;
                            case 2:
                                if (holder.free.length()==1){
                                    holder.free = "0";
                                }else {
                                    holder.free = holder.free.substring(0, holder.free.length() - 1);
//                            holder.value.substring(0, holder.value.length()-1);
//                            holder.value.replaceFirst(".$","");
                                }
                                holder.txt_free.setText(holder.free);
                                break;
                            default:
                                break;
                        }
                         holder.txt_get_money.setText(String.valueOf((Double.parseDouble(holder.value)*Double.parseDouble(holder.price))-Double.parseDouble(holder.discount)));

                    }
                });
                holder.ok= row.findViewById(R.id.ok);
                holder.ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.dialog.dismiss();
                        set_value(data,holder.discount,holder.price,holder.value,holder.free);
                    }
                });


//                holder.txt_sum_price= row.findViewById(R.id.txt_sum_price);

                builder.setView(row);
                holder.dialog = builder.create();
                holder.dialog.show();



            }
        });


    }

    private void set_pad_click(MyViewHolder holder, String s) {
        switch (holder.discount_state){
            case 0:
                if (holder.value.equals("0")){
                    holder.value=s;
                }else {
                    holder.value = holder.value+s;
                }
                holder.txt_value.setText(holder.value);
                break;
            case 1:
                if (holder.discount.equals("0")){
                    holder.discount=s;
                }else {
                    holder.discount = holder.discount+s;
                }
                holder.txt_discount.setText(holder.discount);
                break;
            case 2:
                if (holder.free.equals("0")){
                    holder.free=s;
                }else {
                    holder.free = holder.free+s;
                }
                holder.txt_free.setText(holder.free);
                break;
            default:
                break;
        }
       holder.txt_get_money.setText(String.valueOf((Double.parseDouble(holder.value)*Double.parseDouble(holder.price))-Double.parseDouble(holder.discount)));

    }

    private void set_value(SaleProduct data, String discount, String price,String value,String free) {

        for (int i = 0; i < mData.size(); i++) {

            if (mData.get(i).getProduct_runno()==data.getProduct_runno()){
                mData.get(i).setDiscount(Integer.parseInt(discount));
                mData.get(i).setQty(Integer.parseInt(value));
                mData.get(i).setSale_price((int) Math.round(Double.parseDouble(price)));
                mData.get(i).setQty_free(Integer.parseInt(free));
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

    private void get_data_product_price(final MyViewHolder holder, SaleProduct data) {
        String URL = SaveState.getURL() +"productprice?isactive=true&ordering=runno&product_runno="+data.getProduct_runno();
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
        Button btn_sell;
        Button btn_price;
        AlertDialog dialog;
        Button one ,two,three,four,five,six,seven,eight,nine,zero,c,x,ok;
        TextView txt_free;
        TextView txt_value;
        TextView txt_discount;
        TextView txt_get_money;
        String value = "0";
        String price = "0";
        String discount = "0";
        String free = "0";
        ProductPriceAdapter adapter;
        RecyclerView recyclerView;
        List<ProductPrice> productPriceList;
        SelectPriceCallBack selectPriceCallBack = this;
        int discount_state =  0;

        public MyViewHolder(final View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_name);
            btn_sell = itemView.findViewById(R.id.btn_sell);
            btn_price = itemView.findViewById(R.id.btn_price);

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