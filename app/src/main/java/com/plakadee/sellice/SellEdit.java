package com.plakadee.sellice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.plakadee.sellice.DataObj.Customer;
import com.plakadee.sellice.DataObj.OrderDeatail;
import com.plakadee.sellice.DataObj.SaleProduct;
import com.plakadee.sellice.DataObj.SaleProductEdit;
import com.plakadee.sellice.DataState.SaveState;
import com.plakadee.sellice.DialogAdaper.GetIceFormSaleAdapter;
import com.plakadee.sellice.Special.CalOrder;
import com.plakadee.sellice.Spiner.SaleInterface;
import com.plakadee.sellice.Spiner.SellAdapter;
import com.plakadee.sellice.Spiner.SellEditAdapter;
import com.plakadee.sellice.Tools.GetDocDate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellEdit extends AppCompatActivity implements SaleInterface {
    Customer customer;
    RecyclerView recyclerView;
    Button btn_positive;
    Button btn_negative;
    TextView txt_sum_value;
    TextView txt_sum_price;
    TextView txt_sum_discount;
    TextView txt_sum_value_old;
    TextView txt_sum_price_old;
    TextView txt_sum_discount_old;
    TextView txt_sum_free;
    TextView txt_sum_free_old;
    SellEditAdapter adapter;
    List<SaleProductEdit> product;
    Context context;
    AlertDialog dialog;
    Button btn_select_date;
    int day;
    int mounth;
    int year;
    GetIceFormSaleAdapter getIceFormSaleAdapter;
    int car_runno;
    int line_runno;
    int employee_runno;
    int customer_runno;
    GetDocDate getDocDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_edit);
        context = this;
        getDocDate = new GetDocDate();
        car_runno = Integer.parseInt(SaveState.getCarRunno(context));
        line_runno = Integer.parseInt(SaveState.getLineRunno(context));
        employee_runno = Integer.parseInt(SaveState.getEmployeeRunno(context));
        customer_runno = Integer.parseInt(SaveState.getCustomerRunno(context));
        find_id();
        set_click();
        set_recyclerView();


        try {
            JSONObject jsonObject = new JSONObject(SaveState.getCustomer(this));
            customer = new Customer(jsonObject);
        }catch (JSONException err){
            Log.d("Error", err.toString());
        }
    }

    private void set_recyclerView() {
        product = new ArrayList<>();
        adapter = new SellEditAdapter(context,product,this);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        get_data_sale();

    }

    private void get_data_sale() {

        String URL = SaveState.getURL()+ "vwsale?isactive=true&ordering=runno&employee_runno="+employee_runno+"&sale_date="+getDocDate.get_doc_date_today()+"&customer_runno="+customer_runno;
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                SaleProductEdit dummy = new SaleProductEdit(object);
                                product.add(dummy);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                        int value = 0;
                        int price = 0;
                        int discount = 0;
                        for (int i = 0; i < adapter.mData.size(); i++) {
                            value += adapter.mData.get(i).getQty();
                            discount += adapter.mData.get(i).getDiscount();
                            price += (adapter.mData.get(i).getSale_price()*adapter.mData.get(i).getQty());

                        }
                        txt_sum_value_old.setText(String.valueOf(value));
                        txt_sum_discount_old.setText(String.valueOf(discount));
                        txt_sum_price_old.setText(String.valueOf(price-discount));
                        txt_sum_value.setText(String.valueOf(value));
                        txt_sum_discount.setText(String.valueOf(discount));
                        txt_sum_price.setText(String.valueOf(price-discount));
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



    private void set_click() {
        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload_data();
            }
        });
    }

    private void upload_data() {


        for (int i = 0; i < adapter.mData.size(); i++) {


            SaleProductEdit data = adapter.mData.get(i);
            String URL = SaveState.getURL()+ "edit_sale/"+data.getRunno();
            JSONObject upload = new JSONObject();
            try {
                upload.put("sale_date",getDocDate.get_doc_date_today());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                upload.put("product_runno",data.getProduct_runno());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                upload.put("qty",data.getQty_edit());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                upload.put("sale_price",data.getSale_price_edit());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                upload.put("employee_runno",data.getEmployee_runno());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                upload.put("customer_runno",data.getCustomer_runno());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                upload.put("car_runno",data.getCar_runno());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                upload.put("line_runno",data.getLine_runno());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                upload.put("isactive",true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                upload.put("isdelete",false);
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            new AlertDialog.Builder(context).setMessage(upload.toString()).setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            }).show();
            Log.v("data",upload.toString());
            JsonObjectRequest jsonOblect = new JsonObjectRequest(Request.Method.PUT, URL, upload, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    final Map<String, String> headers = new HashMap<>();
                    headers.put("Authorization", "Basic " + "c2FnYXJAa2FydHBheS5jb206cnMwM2UxQUp5RnQzNkQ5NDBxbjNmUDgzNVE3STAyNzI=");//put your token here
                    return headers;
                }
            };
            Volley.newRequestQueue(context).add(jsonOblect);
            show_get_order();

        }
    }

    private void show_get_order() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
         day = calendar.get(Calendar.DAY_OF_MONTH);
         mounth = calendar.get(Calendar.MONTH);
         year = calendar.get(Calendar.YEAR);
        List<OrderDeatail> orderDeatails = new ArrayList<>();
        for (int i = 0; i < product.size(); i++) {
            SaleProductEdit data = product.get(i);
            OrderDeatail dummy = new OrderDeatail(data.getProduct_runno(), data.getQty_edit()-data.getQty(),0,data.getProduct_name());
            orderDeatails.add(dummy);
        }
        RecyclerView dialogRecyclerview;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View row = inflater.inflate(R.layout.get_ice_dialog,null);
        btn_select_date = row.findViewById(R.id.btn_select_date);
        btn_select_date.setText(getDocDate.get_text_date_form_this(year,mounth,day));

        btn_select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, android.R.style.Widget_Material_Light_DatePicker, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int inyear, int month, int dayOfMonth) {
                        day = dayOfMonth;
                        mounth = month;
                        year = inyear;
                        btn_select_date.setText(getDocDate.get_text_date_form_this(year,mounth,day));
                    }
                }, year, mounth, day);
//                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });
        Button negative = row.findViewById(R.id.btn_negative);
        negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
        Button positive = row.findViewById(R.id.btn_positive);
        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_order(getIceFormSaleAdapter);
            }
        });
        dialogRecyclerview = row.findViewById(R.id.recyclerview);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        getIceFormSaleAdapter = new GetIceFormSaleAdapter(context,orderDeatails);
        llm.setOrientation(RecyclerView.VERTICAL);
        dialogRecyclerview.setLayoutManager(llm);
        dialogRecyclerview.setAdapter(getIceFormSaleAdapter);
        builder.setView(row);
        dialog = builder.create();
        dialog.show();
    }

    private void get_order(GetIceFormSaleAdapter list) {
        CalOrder calOrder = new CalOrder(context,getIceFormSaleAdapter,this,year,mounth,day);


    }

    private void find_id() {
        recyclerView = findViewById(R.id.recyclerview);
        btn_negative = findViewById(R.id.btn_negative);
        btn_positive = findViewById(R.id.btn_positive);
        txt_sum_value = findViewById(R.id.txt_sum_value);
        txt_sum_price = findViewById(R.id.txt_sum_price);
        txt_sum_discount = findViewById(R.id.txt_sum_discount);

        txt_sum_value_old = findViewById(R.id.txt_sum_value_old);
        txt_sum_price_old = findViewById(R.id.txt_sum_price_old);
        txt_sum_discount_old = findViewById(R.id.txt_sum_discount_old);
        txt_sum_free = findViewById(R.id.txt_sum_free);
        txt_sum_free_old = findViewById(R.id.txt_sum_old_free);




    }

    @Override
    public void calculate() {
        int value = 0;
        int price = 0;
        int discount = 0;
        int free = 0;
        for (int i = 0; i < adapter.mData.size(); i++) {
            value += adapter.mData.get(i).getQty_edit();
            discount += adapter.mData.get(i).getDiscount_edit();
//            free += adapter.mData.get(i).get();
            price += (adapter.mData.get(i).getSale_price_edit()*adapter.mData.get(i).getQty_edit());

        }
        txt_sum_value.setText(String.valueOf(value));
        txt_sum_discount.setText(String.valueOf(discount));
        txt_sum_price.setText(String.valueOf(price-discount));
    }

    @Override
    public void telldebt(String debt) {


    }
}