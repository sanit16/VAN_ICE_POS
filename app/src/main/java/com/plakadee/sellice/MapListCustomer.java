package com.plakadee.sellice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.plakadee.sellice.DataObj.Customer;
import com.plakadee.sellice.Spiner.CustomerListAdapter;
import com.plakadee.sellice.Spiner.CustomerMapListAdapter;
import com.plakadee.sellice.Spiner.ShowMapCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MapListCustomer extends AppCompatActivity implements OnMapReadyCallback , ShowMapCallback {
    Button btn_negative;
    RecyclerView recyclerView;
    CustomerMapListAdapter adapter;
    ArrayList<Customer>  customers;
    Context context;
    SupportMapFragment mapFragment;
    private FusedLocationProviderClient fusedLocationClient;
    LatLng latlon;
    GoogleMap googleMap;
//    Button btn_debt;
//    Button btn_buy;
//    boolean debt = false;
//    boolean buy = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_list_customer);
        context = this;
        find_id();
        set_click();
        set_recyclerView();
        getLocation();
    }

    private void set_recyclerView() {
        customers = new ArrayList<>();
        adapter = new CustomerMapListAdapter(context,customers,this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);

    }

    private void get_data_customer() {
        String URL = "http://kis-ws-01.kraois.com/customer?isactive=true&ordering=runno";
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Customer customer = new Customer(jsonObject);
                                if (customer.getLatitude()!=null){
                                    LatLng latLng =  new LatLng(Double.parseDouble(customer.getLatitude()),Double.parseDouble(customer.getLongitude()) );
                                    MarkerOptions markerOptions = new MarkerOptions();
                                    markerOptions.position(latLng);
                                    markerOptions.title(customer.getCustomer_name());
                                    markerOptions.getPosition();
                                    googleMap.addMarker(markerOptions);
                                }

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

//        btn_debt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                debt = !debt;
//                set_marker();
//            }
//        });
//        btn_buy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                buy = !buy;
//            }
//        });
    }

//    private void set_marker() {
//        for (int i = 0; i < customers.size(); i++) {
//            Customer cus =  customers.get(i);
//            final String[] stateArr = cus.getCustomer_comment().split(",");
//            if (debt){
//
//            }
//
//        }
//    }

    private void find_id() {
        btn_negative = findViewById(R.id.btn_negative);
        recyclerView = findViewById(R.id.recyclerview);
//        btn_debt = findViewById(R.id.btn_debt);
//        btn_buy = findViewById(R.id.btn_buy);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
//        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in you"));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlon,16));

        LatLng latLng =  latlon;
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        this.googleMap.clear();
        markerOptions.title("คุณ");
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.delivery));
        markerOptions.getPosition();
        this.googleMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlon,16));
        get_data_customer();


    }
    private void getLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapListCustomer.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);

            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            latlon = new LatLng(location.getLatitude(),location.getLongitude());
                            mapFragment = (SupportMapFragment) getSupportFragmentManager()
                                    .findFragmentById(R.id.map);
                            mapFragment.getMapAsync(MapListCustomer.this);
                        }
                    }
                });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1){
            getLocation();
        }
    }

    @Override
    public void customer_click(LatLng latLng2) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng2,17));


    }
}