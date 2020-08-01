package com.plakadee.sellice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.plakadee.sellice.DataObj.EditRecive;
import com.plakadee.sellice.DataObj.OrderDeatail;
import com.plakadee.sellice.DataObj.OrderHeader;
import com.plakadee.sellice.DataState.SaveState;
import com.plakadee.sellice.Spiner.GetIceAdapter;
import com.plakadee.sellice.Spiner.GetIceInterface;
import com.plakadee.sellice.Spiner.ReciveAdapter;
import com.plakadee.sellice.Tools.GetDocDate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
public class ReciveIce extends AppCompatActivity implements GetIceInterface {
    ReciveAdapter adapter;
    List<OrderDeatail> orderDeatail;
//    TextView txt_select_date;
    TextView txt_sum_price;
    TextView txt_sum_price2;

    RecyclerView recyclerView;
    Button btn_positive;
    Button btn_negative;
    Context context;
    OrderHeader orderHeader;
    int day;
    int mounth;
    int year;
    int employee_runno;
    int car_runno;
    int line_runno;
    int customer_runno;
    GetDocDate getDocDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recive_ice);
        getDocDate = new GetDocDate();
        context = this;
        line_runno = Integer.parseInt(SaveState.getLineRunno(context));
        car_runno = Integer.parseInt(SaveState.getCarRunno(context));
        employee_runno = Integer.parseInt(SaveState.getEmployeeRunno(context));
        customer_runno = Integer.parseInt(SaveState.getCustomerRunno(context));
        find_id();
        set_click();
        set_recyclerview();
    }

    private void set_recyclerview() {
        orderDeatail = new ArrayList<>();
        adapter = new ReciveAdapter(context,orderDeatail,this);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        get_data_product();
    }

    private void get_data_product() {

        String URL = "http://kis-ws-01.kraois.com/vworderheader?isactive=true&line_runno="+line_runno+"&req_date="+getDocDate.get_doc_date_today();
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length()==0){
                            new AlertDialog.Builder(context).setTitle(R.string.notfound_order).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    finish();
                                }
                            }).show();
                        }else {
                            try {
                                orderHeader = new OrderHeader();
                                orderHeader.setAll(response.getJSONObject(0),context);
//                                Toast.makeText(context, orderHeader.getDoc_no(), Toast.LENGTH_SHORT).show();
                                get_data_detail();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
//                        Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
//                        for (int i = 0; i < response.length(); i++) {
//                            try {
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                OrderDeatail order = new OrderDeatail(jsonObject,0,0);
//                                orderDeatail.add(order);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        adapter.notifyDataSetChanged();

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

    private void get_data_detail() {
        String URL = "http://kis-ws-01.kraois.com/vworderdetail?isactive=true&doc_no="+orderHeader.getDoc_no();
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
//                        Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                OrderDeatail order = new OrderDeatail(jsonObject);

                                orderDeatail.add(order);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                        int sum_value = 0;
                        int sum_value2 = 0;

                        for (int i = 0; i < adapter.mData.size(); i++) {
                            sum_value += adapter.mData.get(i).getOrder_qty1();
                            sum_value2 += adapter.mData.get(i).getOrder_qty2();

                        }
                        txt_sum_price.setText(String.valueOf(sum_value));
                        txt_sum_price2.setText(String.valueOf(sum_value2));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();

                        Log.d("Error.Response", error.toString());
                    }
                }
        );
        queue.add(getRequest);
    }

    private void set_click() {
        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        mounth = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

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
        EditRecive editRecive = new EditRecive();
        SaveState.setDocNo(context,orderHeader.getDoc_no());
        editRecive.edit(context,this,orderHeader,adapter.mData);
    }

    private void find_id() {
        recyclerView = findViewById(R.id.recyclerview);
        txt_sum_price = findViewById(R.id.txt_sum_price);
        btn_positive = findViewById(R.id.btn_positive);
        btn_negative = findViewById(R.id.btn_negative);
        txt_sum_price2 = findViewById(R.id.txt_sum_price2);

    }

    @Override
    public void calculate() {
        int sum_value = 0;
        int sum_value2 = 0;

        for (int i = 0; i < adapter.mData.size(); i++) {
            sum_value += adapter.mData.get(i).getOrder_qty1();
            sum_value2 += adapter.mData.get(i).getOrder_qty2();

        }
        txt_sum_price.setText(String.valueOf(sum_value));
        txt_sum_price2.setText(String.valueOf(sum_value2));

    }
}