package com.codingblocks.restapiretrofitjson.adapters;

/**
 * Created by rippy3402 on 04-07-2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;


import com.codingblocks.restapiretrofitjson.R;
import com.codingblocks.restapiretrofitjson.models.Photo;

import java.util.ArrayList;
public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder> {
    private Context context;
    private ArrayList<Photo> photoArrayList;
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void onItemClicked(int pos);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public AlbumsAdapter(Context context,ArrayList<Photo> photoArrayList) {
        this.context = context;
        this.photoArrayList=photoArrayList;
    }
    public void updatePhotoLists(ArrayList<Photo> photoArrayList){
        this.photoArrayList=photoArrayList;
        notifyDataSetChanged();
    }
    @Override
    public AlbumsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li =
                (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.list_albums, parent, false);

        return new AlbumsViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(AlbumsViewHolder holder, int position) {
        final  Photo photo=photoArrayList.get(position);
        holder.tvAlbumsList.setText(photo.getTitle());
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null)
                {
                    onItemClickListener.onItemClicked(photo.getAlbumId());
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return photoArrayList.size();
    }

    public class AlbumsViewHolder extends RecyclerView.ViewHolder{
        TextView tvAlbumsList;
        View rootView;
        public AlbumsViewHolder(View itemView) {
            super(itemView);
            tvAlbumsList=(TextView) itemView.findViewById(R.id.tvAlbumLists);
            rootView=itemView;
        }
    }
}


