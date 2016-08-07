package com.spentas.javad.doctorfinder.network;

import java.util.HashMap;

/**
 * Created by Javad on 8/2/16.
 */
public class NetworkConfig {
    public static final String BASE_URL = "http://52.76.85.10/test/";
    public static final HashMap <String,String> ENDPOINT = new HashMap<>();
    public static final String LOCATION_ENDPOINT = "location.json";
    public static final String PROFILE_ENDPOINT = "profile/%d.json";
    public static final String DATA_ENDPOINT = "datalist.json";

}
