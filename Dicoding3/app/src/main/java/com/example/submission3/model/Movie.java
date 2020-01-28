package com.example.submission3.model;


import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Movie implements Parcelable {
    private String photo;
    private String title;
    private String release;
    private String count;
    private Double vote_average;
    private Double popularity;
    private String detail;
    private String language;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }
    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    private String banner;


    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
    public double getVote_Average() {
        return vote_average;
    }

    public void setAverage(Double vote_average) {
        this.vote_average = vote_average;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDirector() {
        return count;
    }

    public void setDirector(String director) {
        this.count = director;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    private String rating;


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public Movie(JSONObject object) {
        try {
            Integer id = object.getInt("id");
            String vote_count = object.getString("vote_count");
            Double vote_average = object.getDouble("vote_average");
            String title = object.getString("title");
            String poster_path = object.getString("poster_path");
            String backdrop_path = object.getString("backdrop_path");
            String release_date = object.getString("release_date");
            String overview = object.getString("overview");
            Double popularity = object.getDouble("popularity");
            String language = object.getString("original_language");


            this.count = vote_count;
            this.vote_average = vote_average;
            this.title = title;
            this.photo = poster_path;
            this.banner = backdrop_path;
            this.release = release_date;
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(photo);
        dest.writeString(banner);
        dest.writeString(title);
        dest.writeString(release);
        dest.writeString(count);
        dest.writeValue(vote_average);
        dest.writeString(rating);
        dest.writeValue(popularity);
        dest.writeString(detail);
        dest.writeString(language);
        dest.writeInt(id);
    }
    public Movie(Parcel in) {
        photo = in.readString();
        banner = in.readString();
        title = in.readString();
        release = in.readString();
        count = in.readString();
        vote_average= (Double) in.readValue(Double.class.getClassLoader());
        rating = in.readString();
        popularity = (Double) in.readValue(Double.class.getClassLoader());
        detail = in.readString();
        language = in.readString();
        id= in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public Movie() {

    }

}
