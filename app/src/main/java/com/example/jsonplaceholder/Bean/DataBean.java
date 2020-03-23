package com.example.jsonplaceholder.Bean;

import android.os.Parcel;
import android.os.Parcelable;

public class DataBean implements Parcelable {

    /**
     * albumId : 1
     * id : 1
     * title : accusamus beatae ad facilis cum similique qui sunt
     * url : https://via.placeholder.com/600/92c952
     * thumbnailUrl : https://via.placeholder.com/150/92c952
     */

    private String albumId;
    private String id;
    private String title;
    private String url;
    private String thumbnailUrl;

    protected DataBean(Parcel in) {
        albumId = in.readString();
        id = in.readString();
        title = in.readString();
        url = in.readString();
        thumbnailUrl = in.readString();
    }

    public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
        @Override
        public DataBean createFromParcel(Parcel in) {
            return new DataBean(in);
        }

        @Override
        public DataBean[] newArray(int size) {
            return new DataBean[size];
        }
    };

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(albumId);
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(url);
        parcel.writeString(thumbnailUrl);
    }
}
