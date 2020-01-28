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
import com.example.submission3.db.FavoriteHelper;
import com.example.submission3.model.Movie;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMoviesFragment extends Fragment {

    public static final String KEY_MOVIES = "movies";
    public ArrayList<Movie> listMovies = new ArrayList<>();
    public RecyclerView rvMovie;
    public ProgressBar progressBar;
    private MovieAdapter listMovieAdapter;
    private FavoriteHelper favoriteHelper;
    private Bundle saveState;

    public FavoriteMoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }
    @Override
    public void onStart() {
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovie.setHasFixedSize(true);
        progressBar.setVisibility(View.VISIBLE);
        favoriteHelper = FavoriteHelper.getInstance(getContext());
        favoriteHelper.open();

        listMovieAdapter = new MovieAdapter(getContext());
        rvMovie.setAdapter(listMovieAdapter);

        if (saveState == null) {
            listMovies.clear();
            listMovies.addAll(favoriteHelper.getAllFavorites());
            if (listMovies != null) {
                listMovieAdapter.setListFavorite(listMovies);
            } else {
                Toast.makeText(getContext(), getResources().getString(R.string.empty_data), Toast.LENGTH_SHORT).show();
            }
        } else {
            ArrayList<Movie> list = saveState.getParcelableArrayList(KEY_MOVIES);
            if (list != null) {
                listMovieAdapter.setListFavorite(list);
            }
        }
        super.onStart();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovie = view.findViewById(R.id.rv_category);
        progressBar = view.findViewById(R.id.progressBar);
        if (savedInstanceState != null) {
            saveState = savedInstanceState;
        }
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY_MOVIES, listMovieAdapter.getListMovie());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        favoriteHelper.close();
    }
}
