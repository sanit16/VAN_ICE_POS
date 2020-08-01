package com.plakadee.sellice.DataObj;

import org.json.JSONException;
import org.json.JSONObject;

public class OrderDetailEdit {
    String product_name;
    int runno;
    String doc_no;
    int product_runno;
    int order_qty1;
    int order_qty2;
    String order_note;
    boolean isactive;
    boolean isdelete;

    public OrderDetailEdit() {
    }

    public OrderDetailEdit(JSONObject jsonObject) {

        try {
            this.product_name = jsonObject.getString("product_name") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.runno = jsonObject.getInt("runno") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.doc_no =jsonObject.getString("doc_no") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.product_runno =jsonObject.getInt("product_runno") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.order_qty1 =jsonObject.getInt("order_qty1") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.order_qty2 =jsonObject.getInt("order_qty2") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.order_note =jsonObject.getString("order_note");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.isactive =jsonObject.getBoolean("isactive") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.isdelete =jsonObject.getBoolean("isdelete") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getRunno() {
        return runno;
    }

    public void setRunno(int runno) {
        this.runno = runno;
    }

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    public int getProduct_runno() {
        return product_runno;
    }

    public void setProduct_runno(int product_runno) {
        this.product_runno = product_runno;
    }

    public int getOrder_qty1() {
        return order_qty1;
    }

    public void setOrder_qty1(int order_qty1) {
        this.order_qty1 = order_qty1;
    }

    public int getOrder_qty2() {
        return order_qty2;
    }

    public void setOrder_qty2(int order_qty2) {
        this.order_qty2 = order_qty2;
    }

    public String getOrder_note() {
        return order_note;
    }

    public void setOrder_note(String order_note) {
        this.order_note = order_note;
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
}
