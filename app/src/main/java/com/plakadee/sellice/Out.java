package com.plakadee.sellice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
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
import com.plakadee.sellice.DataObj.Car;
import com.plakadee.sellice.DataObj.Line;
import com.plakadee.sellice.DataObj.OrderDeatail;
import com.plakadee.sellice.DataObj.OrderHeader;
import com.plakadee.sellice.DataState.SaveState;
import com.plakadee.sellice.Interface.TransferInterface;
import com.plakadee.sellice.Spiner.GetCarAdapter;
import com.plakadee.sellice.Spiner.GetIceAdapter;
import com.plakadee.sellice.Spiner.GetIceInterface;
import com.plakadee.sellice.Spiner.GetLineAdapter;
import com.plakadee.sellice.Tools.GetDocDate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Out extends AppCompatActivity implements TransferInterface, GetIceInterface {
    GetIceAdapter adapter;
    GetLineAdapter lineAdapter;
    GetCarAdapter carAdapter;
    List<Car> cars;
    List<Line> lines;
    List<OrderDeatail> orderDeatail;
//    TextView txt_select_date;
    TextView txt_sum_price;
    RecyclerView recyclerView;
    RecyclerView recyclerView_car;
    RecyclerView recyclerView_line;
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
    GetDocDate getDocDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out);
        getDocDate = new GetDocDate();
        context = this;
        find_id();
        set_click();
        set_recyclerview();
        set_recyclerview_line();
        set_recyclerview_car();
    }

    private void set_recyclerview_car() {
        cars = new ArrayList<>();
        carAdapter = new GetCarAdapter(context,cars,this);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView_car.setLayoutManager(llm);
        recyclerView_car.setAdapter(carAdapter);
        get_data_product_car();
    }
    private void set_recyclerview_line() {
        lines = new ArrayList<>();
        lineAdapter = new GetLineAdapter(context,lines,this);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView_line.setLayoutManager(llm);
        recyclerView_line.setAdapter(lineAdapter);
        get_data_product_line();
    }

    private void get_data_product_line() {
        String URL = SaveState.getURL()+"line?isactive=true&ordering=runno";
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Line order = new Line(jsonObject);
                                lines.add(order);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        lineAdapter.notifyDataSetChanged();

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
    private void get_data_product_car() {
        String URL = SaveState.getURL()+"car?isactive=true&ordering=runno";
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Car order = new Car(jsonObject);
                                cars.add(order);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        carAdapter.notifyDataSetChanged();

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
    private void set_recyclerview() {
        orderDeatail = new ArrayList<>();
        adapter = new GetIceAdapter(context,orderDeatail,this);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        get_data_product();
    }

    private void get_data_product() {
        String URL = SaveState.getURL()+"product?isactive=true&ordering=runno";
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                OrderDeatail order = new OrderDeatail(jsonObject,0,0);
                                orderDeatail.add(order);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();

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
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        mounth = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
//        txt_select_date.setText(getDocDate.get_text_date_form_this(year,mounth,day));
//
//        txt_select_date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                DatePickerDialog datePickerDialog = new DatePickerDialog(context, android.R.style.Widget_Material_Light_DatePicker, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int inyear, int month, int dayOfMonth) {
//                        day = dayOfMonth;
//                        mounth = month;
//                        year = inyear;
//                        txt_select_date.setText(getDocDate.get_text_date_form_this(year,mounth,day));
//
//
//                    }
//                }, year, mounth, day);
////                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                datePickerDialog.show();
//
//            }
//        });
        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                line_runno = Integer.parseInt(SaveState.getLineRunno(context));
//                car_runno = Integer.parseInt(SaveState.getCarRunno(context));
//                employee_runno = Integer.parseInt(SaveState.getEmployeeRunno(context));
//                customer_runno = Integer.parseInt(SaveState.getCustomerRunno(context));
//
//                upload_data();
            }
        });
    }

//    private void upload_data() {
//        OrderHeader orderHeader = new OrderHeader(getDocDate.get_doc_date_today(),getDocDate.get_doc_date_form_this(year,mounth,day),customer_runno,employee_runno,line_runno,car_runno,null);
//        orderHeader.setDetail(adapter.mData);
//        orderHeader.uploadOrderHeader(context,this);
//    }

    private void find_id() {
//        txt_select_date = findViewById(R.id.txt_select_date);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView_line = findViewById(R.id.recyclerview_line);
        recyclerView_car = findViewById(R.id.recyclerview_car);
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
    @Override
    public void SelectLine(Line data) {
        line_runno =Integer.parseInt(data.getRunno());
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            if (car.getLine_runno().equals(data.getRunno())){
                carAdapter.runno =car.getRunno();
                car_runno = Integer.parseInt(car.getRunno());
                carAdapter.notifyDataSetChanged();
//                recyclerView_car.setSelected();
                return;
            }

        }


    }

    @Override
    public void SelectCar(Car data) {
        car_runno = Integer.parseInt(data.getRunno());

    }

    @Override
    public void MoveCarCenter(int position) {
        recyclerView_car.scrollToPosition(position);

    }


}