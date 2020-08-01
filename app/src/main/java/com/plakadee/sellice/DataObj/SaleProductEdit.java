package com.plakadee.sellice.DataObj;

import org.json.JSONException;
import org.json.JSONObject;

public class SaleProductEdit {
    int runno;
    String sale_date;
    int product_runno;
    int qty;
    int qty_free;
    double sale_price;
    int employee_runno;
    int customer_runno;
    int car_runno;
    int line_runno;
    int discount;

    String product_name;
    int qty_edit;
    double sale_price_edit;
    int discount_edit;
    public SaleProductEdit() {
    }

    public SaleProductEdit(JSONObject jsonObject) {
        try {
            this.runno = jsonObject.getInt("runno") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.sale_date =jsonObject.getString("sale_date") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.product_runno =jsonObject.getInt("product_runno") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.qty =jsonObject.getInt("qty") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.sale_price =jsonObject.getDouble("sale_price") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.employee_runno =jsonObject.getInt("employee_runno") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.customer_runno =jsonObject.getInt("customer_runno") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.car_runno =jsonObject.getInt("car_runno") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.line_runno =jsonObject.getInt("line_runno") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.product_name =jsonObject.getString("product_name") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.qty_edit =jsonObject.getInt("qty") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.discount_edit = jsonObject.getInt("discount");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.sale_price_edit = jsonObject.getDouble("sale_price");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.discount = jsonObject.getInt("discount") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.qty_free = jsonObject.getInt("qty_free");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getQty_free() {
        return qty_free;
    }

    public void setQty_free(int qty_free) {
        this.qty_free = qty_free;
    }

    public double getSale_price_edit() {
        return sale_price_edit;
    }

    public void setSale_price_edit(double sale_price_edit) {
        this.sale_price_edit = sale_price_edit;
    }

    public int getDiscount_edit() {
        return discount_edit;
    }

    public void setDiscount_edit(int discount_edit) {
        this.discount_edit = discount_edit;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getRunno() {
        return runno;
    }

    public void setRunno(int runno) {
        this.runno = runno;
    }

    public String getSale_date() {
        return sale_date;
    }

    public void setSale_date(String sale_date) {
        this.sale_date = sale_date;
    }

    public int getProduct_runno() {
        return product_runno;
    }

    public void setProduct_runno(int product_runno) {
        this.product_runno = product_runno;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getSale_price() {
        return sale_price;
    }

    public void setSale_price(double sale_price) {
        this.sale_price = sale_price;
    }

    public int getEmployee_runno() {
        return employee_runno;
    }

    public void setEmployee_runno(int employee_runno) {
        this.employee_runno = employee_runno;
    }

    public int getCustomer_runno() {
        return customer_runno;
    }

    public void setCustomer_runno(int customer_runno) {
        this.customer_runno = customer_runno;
    }

    public int getCar_runno() {
        return car_runno;
    }

    public void setCar_runno(int car_runno) {
        this.car_runno = car_runno;
    }

    public int getLine_runno() {
        return line_runno;
    }

    public void setLine_runno(int line_runno) {
        this.line_runno = line_runno;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getQty_edit() {
        return qty_edit;
    }

    public void setQty_edit(int qty_edit) {
        this.qty_edit = qty_edit;
    }
}
