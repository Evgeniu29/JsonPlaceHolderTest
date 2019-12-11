package com.paad.testtask.photolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paad.testtask.R;
import com.paad.testtask.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;


public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.MyViewHolder> {

    private List<Photo> photoList;
    private OnPhotoClickListener listener;
    Context context;


    public PhotoAdapter(Context  context, List<Photo> photoList, OnPhotoClickListener listener) {
        this.context = context;
        this.photoList = photoList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View result = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
        return new MyViewHolder(result);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.setIsRecyclable(false);

        holder.onBind(photoList.get(position));

    }

    @Override
    public int getItemCount() {

        if(photoList == null)
            return 0;
        return photoList.size();
    }

    public void setListData(List<Photo> photoList) {
        photoList = photoList;
        notifyDataSetChanged();
    }




    class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout item;

        private TextView albumId;

        private TextView photoId;

        private TextView title;

        private ImageView photoImage;


        MyViewHolder(View itemView) {

            super(itemView);

            item = itemView.findViewById(R.id.photo_item);

            albumId = itemView.findViewById(R.id.albumId);

            photoId = itemView.findViewById(R.id.photoId);

            title = itemView.findViewById(R.id.title);

            photoImage = itemView.findViewById(R.id.photo);




        }

        public void onBind(final Photo photo) {

            String imageUrl = photo.getUrl();

            albumId.setText(photo.getAlbumId().toString());

            photoId.setText(photo.getId().toString());

            title.setText(photo.getTitle());

            Picasso.with(context).load(imageUrl).into(photoImage);


        }




    }

    public List<Photo> getArrayList(){
        return this.photoList;
    }





}
