package com.example.polygons;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
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
import com.example.polygons.Model.Sagaing;
import com.example.polygons.Model.Shan;
import com.example.polygons.Model.TaNinTharYi;
import com.example.polygons.Model.Yangon;
import com.github.mikephil.charting.charts.PieChart;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CustomCap;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.RoundCap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * An activity that displays a Google map with polylines to represent paths or routes,
 * and polygons to represent areas.
 */

public class PolyFragment extends Fragment implements
                OnMapReadyCallback,
                GoogleMap.OnPolylineClickListener,
                GoogleMap.OnPolygonClickListener {
    PieChart pieChart;

    private static final int COLOR_BLACK_ARGB = 0xff000000;
    private static final int COLOR_WHITE_ARGB = 0xffffffff;
    private static final int COLOR_GREEN_ARGB = 0xff388E3C;
    private static final int COLOR_PURPLE_ARGB = 0xff81C784;
    private static final int COLOR_ORANGE_ARGB = 0xffF57F17;
    private static final int COLOR_BLUE_ARGB = 0xffF9A825;
    private static final int POLYLINE_STROKE_WIDTH_PX = 12;
    private static final int POLYGON_STROKE_WIDTH_PX = 8;
    private static final int PATTERN_DASH_LENGTH_PX = 20;
    private static final int PATTERN_GAP_LENGTH_PX = 20;
    private static final PatternItem DOT = new Dot();
    private static final PatternItem DASH = new Dash(PATTERN_DASH_LENGTH_PX);
    private static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);

    // Create a stroke pattern of a gap followed by a dot.
    private static final List<PatternItem> PATTERN_POLYLINE_DOTTED = Arrays.asList(GAP, DOT);

    // Create a stroke pattern of a gap followed by a dash.
    private static final List<PatternItem> PATTERN_POLYGON_ALPHA = Arrays.asList(GAP, DASH);

    // Create a stroke pattern of a dot followed by a gap, a dash, and another gap.
    private static final List<PatternItem> PATTERN_POLYGON_BETA =
            Arrays.asList(DOT, GAP, DASH, GAP);

    MapView mapView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_maps, container, false);

        if(Application.laglatYangon==null) {
            Api_Pago();
            Api_ayeyarwaddy();
            Api_Kachin();
            Api_kayah();
            Api_MagWay();
            Api_Mandalay();
            Api_NayPyiTaw();
            Api_Rakhine();
            Api_Mon();
            Api_Shan();
            Api_TaNinTharYee();
            Api_Sagaing();
            //Api_Yangon();
            Api_Kayin();
            Api_Chin();
        }



        mapView = (MapView) v.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);


        return v;
    }

    private void Api_Pago() {

        final Call<Bago> userModelCall = Application.retrofitHelper.bago("bago");


        userModelCall.enqueue(new Callback<Bago>() {
            @Override
            public void onResponse(Call<Bago> call, Response<Bago> response) {

               // Toast.makeText(PolyFragment.this,response.body()+"",Toast.LENGTH_LONG).show();
                Log.d("response",response.body()+"");
                Application.lagLatModels=response.body().getBago();
                mapView.onResume();
                mapView.getMapAsync(PolyFragment.this);


            }
            @Override
            public void onFailure(Call<Bago> call, Throwable t) {


                Log.d("Message",t.getMessage());
            }
        });
    }

    private void Api_ayeyarwaddy() {

        final Call<AyeYarWaddy> userModelCall = Application.retrofitHelper.ayeyarwaday("ayeyarwaddy");


        userModelCall.enqueue(new Callback<AyeYarWaddy>() {
            @Override
            public void onResponse(Call<AyeYarWaddy> call, Response<AyeYarWaddy> response) {

                // Toast.makeText(PolyFragment.this,response.body()+"",Toast.LENGTH_LONG).show();
                Log.d("response",response.body()+"");
                Application.lagLatAyeyarModels=response.body().getAyeyarwaddy();
                mapView.onResume();
                mapView.getMapAsync(PolyFragment.this);

            }
            @Override
            public void onFailure(Call<AyeYarWaddy> call, Throwable t) {


              //  Log.d("Message",t.getMessage());
            }
        });
    }


    private void Api_Kachin() {
        
        final Call<Kachin> userModelCall = Application.retrofitHelper.kachin("kachin");


        userModelCall.enqueue(new Callback<Kachin>() {
            @Override
            public void onResponse(Call<Kachin> call, Response<Kachin> response) {

                // Toast.makeText(PolyFragment.this,response.body()+"",Toast.LENGTH_LONG).show();
                Log.d("response",response.body()+"");
                Application.lagLatKachinModels=response.body().getKachin();
                mapView.onResume();
                mapView.getMapAsync(PolyFragment.this);

            }
            @Override
            public void onFailure(Call<Kachin> call, Throwable t) {


              //  Log.d("Message",t.getMessage());
            }
        });
    }

    private void Api_kayah() {

        final Call<Kayah> userModelCall = Application.retrofitHelper.kayah("kayah");


        userModelCall.enqueue(new Callback<Kayah>() {
            @Override
            public void onResponse(Call<Kayah> call, Response<Kayah> response) {

                // Toast.makeText(PolyFragment.this,response.body()+"",Toast.LENGTH_LONG).show();
                Log.d("response",response.body()+"");
                Application.lagLatKayahModels=response.body().getKayah();
                mapView.onResume();
                mapView.getMapAsync(PolyFragment.this);

            }
            @Override
            public void onFailure(Call<Kayah> call, Throwable t) {


                //Log.d("Message",t.getMessage());
            }
        });
    }


    private void Api_MagWay() {

        final Call<MagWay> userModelCall = Application.retrofitHelper.magway("magway");


        userModelCall.enqueue(new Callback<MagWay>() {
            @Override
            public void onResponse(Call<MagWay> call, Response<MagWay> response) {

                // Toast.makeText(PolyFragment.this,response.body()+"",Toast.LENGTH_LONG).show();
                Log.d("response",response.body()+"");
                Application.laglatMagWayModels=response.body().getMagway();
                mapView.onResume();
                mapView.getMapAsync(PolyFragment.this);

            }
            @Override
            public void onFailure(Call<MagWay> call, Throwable t) {


                //Log.d("Message",t.getMessage());
            }
        });
    }

    private void Api_Mandalay() {

        final Call<Mandalay> userModelCall = Application.retrofitHelper.mandalay("mandalay");


        userModelCall.enqueue(new Callback<Mandalay>() {
            @Override
            public void onResponse(Call<Mandalay> call, Response<Mandalay> response) {

                // Toast.makeText(PolyFragment.this,response.body()+"",Toast.LENGTH_LONG).show();
                Log.d("response",response.body()+"");
                Application.laglatMandalayModels=response.body().getMandalay();
                mapView.onResume();
                mapView.getMapAsync(PolyFragment.this);

            }
            @Override
            public void onFailure(Call<Mandalay> call, Throwable t) {


              //  Log.d("Message",t.getMessage());
            }
        });
    }


    private void Api_NayPyiTaw() {

        final Call<NayPyiTaw> userModelCall = Application.retrofitHelper.naypyitaw("naypyitaw");


        userModelCall.enqueue(new Callback<NayPyiTaw>() {
            @Override
            public void onResponse(Call<NayPyiTaw> call, Response<NayPyiTaw> response) {

                // Toast.makeText(PolyFragment.this,response.body()+"",Toast.LENGTH_LONG).show();
                Log.d("response",response.body()+"");
                Application.laglatNaypyiTawModels=response.body().getNaypyitaw();
                mapView.onResume();
                mapView.getMapAsync(PolyFragment.this);

            }
            @Override
            public void onFailure(Call<NayPyiTaw> call, Throwable t) {


               // Log.d("Message",t.getMessage());
            }
        });
    }


    private void Api_Rakhine() {

        final Call<Rakhine> userModelCall = Application.retrofitHelper.rakhine("rakhine");


        userModelCall.enqueue(new Callback<Rakhine>() {
            @Override
            public void onResponse(Call<Rakhine> call, Response<Rakhine> response) {

                // Toast.makeText(PolyFragment.this,response.body()+"",Toast.LENGTH_LONG).show();
                Log.d("response",response.body()+"");
                Application.laglatRakhineModels=response.body().getRakhine();
                mapView.onResume();
                mapView.getMapAsync(PolyFragment.this);

            }
            @Override
            public void onFailure(Call<Rakhine> call, Throwable t) {


                //Log.d("Message",t.getMessage());
            }
        });
    }



    private void Api_Mon() {

        final Call<Mon> userModelCall = Application.retrofitHelper.mon("mon");


        userModelCall.enqueue(new Callback<Mon>() {
            @Override
            public void onResponse(Call<Mon> call, Response<Mon> response) {

                // Toast.makeText(PolyFragment.this,response.body()+"",Toast.LENGTH_LONG).show();
                Log.d("response",response.body()+"");
                Application.laglatMonModels=response.body().getMon();
                mapView.onResume();
                mapView.getMapAsync(PolyFragment.this);

            }
            @Override
            public void onFailure(Call<Mon> call, Throwable t) {


              //  Log.d("Message",t.getMessage());
            }
        });
    }


    private void Api_Shan() {

        final Call<Shan> userModelCall = Application.retrofitHelper.shan("shan");

        userModelCall.enqueue(new Callback<Shan>() {
            @Override
            public void onResponse(Call<Shan> call, Response<Shan> response) {

                // Toast.makeText(PolyFragment.this,response.body()+"",Toast.LENGTH_LONG).show();
                Log.d("response",response.body()+"");
                Application.laglatShanModels=response.body().getShan();
                mapView.onResume();
                mapView.getMapAsync(PolyFragment.this);

            }
            @Override
            public void onFailure(Call<Shan> call, Throwable t) {


               // Log.d("Message",t.getMessage());
            }
        });
    }


    private void Api_TaNinTharYee() {

        final Call<TaNinTharYi> userModelCall = Application.retrofitHelper.tanintharyee("tanintharyi");

        userModelCall.enqueue(new Callback<TaNinTharYi>() {
            @Override
            public void onResponse(Call<TaNinTharYi> call, Response<TaNinTharYi> response) {

                // Toast.makeText(PolyFragment.this,response.body()+"",Toast.LENGTH_LONG).show();
                Log.d("response",response.body()+"");
                Application.laglatTaNinTharYee=response.body().getTanintharyi();
                mapView.onResume();
                mapView.getMapAsync(PolyFragment.this);

            }
            @Override
            public void onFailure(Call<TaNinTharYi> call, Throwable t) {


               // Log.d("Message",t.getMessage());
            }
        });
    }


    private void Api_Sagaing() {

        final Call<Sagaing> userModelCall = Application.retrofitHelper.sagaing("sagaing");

        userModelCall.enqueue(new Callback<Sagaing>() {
            @Override
            public void onResponse(Call<Sagaing> call, Response<Sagaing> response) {

                // Toast.makeText(PolyFragment.this,response.body()+"",Toast.LENGTH_LONG).show();
                Log.d("response",response.body()+"");
                Application.laglatSagaing=response.body().getSagaing();
                mapView.onResume();
                mapView.getMapAsync(PolyFragment.this);

            }
            @Override
            public void onFailure(Call<Sagaing> call, Throwable t) {


               // Log.d("Message",t.getMessage());
            }
        });
    }


    private void Api_Yangon() {

        final Call<Yangon> userModelCall = Application.retrofitHelper.yangon("yangon");

        userModelCall.enqueue(new Callback<Yangon>() {
            @Override
            public void onResponse(Call<Yangon> call, Response<Yangon> response) {

                // Toast.makeText(PolyFragment.this,response.body()+"",Toast.LENGTH_LONG).show();
                Log.d("response",response.body()+"");
                Application.laglatYangon=response.body().getYangon();
                mapView.onResume();
                mapView.getMapAsync(PolyFragment.this);

            }
            @Override
            public void onFailure(Call<Yangon> call, Throwable t) {


              //  Log.d("Message",t.getMessage());
            }
        });
    }


    private void Api_Kayin() {

        final Call<Kayin> userModelCall = Application.retrofitHelper.kayin("kayin");

        userModelCall.enqueue(new Callback<Kayin>() {
            @Override
            public void onResponse(Call<Kayin> call, Response<Kayin> response) {

                // Toast.makeText(PolyFragment.this,response.body()+"",Toast.LENGTH_LONG).show();
                Log.d("response",response.body()+"");
                Application.laglatKayin=response.body().getKayin();
                mapView.onResume();
                mapView.getMapAsync(PolyFragment.this);

            }
            @Override
            public void onFailure(Call<Kayin> call, Throwable t) {


              //  Log.d("Message",t.getMessage());
            }
        });
    }


    private void Api_Chin() {

        final Call<Chin> userModelCall = Application.retrofitHelper.chin("chin");

        userModelCall.enqueue(new Callback<Chin>() {
            @Override
            public void onResponse(Call<Chin> call, Response<Chin> response) {

                // Toast.makeText(PolyFragment.this,response.body()+"",Toast.LENGTH_LONG).show();
                Log.d("response",response.body()+"");
                Application.laglatChin=response.body().getChin();
                mapView.onResume();
                mapView.getMapAsync(PolyFragment.this);

            }
            @Override
            public void onFailure(Call<Chin> call, Throwable t) {


             //   Log.d("Message",t.getMessage());
            }
        });
    }





    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * In this tutorial, we add polylines and polygons to represent routes and areas on the map.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {


        int i;
        int a;

        List<LatLng> latlngs1 = new ArrayList<>();
        List<LatLng> latlngs2 = new ArrayList<>();
        List<LatLng> latlngs3 = new ArrayList<>();
        List<LatLng> latlngs4 = new ArrayList<>();
        List<LatLng> latlngs5 = new ArrayList<>();
        List<LatLng> latlngs6 = new ArrayList<>();
        List<LatLng> latlngs7 = new ArrayList<>();
        List<LatLng> latlngs8 = new ArrayList<>();
        List<LatLng> latlngs9 = new ArrayList<>();
        List<LatLng> latlngs10 = new ArrayList<>();
        List<LatLng> latlngs11 = new ArrayList<>();
        List<LatLng> latlngs12 = new ArrayList<>();
        List<LatLng> latlngs13 = new ArrayList<>();
        List<LatLng> latlngs14 = new ArrayList<>();
        List<LatLng> latlngs15 = new ArrayList<>();


        if(Application.lagLatModels!=null) {

            for (i = 0; i < Application.lagLatModels.size(); i++) {
                for(a=0;a<Application.lagLatModels.get(i).size();a++) {
                    latlngs1.add(new LatLng(Application.lagLatModels.get(i).get(a).getLat(), Application.lagLatModels.get(i).get(a).getLng()));
                }
            }


            Polygon polygon2 = googleMap.addPolygon(new PolygonOptions()
                    .clickable(true)
                    .addAll(latlngs1));
            polygon2.setTag("beta");
            stylePolygon(polygon2);

        }

        if(Application.lagLatAyeyarModels!=null) {

            for (i = 0; i < Application.lagLatAyeyarModels.size(); i++) {
                for(a=0;a<Application.lagLatAyeyarModels.get(i).size();a++) {
                    latlngs2.add(new LatLng(Application.lagLatAyeyarModels.get(i).get(a).getLat(), Application.lagLatAyeyarModels.get(i).get(a).getLng()));
                }
            }

            Polygon polygon2 = googleMap.addPolygon(new PolygonOptions()
                    .clickable(true)
                    .addAll(latlngs2));
            polygon2.setTag("beta");
            stylePolygon(polygon2);

        }

        if(Application.lagLatKachinModels!=null)
        {
            for (i = 0; i < Application.lagLatKachinModels.size(); i++) {
                for(a=0;a<Application.lagLatKachinModels.get(i).size();a++) {
                    latlngs3.add(new LatLng(Application.lagLatKachinModels.get(i).get(a).getLat(), Application.lagLatKachinModels.get(i).get(a).getLng()));
                }
            }

            Polygon polygon2 = googleMap.addPolygon(new PolygonOptions()
                    .clickable(true)
                    .addAll(latlngs3));
            polygon2.setTag("beta");
            stylePolygon(polygon2);

        }


        if(Application.lagLatKayahModels!=null)
        {
            for (i = 0; i < Application.lagLatKayahModels.size(); i++) {
                for(a=0;a<Application.lagLatKayahModels.get(i).size();a++) {
                    latlngs4.add(new LatLng(Application.lagLatKayahModels.get(i).get(a).getLat(), Application.lagLatKayahModels.get(i).get(a).getLng()));
                }
            }

            Polygon polygon2 = googleMap.addPolygon(new PolygonOptions()
                    .clickable(true)
                    .addAll(latlngs4));
            polygon2.setTag("beta");
            stylePolygon(polygon2);

        }


        if(Application.laglatMagWayModels!=null)
        {
            for (i = 0; i < Application.laglatMagWayModels.size(); i++) {
                for(a=0;a<Application.laglatMagWayModels.get(i).size();a++) {
                    latlngs5.add(new LatLng(Application.laglatMagWayModels.get(i).get(a).getLat(), Application.laglatMagWayModels.get(i).get(a).getLng()));
                }
            }

            Polygon polygon2 = googleMap.addPolygon(new PolygonOptions()
                    .clickable(true)
                    .addAll(latlngs5));
            polygon2.setTag("beta");
            stylePolygon(polygon2);

        }


        if(Application.laglatMandalayModels!=null)
        {
            for (i = 0; i < Application.laglatMandalayModels.size(); i++) {
                for(a=0;a<Application.laglatMandalayModels.get(i).size();a++) {
                    latlngs6.add(new LatLng(Application.laglatMandalayModels.get(i).get(a).getLat(), Application.laglatMandalayModels.get(i).get(a).getLng()));
                }
            }

            Polygon polygon2 = googleMap.addPolygon(new PolygonOptions()
                    .clickable(true)
                    .addAll(latlngs6));
            polygon2.setTag("beta");
            stylePolygon(polygon2);

        }



        if(Application.laglatNaypyiTawModels!=null)
        {
            for (i = 0; i < Application.laglatNaypyiTawModels.size(); i++) {
                for(a=0;a<Application.laglatNaypyiTawModels.get(i).size();a++) {
                    latlngs7.add(new LatLng(Application.laglatNaypyiTawModels.get(i).get(a).getLat(), Application.laglatNaypyiTawModels.get(i).get(a).getLng()));
                }
            }


            Polygon polygon2 = googleMap.addPolygon(new PolygonOptions()
                    .clickable(true)
                    .addAll(latlngs7));
            polygon2.setTag("beta");
            stylePolygon(polygon2);

        }

        if(Application.laglatRakhineModels!=null)
        {
            for (i = 0; i < Application.laglatRakhineModels.size(); i++) {
                for(a=0;a<Application.laglatRakhineModels.get(i).size();a++) {
                    latlngs8.add(new LatLng(Application.laglatRakhineModels.get(i).get(a).getLat(), Application.laglatRakhineModels.get(i).get(a).getLng()));
                }
            }

            Polygon polygon2 = googleMap.addPolygon(new PolygonOptions()
                    .clickable(true)
                    .addAll(latlngs8));
            polygon2.setTag("beta");
            stylePolygon(polygon2);

        }

        if(Application.laglatMonModels!=null)
        {
            for (i = 0; i < Application.laglatMonModels.size(); i++) {
                for(a=0;a<Application.laglatMonModels.get(i).size();a++) {
                    latlngs9.add(new LatLng(Application.laglatMonModels.get(i).get(a).getLat(), Application.laglatMonModels.get(i).get(a).getLng()));
                }
            }

            Polygon polygon5 = googleMap.addPolygon(new PolygonOptions()
                    .clickable(true)
                    .addAll(latlngs9));
            polygon5.setTag("beta");
            stylePolygon(polygon5);

        }

        if(Application.laglatShanModels!=null)
        {
            for (i = 0; i < Application.laglatShanModels.size(); i++) {
                for(a=0;a<Application.laglatShanModels.get(i).size();a++) {
                    latlngs10.add(new LatLng(Application.laglatShanModels.get(i).get(a).getLat(), Application.laglatShanModels.get(i).get(a).getLng()));
                }
            }

            Polygon polygon5 = googleMap.addPolygon(new PolygonOptions()
                    .clickable(true)
                    .addAll(latlngs10));
            polygon5.setTag("beta");
            stylePolygon(polygon5);

        }

        if(Application.laglatTaNinTharYee!=null)
        {
            for (i = 0; i < Application.laglatTaNinTharYee.size(); i++) {
                for(a=0;a<Application.laglatTaNinTharYee.get(i).size();a++) {
                    latlngs11.add(new LatLng(Application.laglatTaNinTharYee.get(i).get(a).getLat(), Application.laglatTaNinTharYee.get(i).get(a).getLng()));
                }
            }

            Polygon polygon5 = googleMap.addPolygon(new PolygonOptions()
                    .clickable(true)
                    .addAll(latlngs11));
            polygon5.setTag("beta");
            stylePolygon(polygon5);

        }

        if(Application.laglatSagaing!=null)
        {
            for (i = 0; i < Application.laglatSagaing.size(); i++) {
                for(a=0;a<Application.laglatSagaing.get(i).size();a++) {
                    latlngs12.add(new LatLng(Application.laglatSagaing.get(i).get(a).getLat(), Application.laglatSagaing.get(i).get(a).getLng()));
                }
            }

            Polygon polygon5 = googleMap.addPolygon(new PolygonOptions()
                    .clickable(true)
                    .addAll(latlngs12));
            polygon5.setTag("beta");
            stylePolygon(polygon5);

        }


        if(Application.laglatYangon!=null)
        {
            for (i = 0; i < Application.laglatYangon.size(); i++) {
                for(a=0;a<Application.laglatYangon.get(i).size();a++) {
                    latlngs13.add(new LatLng(Application.laglatYangon.get(i).get(a).getLat(), Application.laglatYangon.get(i).get(a).getLng()));
                }
            }

            Polygon polygon5 = googleMap.addPolygon(new PolygonOptions()
                    .clickable(true)
                    .addAll(latlngs13));
            polygon5.setTag("beta");
            stylePolygon(polygon5);

        }

        if(Application.laglatKayin!=null)
        {
            for (i = 0; i < Application.laglatKayin.size(); i++) {
                for(a=0;a<Application.laglatKayin.get(i).size();a++) {
                    latlngs14.add(new LatLng(Application.laglatKayin.get(i).get(a).getLat(), Application.laglatKayin.get(i).get(a).getLng()));
                }
            }

            Polygon polygon5 = googleMap.addPolygon(new PolygonOptions()
                    .clickable(true)
                    .addAll(latlngs14));
            polygon5.setTag("beta");
            stylePolygon(polygon5);

        }

        if(Application.laglatChin!=null)
        {
            for (i = 0; i < Application.laglatChin.size(); i++) {
                for(a=0;a<Application.laglatChin.get(i).size();a++) {
                    latlngs15.add(new LatLng(Application.laglatChin.get(i).get(a).getLat(), Application.laglatChin.get(i).get(a).getLng()));
                }
            }

            Polygon polygon5 = googleMap.addPolygon(new PolygonOptions()
                    .clickable(true)
                    .addAll(latlngs15));
            polygon5.setTag("beta");
            stylePolygon(polygon5);

        }




        float f = (float) 4.9;
        // Position the map's camera near Alice Springs in the center of Australia,
        // and set the zoom factor so most of Australia shows on the screen.
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.777593, 96.215221), f));
        // Set listeners for click events.
        googleMap.setOnPolylineClickListener(this);
        googleMap.setOnPolygonClickListener(this);
    }

    /**
     * Styles the polyline, based on type.
     * @param polyline The polyline object that needs styling.
     */
    private void stylePolyline(Polyline polyline) {
        String type = "";
        // Get the data object stored with the polyline.
        if (polyline.getTag() != null) {
            type = polyline.getTag().toString();
        }

        switch (type) {
            // If no type is given, allow the API to use the default.
            case "A":
                // Use a custom bitmap as the cap at the start of the line.
                polyline.setStartCap(
                        new CustomCap(
                                BitmapDescriptorFactory.fromResource(R.drawable.ic_arrow), 10));
                break;
            case "B":
                // Use a round cap at the start of the line.
                polyline.setStartCap(new RoundCap());
                break;
        }


        polyline.setEndCap(new RoundCap());
        polyline.setWidth(POLYLINE_STROKE_WIDTH_PX);
        polyline.setColor(COLOR_BLACK_ARGB);
        polyline.setJointType(JointType.ROUND);
    }

    /**
     * Styles the polygon, based on type.
     * @param polygon The polygon object that needs styling.
     */
    private void stylePolygon(Polygon polygon) {
        String type = "";
        // Get the data object stored with the polygon.
        if (polygon.getTag() != null) {
            type = polygon.getTag().toString();
        }

        List<PatternItem> pattern = null;
        int strokeColor = COLOR_BLACK_ARGB;
        int fillColor = COLOR_WHITE_ARGB;

        switch (type) {
            // If no type is given, allow the API to use the default.
            case "alpha":
                // Apply a stroke pattern to render a dashed line, and define colors.
                pattern = PATTERN_POLYGON_ALPHA;
                strokeColor = COLOR_GREEN_ARGB;
                fillColor = COLOR_PURPLE_ARGB;
                break;
            case "beta":
                // Apply a stroke pattern to render a line of dots and dashes, and define colors.
                pattern = PATTERN_POLYGON_BETA;
                strokeColor = COLOR_ORANGE_ARGB;
                fillColor = COLOR_BLUE_ARGB;
                break;
        }

        polygon.setStrokePattern(pattern);
        polygon.setStrokeWidth(POLYGON_STROKE_WIDTH_PX);
        polygon.setStrokeColor(strokeColor);
        polygon.setFillColor(fillColor);

    }

    /**
     * Listens for clicks on a polyline.
     * @param polyline The polyline object that the user has clicked.
     */
    @Override
    public void onPolylineClick(Polyline polyline) {
        // Flip from solid stroke to dotted stroke pattern.
        if ((polyline.getPattern() == null) || (!polyline.getPattern().contains(DOT))) {
            polyline.setPattern(PATTERN_POLYLINE_DOTTED);
        } else {
            // The default pattern is a solid stroke.
            polyline.setPattern(null);
        }

        Toast.makeText(getActivity(), "Route type " + polyline.getTag().toString(),Toast.LENGTH_SHORT).show();
    }

    /**
     * Listens for clicks on a polygon.
     * @param polygon The polygon object that the user has clicked.
     */
    @Override
    public void onPolygonClick(Polygon polygon) {
        // Flip the values of the red, green, and blue components of the polygon's color.
        int color = polygon.getStrokeColor() ^ 0x00ffffff;
        polygon.setStrokeColor(color);
        color = polygon.getFillColor() ^ 0x00ffffff;
        polygon.setFillColor(color);

        Toast.makeText(getActivity(), "Area type " + polygon.getTag().toString(), Toast.LENGTH_SHORT).show();
    }





}
