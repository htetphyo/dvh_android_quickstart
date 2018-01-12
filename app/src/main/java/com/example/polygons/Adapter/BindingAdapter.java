package com.example.polygons.Adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanjay on 10/12/16.
 */

public class BindingAdapter<T> extends RecyclerView.Adapter<BindingAdapter.BindingHolder> {

    int holderLayout, variableId;
    AbstractList<T> items = new ArrayList<>();
    OnItemClickListener<T> onItemClickListener;

    public BindingAdapter(int holderLayout, int variableId, AbstractList<T> items) {
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

    public void remove(T item) {
        int i = items.indexOf(item);
        if (i >= 0) {
            items.remove(item);
            notifyItemRemoved(i);
        }

    }

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

    public AbstractList<T> getAll() {
        return items;
    }


    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(holderLayout, parent, false);
        return new BindingHolder(v);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, final int position) {
        final T item = items.get(position);
        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(position, item, view);
            }
        });
        holder.getBinding().setVariable(variableId, item);
        //holder.getBinding().executePendingBindings();

        Log.d("onBindViewHolder", "onBindViewHolder");

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
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

}
