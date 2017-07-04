package com.codingblocks.restapiretrofitjson.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codingblocks.restapiretrofitjson.R;
import com.codingblocks.restapiretrofitjson.adapters.ThumbNailAdapter;

import com.codingblocks.restapiretrofitjson.api.PhotosAPI;
import com.codingblocks.restapiretrofitjson.models.Photo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ThumbNailUrlActivity extends AppCompatActivity {

    RecyclerView rvthumbnail;
    ArrayList<Photo> photoArrayList = new ArrayList<>();
    ThumbNailAdapter thumbNailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thumb_nail_url);
        rvthumbnail = (RecyclerView) findViewById(R.id.rvthumbnail);
        rvthumbnail.setLayoutManager(new LinearLayoutManager(this));
        final ThumbNailAdapter thumbNailsAdapter = (ThumbNailAdapter) new ThumbNailAdapter(this, new ArrayList<Photo>());
        rvthumbnail.setAdapter(thumbNailsAdapter);
        thumbNailsAdapter.setOnItemClickListener(new ThumbNailAdapter.OnItemClickListener() {
            @Override
            public void OnItemClicked(String title, String url) {
                Intent intent=new Intent(ThumbNailUrlActivity.this,UrlActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(
                        GsonConverterFactory.create()
                )
                .build();

        PhotosAPI photosAPI = retrofit.create(PhotosAPI.class);
        int albumid = getIntent().getIntExtra("albumId", -1);
        photosAPI.getPhotosByAlbumId(albumid).enqueue(new Callback<ArrayList<Photo>>() {
            @Override
            public void onResponse(Call<ArrayList<Photo>> call, Response<ArrayList<Photo>> response) {
                thumbNailsAdapter.updatethumbnaillists(response.body());
            }
            @Override
            public void onFailure(Call<ArrayList<Photo>> call, Throwable t) {

            }
        });
    }
}
