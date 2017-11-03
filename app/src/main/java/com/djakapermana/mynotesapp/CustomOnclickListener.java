package com.djakapermana.mynotesapp;

import android.view.View;

/**
 * Created by Djaka on 29/10/2017.
 */

public class CustomOnclickListener implements View.OnClickListener {
    private int position;
    private OnItemClickCallback onItemClickCallback;
    public CustomOnclickListener(int position, OnItemClickCallback onItemClickCallback){
        this.position = position;
        this.onItemClickCallback = onItemClickCallback;
    }
    @Override
    public void onClick(View view) {
        onItemClickCallback.onItemClicked(view, position);
    }

    public interface OnItemClickCallback{
        void onItemClicked(View view, int position);
    }
}
