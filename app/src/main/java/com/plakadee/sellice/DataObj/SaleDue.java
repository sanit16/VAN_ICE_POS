package com.plakadee.sellice.DataObj;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.plakadee.sellice.DataState.SaveState;
import com.plakadee.sellice.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SaleDue {
    String doc_date;
    String due_type;
    int line_runno;
    int employee_runno;
    int customer_runno;
    int due_value;
    String due_note;
    boolean isactive;
    boolean isdelete;
    Context mContext;
    public SaleDue() {
    }

    public SaleDue(String doc_date, String due_type, int line_runno, int employee_runno, int customer_runno, int due_value, String due_note, boolean isactive, boolean isdelete) {
        this.doc_date = doc_date;
        this.due_type = due_type;
        this.line_runno = line_runno;
        this.employee_runno = employee_runno;
        this.customer_runno = customer_runno;
        this.due_value = due_value;
        this.due_note = due_note;
        this.isactive = isactive;
        this.isdelete = isdelete;
    }

    public String getDoc_date() {
        return doc_date;
    }

    public void setDoc_date(String doc_date) {
        this.doc_date = doc_date;
    }

    public String getDue_type() {
        return due_type;
    }

    public void setDue_type(String due_type) {
        this.due_type = due_type;
    }

    public int getLine_runno() {
        return line_runno;
    }

    public void setLine_runno(int line_runno) {
        this.line_runno = line_runno;
    }

    public int getEmployee_runno() {
        return employee_runno;
    }

    public void setEmployee_runno(int employee_runno) {
        this.employee_runno = employee_runno;
    }

    public int getCustomer_runno() {
        return customer_runno;
    }

    public void setCustomer_runno(int customer_runno) {
        this.customer_runno = customer_runno;
    }

    public int getDue_value() {
        return due_value;
    }

    public void setDue_value(int due_value) {
        this.due_value = due_value;
    }

    public String getDue_note() {
        return due_note;
    }

    public void setDue_note(String due_note) {
        this.due_note = due_note;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public boolean isIsdelete() {
        return isdelete;
    }

    public void setIsdelete(boolean isdelete) {
        this.isdelete = isdelete;
    }
    public void upload(Context context){
        this.mContext = context;
        JSONObject upload = new JSONObject();
        try {
            upload.put("doc_date",doc_date);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("due_type",due_type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("line_runno",line_runno);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("employee_runno",employee_runno);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("customer_runno",customer_runno);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("due_value",due_value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("due_note",due_note);
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
        Log.v("upload",upload.toString());

        String URL = SaveState.getURL()+ "add_saledue";
        JsonObjectRequest jsonOblect = new JsonObjectRequest(Request.Method.POST, URL, upload, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.v("createdebt",response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("createdebt",error.toString());


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
    }
}
