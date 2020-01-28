package com.example.submission3.viewModel;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.submission3.model.TVShow;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TvShowViewModel extends ViewModel {
    private static final String API_KEY ="eb2a01772536fc76e0d34b1f3ee9eff3";
    private MutableLiveData<ArrayList<TVShow>> listTvShow = new MutableLiveData<>();

    public void setTVShow (final String tvShow){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TVShow> listItem = new ArrayList<>();

        String url ="https://api.themoviedb.org/3/tv/top_rated?api_key="+ API_KEY+"&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list =responseObject.getJSONArray("results");

                    for (int i = 0; i<list.length();i++){
                        JSONObject tv = list.getJSONObject(i);
                        TVShow tvShowItem = new TVShow(tv);
                        listItem.add(tvShowItem);
                    }
                    listTvShow.postValue(listItem);
                }catch(Exception e){
                    Log.d("Exception",e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure",error.getMessage());
            }
        });
    }
    public LiveData<ArrayList<TVShow>> getTvShow(){
        return listTvShow;}
}
