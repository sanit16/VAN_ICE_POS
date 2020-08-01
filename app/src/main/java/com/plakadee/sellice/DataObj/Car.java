package com.plakadee.sellice.DataObj;

import org.json.JSONException;
import org.json.JSONObject;

public class Car {
    String runno;
    String car_no;
    String brand_name;
    String model_name;
    String vin;
    String car_note;
    String isactive;
    String isdelete;
    String create_user;
    String create_date;
    String update_user;
    String update_date;
    String warehouse_runno;
    String car_code;
    String line_runno;
    String company_runno;

    public Car() {
    }

    public Car(JSONObject object)  {
        try {
            this.runno = object.getString("runno") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.car_no =object.getString("car_no") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.brand_name =object.getString("brand_name") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.model_name =object.getString("model_name") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.vin =object.getString("vin") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.car_note =object.getString("car_note") ;
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
            this.create_user =object.getString("create_user") ;
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
        try {
            this.warehouse_runno =object.getString("warehouse_runno") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.car_code =object.getString("car_code") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.line_runno =object.getString("line_runno") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.company_runno =object.getString("company_runno") ;
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

    public String getCar_no() {
        return car_no;
    }

    public void setCar_no(String car_no) {
        this.car_no = car_no;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCar_note() {
        return car_note;
    }

    public void setCar_note(String car_note) {
        this.car_note = car_note;
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

    public String getWarehouse_runno() {
        return warehouse_runno;
    }

    public void setWarehouse_runno(String warehouse_runno) {
        this.warehouse_runno = warehouse_runno;
    }

    public String getCar_code() {
        return car_code;
    }

    public void setCar_code(String car_code) {
        this.car_code = car_code;
    }

    public String getLine_runno() {
        return line_runno;
    }

    public void setLine_runno(String line_runno) {
        this.line_runno = line_runno;
    }

    public String getCompany_runno() {
        return company_runno;
    }

    public void setCompany_runno(String company_runno) {
        this.company_runno = company_runno;
    }
}
