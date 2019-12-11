package com.paad.testtask.photolist;


import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.paad.testtask.R;
import com.paad.testtask.TransferBetweenFragments;
import com.paad.testtask.commentslist.CommentAdapter;
import com.paad.testtask.model.Photo;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class PhotoFragment extends Fragment implements PhotoContract.View {

    private PhotoContract.Presenter presenter;
    TransferBetweenFragments transferBetweenFragments;
    private RecyclerView recyclerView;
    private Context context;
    private  PhotoAdapter photoAdapter;
    long photoId;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.context = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_album, container, false);
    }

    public PhotoFragment(long photoId){

        this.photoId = photoId;
    }


    public PhotoFragment newInstance() {

        Bundle args = new Bundle();

        PhotoFragment fragment = new PhotoFragment(photoId);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new PhotoPresenter(this, (Application)this.context.getApplicationContext());

        recyclerView = view.findViewById(R.id.album_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);



        if (isNetworkAvailable()) {

            presenter.onLoadPhoto(photoId);
        }

        else {
            try {
                photoAdapter = new PhotoAdapter(this.context, presenter.getAllPhotos(),  photoId-> {
                    transferBetweenFragments.goFromPhotoToPhoto(photoId);

                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            recyclerView.setAdapter(photoAdapter);
        }

    }


    public void showPhotoList(List<Photo> photos) {

        if(context!= null) {

            presenter.onSavePhotos(photos);

            PhotoAdapter adapter = new PhotoAdapter(context, photos, new OnPhotoClickListener() {

                @Override
                public void onPhotoClick(int photoID) {

                    transferBetweenFragments.goFromCommentToComment(photoID);

                }
            });

            recyclerView.setAdapter(adapter);

        }
    }



    @Override
    public void showErrorMessage() {

        Toast.makeText(this.getContext(),"Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager manager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;

        if (networkInfo != null && networkInfo.isConnected()) {
            // Network is present and connected
            isAvailable = true;
        }

        return isAvailable;
    }

}
