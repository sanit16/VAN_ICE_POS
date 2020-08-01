package com.plakadee.sellice.DataObj;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductGet {
    String runno;
    String doc_no;
    String product_runno;
    String product_code;
    String product_name;
    String order_qty1;
    String order_qty2;
    String category_runno;
    String category_name;
    String order_note;
    String isactive;
    String isdelete;

    public ProductGet() {
    }

    public ProductGet(JSONObject object) {
        try {
            this.runno = object.getString("runno") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.doc_no =object.getString("doc_no") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.product_runno =object.getString("product_runno") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.product_code =object.getString("product_code") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.product_name =object.getString("product_name") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.order_qty1 =object.getString("order_qty1") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.order_qty2 =object.getString("order_qty2") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.category_runno = object.getString("category_runno");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.category_name =object.getString("category_name") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.order_note =object.getString("order_note") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.isactive =object.getString("isactive") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.isdelete =object.getString("isdelete") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getRunno() {
        return runno;
    }

    public void setRunno(String runno) {
        this.runno = runno;
    }

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    public String getProduct_runno() {
        return product_runno;
    }

    public void setProduct_runno(String product_runno) {
        this.product_runno = product_runno;
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

    public String getOrder_qty1() {
        return order_qty1;
    }

    public void setOrder_qty1(String order_qty1) {
        this.order_qty1 = order_qty1;
    }

    public String getOrder_qty2() {
        return order_qty2;
    }

    public void setOrder_qty2(String order_qty2) {
        this.order_qty2 = order_qty2;
    }

    public String getCategory_runno() {
        return category_runno;
    }

    public void setCategory_runno(String category_runno) {
        this.category_runno = category_runno;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getOrder_note() {
        return order_note;
    }

    public void setOrder_note(String order_note) {
        this.order_note = order_note;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public String getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete;
    }
}
