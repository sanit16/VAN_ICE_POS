package com.plakadee.sellice.Spiner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.plakadee.sellice.DataObj.OrderDeatail;
import com.plakadee.sellice.DataObj.OrderDetailEdit;
import com.plakadee.sellice.DataObj.OrderHeader;
import com.plakadee.sellice.DataObj.OrderHeaderEdit;
import com.plakadee.sellice.DataState.SaveState;
import com.plakadee.sellice.GetIce;
import com.plakadee.sellice.R;
import com.plakadee.sellice.Tools.GetDocDate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
public class GetIceEdit extends AppCompatActivity implements GetIceInterface {
    GetIceEditAdapter adapter;
    List<OrderDetailEdit> orderDeatailEdit;
    TextView txt_select_date;
    TextView txt_sum_price;
    RecyclerView recyclerView;
    Button btn_positive;
    Button btn_negative;
    Context context;
    int day;
    int mounth;
    int year;
    int employee_runno;
    int car_runno;
    int line_runno;
    int customer_runno;
    OrderHeader orderHeader;
    GetDocDate getDocDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_ice_edit);
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
        orderDeatailEdit = new ArrayList<>();
        adapter = new GetIceEditAdapter(context,orderDeatailEdit,this);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        get_data_product();
    }

    private void get_data_product() {

        String URL = SaveState.getURL()+ "orderheader?isactive=true&req_date="+ getDocDate.get_doc_date_tomorrow() +"&ordering=runno&line_runno="+line_runno;
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length()==0){

                            Intent intent = new Intent(GetIceEdit.this, GetIce.class);
                            startActivity(intent);
                            finish();
                        }else {

                                try {
                                    JSONObject jsonObject = response.getJSONObject(0);
                                     orderHeader = new OrderHeader();
                                     orderHeader.setAll(jsonObject,context);

                                     set_req_date();
                                    get_detail();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                        }


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
        adapter.notifyDataSetChanged();
    }

    private void set_req_date() {
        final String[] dateArr = orderHeader.getReq_date().split("-");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(dateArr[0]),(Integer.parseInt(dateArr[1])-1),Integer.parseInt(dateArr[2]));
//        calendar.add(Calendar.DATE, 1);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        mounth = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        txt_select_date.setText(" วันที่ "+String.valueOf(day)+" เดือน "+String.valueOf(mounth+1)+" ปี "+String.valueOf(year));
    }

    private void get_detail() {
        String URL = SaveState.getURL()+"vworderdetail?isactive=true&ordering=runno&doc_no="+orderHeader.getDoc_no();
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                OrderDetailEdit order = new OrderDetailEdit(jsonObject);
                                orderDeatailEdit.add(order);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                        int sum_value = 0;
                        for (int i = 0; i < adapter.mData.size(); i++) {
                            sum_value += adapter.mData.get(i).getOrder_qty1();
                        }
                        txt_sum_price.setText(String.valueOf(sum_value));

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
        adapter.notifyDataSetChanged();
    }

    private void set_click() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        mounth = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        txt_select_date.setText(" วันที่ "+String.valueOf(day)+" เดือน "+String.valueOf(mounth+1)+" ปี "+String.valueOf(year));

        txt_select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(context, android.R.style.Widget_Material_Light_DatePicker, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int inyear, int month, int dayOfMonth) {
                        day = dayOfMonth;
                        mounth = month;
                        year = inyear;
                        txt_select_date.setText(" วันที่ "+String.valueOf(day)+" เดือน "+String.valueOf(mounth+1)+" ปี "+String.valueOf(year));


                    }
                }, year, mounth, day);
//                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });
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
        String req_date = year+"-"+(mounth+1)+"-"+day;
        orderHeader.setReq_date(req_date);
        OrderHeaderEdit orderHeaderEdit = new OrderHeaderEdit(orderHeader);
        orderHeaderEdit.setDetail(adapter.mData);
        orderHeaderEdit.editGetIce(context,this);
//        orderHeader.setDetail(adapter.mData);
//        orderHeader.uploadOrderHeader(context);
    }

    private void find_id() {
        txt_select_date = findViewById(R.id.txt_select_date);
        recyclerView = findViewById(R.id.recyclerview);
        txt_sum_price = findViewById(R.id.txt_sum_price);
        btn_positive = findViewById(R.id.btn_positive);
        btn_negative = findViewById(R.id.btn_negative);
    }

    @Override
    public void calculate() {
        int sum_value = 0;
        for (int i = 0; i < adapter.mData.size(); i++) {
            sum_value += adapter.mData.get(i).getOrder_qty1();
        }
        txt_sum_price.setText(String.valueOf(sum_value));

    }
}