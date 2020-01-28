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
import com.example.submission3.model.Movie;
import com.example.submission3.detail.MovieDetailActivity;
import com.example.submission3.R;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context context;
    public MovieAdapter(Context context) {
        this.context = context;
    }
    private ArrayList<Movie> mData = new ArrayList<>();

    public MovieAdapter() {

    }

    public void setData (ArrayList<Movie>items){
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_movie,parent,false);
        return new MovieViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }
    public void setListFavorite(ArrayList<Movie> listMovie) {
        this.mData = new ArrayList<>();
        this.mData.addAll(listMovie);
        notifyDataSetChanged();
    }

    public ArrayList<Movie> getListMovie() {
        return mData;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgPhoto;
        TextView tvTitle,tvDateReleased,tvVoteAverage,tvVoteCount,tvOverview;
        private MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.txt_title);
            tvDateReleased = itemView.findViewById(R.id.txt_date);
            tvVoteAverage = itemView.findViewById(R.id.tv_item_vote_average);
            tvVoteCount = itemView.findViewById(R.id.tv_item_vote_count);
            imgPhoto = itemView.findViewById(R.id.img_photo);
            tvOverview = itemView.findViewById(R.id.txt_detail);
            itemView.setOnClickListener(this);
        }

        public void bind(Movie movie) {
            String vote_average = Double.toString(movie.getVote_Average());


            tvTitle.setText(movie.getTitle());
            tvDateReleased.setText(movie.getRelease());
            tvVoteAverage.setText(vote_average);
            tvVoteCount.setText(movie.getCount());
            tvOverview.setText(movie.getDetail());

            Glide.with(itemView.getContext())
                    .load(BuildConfig.TMDB_URL_POSTER + movie.getPhoto())
                    .into(imgPhoto);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Movie movie = mData.get(position);

            movie.setTitle(movie.getTitle());

            Intent moveWithObjectIntent = new Intent(itemView.getContext(),MovieDetailActivity.class);
            moveWithObjectIntent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
            itemView.getContext().startActivity(moveWithObjectIntent);

        }
    }
}
