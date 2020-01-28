package com.example.submission3.detail;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.submission3.BuildConfig;
import com.example.submission3.R;
import com.example.submission3.db.FavoriteHelper;
import com.example.submission3.model.Movie;
import com.example.submission3.model.TVShow;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import static com.example.submission3.detail.MovieDetailActivity.EXTRA_MOVIE;

public class TvShowDetailActivity extends AppCompatActivity {

    public static final String EXTRA_TV_SHOW = "extra_tvShow";

    TextView tvTitle, tvDateReleased, tvVoteAverage, tvVoteCount, tvOverview, txtLanguage, tvPopularity;
    ImageView imgPhoto;
    private ProgressBar progressBar;
    private TVShow tvShow;
    private FavoriteHelper favoriteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);

        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.colorTitle));
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.colorTitle));


        tvTitle = findViewById(R.id.txt_title);
        tvDateReleased = findViewById(R.id.txt_date);
        tvVoteCount = findViewById(R.id.tv_item_vote_count);
        tvVoteAverage = findViewById(R.id.tv_item_vote_average);
        tvOverview = findViewById(R.id.txt_detail);
        txtLanguage = findViewById(R.id.txt_language);
        tvPopularity = findViewById(R.id.popularity);
        imgPhoto = findViewById(R.id.img_photo);

        tvShow = getIntent().getParcelableExtra(EXTRA_TV_SHOW);
        getSupportActionBar().setTitle(tvShow.getTitle());

        progressBar = findViewById(R.id.progressDetailMovie);
        progressBar.setVisibility(View.VISIBLE);


        String popularity = Double.toString(tvShow.getPopularity());
        String vote_average = Double.toString(tvShow.getVote_average());

        tvOverview.setText(tvShow.getDetail());
        tvVoteCount.setText(tvShow.getCount());
        tvVoteAverage.setText(vote_average);
        tvDateReleased.setText(tvShow.getRelease());
        txtLanguage.setText(tvShow.getLanguage());
        tvPopularity.setText(popularity);

        Glide.with(getApplicationContext())
                .load(BuildConfig.TMDB_URL_POSTER + tvShow.getPhoto())
                .into(imgPhoto);
        progressBar.setVisibility(View.INVISIBLE);

        favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());
        favoriteHelper.open();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (favoriteHelper.isExist2(tvShow)) {
            getMenuInflater().inflate(R.menu.menu_already_favorite, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_add_favorite, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_favorite) {
            if (!favoriteHelper.isExist2(tvShow)) {
                long result = favoriteHelper.insertFavorite2(tvShow);
                if (result > 0) {
                    item.setIcon(R.drawable.ic_favorite_yellow_24dp);
                    Toast.makeText(TvShowDetailActivity.this, getResources().getString(R.string.success_add_favorite), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TvShowDetailActivity.this, getResources().getString(R.string.failed__add_favorite), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(TvShowDetailActivity.this, getResources().getString(R.string.favorite_is_exist), Toast.LENGTH_SHORT).show();
            }
        } else {
            if (item.getItemId() == R.id.action_delete_favorite) {
                int result = favoriteHelper.deleteFavorite2(tvShow.getId());
                if (result > 0) {
                    item.setIcon(R.drawable.ic_favorite_white_24dp);
                    Toast.makeText(TvShowDetailActivity.this, getResources().getString(R.string.success_delete_favorite), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TvShowDetailActivity.this, getResources().getString(R.string.failed__delete_favorite), Toast.LENGTH_SHORT).show();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
