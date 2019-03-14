package com.mgh.filmmaster.Models;

import android.os.Parcel;
import android.os.Parcelable;



public class ActorModel implements Parcelable{
    private int id;
    private String name;
    private String characterName;
    private String picUrl;
    public ActorModel(int id, String name, String characterName, String picUrl) {
        this.id = id;
        this.name = name;
        this.characterName = characterName;
        this.picUrl = picUrl;
    }

    protected ActorModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        characterName = in.readString();
        picUrl = in.readString();
    }

    public static final Creator<ActorModel> CREATOR = new Creator<ActorModel>() {
        @Override
        public ActorModel createFromParcel(Parcel in) {
            return new ActorModel(in);
        }

        @Override
        public ActorModel[] newArray(int size) {
            return new ActorModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(characterName);
        dest.writeString(picUrl);
    }
}