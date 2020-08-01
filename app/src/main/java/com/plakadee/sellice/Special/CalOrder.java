package com.plakadee.sellice.Special;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.plakadee.sellice.DataObj.OrderDeatail;
import com.plakadee.sellice.DataObj.OrderDetailEdit;
import com.plakadee.sellice.DataObj.OrderHeader;
import com.plakadee.sellice.DataState.SaveState;
import com.plakadee.sellice.DialogAdaper.GetIceFormSaleAdapter;
import com.plakadee.sellice.GetIce;
import com.plakadee.sellice.R;
import com.plakadee.sellice.Spiner.GetIceEdit;
import com.plakadee.sellice.Tools.GetDocDate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalOrder {
    Context context;
    List<OrderDeatail> list;
    Activity activity;
    int employee_runno;
    int car_runno;
    int line_runno;
    int customer_runno;
    OrderHeader orderHeader;
    JSONArray detail;
    GetDocDate getDocDate;
    private ArrayList<OrderDetailEdit> orderDeatailEdit;
    int day;
    int mounth;
    int year;
    public CalOrder(Context con, GetIceFormSaleAdapter adapter, Activity activity,int year,int mounth,int day) {
        getDocDate = new GetDocDate();
        this.context = con;
        this.list = adapter.mData;
        this.activity = activity;
        line_runno = Integer.parseInt(SaveState.getLineRunno(context));
        car_runno = Integer.parseInt(SaveState.getCarRunno(context));
        employee_runno = Integer.parseInt(SaveState.getEmployeeRunno(context));
        customer_runno = Integer.parseInt(SaveState.getCustomerRunno(context));
        this.year = year;
        this.mounth = mounth;
        this.day = day;
        check_state();

    }

    private void check_state() {
        String URL = SaveState.getURL()+ "orderheader?isactive=true&req_date="+ getDocDate.get_doc_date_form_this(year,mounth,day) +"&ordering=runno&line_runno="+line_runno;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length()==0){
                            Log.v("check","none");
                                create_new_order();
                        }else {
                            try {
                                orderHeader = new OrderHeader();
                                orderHeader.setAll(response.getJSONObject(0),context);
                                Log.v("check","have");
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
    }

    private void create_new_order() {
        Log.v("check","create new");

        JSONObject header = new JSONObject();
        try {
            header.put("doc_date", getDocDate.get_yesterday_form_this(year,mounth,day));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            header.put("req_date",getDocDate.get_doc_date_form_this(year,mounth,day));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            header.put("customer_runno",customer_runno);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            header.put("employee_runno",employee_runno);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            header.put("line_runno",line_runno);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            header.put("order_note",null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            header.put("isapprove",false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            header.put("iscomplete",false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            header.put("isactive",true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            header.put("isdelete",false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        detail = new JSONArray();
        for (int i = 0; i <list.size() ; i++) {
            JSONObject dummy = new JSONObject();

            try {
                dummy.put("product_runno",list.get(i).getProduct_runno());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                dummy.put("order_qty1",list.get(i).getOrder_qty1());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                dummy.put("order_qty2",0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                dummy.put("order_note",null);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                dummy.put("isactive",true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                dummy.put("isdelete",false);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            detail.put(dummy);



        }
        JSONObject upload = new JSONObject();
        try {
            upload.put("header",header);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("detail",(JSONArray)detail);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String URL = SaveState.getURL() + "add_order/";

        JsonObjectRequest jsonOblect = new JsonObjectRequest(Request.Method.POST, URL, upload, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.v("check","createNewComplete");
//                Toast.makeText(mContext, mContext.getResources().getString(R.string.upload_complete), Toast.LENGTH_SHORT).show();
//                activity.finish();
//                    Toast.makeText(mContext, "Response:  " + response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("check","createNewError");
//                Toast.makeText(mContext, mContext.getResources().getString(R.string.somthing_worng), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(mContext, "error:  " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Basic " + "c2FnYXJAa2FydHBheS5jb206cnMwM2UxQUp5RnQzNkQ5NDBxbjNmUDgzNVE3STAyNzI=");//put your token here
                return headers;
            }
        };
        Volley.newRequestQueue(context).add(jsonOblect);
        Toast.makeText(context, context.getResources().getString(R.string.upload_complete), Toast.LENGTH_SHORT).show();
        activity.finish();

    }

    private void get_detail() {
        orderDeatailEdit = new ArrayList<>();
        String URL = SaveState.getURL()+"vworderdetail?isactive=true&ordering=runno&doc_no="+orderHeader.getDoc_no();
        RequestQueue queue = Volley.newRequestQueue(context);
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
                        Log.v("check","getdata");
                        Log.v("check",orderDeatailEdit.toString());
                        edit_all();

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

    private void edit_all() {
        Log.v("size", String.valueOf(orderDeatailEdit.size()));
        Log.v("size", String.valueOf(list.size()));
        for (int i = 0; i < orderDeatailEdit.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (orderDeatailEdit.get(i).getProduct_runno()==list.get(j).getProduct_runno()){
//                    Log.v("size", String.valueOf(list.size()));
                    Log.v("in","inloop");
//                    if (list.get(i).get)
                    orderDeatailEdit.get(i).setOrder_qty1(orderDeatailEdit.get(i).getOrder_qty1()+list.get(j).getOrder_qty1());
                    Log.v("cal",String.valueOf(orderDeatailEdit.get(i).getOrder_qty1()));
                    update_one(orderDeatailEdit.get(i));
                }

            }

        }
        activity.finish();
    }

    private void update_one(OrderDetailEdit orderDetailEdit) {
        Log.v("check","editOne");
        String URL = SaveState.getURL() + "edit_orderdetail/" + orderDetailEdit.getRunno();
        JSONObject upload = new JSONObject();
        try {
            upload.put("runno",orderDetailEdit.getRunno());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("doc_no",orderDetailEdit.getDoc_no());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("product_runno",orderDetailEdit.getProduct_runno());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("order_qty1",orderDetailEdit.getOrder_qty1());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("order_qty2",orderDetailEdit.getOrder_qty2());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("order_note",null);
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
        Log.v("upload",String.valueOf(upload));

        JsonObjectRequest jsonOblect = new JsonObjectRequest(Request.Method.PUT, URL, upload, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.v("check","sucess");
//                Toast.makeText(mContext, mContext.getResources().getString(R.string.upload_complete), Toast.LENGTH_SHORT).show();
//                activity.finish();
//                    Toast.makeText(mContext, "Response:  " + response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("check","error");
//                Toast.makeText(mContext, mContext.getResources().getString(R.string.somthing_worng), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(mContext, "error:  " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Basic " + "c2FnYXJAa2FydHBheS5jb206cnMwM2UxQUp5RnQzNkQ5NDBxbjNmUDgzNVE3STAyNzI=");//put your token here
                return headers;
            }
        };
        Volley.newRequestQueue(context).add(jsonOblect);
        Log.v("check","compleatLoop");
    }
}
