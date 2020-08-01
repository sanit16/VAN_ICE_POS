package com.plakadee.sellice.DataObj;

import org.json.JSONException;
import org.json.JSONObject;

public class SaleProduct {
    int product_runno;
    int qty;
    int qty_free;
    int sale_price;
    int employee_runno;
    int customer_runno;
    int car_runno;
    int line_runno;
    int discount;
    int unit_runno;

    String product_name;
    int category_runno;
    String category_name;

    public SaleProduct() {
    }

    public SaleProduct(JSONObject jsonObject, int qty, int sale_price, int employee_runno, int customer_runno, int car_runno, int line_runno, int discount,int qty_free)  {
        try {
            this.product_runno = jsonObject.getInt("product_runno")  ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.qty = qty;
        this.sale_price = sale_price;
        this.employee_runno = employee_runno;
        this.customer_runno = customer_runno;
        this.car_runno = car_runno;
        this.line_runno = line_runno;
        this.discount = discount;
        this.qty_free = qty_free;
//        this.unit_runno = jsonObject.getInt("unit_runno");
        try {
            this.product_name = jsonObject.getString("product_name") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.category_runno = jsonObject.getInt("category_runno") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.category_name = jsonObject.getString("category_name") ;
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

    public int getSale_price() {
        return sale_price;
    }

    public void setSale_price(int sale_price) {
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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getUnit_runno() {
        return unit_runno;
    }

    public void setUnit_runno(int unit_runno) {
        this.unit_runno = unit_runno;
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

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
