package com.example.submission3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import com.example.submission3.fragment.FavoriteFragment;
import com.example.submission3.fragment.MoviesFragment;
import com.example.submission3.fragment.TVShowFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private final String STATE_TITLE = "state_string";
    public String title = "Movie";

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = (item)->{
        Fragment fragment;
        switch (item.getItemId()){
            case  R.id.navigation_movie:
                fragment = new MoviesFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_layout,fragment,fragment.getClass().getSimpleName())
                        .commit();
                return true;
            case R.id.navigation_tvShow:
                fragment = new TVShowFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_layout,fragment,fragment.getClass().getSimpleName())
                        .commit();
                return true;

            case R.id.navigation_favorite:
                fragment = new FavoriteFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_layout,fragment,fragment.getClass().getSimpleName())
                        .commit();
                return true;
        }
        return false;
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_TITLE, title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        if (savedInstanceState == null){
            navigationView.setSelectedItemId(R.id.navigation_movie);
        }
        else{
            title= savedInstanceState.getString(STATE_TITLE);
            setActionBarTitle(title);
        }
    }
    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.action_change_settings){
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
