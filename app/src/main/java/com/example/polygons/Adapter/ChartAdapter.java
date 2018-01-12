package com.example.polygons.Adapter;

/**
 * Created by waiyan on 11/24/17.
 */

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.polygons.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class ChartAdapter<T> extends RecyclerView.Adapter<ChartAdapter.BindingHolder>{

    private float[] yData = {25.3f, 10.6f, 66.76f, 44.32f, 46.01f, 16.89f, 23.9f};
    private String[] xData = {"Mitch", "Jessica" , "Mohammad" , "Kelsey", "Sam", "Robert", "Ashley"};
    private Context contexts;
    PieChart pieChart;
    private int holderLayout, variableId;
    private ArrayList<T> items = new ArrayList<>();
    BindingAdapter.OnItemClickListener<T>  onItemClickListener;



    public ChartAdapter(Context context,int holderLayout, int variableId, ArrayList<T> items) {
        contexts=context;
        this.holderLayout = holderLayout;
        this.variableId = variableId;
        this.items = items;
    }

    public void set(int position, T item) {
        items.set(position, item);
        notifyItemChanged(position);
    }

    public void add(T item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    public void add(int position, T item) {
        items.add(position, item);
        notifyItemInserted(position);
    }

    public void addAll(List<T> item) {
        int i = item.size();
        items.addAll(item);
        notifyItemRangeInserted(items.size() - i, items.size());
    }

//    public void remove( item) {
//        int i = items.indexOf(item);
//        if (i >= 0) {
//            items.remove(item);
//            notifyItemRemoved(i);
//        }
//
//    }

    public void remove(int i) {
        items.remove(i);
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, items.size());
    }

    public void removeAll(List<T> item) {
        items.removeAll(item);
        notifyDataSetChanged();
    }

    public T get(int i) {
        return items.get(i);
    }

    public ArrayList<T> getAll() {
        return items;
    }


    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(holderLayout, parent, false);
        return new BindingHolder(v);
    }

    @Override
    public void onBindViewHolder(final BindingHolder holder, final int position) {
        final T item = items.get(position);
        pieChart = (PieChart) holder.getBinding().getRoot().findViewById(R.id.pie_chart);
        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(position, item, view);
            }
        });
        holder.getBinding().setVariable(variableId, item);

        Toast.makeText(contexts,position+"",Toast.LENGTH_SHORT).show();
        addDataSet();
        // pieChart.setDescription("");
        pieChart.setRotationEnabled(true);
        //pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("");
        pieChart.setCenterTextSize(10);
        //pieChart.setDrawEntryLabels(true);
        //pieChart.setEntryLabelTextSize(20);
        //More options just check out the documentation!



        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                Toast.makeText(contexts, "EmployeeK", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        Log.d("onBindViewHolder", "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void setOnItemClickListener(BindingAdapter.OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public interface OnItemClickListener<T> {
        void onItemClick(int position, T item, View view);
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public BindingHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }


    private void addDataSet() {


        Log.d("hello", "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for (int i = 0; i < yData.length; i++) {
            yEntrys.add(new PieEntry(yData[i], i));
        }

        for (int i = 1; i < xData.length; i++) {
            xEntrys.add(xData[i]);
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);

       // pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

}
