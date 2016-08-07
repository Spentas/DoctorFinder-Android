package com.spentas.javad.doctorfinder.util;

/**
 * Logger Helper
 */
public class Logger {
    private static final String APP_TAG = "DrFinder";
    public static void i(String msg){
        android.util.Log.i(APP_TAG,msg);
    }
    public static void e(String msg,Throwable e ){
        android.util.Log.e(APP_TAG,msg,e);
    }
}
