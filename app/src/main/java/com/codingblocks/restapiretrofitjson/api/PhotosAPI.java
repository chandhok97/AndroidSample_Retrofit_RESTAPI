package com.codingblocks.restapiretrofitjson.api;

import com.codingblocks.restapiretrofitjson.models.Photo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rippy3402 on 04-07-2017.
 */

public interface PhotosAPI {
    @GET("/photos")  //1
    Call<ArrayList<Photo>> getPhotos();
    @GET("/photos/{id}")  //3
    Call<Photo> getPhotoById(
            @Path("id") int id
    );
    @GET("/photos")  //2
    Call<ArrayList<Photo>> getPhotosByAlbumId(
            @Query("albumId") int albumId
    );
}
