package com.plakadee.sellice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.plakadee.sellice.DataObj.Customer;
import com.plakadee.sellice.DataObj.CustomerUpload;
import com.plakadee.sellice.DataState.SaveState;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CustomerEdit extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap googleMap;
    SupportMapFragment mapFragment;
    private FusedLocationProviderClient fusedLocationClient;
    LatLng latlon;

    LinearLayout layer;
    TextView textView;
    TextView positive;
    private double lat;
    private double lon;
    Context context;
    CustomerUpload customerUpload;
    Button btn_positive;
    Button btn_negative;
    EditText edt_username;
    EditText edt_phone;
    String name;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit);
        name = SaveState.getCustomerName(this);
        phone = SaveState.getCustomerPhone(this);
        lat = Double.parseDouble(SaveState.getCustomerLat(this));
        lon = Double.parseDouble(SaveState.getCustomerLon(this));
        customerUpload = new CustomerUpload();
        searchID();
        assign_value();

        getLocation();
        set_click();
    }

    private void assign_value() {
        edt_username.setText(name);
        edt_phone.setText(phone);
    }

    private void set_click() {
        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerEdit.this,CustomerList.class);
                startActivity(intent);
                finish();
            }
        });
        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = edt_username.getText().toString();
                String phone_number = edt_phone.getText().toString();
                if (user_name.length()==0){
                    Toast.makeText(context, context.getResources().getString(R.string.pleas_type_user_name), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phone_number.length()==0){
                    phone_number = "0000000000";
                }
                customerUpload.setCustomer_name(user_name);
                customerUpload.setPhone_number(phone_number);
                int line_runno = Integer.parseInt(SaveState.getLineRunno(context));
                customerUpload.setLine_runno(line_runno);
                customerUpload.upload(context, CustomerEdit.this);

            }
        });
    }

    private void searchID() {
        context = this;
        layer = findViewById(R.id.layer);
        textView = findViewById(R.id.text);
        btn_positive = findViewById(R.id.btn_positive);
        btn_negative = findViewById(R.id.btn_negative);
        edt_username = findViewById(R.id.edt_username);
        edt_phone = findViewById(R.id.edt_phone);

    }

    private void getLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CustomerEdit.this,
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
                            latlon = new LatLng(lat,lon);
                            mapFragment = (SupportMapFragment) getSupportFragmentManager()
                                    .findFragmentById(R.id.map);
                            mapFragment.getMapAsync(CustomerEdit.this);
                        }
                    }
                });
    }
    @Override
    public void onMapReady(final GoogleMap googleMapP) {
        this.googleMap = googleMapP;
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlon,16));

        googleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                layer.setVisibility(View.INVISIBLE);
            }
        });
        googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                layer.setVisibility(View.VISIBLE);
                lat = googleMap.getCameraPosition().target.latitude;
                lon = googleMap.getCameraPosition().target.longitude;
                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(CustomerEdit.this, Locale.getDefault());
                try {
                    addresses = geocoder.getFromLocation(lat, lon, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    if (addresses.size() != 0) {
                        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

//                        InfomationSaveState.setAddress(context,addresses.get(0).getAddressLine(0));
//                        InfomationSaveState.setState(context,addresses.get(0).getLocality());
//                        InfomationSaveState.setProvince(context,addresses.get(0).getSubAdminArea());
//                        InfomationSaveState.setCity(context,addresses.get(0).getAdminArea());
//                        InfomationSaveState.setCountry(context,addresses.get(0).getCountryName());
//                        InfomationSaveState.setZipCode(context,addresses.get(0).getPostalCode());
//                        InfomationSaveState.setLatitude(LocationAc.this,String.valueOf(lat));
//                        InfomationSaveState.setLongitude(LocationAc.this,String.valueOf(lon));
                        customerUpload.setAddress_no(addresses.get(0).getAddressLine(0));
                        customerUpload.setSubdistrict_name(addresses.get(0).getLocality());
                        customerUpload.setDistrict_name(addresses.get(0).getSubAdminArea());
                        customerUpload.setProvince_name(addresses.get(0).getAdminArea());
                        customerUpload.setCountry_name(addresses.get(0).getCountryName());
                        customerUpload.setZip_code(addresses.get(0).getPostalCode());
                        customerUpload.setLatitude(lat);
                        customerUpload.setLongitude(lon);

                        textView.setText(address);

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        // Add a marker in Sydney, Australia, and move the camera.
        // latlon = new LatLng(-34, 151);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1){
            getLocation();
        }
    }

}