package com.codingblocks.restapiretrofitjson.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.codingblocks.restapiretrofitjson.R;
import com.squareup.picasso.Picasso;


public class UrlActivity extends AppCompatActivity {
    ImageView ivurl;
    TextView tvurl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);
        ivurl=(ImageView)findViewById(R.id.ivurl);
        tvurl=(TextView) findViewById(R.id.tvurl);
        String title=getIntent().getStringExtra("title");
        String url=getIntent().getStringExtra("url");
        Picasso.with(this).load(url).into(ivurl);
        tvurl.setText(title);
    }
}

