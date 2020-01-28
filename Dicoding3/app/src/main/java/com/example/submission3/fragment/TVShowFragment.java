package com.example.submission3.fragment;




import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.submission3.adapter.TvShowAdapter;
import com.example.submission3.R;
import com.example.submission3.model.TVShow;
import com.example.submission3.viewModel.TvShowViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowFragment extends Fragment {
    private TvShowAdapter adapter;
    private ProgressBar progressBar;
    private TvShowViewModel tvShowViewModel;
    public TVShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        adapter = new TvShowAdapter();
        View view = inflater.inflate(R.layout.fragment_tvshow, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rc_tvShow);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progressBar);

        tvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel.class);
        tvShowViewModel.getTvShow().observe(this, getTvShow);
        tvShowViewModel.setTVShow("EXTRA_TV_SHOW");


        showLoading(true);

        return view;
    }
    private Observer<ArrayList<TVShow>> getTvShow = new Observer<ArrayList<TVShow>>() {
        @Override
        public void onChanged(ArrayList<TVShow> tvShows) {
            if (tvShows != null) {
                adapter.refill(tvShows);
            }

            showLoading(false);
        }
    };

    private void showLoading ( boolean b){
        if (b) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}
