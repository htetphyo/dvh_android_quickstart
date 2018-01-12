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

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by waiyan on 5/14/17.
 */

public interface ApiService {

    //Bago Region Api
    @GET(ApiConstants.reg)
    Call<Bago> bago(@Query(value="reg") String reg);

    //Ayeyarwaday Region Api
    @GET(ApiConstants.reg)
    Call<AyeYarWaddy> ayeyarwaddy(@Query(value="reg") String reg);

    //Kachin Region Api
    @GET(ApiConstants.reg)
    Call<Kachin> kachin(@Query(value="reg") String reg);

    //Kayah Region Api
    @GET(ApiConstants.reg)
    Call<Kayah> kayah(@Query(value="reg") String reg);

    //MagWay Region Api
    @GET(ApiConstants.reg)
    Call<MagWay> magway(@Query(value="reg") String reg);

    //Mandalay Region Api
    @GET(ApiConstants.reg)
    Call<Mandalay> mandalay(@Query(value="reg") String reg);

    //NayPyiTaw Region Api
    @GET(ApiConstants.reg)
    Call<NayPyiTaw> naypyitaw(@Query(value="reg") String reg);

    //Rakhine Region Api
    @GET(ApiConstants.reg)
    Call<Rakhine> rakhine(@Query(value="reg") String reg);

    //Mon Region Api
    @GET(ApiConstants.reg)
    Call<Mon> mon(@Query(value="reg") String reg);

    //Shan Region Api
    @GET(ApiConstants.reg)
    Call<Shan> shan(@Query(value="reg") String reg);

    //TaninTharYee Region Api
    @GET(ApiConstants.reg)
    Call<TaNinTharYi> tanintharyee(@Query(value="reg") String reg);

    //Sagaing Region Api
    @GET(ApiConstants.reg)
    Call<Sagaing> sagaing(@Query(value="reg") String reg);

    //Yangon Region Api
    @GET(ApiConstants.reg)
    Call<Yangon> yangon(@Query(value="reg") String reg);

    //Kayin Region Api
    @GET(ApiConstants.reg)
    Call<Kayin> kayin(@Query(value="reg") String reg);


    //Kayin Region Api
    @GET(ApiConstants.reg)
    Call<Chin> chin(@Query(value="reg") String reg);


    //Kayin Region Api
    @GET(ApiConstants.aya_population)
    Call<RegionPopulation> ayeyarwadaypopulation();





}
