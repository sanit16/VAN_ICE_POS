package com.plakadee.sellice.DataState;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveState {
    static final String CAR_RUNNO = "CAR_RUNNO";
    static final String CAR_NO = "CAR_NO";
    static final String LINE_RUNNO = "LINE_RUNNO";
    static final String LINE_NAME = "LINE_NAME";
    static final String LOGIN_STATE = "LOGIN_STATE";
    static final String CUSTOMER = "CUSTOMER";
    static final String EMPLOYEE_RUNNO = "EMPLOYEE_RUNNO";
    static final String DOC_NO = "DOC_NO";
    static final String CUSTOMER_RUNNO = "CUSTOMER_RUNNO";
    static final String CUSTOMER_NAME = "CUSTOMER_NAME";
    static final String CUSTOMER_PHONE = "CUSTOMER_PHONE";
    static final String CUSTOMER_LAT = "CUSTOMER_LAT";
    static final String CUSTOMER_LON = "CUSTOMER_LON";


    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }
    public static String getCustomerName(Context ctx) {
        return getSharedPreferences(ctx).getString(CUSTOMER_NAME, "");
    }

    public static void setCustomerName(Context ctx, String text) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(CUSTOMER_NAME, text);
        editor.commit();
    }
    public static String getCustomerPhone(Context ctx) {
        return getSharedPreferences(ctx).getString(CUSTOMER_PHONE, "");
    }

    public static void setCustomerPhone(Context ctx, String text) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(CUSTOMER_PHONE, text);
        editor.commit();
    }
    public static String getCustomerLat(Context ctx) {
        return getSharedPreferences(ctx).getString(CUSTOMER_LAT, "");
    }

    public static void setCustomerLat(Context ctx, String text) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(CUSTOMER_LAT, text);
        editor.commit();
    }
    public static String getCustomerLon(Context ctx) {
        return getSharedPreferences(ctx).getString(CUSTOMER_LON, "");
    }

    public static void setCustomerLon(Context ctx, String text) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(CUSTOMER_LON, text);
        editor.commit();
    }
    public static void setEmployeeRunno(Context ctx, String text) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(EMPLOYEE_RUNNO, text);
        editor.commit();
    }
    public static String getEmployeeRunno(Context ctx) {
        return getSharedPreferences(ctx).getString(EMPLOYEE_RUNNO, "");
    }
    public static String getDocNo(Context ctx) {
        return getSharedPreferences(ctx).getString(DOC_NO, "");
    }

    public static void setDocNo(Context ctx, String text) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(DOC_NO, text);
        editor.commit();
    }
    public static String getCustomerRunno(Context ctx) {
        return getSharedPreferences(ctx).getString(CUSTOMER_RUNNO, "");
    }

    public static void setCustomerRunno(Context ctx, String text) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(CUSTOMER_RUNNO, text);
        editor.commit();
    }

    public static void setCustomer(Context ctx, String text) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(CUSTOMER, text);
        editor.commit();
    }
    public static String getCustomer(Context ctx) {
        return getSharedPreferences(ctx).getString(CUSTOMER, "");
    }
    public static void setCarRunno(Context ctx, String text) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(CAR_RUNNO, text);
        editor.commit();
    }
    public static String getCarRunno(Context ctx) {
        return getSharedPreferences(ctx).getString(CAR_RUNNO, "");
    }
    public static void setCarNo(Context ctx, String text) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(CAR_NO, text);
        editor.commit();
    }
    public static String getCarNo(Context ctx) {
        return getSharedPreferences(ctx).getString(CAR_NO, "");
    }
    public static void setLineRunno(Context ctx, String text) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(LINE_RUNNO, text);
        editor.commit();
    }
    public static String getLineRunno(Context ctx) {
        return getSharedPreferences(ctx).getString(LINE_RUNNO, "");
    }
    public static void setLineName(Context ctx, String text) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(LINE_NAME, text);
        editor.commit();
    }
    public static String getLineName(Context ctx) {
        return getSharedPreferences(ctx).getString(LINE_NAME, "");
    }
    public static void setLoginState(Context ctx, String text) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(LOGIN_STATE, text);
        editor.commit();
    }
    public static String getLoginState(Context ctx) {
        return getSharedPreferences(ctx).getString(LOGIN_STATE, "");
    }
    public static String getURL(){
        return  "http://kis-ws-01.kraois.com/";

    }
}
