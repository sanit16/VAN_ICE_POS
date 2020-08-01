package com.plakadee.sellice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.plakadee.sellice.DataObj.Customer;
import com.plakadee.sellice.DataObj.OrderDeatail;
import com.plakadee.sellice.DataObj.ProductGet;
import com.plakadee.sellice.DataObj.SaleDue;
import com.plakadee.sellice.DataObj.SaleProduct;
import com.plakadee.sellice.DataState.SaveState;
import com.plakadee.sellice.DialogAdaper.GetIceFormSaleAdapter;
import com.plakadee.sellice.Special.CalOrder;
import com.plakadee.sellice.Spiner.SaleInterface;
import com.plakadee.sellice.Spiner.SellAdapter;
import com.plakadee.sellice.Tools.GetDebtInWeek;
import com.plakadee.sellice.Tools.GetDocDate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Selll extends AppCompatActivity implements SaleInterface {
    Customer customer;
    RecyclerView recyclerView;
    Button btn_positive;
    Button btn_negative;
    TextView txt_sum_value;
    TextView txt_sum_price;
    TextView txt_sum_discount;
    TextView txt_old_debt;
    private TextView txt_sum_free;
    SellAdapter adapter;
    List<SaleProduct> product;
    Context context;
    AlertDialog dialog;
    Button btn_select_date;
    int day;
    int mounth;
    int year;
    AlertDialog alertDialog;
    GetIceFormSaleAdapter getIceFormSaleAdapter;
    int car_runno;
    int line_runno;
    int employee_runno;
    int customer_runno;
    TextView net_price, real_pay, credit, full_pay, haft_pay;
    Button one ,two,three,four,five,six,seven,eight,nine,zero,c,x;
    String str_real_pay;
    String str_net_pay;
    String str_credit;
    String str_debt;
    GetDocDate getDocDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selll);
        getDocDate = new GetDocDate();
        context = this;
        car_runno = Integer.parseInt(SaveState.getCarRunno(context));
        line_runno = Integer.parseInt(SaveState.getLineRunno(context));
        employee_runno = Integer.parseInt(SaveState.getEmployeeRunno(context));
        customer_runno = Integer.parseInt(SaveState.getCustomerRunno(context));
        find_id();
        set_click();
        set_recyclerView();


        try {
            JSONObject jsonObject = new JSONObject(SaveState.getCustomer(this));
            customer = new Customer(jsonObject);
        } catch (JSONException err) {
            Log.d("Error", err.toString());
        }
    }

    private void set_recyclerView() {
        product = new ArrayList<>();
        adapter = new SellAdapter(context, product, this);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        get_data_sale();

    }

    private void get_data_sale() {

        String URL = SaveState.getURL() + "sale?isactive=true&ordering=runno&customer_runno=" + customer_runno + "&sale_date=" + getDocDate.get_doc_date_today();
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() == 0) {
                            get_data_product();

                        } else {
                            Intent intent = new Intent(Selll.this, SellEdit.class);
                            startActivity(intent);
                            finish();
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

    private void get_data_product() {


        final String doc_no = SaveState.getDocNo(context);
        String URL = SaveState.getURL() + "vworderdetail?isactive=true&ordering=runno&doc_no=" + doc_no;
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                SaleProduct dummy = new SaleProduct(jsonObject, 0, 0, employee_runno, customer_runno, car_runno, line_runno, 0,0);
                                product.add(dummy);
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
                get_money();
            }
        });
    }

    private void get_money() {
         str_real_pay="0";
         str_net_pay= txt_sum_price.getText().toString();
         str_credit="0";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View row = mInflater.inflate(R.layout.payment_method, null);
        txt_old_debt = row.findViewById(R.id.txt_old_debt);
        get_debt();
        Button positive = row.findViewById(R.id.btn_positive);
        net_price = row.findViewById(R.id.txt_net_pay);
        net_price.setText(str_net_pay);
        real_pay = row.findViewById(R.id.txt_real_pay);
        real_pay.setText(str_real_pay);
        credit = row.findViewById(R.id.txt_credit);
        credit.setText(str_net_pay);
        full_pay = row.findViewById(R.id.txt_full_pay);
        full_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_real_pay = str_net_pay;
                real_pay.setText(str_real_pay);
                credit.setText(String.valueOf(Double.parseDouble(str_net_pay)-Double.parseDouble(str_real_pay)));

            }
        });
        haft_pay = row.findViewById(R.id.txt_haft_pay);
        haft_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_real_pay = String.valueOf((int) (Integer.parseInt(str_net_pay) /2)) ;
                real_pay.setText(str_real_pay);
                credit.setText(String.valueOf(Double.parseDouble(str_net_pay)-Double.parseDouble(str_real_pay)));
            }
        });
        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Double.parseDouble(credit.getText().toString())>0){
                    create_debt((int)Double.parseDouble(credit.getText().toString()));

                }
                upload_data();

            }
        });
        Button negative = row.findViewById(R.id.btn_negative);
        negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        one = row.findViewById(R.id.one);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_pad_click("1");

            }
        });
        two= row.findViewById(R.id.two);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_pad_click("2");

            }
        });
        three= row.findViewById(R.id.three);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_pad_click("3");

            }
        });
        four= row.findViewById(R.id.four);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_pad_click("4");

            }
        });
        five= row.findViewById(R.id.five);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_pad_click("5");

            }
        });
        six= row.findViewById(R.id.six);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_pad_click("6");

            }
        });
        seven= row.findViewById(R.id.seven);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_pad_click("7");

            }
        });
        eight= row.findViewById(R.id.eight);
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_pad_click("8");

            }
        });
        nine= row.findViewById(R.id.nine);
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_pad_click("9");

            }
        });
        zero= row.findViewById(R.id.zero);
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_pad_click("0");

            }
        });
        c= row.findViewById(R.id.c);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_real_pay="0";

//                        holder.txt_sum_price.setText(String.valueOf(Double.parseDouble(holder.value)*Double.parseDouble(holder.price)));
                credit.setText(String.valueOf(Double.parseDouble(str_net_pay)+Double.parseDouble(str_debt)-Double.parseDouble(str_real_pay)));
                real_pay.setText(str_real_pay);
            }
        });
        x= row.findViewById(R.id.x);
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        if (str_real_pay.length()==1){
                            str_real_pay = "0";
                        }else {
                            str_real_pay =str_real_pay.substring(0, str_real_pay.length() - 1);
//                            holder.value.substring(0, holder.value.length()-1);
//                            holder.value.replaceFirst(".$","");
                        }
                        real_pay.setText(str_real_pay);

                credit.setText(String.valueOf(Double.parseDouble(str_net_pay)+Double.parseDouble(str_debt)-Double.parseDouble(str_real_pay)));

            }
        });
        builder.setView(row);
        alertDialog = builder.create();
        alertDialog.show();

    }

    private void get_debt() {
        GetDebtInWeek getDebtInWeek = new GetDebtInWeek();
        getDebtInWeek.get_debt_form_this(this,this);
    }

    private void create_debt(int value) {
        int line_runno = Integer.parseInt(SaveState.getLineRunno(context));
        int employee_runno = Integer.parseInt(SaveState.getEmployeeRunno(context));
        int customer_runno = Integer.parseInt(SaveState.getCustomerRunno(context));

        SaleDue  saleDue = new SaleDue(getDocDate.get_doc_date_today(),"A",line_runno,employee_runno,customer_runno,value,"-",true,false);
        saleDue.upload(context);

    }

    private void set_pad_click(String s) {
        if (str_real_pay.equals("0")){
            str_real_pay=s;
        }else {
            str_real_pay = str_real_pay+s;
        }
        real_pay.setText(str_real_pay);
        credit.setText(String.valueOf(Double.parseDouble(str_net_pay)+Double.parseDouble(str_debt)-Double.parseDouble(str_real_pay)));

    }


    private void upload_data() {
        String URL = SaveState.getURL() + "add_sale";


        for (int i = 0; i < adapter.mData.size(); i++) {
            SaleProduct data = adapter.mData.get(i);
            JSONObject upload = new JSONObject();
            try {
                upload.put("sale_date", getDocDate.get_doc_date_today());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                upload.put("product_runno", data.getProduct_runno());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                upload.put("qty", data.getQty());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                upload.put("sale_price", data.getSale_price());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                upload.put("employee_runno", data.getEmployee_runno());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                upload.put("customer_runno", data.getCustomer_runno());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                upload.put("car_runno", data.getCar_runno());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                upload.put("line_runno", data.getLine_runno());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                upload.put("discount", data.getDiscount());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                upload.put("qty_free", data.getQty_free());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                upload.put("isactive", true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                upload.put("isdelete", false);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.v("uploadSell",upload.toString());
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
            show_get_order();

        }
    }

    private void show_get_order() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        mounth = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        List<OrderDeatail> orderDeatails = new ArrayList<>();
        for (int i = 0; i < product.size(); i++) {
            SaleProduct data = product.get(i);
            OrderDeatail dummy = new OrderDeatail(data.getProduct_runno(), data.getQty(), 0, data.getProduct_name());
            orderDeatails.add(dummy);
        }
        RecyclerView dialogRecyclerview;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View row = inflater.inflate(R.layout.get_ice_dialog, null);
        btn_select_date = row.findViewById(R.id.btn_select_date);
        btn_select_date.setText(getDocDate.get_text_date_form_this(year,mounth,day));

        btn_select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, android.R.style.Widget_Material_Light_DatePicker, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int inyear, int month, int dayOfMonth) {
                        day = dayOfMonth;
                        mounth = month;
                        year = inyear;
                        btn_select_date.setText(getDocDate.get_text_date_form_this(year,mounth,day));
                    }
                }, year, mounth, day);
//                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });
        Button negative = row.findViewById(R.id.btn_negative);
        negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
        Button positive = row.findViewById(R.id.btn_positive);
        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_order(getIceFormSaleAdapter);
            }
        });
        dialogRecyclerview = row.findViewById(R.id.recyclerview);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        getIceFormSaleAdapter = new GetIceFormSaleAdapter(context, orderDeatails);
        llm.setOrientation(RecyclerView.VERTICAL);
        dialogRecyclerview.setLayoutManager(llm);
        dialogRecyclerview.setAdapter(getIceFormSaleAdapter);
        builder.setView(row);
        dialog = builder.create();
        dialog.show();

    }

    private void get_order(GetIceFormSaleAdapter list) {
        CalOrder calOrder = new CalOrder(context, getIceFormSaleAdapter, this, year, mounth, day);


    }

    private void find_id() {
        recyclerView = findViewById(R.id.recyclerview);
        btn_negative = findViewById(R.id.btn_negative);
        btn_positive = findViewById(R.id.btn_positive);
        txt_sum_value = findViewById(R.id.txt_sum_value);
        txt_sum_price = findViewById(R.id.txt_sum_price);
        txt_sum_discount = findViewById(R.id.txt_sum_discount);
        txt_sum_free= findViewById(R.id.txt_sum_free);
        txt_old_debt= findViewById(R.id.txt_old_debt);


    }

    @Override
    public void calculate() {
        int value = 0;
        int price = 0;
        int discount = 0;
        int free = 0;
        for (int i = 0; i < adapter.mData.size(); i++) {
            value += adapter.mData.get(i).getQty();
            discount += adapter.mData.get(i).getDiscount();
            price += (adapter.mData.get(i).getSale_price() * adapter.mData.get(i).getQty());
            free += adapter.mData.get(i).getQty_free();

        }
        txt_sum_value.setText(String.valueOf(value));
        txt_sum_discount.setText(String.valueOf(discount));
        txt_sum_price.setText(String.valueOf(price - discount));
        txt_sum_free.setText(String.valueOf(free));
    }

    @Override
    public void telldebt(String debt) {
        str_debt = debt;
        txt_old_debt.setText(debt);
        credit.setText(String.valueOf(Double.parseDouble(str_net_pay)+Double.parseDouble(str_debt)-Double.parseDouble(str_real_pay)));

    }
}