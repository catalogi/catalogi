package com.dicoding.catalog.adapter;

import android.view.View;

public class OnClickListener implements View.OnClickListener {

    private int position;
    private OnItemClickCallback onItemClickCallback;

    public OnClickListener(int position, OnItemClickCallback onItemClickCallback){
        this.position =position;
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public void onClick(View view) {
        onItemClickCallback.onItemClicked(view,position);
    }

    public interface OnItemClickCallback{
        void onItemClicked(View view, int position);
    }
}
