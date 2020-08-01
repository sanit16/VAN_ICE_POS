package com.plakadee.sellice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.plakadee.sellice.DataState.SaveState;
import com.plakadee.sellice.Spiner.GetIceEdit;

public class MainActivity extends AppCompatActivity {
    Button crv_get_ice;
    Button get_ice;
    RelativeLayout crv_get_bucket;
    RelativeLayout crv_sell;
    RelativeLayout crv_send_money;
    Button btn_close;
    Button btn_logout;
    Button btn_tran;
    Button btn_uot;
//    CardView crv_se;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        find_id();
        set_click();
    }

    private void set_click() {
        crv_get_ice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GetIceEdit.class);
                startActivity(intent);
            }
        });
        crv_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CustomerList.class);
                startActivity(intent);
            }
        });
        crv_send_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Send.class);
                startActivity(intent);
            }
        });
        get_ice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ReciveIce.class);
                startActivity(intent);
            }
        });
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveState.setLoginState(MainActivity.this,"logout");
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
                finish();
            }
        });
        btn_uot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Out.class);
                startActivity(intent);
            }
        });
    }

    private void find_id() {
        crv_get_ice = findViewById(R.id.crv_get_ice);
        get_ice = findViewById(R.id.btn_get);
//        crv_get_bucket = findViewById(R.id.crv_get_bucket);
        crv_sell = findViewById(R.id.crv_sell);
        crv_send_money = findViewById(R.id.crv_send_money);
        btn_logout = findViewById(R.id.loguot);
        btn_close = findViewById(R.id.close);
        btn_tran = findViewById(R.id.btn_tran);
        btn_uot = findViewById(R.id.btn_out);
    }
}
