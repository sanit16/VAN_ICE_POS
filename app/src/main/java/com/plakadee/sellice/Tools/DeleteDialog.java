package com.plakadee.sellice.Tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.plakadee.sellice.R;

public class DeleteDialog {
    AlertDialog alertDialog;
    Context mContext;
    String runno;
    Button btn_positive;
    Button btn_negative;
    TextView txt_name;
    TextView txt_phone;
    String name;
    String phone;
    public DeleteDialog(Context context,String runno,String name,String phone) {
        this.mContext=context;
        this.runno = runno;
        this.name = name;
        this.phone = phone;
        show_dialog();

    }

    private void show_dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View row = inflater.inflate(R.layout.delete_dialog,null);
        btn_positive = row.findViewById(R.id.btn_positive);
        btn_negative = row.findViewById(R.id.btn_negative);
        txt_name = row.findViewById(R.id.txt_name);
        txt_phone = row.findViewById(R.id.txt_phone);
        txt_name.setText(name);
        txt_phone.setText(phone);
        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delet_this_item();
            }
        });
        builder.setView(row);
        alertDialog = builder.create();
        alertDialog.show();
    }

    private void delet_this_item() {


    }
}
