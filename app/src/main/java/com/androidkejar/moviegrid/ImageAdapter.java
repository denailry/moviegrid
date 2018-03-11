package com.androidkejar.moviegrid;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by denail on 5/31/2017.
 */

public class ImageAdapter extends BaseAdapter{
    Context mContext;
    ArrayList<String> mThumbIds;

    public ImageAdapter(Context mContext, ArrayList<String> mThumbIds) {
        Integer a = mThumbIds.size();
        this.mContext = mContext;
        this.mThumbIds = mThumbIds;
    }

    public int getCount() {
        return mThumbIds.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        imageView = new ImageView(mContext);
        imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500));
        Integer x = imageView.getWidth();
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setPadding(0, 0, 0, 0);

        String image = mThumbIds.get(position);
        Picasso.with(imageView.getContext()).load(image).into(imageView);

        //imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    public void refreshData(ArrayList<String> posterURL){
        Integer listMovieSize = posterURL.size();
        this.mThumbIds = posterURL;
        notifyDataSetChanged();
    }

    /* references to our images
    private Integer[] mThumbIds = {
            R.drawable.img4, R.drawable.img2,
            R.drawable.img3
    };
    */
}
