package com.plakadee.sellice.DataObj;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.plakadee.sellice.CustomerList;
import com.plakadee.sellice.DataState.SaveState;
import com.plakadee.sellice.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CustomerUpload {
    int runno;
    int customer_code;
    String customer_name;
    String customer_contact;
    String customer_description;
    String phone_number;
    String fax_number;
    String address_no;
    String subdistrict_name;
    String district_name;
    String province_name;
    String country_name;
    String zip_code;
    int line_runno;
    double latitude;
    double longitude;
    String customer_comment;
    Context mContext;

    public CustomerUpload() {
    }
    public void upload(Context context, Activity activity){
        String URL = SaveState.getURL()+ "add_customer";

        this.mContext = context;
        JSONObject  upload = new JSONObject();
        try {
            upload.put("customer_code",customer_code);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("customer_name",customer_name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("customer_contact",customer_contact);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("customer_description",customer_description);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("phone_number",phone_number);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("fax_number",fax_number);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("address_no",address_no);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("subdistrict_name",subdistrict_name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("district_name",district_name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("province_name",province_name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("country_name",country_name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("zip_code",zip_code);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("line_runno",line_runno);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("latitude",latitude);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("longitude",longitude);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            upload.put("customer_comment",customer_comment);
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
        Log.v("json",upload.toString());
        JsonObjectRequest jsonOblect = new JsonObjectRequest(Request.Method.POST, URL, upload, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                new AlertDialog.Builder(mContext).setMessage(response.toString()).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                new AlertDialog.Builder(mContext).setMessage(error.toString()).show();


            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Basic " + "c2FnYXJAa2FydHBheS5jb206cnMwM2UxQUp5RnQzNkQ5NDBxbjNmUDgzNVE3STAyNzI=");//put your token here
                return headers;
            }
        };
        Volley.newRequestQueue(mContext).add(jsonOblect);
        Toast.makeText(context, context.getResources().getString(R.string.upload_complete), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(activity, CustomerList.class);
        mContext.startActivity(intent);
        activity.finish();

    }

    public CustomerUpload(int runno, int customer_code, String customer_name, String customer_contact, String customer_description, String phone_number, String fax_number, String address_no, String subdistrict_name, String district_name, String province_name, String country_name, String zip_code, int line_runno, double latitude, double longitude, String customer_comment) {
        this.runno = runno;
        this.customer_code = customer_code;
        this.customer_name = customer_name;
        this.customer_contact = customer_contact;
        this.customer_description = customer_description;
        this.phone_number = phone_number;
        this.fax_number = fax_number;
        this.address_no = address_no;
        this.subdistrict_name = subdistrict_name;
        this.district_name = district_name;
        this.province_name = province_name;
        this.country_name = country_name;
        this.zip_code = zip_code;
        this.line_runno = line_runno;
        this.latitude = latitude;
        this.longitude = longitude;
        this.customer_comment = customer_comment;
    }

    public int getRunno() {
        return runno;
    }

    public void setRunno(int runno) {
        this.runno = runno;
    }

    public int getCustomer_code() {
        return customer_code;
    }

    public void setCustomer_code(int customer_code) {
        this.customer_code = customer_code;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_contact() {
        return customer_contact;
    }

    public void setCustomer_contact(String customer_contact) {
        this.customer_contact = customer_contact;
    }

    public String getCustomer_description() {
        return customer_description;
    }

    public void setCustomer_description(String customer_description) {
        this.customer_description = customer_description;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getFax_number() {
        return fax_number;
    }

    public void setFax_number(String fax_number) {
        this.fax_number = fax_number;
    }

    public String getAddress_no() {
        return address_no;
    }

    public void setAddress_no(String address_no) {
        this.address_no = address_no;
    }

    public String getSubdistrict_name() {
        return subdistrict_name;
    }

    public void setSubdistrict_name(String subdistrict_name) {
        this.subdistrict_name = subdistrict_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public int getLine_runno() {
        return line_runno;
    }

    public void setLine_runno(int line_runno) {
        this.line_runno = line_runno;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCustomer_comment() {
        return customer_comment;
    }

    public void setCustomer_comment(String customer_comment) {
        this.customer_comment = customer_comment;
    }
}
