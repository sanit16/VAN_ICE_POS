package com.plakadee.sellice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.plakadee.sellice.DataObj.Customer;
import com.plakadee.sellice.DataObj.Line;
import com.plakadee.sellice.DataState.SaveState;
import com.plakadee.sellice.Spiner.CustomerListAdapter;
import com.plakadee.sellice.Tools.GetDistance;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomerList extends AppCompatActivity implements LocationListener {
    Button btn_negative;
    Button btn_positive;
    Button btn_show_map;
    RecyclerView recyclerView;
    CustomerListAdapter adapter;
    ArrayList<Customer> customers;
    Context context;
    LocationManager mLocation;
    int line_runno ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        line_runno = Integer.parseInt(SaveState.getLineRunno(this));
        mLocation = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLocation.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 10, CustomerList.this);
        context = this;
        setContentView(R.layout.activity_customer_list);
        find_id();
        set_click();
        set_recyclerView();
    }

    private void set_recyclerView() {
        customers = new ArrayList<>();
        adapter = new CustomerListAdapter(context,customers,this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        get_data_customer();

    }

    private void get_data_customer() {
        String URL = SaveState.getURL() +"customer?isactive=true&ordering=runno&line_runno="+line_runno;
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Customer customer = new Customer(jsonObject);
                                customers.add(customer);
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
        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerList.this,CreateUser.class);
                startActivity(intent);
                finish();

            }
        });
        btn_show_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerList.this,MapListCustomer.class);
                startActivity(intent);
            }
        });
    }

    private void find_id() {
        btn_positive = findViewById(R.id.btn_positive);
        btn_negative = findViewById(R.id.btn_negative);
        recyclerView = findViewById(R.id.recyclerview);
        btn_show_map = findViewById(R.id.btn_show_map);
    }

    @Override
    public void onLocationChanged(Location location) {
        for (int i = 0; i < adapter.mData.size(); i++) {
            double startlat = location.getLatitude();
            double startlon =location.getLongitude();
            double endlat = Double.parseDouble(adapter.mData.get(i).getLatitude());
            double endlon = Double.parseDouble(adapter.mData.get(i).getLongitude());
            double distance = GetDistance.getDistance(startlat,startlon,endlat,endlon);
            Toast.makeText(context, String.valueOf(distance), Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    protected void onPause() {
        mLocation.removeUpdates(this);
        super.onPause();
    }

    @Override
    protected void onStop() {
        mLocation.removeUpdates(this);
        super.onStop();
    }
}