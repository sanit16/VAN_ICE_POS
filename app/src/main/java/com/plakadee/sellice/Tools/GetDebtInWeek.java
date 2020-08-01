package com.plakadee.sellice.Tools;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.plakadee.sellice.DataObj.OrderDeatail;
import com.plakadee.sellice.DataState.SaveState;
import com.plakadee.sellice.Spiner.SaleInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class GetDebtInWeek  {
    Context mContext;
    SaleInterface saleInterface;
    int year;
    int month;
    int day;
    GetDocDate getDocDate;
    double alldebt;
    double allpaid;
    public GetDebtInWeek() {
    }
    public void get_debt_form_this(Context context , SaleInterface saleInterface1){
        getDocDate = new GetDocDate();
        this.saleInterface = saleInterface1;
        this.mContext = context;
        Calendar calendar = Calendar.getInstance();
         day = calendar.get(Calendar.DAY_OF_MONTH);
         month = calendar.get(Calendar.MONTH)+1;
         year = calendar.get(Calendar.YEAR);
         if (day>15&&day<31){
             String form = getDocDate.get_doc_date_form_this(year,month,16);
             String to = getDocDate.get_doc_date_form_this(year,month,Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
             get_debt(form,to);
         }else {
             String form = getDocDate.get_doc_date_form_this(year,month,1);
             String to = getDocDate.get_doc_date_form_this(year,month,15);
             get_debt(form,to);
         }
    }

    private void get_debt(String form, String to) {
        String URL = SaveState.getURL() +"saledue?isactive=true&ordering=runno&doc_date__gte="+form+"&doc_date__lte="+to;
        RequestQueue queue = Volley.newRequestQueue(mContext);
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length()==0){
                            saleInterface.telldebt("0");
                        }else {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    double value = jsonObject.getDouble("due_value");

                                    if (jsonObject.getString("due_type").equals("A")){
                                        alldebt +=value;
                                    }else {
                                        allpaid +=value;
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            saleInterface.telldebt(String.valueOf(alldebt-allpaid));

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
}
