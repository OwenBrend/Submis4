package com.example.submission3.fragment;



import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviderKt;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.submission3.adapter.MovieAdapter;
import com.example.submission3.model.Movie;
import com.example.submission3.viewModel.MoviesViewModel;
import com.example.submission3.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {
    private MovieAdapter adapter;
    private ProgressBar progressBar;
    private MoviesViewModel moviesViewModel;

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        adapter = new MovieAdapter();
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progressBar);

        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesViewModel.getMovie().observe(this, getMovie);
        moviesViewModel.setMovies("extra_movie");

        showLoading(true);
        return view;
    }
    private Observer<ArrayList<Movie>> getMovie = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movies) {
            if (movies != null) {
                adapter.setData(movies);
            }
            showLoading(false);
        }
    };

    private void showLoading(boolean b) {
        if(b){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }
}

