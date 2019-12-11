package com.paad.testtask.albumlist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paad.testtask.CommentActivity;
import com.paad.testtask.PhotoActivity;
import com.paad.testtask.R;
import com.paad.testtask.model.Album;
import com.paad.testtask.model.Post;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;


public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {

    private List<Album> albumList;
    private OnAlbumClickListener listener;
    Context context;
    String name;
    int id;

    public AlbumAdapter(Context  context, List<Album> albumList, OnAlbumClickListener listener) {
        this.context = context;
        this.albumList = albumList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View result = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);
        return new MyViewHolder(result);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.setIsRecyclable(false);

        holder.onBind(albumList.get(position));

    }

    @Override
    public int getItemCount() {

        if(albumList == null)
            return 0;
        return albumList.size();
    }

    public void setListData(List<Album> albumList) {
        albumList = albumList;
        notifyDataSetChanged();
    }




    class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout item;

        private TextView userId;

        private TextView albums_albumId;

        private TextView albumTitle;


        MyViewHolder(View itemView) {

            super(itemView);

            item = itemView.findViewById(R.id.album_item);

            userId = itemView.findViewById(R.id.userId);

            albums_albumId = itemView.findViewById(R.id.albums_albumId);

            albumTitle = itemView.findViewById(R.id.albumTitle);

        }

        public void onBind(final Album album) {


            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, PhotoActivity.class);

                    intent.putExtra("albumId", album.getId());

                    startActivity(context, intent, null);

                }
            });

            userId.setText(album.getUserId().toString());


            albums_albumId.setText(album.getId().toString());

            albumTitle.setText(album.getTitle());



        }




    }

    public List<Album> getArrayList(){
        return this.albumList;
    }





}
