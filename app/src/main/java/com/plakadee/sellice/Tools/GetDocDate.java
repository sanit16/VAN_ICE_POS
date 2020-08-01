package com.plakadee.sellice.Tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GetDocDate {
    public GetDocDate() {
    }
    public String get_doc_date_today(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int mounth = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);
        return year+"-"+(mounth<10?("0"+mounth):(mounth))+"-"+day;
    }
    public String get_doc_date_tomorrow(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int mounth = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);
        return year+"-"+(mounth<10?("0"+mounth):(mounth))+"-"+day;
    }
    public String get_doc_date_form_this(int year,int month,int day){
        month = month+1;
        return String.valueOf(year) +"-"+ String.valueOf((month<10?("0"+month):(month))) + "-" + String.valueOf(day);
    }
    public String get_yesterday_form_this(int year,int month,int day){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(year,month,day);
        cal.add(Calendar.DATE, -1);
        dateFormat.format(cal.getTime());
        int thisday = cal.get(Calendar.DAY_OF_MONTH);
        int thismounth = cal.get(Calendar.MONTH)+1;
        int thisyear = cal.get(Calendar.YEAR);
        return thisyear+"-"+(thismounth<10?("0"+thismounth):(thismounth))+"-"+thisday;
    }
    public String get_yesterday(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int mounth = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);
        return year+"-"+(mounth<10?("0"+mounth):(mounth))+"-"+day;
    }
    public String get_text_date_today(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int mounth = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);
        return " วันที่ "+String.valueOf(day)+" เดือน "+String.valueOf((mounth<10?("0"+mounth):(mounth)))+" ปี "+String.valueOf(year);
    }
    public String get_text_date_tomorrow(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int mounth = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);
        return " วันที่ "+String.valueOf(day)+" เดือน "+String.valueOf((mounth<10?("0"+mounth):(mounth)))+" ปี "+String.valueOf(year);
    }
    public String get_text_date_form_this(int year,int month,int day){
        month = month+1;
       return  " วันที่ " + String.valueOf(day) + " เดือน " + String.valueOf((month<10?("0"+month):(month))) + " ปี " + String.valueOf(year);

    }
}
