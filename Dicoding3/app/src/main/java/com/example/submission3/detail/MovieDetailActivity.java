package com.example.submission3.detail;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import com.google.android.material.appbar.CollapsingToolbarLayout;


public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";

    TextView tvDateReleased, tvVoteAverage, tvVoteCount, tvOverview, txtLanguage, tvPopularity;
    ImageView imgPhoto;
    private Movie movie;
    private FavoriteHelper favoriteHelper;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.colorTitle));
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.colorTitle));

        tvDateReleased = findViewById(R.id.txt_date);
        tvVoteCount = findViewById(R.id.tv_item_vote_count);
        tvVoteAverage = findViewById(R.id.tv_item_vote_average);
        tvOverview = findViewById(R.id.txt_detail);
        txtLanguage = findViewById(R.id.txt_language);
        tvPopularity = findViewById(R.id.popularity);
        imgPhoto = findViewById(R.id.img_photo);

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        if (movie != null) {
            getSupportActionBar().setTitle(movie.getTitle());

            progressBar = findViewById(R.id.progressDetailMovie);
            progressBar.setVisibility(View.VISIBLE);

//            String popularity = movie.getPopularity().toString();
            String vote_average = movie.getAverage().toString();
            tvDateReleased.setText(movie.getRelease());
            tvVoteCount.setText(movie.getCount());
            tvVoteAverage.setText(vote_average);
            tvOverview.setText(movie.getDetail());
            txtLanguage.setText(movie.getLanguage());
         //   tvPopularity.setText(popularity);

            Glide.with(getApplicationContext())
                    .load(BuildConfig.TMDB_URL_POSTER + movie.getPhoto())
                    .into(imgPhoto);
            progressBar.setVisibility(View.INVISIBLE);
        }


        favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());
        favoriteHelper.open();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (favoriteHelper.isExist(movie)) {
            getMenuInflater().inflate(R.menu.menu_already_favorite, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_add_favorite, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_favorite) {
            if (!favoriteHelper.isExist(movie)) {
                long result = favoriteHelper.insertFavorite(movie);
                if (result > 0) {
                    item.setIcon(R.drawable.ic_favorite_yellow_24dp);
                    Toast.makeText(MovieDetailActivity.this, getResources().getString(R.string.success_add_favorite), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MovieDetailActivity.this, getResources().getString(R.string.failed__add_favorite), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MovieDetailActivity.this, getResources().getString(R.string.favorite_is_exist), Toast.LENGTH_SHORT).show();
            }
        } else {
            if (item.getItemId() == R.id.action_delete_favorite) {
                int result = favoriteHelper.deleteFavorite(movie.getId());
                if (result > 0) {
                    item.setIcon(R.drawable.ic_favorite_white_24dp);
                    Toast.makeText(MovieDetailActivity.this, getResources().getString(R.string.success_delete_favorite), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MovieDetailActivity.this, getResources().getString(R.string.failed__delete_favorite), Toast.LENGTH_SHORT).show();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
