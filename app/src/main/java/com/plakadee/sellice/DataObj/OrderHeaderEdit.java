package com.plakadee.sellice.DataObj;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

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

public class OrderHeaderEdit {
    int runno;
    String doc_no;
    String doc_date;
    String req_date;
    int customer_runno;
    int employee_runno;
    int line_runno;
    int car_runno;
    String order_note;
    boolean isapprove;
    boolean iscomplete;
    boolean isactive;
    boolean isdelete;
    JSONArray detail;
    Context mContext;

    public OrderHeaderEdit() {
    }

    public OrderHeaderEdit(OrderHeader orderHeader) {
        this.runno = orderHeader.getRunno();
        this.doc_no = orderHeader.getDoc_no();
        this.doc_date = orderHeader.getDoc_date();
        this.req_date = orderHeader.getReq_date();
        this.customer_runno = orderHeader.getCustomer_runno();
        this.employee_runno = orderHeader.getEmployee_runno();
        this.line_runno = orderHeader.getLine_runno();
        this.car_runno = orderHeader.getCar_runno();
        this.order_note = null;
        this.isapprove = false;
        this.iscomplete = false;
        this.isactive = true;
        this.isdelete = false;
    }

    public void setDetail(List<OrderDetailEdit> list) {
        detail = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("runno", list.get(i).getRunno());
                jsonObject.put("doc_no", list.get(i).getDoc_no());
                jsonObject.put("product_runno", list.get(i).getProduct_runno());
                jsonObject.put("order_qty1", list.get(i).getOrder_qty1());
                jsonObject.put("order_qty2", list.get(i).getOrder_qty2());
                jsonObject.put("isactive", true);
                jsonObject.put("isdelete", false);
                jsonObject.put("order_note", list.get(i).getOrder_note());
                detail.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }

    public void editGetIce(final Context context, final Activity activity) {
        mContext = context;

        JSONObject header = new JSONObject();
        try {
            header.put("runno", isdelete);
        } catch (JSONException e) {
            Toast.makeText(context, "runno", Toast.LENGTH_SHORT).show();
        }
        try {
            header.put("doc_no", doc_no);
        } catch (JSONException e) {
            Toast.makeText(context, "doc_no", Toast.LENGTH_SHORT).show();
        }
        try {
            header.put("doc_date", doc_date);
        } catch (JSONException e) {
            Toast.makeText(context, "doc_date", Toast.LENGTH_SHORT).show();
        }
        try {
            header.put("req_date", req_date);
        } catch (JSONException e) {
            Toast.makeText(context, "req_date", Toast.LENGTH_SHORT).show();
        }
        try {
            header.put("customer_runno", customer_runno);
        } catch (JSONException e) {
            Toast.makeText(context, "customer_runno", Toast.LENGTH_SHORT).show();
        }
        try {
            header.put("employee_runno", employee_runno);
        } catch (JSONException e) {
            Toast.makeText(context, "employee_runno", Toast.LENGTH_SHORT).show();
        }
        try {
            header.put("line_runno", line_runno);
        } catch (JSONException e) {
            Toast.makeText(context, "line_runno", Toast.LENGTH_SHORT).show();
        }
        try {
            header.put("car_runno", car_runno);
        } catch (JSONException e) {
            Toast.makeText(context, "car_runno", Toast.LENGTH_SHORT).show();
        }
        try {
            header.put("order_note", order_note);
        } catch (JSONException e) {
            Toast.makeText(context, "order_note", Toast.LENGTH_SHORT).show();
        }
        try {
            header.put("isapprove", isapprove);
        } catch (JSONException e) {
            Toast.makeText(context, "isapprove", Toast.LENGTH_SHORT).show();
        }
        try {
            header.put("iscomplete", iscomplete);
        } catch (JSONException e) {
            Toast.makeText(context, "iscomplete", Toast.LENGTH_SHORT).show();
        }
        try {
            header.put("isactive", isactive);
        } catch (JSONException e) {
            Toast.makeText(context, "isactive", Toast.LENGTH_SHORT).show();
        }
        try {
            header.put("isdelete", isdelete);
        } catch (JSONException e) {
            Toast.makeText(context, "isdelete", Toast.LENGTH_SHORT).show();
        }
        JSONObject upload = new JSONObject();
        try {
            upload.put("header", header);
        } catch (JSONException e) {
            Toast.makeText(context, "header", Toast.LENGTH_SHORT).show();
        }
        try {
            upload.put("detail", (JSONArray) detail);
        } catch (JSONException e) {
            Toast.makeText(context, "detail", Toast.LENGTH_SHORT).show();
        }
        String URL = SaveState.getURL() + "edit_order/" + doc_no;

        JsonObjectRequest jsonOblect = new JsonObjectRequest(Request.Method.PUT, URL, upload, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                Toast.makeText(mContext, mContext.getResources().getString(R.string.upload_complete), Toast.LENGTH_SHORT).show();
                activity.finish();
//                    Toast.makeText(mContext, "Response:  " + response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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
        Toast.makeText(context, mContext.getResources().getString(R.string.upload_complete), Toast.LENGTH_SHORT).show();
        activity.finish();


    }
}
