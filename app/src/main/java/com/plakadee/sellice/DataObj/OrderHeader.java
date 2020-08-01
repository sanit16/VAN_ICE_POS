package com.plakadee.sellice.DataObj;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.plakadee.sellice.DataState.SaveState;
import com.plakadee.sellice.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderHeader {
    int runno;
    String doc_no;
    String doc_date;
    String req_date;
    int customer_runno;
    int employee_runno;
    int line_runno;
    int car_runno;
    boolean isapprove;
    boolean iscomplete;
    String order_note;
    Context mContext;
    JSONArray jsonDetail;

    public OrderHeader() {
    }

    public OrderHeader(String doc_date, String req_date, int customer_runno, int employee_runno, int line_runno, int car_runno, String order_note) {
        this.doc_date = doc_date;
        this.req_date = req_date;
        this.customer_runno = customer_runno;
        this.employee_runno = employee_runno;
        this.line_runno = line_runno;
        this.car_runno = car_runno;
        this.order_note = order_note;
    }

    public void setAll(JSONObject jsonObject, Context context) {
        mContext = context;
        try {
            this.req_date = jsonObject.getString("req_date");
        } catch (JSONException e) {
            Toast.makeText(mContext, "req_date", Toast.LENGTH_SHORT).show();
        }
        try {
            this.customer_runno = jsonObject.getInt("customer_runno");
        } catch (JSONException e) {
//            Toast.makeText(mContext, "customer_runno", Toast.LENGTH_SHORT).show();
        }
        try {
            this.employee_runno = jsonObject.getInt("employee_runno");
        } catch (JSONException e) {
            Toast.makeText(mContext, "employee_runno", Toast.LENGTH_SHORT).show();
        }
        try {
            this.line_runno = jsonObject.getInt("line_runno");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.car_runno = jsonObject.getInt("car_runno");
        } catch (JSONException e) {
            Toast.makeText(mContext, "line_runno", Toast.LENGTH_SHORT).show();
        }
        try {
            this.doc_no = jsonObject.getString("doc_no");
        } catch (JSONException e) {
            Toast.makeText(mContext, "doc_no", Toast.LENGTH_SHORT).show();
        }
        try {
            this.doc_date = jsonObject.getString("doc_date");
        } catch (JSONException e) {
            Toast.makeText(mContext, "doc_date", Toast.LENGTH_SHORT).show();
        }
        try {
            this.runno = jsonObject.getInt("runno");
        } catch (JSONException e) {
            Toast.makeText(mContext, "runno", Toast.LENGTH_SHORT).show();
        }


    }

    public void setDetail(List<OrderDeatail> list) {
        jsonDetail = new JSONArray();
        for (int i = 0; i < list.size(); i++) {

            try {
                JSONObject object = new JSONObject();
                object.put("product_runno", list.get(i).getProduct_runno());
                object.put("order_qty1", list.get(i).getOrder_qty1());
                object.put("order_qty2", list.get(i).getOrder_qty2());
                object.put("order_note", null);
                object.put("isactive", true);
                object.put("isdelete", false);
                jsonDetail.put(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }

    public void uploadOrderHeader(final Context context, Activity activity) {
        mContext = context;

        String URL = SaveState.getURL() + "add_order";

        JSONObject jon = new JSONObject();
        try {
            jon.put("doc_date", doc_date);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jon.put("req_date", req_date);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jon.put("customer_runno", customer_runno);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jon.put("employee_runno", line_runno);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jon.put("line_runno", line_runno);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jon.put("car_runno", car_runno);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jon.put("order_note", order_note);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jon.put("isapprove", false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jon.put("iscomplete", false);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject upload = new JSONObject();
        try {
            upload.put("header", jon);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("detail", (JSONArray) jsonDetail);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonOblect = new JsonObjectRequest(Request.Method.POST, URL, upload, new Response.Listener<JSONObject>() {
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
        Toast.makeText(context, mContext.getResources().getString(R.string.upload_complete), Toast.LENGTH_SHORT).show();
        activity.finish();
//            VolleyApplication.getInstance().addToRequestQueue(jsonOblect);

//        RequestQueue queue = Volley.newRequestQueue(context);
//        StringRequest getRequest = new StringRequest(Request.Method.POST, URL, jsonObject,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                    }
//                }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }
//        );
//        queue.add(getRequest);
    }

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    public String getReq_date() {
        return req_date;
    }

    public void setReq_date(String req_date) {
        this.req_date = req_date;
    }

    public int getCustomer_runno() {
        return customer_runno;
    }

    public void setCustomer_runno(int customer_runno) {
        this.customer_runno = customer_runno;
    }

    public int getEmployee_runno() {
        return employee_runno;
    }

    public void setEmployee_runno(int employee_runno) {
        this.employee_runno = employee_runno;
    }

    public int getLine_runno() {
        return line_runno;
    }

    public void setLine_runno(int line_runno) {
        this.line_runno = line_runno;
    }

    public int getCar_runno() {
        return car_runno;
    }

    public void setCar_runno(int car_runno) {
        this.car_runno = car_runno;
    }

    public int getRunno() {
        return runno;
    }

    public void setRunno(int runno) {
        this.runno = runno;
    }

    public String getDoc_date() {
        return doc_date;
    }

    public void setDoc_date(String doc_date) {
        this.doc_date = doc_date;
    }
}
