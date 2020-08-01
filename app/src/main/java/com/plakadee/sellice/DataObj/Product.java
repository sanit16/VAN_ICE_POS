package com.plakadee.sellice.DataObj;

import org.json.JSONException;
import org.json.JSONObject;

public class Product {
   String runno;
    String  product_code;
    String product_name;
    String category_runno;
    String unit_runno;
    String product_note;
    String isactive;
    String isdelete;
    String isstock;
    String create_user;
    String create_date;
    String update_user;
    String update_date;

    public Product() {
    }

    public Product(JSONObject object) {
        try {
            this.runno = object.getString("runno") ;
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
            this.category_runno = object.getString("category_runno");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.unit_runno = object.getString("unit_runno");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.product_note =object.getString("product_note") ;
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
        try {
            this.isstock =object.getString("isstock") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.create_user = object.getString("create_user");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.create_date =object.getString("create_date") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.update_user =object.getString("update_user") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.update_date =object.getString("update_date") ;
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

    public String getCategory_runno() {
        return category_runno;
    }

    public void setCategory_runno(String category_runno) {
        this.category_runno = category_runno;
    }

    public String getUnit_runno() {
        return unit_runno;
    }

    public void setUnit_runno(String unit_runno) {
        this.unit_runno = unit_runno;
    }

    public String getProduct_note() {
        return product_note;
    }

    public void setProduct_note(String product_note) {
        this.product_note = product_note;
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

    public String getIsstock() {
        return isstock;
    }

    public void setIsstock(String isstock) {
        this.isstock = isstock;
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
