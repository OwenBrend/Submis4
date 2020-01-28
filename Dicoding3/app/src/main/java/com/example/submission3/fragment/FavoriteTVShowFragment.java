package com.example.submission3.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.submission3.R;
import com.example.submission3.adapter.MovieAdapter;
import com.example.submission3.adapter.TvShowAdapter;
import com.example.submission3.db.FavoriteHelper;
import com.example.submission3.model.TVShow;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTVShowFragment extends Fragment {
    public static final String KEY_TVMOVIES = "tvmovies";
    public ArrayList<TVShow> listTVMovies = new ArrayList<TVShow>();
    public RecyclerView rvTVMovie;
    public ProgressBar progressBar;
    private TvShowAdapter tvShowAdapter;
    private FavoriteHelper favoriteHelper;
    private Bundle saveState;
    private Object TvShowAdapter;

    public FavoriteTVShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onStart() {
        rvTVMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTVMovie.setHasFixedSize(true);
        progressBar.setVisibility(View.VISIBLE);
        favoriteHelper = FavoriteHelper.getInstance(getContext());
        favoriteHelper.open();

        tvShowAdapter = new TvShowAdapter(getContext());
        rvTVMovie.setAdapter(tvShowAdapter);

        if (saveState == null) {
            listTVMovies.clear();
            listTVMovies.addAll(favoriteHelper.getAllFavorites2());
            if (listTVMovies != null) {
                tvShowAdapter.refill(listTVMovies);
            } else {
                Toast.makeText(getContext(), getResources().getString(R.string.empty_data), Toast.LENGTH_SHORT).show();
            }
        } else {
            ArrayList<TVShow> list = saveState.getParcelableArrayList(KEY_TVMOVIES);
            if (list != null) {
                tvShowAdapter.refill(list);
            }
        }
        super.onStart();
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTVMovie = view.findViewById(R.id.rv_category);
        progressBar = view.findViewById(R.id.progressBar);
        if (savedInstanceState != null) {
            saveState = savedInstanceState;
        }
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY_TVMOVIES, tvShowAdapter.getTvData());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        favoriteHelper.close();
    }

}
