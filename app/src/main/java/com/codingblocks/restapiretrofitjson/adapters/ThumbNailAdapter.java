package com.codingblocks.restapiretrofitjson.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codingblocks.restapiretrofitjson.R;
import com.codingblocks.restapiretrofitjson.models.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by rippy3402 on 04-07-2017.
 */

public class ThumbNailAdapter extends RecyclerView.Adapter<ThumbNailAdapter.ThumbNailsViewHolder> {
    private Context context;
    private ArrayList<Photo> photoArrayList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        public void OnItemClicked(String title, String url);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ThumbNailAdapter(Context context, ArrayList<Photo> photoArrayList) {
        this.context = context;
        this.photoArrayList = photoArrayList;
    }

    public void updatethumbnaillists(ArrayList<Photo> photoArrayList) {
        this.photoArrayList = photoArrayList;
        notifyDataSetChanged();
    }

    @Override
    public ThumbNailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li =
                (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.list_thumbnail, parent, false);
        return new ThumbNailsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ThumbNailsViewHolder holder, int position) {
        final Photo photo = photoArrayList.get(position);
        String url = photo.getThumbnailUrl();
        Picasso.with(context).load(url).into(holder.imageView);
        holder.tvthumb.setText(photo.getTitle());
        holder.rootview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.OnItemClicked(photo.getTitle(), photo.getUrl());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return photoArrayList.size();
    }

    public class ThumbNailsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvthumb;
        View rootview;

        public ThumbNailsViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.ivthumb);
            tvthumb = (TextView) itemView.findViewById(R.id.tvthumb);
            rootview = itemView;
        }
    }
}
