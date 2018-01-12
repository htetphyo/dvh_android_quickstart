package com.example.polygons;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.polygons.Adapter.ChartAdapter;
import com.example.polygons.Model.AyeYarWadddyPopulation;
import com.example.polygons.Model.AyeYarWaddy;
import com.example.polygons.Model.Chin;
import com.example.polygons.Model.RegionPopulation;
import com.github.mikephil.charting.charts.PieChart;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by waiyan on 1/6/18.
 */

public class ChartFragment extends Fragment implements
        OnMapReadyCallback,
        GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener {

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

    PieChart pieChart;
    Spinner spinner;
    ChartAdapter chartAdapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    MapView mapView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chart, container, false);
        //pieChart = (PieChart) v.findViewById(R.id.idPieChart);
//        spinner =   (Spinner) v.findViewById(R.id.spinner);
//        recyclerView=(RecyclerView) v.findViewById(R.id.chart_recycler);


        mapView = (MapView) v.findViewById(R.id.state_map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        //
       // String[] sample_data = new String[3];
//        sample_data[0]="Population";
//        sample_data[1]="Age";
//        sample_data[2]="Sex";
//        ArrayAdapter<String> salaryAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, sample_data);
//        spinner.setAdapter(salaryAdapter);

//
//        linearLayoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(linearLayoutManager);
        Api_ayeyarwaddy();

       // Api_AyeyarWadyPopulation();

//
//        ArrayList<AyeYarWadddyPopulation> ayeYarWadddyPopulations = new  ArrayList();
//            AyeYarWadddyPopulation ayeYarWadddyPopulation1 = new AyeYarWadddyPopulation();
//            ayeYarWadddyPopulations.add(ayeYarWadddyPopulation1);
//
//
//        AyeYarWadddyPopulation ayeYarWadddyPopulation2 = new AyeYarWadddyPopulation();
//        ayeYarWadddyPopulations.add(ayeYarWadddyPopulation2);
//
//        AyeYarWadddyPopulation ayeYarWadddyPopulation3 = new AyeYarWadddyPopulation();
//        ayeYarWadddyPopulations.add(ayeYarWadddyPopulation3);
//
//        AyeYarWadddyPopulation ayeYarWadddyPopulation4 = new AyeYarWadddyPopulation();
//        ayeYarWadddyPopulations.add(ayeYarWadddyPopulation4);
//
//        AyeYarWadddyPopulation ayeYarWadddyPopulation5= new AyeYarWadddyPopulation();
//        ayeYarWadddyPopulations.add(ayeYarWadddyPopulation5);
//
//
//        chartAdapter = new ChartAdapter(getActivity(), R.layout.chart_list, com.example.polygons.BR.AyeYarWadddyPopulation, ayeYarWadddyPopulations);
//        recyclerView.setAdapter(chartAdapter);




        return v;
    }


    private void Api_AyeyarWadyPopulation() {

        final Call<RegionPopulation> userModelCall = Application.retrofitHelper.ayeyarwadaypopulation();

        userModelCall.enqueue(new Callback<RegionPopulation>() {
            @Override
            public void onResponse(Call<RegionPopulation> call, Response<RegionPopulation> response) {

                // Toast.makeText(PolyFragment.this,response.body()+"",Toast.LENGTH_LONG).show();
                Log.d("response_aye",response.body()+"");


            }
            @Override
            public void onFailure(Call<RegionPopulation> call, Throwable t) {


                Log.d("Message_aye",t.getMessage());
            }
        });
    }


    @Override
    public void onPolygonClick(Polygon polygon) {

        // Flip the values of the red, green, and blue components of the polygon's color.
        int color = polygon.getStrokeColor() ^ 0x00ffffff;
        polygon.setStrokeColor(color);
        color = polygon.getFillColor() ^ 0x00ffffff;
        polygon.setFillColor(color);

        Toast.makeText(getActivity(), "Area type " + polygon.getTag().toString(), Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onPolylineClick(Polyline polyline) {

        if ((polyline.getPattern() == null) || (!polyline.getPattern().contains(DOT))) {
            polyline.setPattern(PATTERN_POLYLINE_DOTTED);
        } else {
            // The default pattern is a solid stroke.
            polyline.setPattern(null);
        }

        Toast.makeText(getActivity(), "Route type " + polyline.getTag().toString(),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        int i ;
        int a ;
        List<LatLng> latlngs1 = new ArrayList<>();


        if(Application.lagLatAyeyarModels!=null) {

            for (i = 0; i < Application.lagLatAyeyarModels.size(); i++) {
                for(a=0;a<Application.lagLatAyeyarModels.get(i).size();a++) {
                    latlngs1.add(new LatLng(Application.lagLatAyeyarModels.get(i).get(a).getLat(), Application.lagLatAyeyarModels.get(i).get(a).getLng()));
                }
            }

            Polygon polygon2 = googleMap.addPolygon(new PolygonOptions()
                    .clickable(true)
                    .addAll(latlngs1));
            polygon2.setTag("beta");
            stylePolygon(polygon2);

        }


        float f = (float) 7;
    // Position the map's camera near Alice Springs in the center of Australia,
    // and set the zoom factor so most of Australia shows on the screen.
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(17.0265275,94.960546), f));
    // Set listeners for click events.
        googleMap.setOnPolylineClickListener(this);
        googleMap.setOnPolygonClickListener(this);
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


    private void Api_ayeyarwaddy() {

        final Call<AyeYarWaddy> userModelCall = Application.retrofitHelper.ayeyarwaday("ayeyarwaddy");


        userModelCall.enqueue(new Callback<AyeYarWaddy>() {
            @Override
            public void onResponse(Call<AyeYarWaddy> call, Response<AyeYarWaddy> response) {

                // Toast.makeText(PolyFragment.this,response.body()+"",Toast.LENGTH_LONG).show();
                Log.d("response",response.body()+"");
                Application.lagLatAyeyarModels=response.body().getAyeyarwaddy();
                mapView.onResume();
                mapView.getMapAsync(ChartFragment.this);

            }
            @Override
            public void onFailure(Call<AyeYarWaddy> call, Throwable t) {


                Log.d("Message",t.getMessage());
            }
        });
    }


}
