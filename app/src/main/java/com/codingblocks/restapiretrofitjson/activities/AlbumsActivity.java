package com.codingblocks.restapiretrofitjson.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codingblocks.restapiretrofitjson.R;
import com.codingblocks.restapiretrofitjson.adapters.AlbumsAdapter;
import com.codingblocks.restapiretrofitjson.api.PhotosAPI;
import com.codingblocks.restapiretrofitjson.models.Photo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumsActivity extends AppCompatActivity {

    RecyclerView rvAlbums;
    ArrayList<Photo> photoArrayList=new ArrayList<>();
    AlbumsAdapter albumsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        rvAlbums = (RecyclerView) findViewById(R.id.rvAlbumList);
        rvAlbums.setLayoutManager(new LinearLayoutManager(this));
        albumsAdapter=(AlbumsAdapter) new AlbumsAdapter(this,new ArrayList<Photo>());
        rvAlbums.setAdapter(albumsAdapter);
        albumsAdapter.setOnItemClickListener(new AlbumsAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int pos) {
                Intent album=new Intent(AlbumsActivity.this,ThumbNailUrlActivity.class);
                album.putExtra("albumId",pos);
                startActivity(album);
            }
        });
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final PhotosAPI photosAPI = retrofit.create(PhotosAPI.class);
        photosAPI.getPhotos().enqueue(new Callback<ArrayList<Photo>>() {
            @Override
            public void onResponse(Call<ArrayList<Photo>> call, Response<ArrayList<Photo>> response) {
                albumsAdapter.updatePhotoLists(response.body());
            }
            @Override
            public void onFailure(Call<ArrayList<Photo>> call, Throwable t) {

            }
        });
    }
}
