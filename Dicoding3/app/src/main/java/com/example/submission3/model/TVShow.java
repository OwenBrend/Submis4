package com.example.submission3.model;


import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class TVShow implements Parcelable {
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    private String photo;
    private String title;
    private String release;
    private String count;
    private Double vote_average;
    private Double popularity;
    private String detail;
    private String language;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private  Integer id;
    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    private String banner;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }


    public TVShow() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(photo);
        dest.writeString(banner);
        dest.writeString(title);
        dest.writeString(release);
        dest.writeString(count);
        dest.writeValue(vote_average);
        dest.writeValue(popularity);
        dest.writeString(detail);
        dest.writeString(language);
        dest.writeInt(id);
    }

    public TVShow(JSONObject object) {
        try {
            Integer id = object.getInt("id");
            String vote_count = object.getString("vote_count");
            Double vote_average = object.getDouble("vote_average");
            String title = object.getString("name");
            String poster_path = object.getString("poster_path");
            String first_air_date = object.getString("first_air_date");
            String overview = object.getString("overview");
            Double popularity = object.getDouble("popularity");
            String language = object.getString("original_language");
            String backdrop_path = object.getString("backdrop_path");


            this.count = vote_count;
            this.vote_average = vote_average;
            this.title = title;
            this.photo = poster_path;
            this.banner = backdrop_path;
            this.release = first_air_date;
            this.detail = overview;
            this.popularity= popularity;
            this.language = language;
            this.id= id;


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public int describeContents() {
        return 0;
    }


    public TVShow(Parcel in) {
        photo = in.readString();
        banner = in.readString();
        title = in.readString();
        release = in.readString();
        count = in.readString();
        vote_average= (Double) in.readValue(Double.class.getClassLoader());
        popularity = (Double) in.readValue(Double.class.getClassLoader());
        detail = in.readString();
        language = in.readString();
        id = in.readInt();
    }

    public static final Parcelable.Creator<TVShow> CREATOR = new Parcelable.Creator<TVShow>() {
        @Override
        public TVShow createFromParcel(Parcel in) {
            return new TVShow(in);
        }

        @Override
        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };

}
