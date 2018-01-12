package com.example.polygons;

import com.example.polygons.Model.AyeYarWadddyPopulation;
import com.example.polygons.Model.AyeYarWaddy;
import com.example.polygons.Model.Bago;
import com.example.polygons.Model.Chin;
import com.example.polygons.Model.Kachin;
import com.example.polygons.Model.Kayah;
import com.example.polygons.Model.Kayin;
import com.example.polygons.Model.MagWay;
import com.example.polygons.Model.Mandalay;
import com.example.polygons.Model.Mon;
import com.example.polygons.Model.NayPyiTaw;
import com.example.polygons.Model.Rakhine;
import com.example.polygons.Model.RegionPopulation;
import com.example.polygons.Model.Sagaing;
import com.example.polygons.Model.Shan;
import com.example.polygons.Model.TaNinTharYi;
import com.example.polygons.Model.Yangon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.CookieManager;
import java.net.CookiePolicy;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by waiyan on 5/14/17.
 */

public class RetrofitHelper {


    Retrofit retrofit;
    ApiService apiService;
    public CookieManager cookieManager;


    public RetrofitHelper() {

        Gson gson = new GsonBuilder().create();


        cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new JavaNetCookieJar(cookieManager))
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Application.base_url)
                .callFactory(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiService = retrofit.create(ApiService.class);

    }

    public Call<Bago> bago(String reg) {
        return apiService.bago(reg);
    }

    public Call<AyeYarWaddy> ayeyarwaday(String reg) {
        return apiService.ayeyarwaddy(reg);
    }

    public Call<Kachin> kachin(String reg) {
        return apiService.kachin(reg);
    }

    public Call<Kayah> kayah(String reg) {
        return apiService.kayah(reg);
    }

    public Call<MagWay> magway(String reg) {
        return apiService.magway(reg);
    }

    public Call<Mandalay> mandalay(String reg) {
        return apiService.mandalay(reg);
    }

    public Call<NayPyiTaw> naypyitaw(String reg) {
        return apiService.naypyitaw(reg);
    }

    public Call<Rakhine> rakhine(String reg) {
        return apiService.rakhine(reg);
    }

    public Call<Mon> mon(String reg) {
        return apiService.mon(reg);
    }

    public Call<Shan> shan(String reg) {
        return apiService.shan(reg);
    }

    public Call<TaNinTharYi> tanintharyee(String reg) {
        return apiService.tanintharyee(reg);
    }

    public Call<Sagaing> sagaing(String reg) {
        return apiService.sagaing(reg);
    }

    public Call<Yangon> yangon(String reg) {
        return apiService.yangon(reg);
    }

    public Call<Kayin> kayin(String reg) {
        return apiService.kayin(reg);
    }

    public Call<Chin> chin(String reg) {
        return apiService.chin(reg);
    }

    public Call<RegionPopulation> ayeyarwadaypopulation() {
        return apiService.ayeyarwadaypopulation();
    }


}
