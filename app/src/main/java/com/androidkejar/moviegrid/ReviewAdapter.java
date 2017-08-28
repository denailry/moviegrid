package com.androidkejar.moviegrid;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidkejar.moviegrid.review.ReviewResult;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    ArrayList<Review> listReview;

    public ReviewAdapter(ArrayList<Review> listReview) {
        this.listReview = listReview;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivIcon;
        TextView tvAuthor, tvDesc;

        public ViewHolder(View v) {
            super(v);
            ivIcon = (ImageView) v.findViewById(R.id.review_icon);
            tvAuthor = (TextView) v.findViewById(R.id.review_author);
            tvDesc = (TextView) v.findViewById(R.id.review_desc);
        }
    }

    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Review result = listReview.get(position);
        Integer x = listReview.size();
        holder.ivIcon.setImageResource(R.drawable.person);
        holder.tvAuthor.setText(result.getAuthor());
        holder.tvDesc.setText(result.getContent());
    }

    public void refreshData(ArrayList<Review> listReview){
        Integer x = listReview.size();
        this.listReview = listReview;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return listReview.size();
    }
}
