package com.example.polygons;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Application extends android.app.Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    public static String apk;
    public static RetrofitHelper retrofitHelper;
    public static Gson gson;
    public static String base_url="http://192.168.10.52/";
    public static ArrayList<ArrayList<LagLatModel>> lagLatModels;
    public static ArrayList<ArrayList<LagLatModel>> lagLatAyeyarModels;
    public static ArrayList<ArrayList<LagLatModel>> lagLatKachinModels;
    public static ArrayList<ArrayList<LagLatModel>> lagLatKayahModels;
    public static ArrayList<ArrayList<LagLatModel>> laglatMagWayModels;
    public static ArrayList<ArrayList<LagLatModel>> laglatMandalayModels;
    public static ArrayList<ArrayList<LagLatModel>> laglatNaypyiTawModels;
    public static ArrayList<ArrayList<LagLatModel>> laglatRakhineModels;
    public static ArrayList<ArrayList<LagLatModel>> laglatMonModels;
    public static ArrayList<ArrayList<LagLatModel>> laglatShanModels;
    public static ArrayList<ArrayList<LagLatModel>> laglatTaNinTharYee;
    public static ArrayList<ArrayList<LagLatModel>> laglatSagaing;
    public static ArrayList<ArrayList<LagLatModel>> laglatYangon;
    public static ArrayList<ArrayList<LagLatModel>> laglatKayin;
    public static ArrayList<ArrayList<LagLatModel>> laglatChin;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofitHelper = new RetrofitHelper();
        gson = new GsonBuilder().serializeNulls().create();

    }



}
