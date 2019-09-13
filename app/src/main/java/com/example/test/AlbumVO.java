package com.example.test;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class AlbumVO implements Parcelable {
    private String thumb;
    private String artist;
    private String date;
    private String type;
    private String title;
    private ArrayList<String> song;
    private String introduce;

    private AlbumVO(Parcel in) {
        thumb = in.readString();
        artist = in.readString();
        date = in.readString();
        type = in.readString();
        title = in.readString();
        song = in.createStringArrayList();
        introduce = in.readString();
    }

    public static final Creator<AlbumVO> CREATOR = new Creator<AlbumVO>() {
        @Override
        public AlbumVO createFromParcel(Parcel in) {
            return new AlbumVO(in);
        }

        @Override
        public AlbumVO[] newArray(int size) {
            return new AlbumVO[size];
        }
    };

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getSong() {
        return song;
    }

    public void setSong(ArrayList<String> song) {
        this.song = song;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Override
    public String toString() {
        return "AlbumVO{" +
                "thumb='" + thumb + '\'' +
                ", artist='" + artist + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", song=" + song +
                ", introduce='" + introduce + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(artist);
        dest.writeString(thumb);
        dest.writeString(date);
        dest.writeString(type);
        dest.writeList(song);
        dest.writeString(introduce);
    }
}
