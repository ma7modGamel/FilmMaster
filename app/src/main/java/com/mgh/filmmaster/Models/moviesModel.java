package com.mgh.filmmaster.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;

public class moviesModel implements Parcelable {
    public static HashMap<Integer,String> Genres = new HashMap<>();
    private int id;
    private String title;
    private String poster_path;
    private String backdrop_path;
    private boolean adult;
    private String overview;
    private String release_date;
    private int  vote_count;
    private double  vote_average;
    private ArrayList<Integer> genresIds = new ArrayList<>();

    public moviesModel(int id, String title, String poster_path, String backdrop_path, boolean adult, String overview, String release_date, int vote_count, double vote_average) {
        this.id = id;
        this.title = title;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.adult = adult;
        this.overview = overview;
        this.release_date = release_date;
        this.vote_count = vote_count;
        this.vote_average = vote_average;
    }

    protected moviesModel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
        adult = in.readByte() != 0;
        overview = in.readString();
        release_date = in.readString();
        vote_count = in.readInt();
        vote_average = in.readDouble();
    }

    public static final Creator<moviesModel> CREATOR = new Creator<moviesModel>() {
        @Override
        public moviesModel createFromParcel(Parcel in) {
            return new moviesModel(in);
        }

        @Override
        public moviesModel[] newArray(int size) {
            return new moviesModel[size];
        }
    };

    public static HashMap<Integer, String> getGenres() {
        return Genres;
    }

    public static void setGenres(HashMap<Integer, String> genres) {
        Genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public ArrayList<Integer> getGenresIds() {
        return genresIds;
    }

    public void setGenresIds(ArrayList<Integer> genresIds) {
        this.genresIds = genresIds;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
        dest.writeByte((byte) (adult ? 1 : 0));
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeInt(vote_count);
        dest.writeDouble(vote_average);
    }
}
