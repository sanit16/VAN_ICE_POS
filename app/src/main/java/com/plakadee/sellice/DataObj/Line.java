package com.plakadee.sellice.DataObj;

import org.json.JSONException;
import org.json.JSONObject;

public class Line {
    String runno;
    String line_code;
    String line_name;
    String branch_runno;
    String create_user;
    String create_date;
    String update_user;
    String update_date;
    String isactive;
    String isdelete;
    String company_runno;

    public Line() {
    }

    public Line(JSONObject object) {
        try {
            this.runno = object.getString("runno") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.line_code =object.getString("line_code") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.line_name = object.getString("line_name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.branch_runno =object.getString("branch_runno") ;
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

    public String getLine_code() {
        return line_code;
    }

    public void setLine_code(String line_code) {
        this.line_code = line_code;
    }

    public String getLine_name() {
        return line_name;
    }

    public void setLine_name(String line_name) {
        this.line_name = line_name;
    }

    public String getBranch_runno() {
        return branch_runno;
    }

    public void setBranch_runno(String branch_runno) {
        this.branch_runno = branch_runno;
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

    public String getCompany_runno() {
        return company_runno;
    }

    public void setCompany_runno(String company_runno) {
        this.company_runno = company_runno;
    }
}
