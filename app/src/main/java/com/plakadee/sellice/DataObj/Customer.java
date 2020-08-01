package com.plakadee.sellice.DataObj;

import org.json.JSONException;
import org.json.JSONObject;

public class Customer {
    String runno;
    String customer_code;
    String customer_name;
    String customer_contact;
    String customer_description;
    String phone_number;
    String fax_number;
    String address_no;
    String subdistrict_name;
    String district_name;
    String province_name;
    String country_name;
    String zip_code;
    String line_runno;
    String latitude;
    String longitude;
    String customer_comment;
    String create_user;
    String create_date;
    String update_user;
    String update_date;
    String isactive;
    String isdelete;
    String status;
    String debt;
    Double distance;
    public Customer() {
    }

    public Customer(JSONObject object) {
        try {
            this.runno =object.getString("runno") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.customer_code =object.getString("customer_code")  ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.customer_name =object.getString("customer_name")  ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.customer_contact =object.getString("customer_contact")  ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.customer_description =object.getString("customer_description")  ;
            final String[] tokens = this.customer_description.split(",");
            if (tokens.length==2){
                this.status = tokens[1];
                this.debt = tokens[0];
            }else {
                this.status = "ไม่ระบุ";
                this.debt = "ไม่ระบุ";
            }

        } catch (JSONException e) {
            this.status = "ไม่ระบุ";
            this.debt = "ไม่ระบุ";
            e.printStackTrace();
        }
        try {
            this.phone_number =object.getString("phone_number")  ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.fax_number =object.getString("fax_number")  ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.address_no = object.getString("address_no") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.subdistrict_name =object.getString("subdistrict_name")  ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.district_name =object.getString("district_name")  ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.province_name =object.getString("province_name")  ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.country_name =object.getString("country_name")  ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.zip_code =object.getString("zip_code")  ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.line_runno =object.getString("line_runno")  ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.latitude =object.getString("latitude")  ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.longitude =object.getString("longitude")  ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.customer_comment =object.getString("customer_comment")  ;
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
            this.update_user =object.getString("update_user")  ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.update_date =object.getString("update_date")  ;
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
    }

    public String getRunno() {
        return runno;
    }

    public void setRunno(String runno) {
        this.runno = runno;
    }

    public String getCustomer_code() {
        return customer_code;
    }

    public void setCustomer_code(String customer_code) {
        this.customer_code = customer_code;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_contact() {
        return customer_contact;
    }

    public void setCustomer_contact(String customer_contact) {
        this.customer_contact = customer_contact;
    }

    public String getCustomer_description() {
        return customer_description;
    }

    public void setCustomer_description(String customer_description) {
        this.customer_description = customer_description;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getFax_number() {
        return fax_number;
    }

    public void setFax_number(String fax_number) {
        this.fax_number = fax_number;
    }

    public String getAddress_no() {
        return address_no;
    }

    public void setAddress_no(String address_no) {
        this.address_no = address_no;
    }

    public String getSubdistrict_name() {
        return subdistrict_name;
    }

    public void setSubdistrict_name(String subdistrict_name) {
        this.subdistrict_name = subdistrict_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getLine_runno() {
        return line_runno;
    }

    public void setLine_runno(String line_runno) {
        this.line_runno = line_runno;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCustomer_comment() {
        return customer_comment;
    }

    public void setCustomer_comment(String customer_comment) {
        this.customer_comment = customer_comment;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
