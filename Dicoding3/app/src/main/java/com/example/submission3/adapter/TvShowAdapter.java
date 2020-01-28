package com.example.submission3.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.submission3.BuildConfig;
import com.example.submission3.R;
import com.example.submission3.detail.TvShowDetailActivity;
import com.example.submission3.model.TVShow;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TVShowViewHolder> {

    private ArrayList<TVShow> tvData = new ArrayList<>();

    public TvShowAdapter() {

    }

    public TvShowAdapter(Context context) {
    }

    public ArrayList<TVShow> getTvData() {
        return tvData;
    }

    public void refill(ArrayList<TVShow> items) {
        tvData.clear();
        tvData.addAll(items);

        notifyDataSetChanged();
    }
    @Override
    public TvShowAdapter.TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View tvView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_tv_show,parent,false);
        return new TVShowViewHolder(tvView);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowAdapter.TVShowViewHolder holder, int position) {
        holder.bind(tvData.get(position));
    }

    @Override
    public int getItemCount() {
        return tvData.size();
    }


    class TVShowViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        ImageView imgPhoto;
        TextView tvTitle,tvDateReleased,tvVoteAverage,tvVoteCount,tvOverview;

        public TVShowViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.txt_title);
            tvDateReleased = itemView.findViewById(R.id.txt_date);
            tvVoteAverage = itemView.findViewById(R.id.tv_item_vote_average);
            tvVoteCount = itemView.findViewById(R.id.tv_item_vote_count);
            imgPhoto = itemView.findViewById(R.id.img_photo);
            tvOverview = itemView.findViewById(R.id.txt_detail);

            itemView.setOnClickListener(this);
        }

        public void bind(TVShow tvShow) {
            String vote_average = Double.toString(tvShow.getVote_average());


            tvVoteAverage.setText(vote_average);
            tvTitle.setText(tvShow.getTitle());
            tvDateReleased.setText(tvShow.getRelease());
            tvVoteCount.setText(tvShow.getCount());
            tvOverview.setText(tvShow.getDetail());

            Glide.with(itemView.getContext())
                    .load(BuildConfig.TMDB_URL_POSTER + tvShow.getPhoto())
                    .into(imgPhoto);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            TVShow tvShow = tvData.get(position);

            tvShow.setTitle(tvShow.getTitle());

            Intent moveWithObject = new Intent(itemView.getContext(), TvShowDetailActivity.class);
            moveWithObject.putExtra(TvShowDetailActivity.EXTRA_TV_SHOW,tvShow);
            itemView.getContext().startActivity(moveWithObject);
        }
    }
}