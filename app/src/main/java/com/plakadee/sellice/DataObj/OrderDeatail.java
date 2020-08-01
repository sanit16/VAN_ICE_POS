package com.plakadee.sellice.DataObj;

import org.json.JSONException;
import org.json.JSONObject;

public class OrderDeatail {
    int runno;
    String doc_no;
    int product_runno;
    int order_qty1;
    int order_qty2;

    String product_code;
    String product_name;
    int category_runno;
    int unit_runno;

    public OrderDeatail(int product_runno, int order_qty1, int order_qty2, String product_name) {
        this.product_runno = product_runno;
        this.order_qty1 = order_qty1;
        this.order_qty2 = order_qty2;
        this.product_name = product_name;
    }

    public OrderDeatail() {
    }

    public OrderDeatail(JSONObject jsonObject, int order_qty1, int order_qty2) {
        try {
            this.product_runno = jsonObject.getInt("runno") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.order_qty1 = order_qty1;
        this.order_qty2 = order_qty2;
        try {
            this.product_code =jsonObject.getString("product_code") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.product_name =jsonObject.getString("product_name") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.category_runno = jsonObject.getInt("category_runno") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.unit_runno =jsonObject.getInt("unit_runno") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.doc_no = jsonObject.getString("doc_no") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public OrderDeatail(JSONObject jsonObject)  {
        try {
            this.product_runno = jsonObject.getInt("product_runno") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.order_qty1 =jsonObject.getInt("order_qty1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.order_qty2 =jsonObject.getInt("order_qty2") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.runno =jsonObject.getInt("runno");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.product_code =jsonObject.getString("product_code") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.product_name =jsonObject.getString("product_name") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.category_runno = jsonObject.getInt("category_runno") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.unit_runno =jsonObject.getInt("unit_runno") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getRunno() {
        return runno;
    }

    public void setRunno(int runno) {
        this.runno = runno;
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

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getCategory_runno() {
        return category_runno;
    }

    public void setCategory_runno(int category_runno) {
        this.category_runno = category_runno;
    }

    public int getUnit_runno() {
        return unit_runno;
    }

    public void setUnit_runno(int unit_runno) {
        this.unit_runno = unit_runno;
    }

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }
}
