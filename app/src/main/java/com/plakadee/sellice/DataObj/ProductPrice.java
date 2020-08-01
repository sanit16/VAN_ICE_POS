package com.plakadee.sellice.DataObj;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductPrice {
    String runno;
    String product_runno;
    String product_price_name;
    String product_price;
    String price_note;
    String isactive;
    String isdelete;
    String create_user;
    String create_date;
    String update_user;
    String update_date;

    public ProductPrice() {
    }

    public ProductPrice(JSONObject object) {
        try {
            this.runno =object.getString("runno")  ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.product_runno =object.getString("product_runno")  ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.product_price_name =object.getString("product_price_name")  ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.product_price =object.getString("product_price")  ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.price_note =object.getString("price_note")  ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.isactive =object.getString("isactive")  ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.isdelete =object.getString("isdelete")  ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.create_user =object.getString("create_user")  ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.create_date =object.getString("create_date")  ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.update_user = object.getString("update_user") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.update_date =object.getString("update_date")  ;
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

    public String getProduct_runno() {
        return product_runno;
    }

    public void setProduct_runno(String product_runno) {
        this.product_runno = product_runno;
    }

    public String getProduct_price_name() {
        return product_price_name;
    }

    public void setProduct_price_name(String product_price_name) {
        this.product_price_name = product_price_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getPrice_note() {
        return price_note;
    }

    public void setPrice_note(String price_note) {
        this.price_note = price_note;
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

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(String update_user) {
        this.update_user = update_user;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }
}
