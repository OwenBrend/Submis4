package com.example.submission3.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.submission3.fragment.FavoriteMoviesFragment;
import com.example.submission3.fragment.FavoriteTVShowFragment;

public class FavoriteTabAdapter extends FragmentPagerAdapter {
    public FavoriteTabAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position ==0){
            return new FavoriteMoviesFragment();
        }
        return new FavoriteTVShowFragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        if(position ==0){
            return "Movie";
        }
        return "TV Show";
    }


    @Override
    public int getCount() {
        return 2;
    }
}
