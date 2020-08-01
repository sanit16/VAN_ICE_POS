package com.plakadee.sellice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.plakadee.sellice.DataObj.Car;
import com.plakadee.sellice.DataObj.Line;
import com.plakadee.sellice.DataState.SaveState;
import com.plakadee.sellice.Spiner.CarSpinerAdapter;
import com.plakadee.sellice.Spiner.LineSpinerAdapter;
import com.plakadee.sellice.Spiner.SelectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Login extends AppCompatActivity implements SelectCallback {
    private EditText edt_username;
    private EditText edt_password;
    private Button spn_line;
    private Button spn_car;
    private Button btn_positive;
    private Button btn_negative;
    private int line_runno = -1;
    private int car_runno = -1;
    private Context context;
    ArrayList<String> car_list;
    ArrayList<String> line_list;
    ArrayList<Car> carArr;
    ArrayList<Line> lineArr;
    LinearLayout lin_car_group;

    AlertDialog car_dialog;
    AlertDialog line_dialog;
    String line;
    String car;
    Car data_car;
    Line data_line;
    String user_name;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (SaveState.getLoginState(this).equals("loged")){
            Intent intent = new Intent(Login.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            context = this;
            carArr = new ArrayList<>();
            lineArr = new ArrayList<>();

            find_id();
            set_onclick();
            get_data_car();
            get_data_line();
        }

//        show_car();
//        show_line();
    }

    private void get_data_line() {
        String URL = "http://kis-ws-01.kraois.com/line?isactive=true&ordering=runno";
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        line_list = new ArrayList<String>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                line_list.add(jsonObject.getString("line_name"));
                                Line line = new Line(jsonObject);
                                lineArr.add(line);
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

    private void get_data_car() {
        String URL = "http://kis-ws-01.kraois.com/car?isactive=true&ordering=runno";
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        car_list = new ArrayList<String>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                car_list.add(jsonObject.getString("car_no"));
                                Car car = new Car(jsonObject);
                                carArr.add(car);
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

    private void set_onclick() {
//          btn_line.setOnClickListener(new View.OnClickListener() {
//              @Override
//              public void onClick(View v) {
//                show_line();
//              }
//          });
//          btn_car.setOnClickListener(new View.OnClickListener() {
//              @Override
//              public void onClick(View v) {
//                show_car();
//              }
//          });
        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 user_name = edt_username.getText().toString();
                 password = edt_password.getText().toString();

                if (user_name.length() == 0) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("กรุณากรอกชื่อผู้ใช้");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "เข้าใจแล้ว",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    return;
                                }
                            });


                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                } else
                if (password.length() == 0) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("กรุณากรอกรหัสผ่าน");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "เข้าใจแล้ว",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    return;
                                }
                            });


                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }  else
                if (data_line.getRunno().isEmpty()) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("กรุณาเลือกสายส่ง");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "เข้าใจแล้ว",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    return;
                                }
                            });


                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                } else
                if (data_car.getRunno().isEmpty()) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("กรุณาเลือกรถ");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "เข้าใจแล้ว",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    return;
                                }
                            });


                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                } else {
                    login();


                }

            }
        });
        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        spn_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_car();
            }
        });
        spn_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_line();
            }
        });
    }

    private void login() {
    JSONObject data = new JSONObject();
        try {
            data.put("username",user_name);
            data.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String URL = "http://kis-ws-01.kraois.com/authentication";
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.POST, URL, data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                        SaveState.setCarNo(context,data_car.getCar_no());
                        SaveState.setCarRunno(context,data_car.getRunno());
                        SaveState.setLineRunno(context,data_line.getRunno());
                        SaveState.setLineName(context,data_line.getLine_name());
                        SaveState.setEmployeeRunno(context,"2");
                        SaveState.setLoginState(context,"loged");
                        Intent intent = new Intent(Login.this,MainActivity.class);
                        startActivity(intent);
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

    private void show_car() {

        CarSpinerAdapter adapter = new CarSpinerAdapter(context,carArr,this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.recycler_list,null);
        RecyclerView recyclerView=row.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        builder.setView(row);
        car_dialog =  builder.create();
        car_dialog.show();


    }

    private void show_line() {
        LineSpinerAdapter adapter = new LineSpinerAdapter(context,lineArr,this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.recycler_list,null);
        RecyclerView recyclerView=row.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        builder.setView(row);
        line_dialog = builder.create();
        line_dialog.show();
    }

    private void find_id() {
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        spn_car = findViewById(R.id.spn_car);
        spn_line = findViewById(R.id.spn_line);
        btn_positive = findViewById(R.id.btn_positive);
        btn_negative = findViewById(R.id.btn_negative);
        lin_car_group = findViewById(R.id.lin_car_group);
    }

    @Override
    public void selected(Car car) {
        data_car = car;
        car_dialog.dismiss();

        spn_car.setText(data_car.getCar_no());
    }

    @Override
    public void selected(Line line) {
        data_line = line;
        lin_car_group.setVisibility(View.VISIBLE);
        for (int i = 0; i < carArr.size(); i++) {
            if (carArr.get(i).getLine_runno().equals(line.getRunno())){
                data_car = carArr.get(i);
                spn_car.setText(carArr.get(i).getCar_no());
            }

        }

        line_dialog.dismiss();

        spn_line.setText(data_line.getLine_name());
    }
}