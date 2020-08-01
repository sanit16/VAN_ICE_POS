package com.plakadee.sellice.DataObj;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.plakadee.sellice.DataState.SaveState;
import com.plakadee.sellice.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditRecive {
    int runno;
    String doc_no;
    String doc_date;
    String req_date;
    int customer_runno;
    int employee_runno;
    int line_runno;
    int car_runno;
    String order_note;
    Context mContext;
    JSONArray jsonDetail;
    boolean isapprove;
    boolean iscomplete;
    public EditRecive() {
    }

    public void edit(Context context, Activity activity, OrderHeader orderHeader, List<OrderDeatail> list) {
        mContext = context;
        this.runno = orderHeader.getRunno();
        this.doc_no = orderHeader.getDoc_no();
        this.doc_date = orderHeader.getDoc_date();
        this.req_date = orderHeader.getReq_date();
//        this.customer_runno = orderHeader.getCustomer_runno();
        this.employee_runno = orderHeader.getEmployee_runno();
        this.line_runno = orderHeader.getLine_runno();
        this.car_runno = orderHeader.getCar_runno();
//        this.order_note = orderHeader.get;
        JSONObject header = new JSONObject();
        jsonDetail = new JSONArray();
        try {
            header.put("runno", orderHeader.getRunno());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            header.put("doc_no", orderHeader.getDoc_no());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            header.put("doc_date", orderHeader.getDoc_date());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            header.put("req_date", orderHeader.getReq_date());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            header.put("customer_runno", orderHeader.getCustomer_runno());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            header.put("employee_runno", orderHeader.getEmployee_runno());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            header.put("line_runno", orderHeader.getLine_runno());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            header.put("car_runno", orderHeader.getCar_runno());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            header.put("order_note", "-");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < list.size(); i++) {
            JSONObject object = new JSONObject();
            try {
                object.put("runno", list.get(i).getRunno());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                object.put("doc_no", list.get(i).getDoc_no());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                object.put("product_runno", list.get(i).getProduct_runno());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                object.put("order_qty1", list.get(i).getOrder_qty1());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                object.put("order_qty2", list.get(i).getOrder_qty2());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                object.put("isactive", true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                object.put("isdelete", false);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                object.put("isapprove", true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                object.put("iscomplete", false);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonDetail.put(object);

        }
        JSONObject upload = new JSONObject();
        try {
            upload.put("header",header);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("detail",(JSONArray)jsonDetail);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String URL = SaveState.getURL() + "edit_order/" + doc_no;

        JsonObjectRequest jsonOblect = new JsonObjectRequest(Request.Method.PUT, URL, upload, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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
        Toast.makeText(context, mContext.getResources().getString(R.string.upload_complete), Toast.LENGTH_SHORT).show();
        activity.finish();
    }

}
