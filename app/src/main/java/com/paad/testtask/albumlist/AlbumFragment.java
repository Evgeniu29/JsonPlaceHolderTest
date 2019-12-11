package com.paad.testtask.albumlist;


import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.paad.testtask.R;
import com.paad.testtask.Singleton;
import com.paad.testtask.TransferBetweenFragments;
import com.paad.testtask.commentslist.CommentAdapter;
import com.paad.testtask.model.Album;
import com.paad.testtask.model.Post;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class AlbumFragment extends Fragment implements AlbumContract.View {

    private AlbumContract.Presenter presenter;
    TransferBetweenFragments transferBetweenFragments;
    private RecyclerView recyclerView;
    private Context context;
    public AlbumAdapter albumAdapter;




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_album, container, false);
    }

    public static AlbumFragment newInstance() {

        Bundle args = new Bundle();

        AlbumFragment fragment = new AlbumFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        presenter = new AlbumPresenter(this, (Application)this.context.getApplicationContext());

        recyclerView = view.findViewById(R.id.album_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);


        if (isNetworkAvailable()) {

            presenter.onLoadAlbum();
        }

        else {
            try {
                albumAdapter = new AlbumAdapter(this.context,presenter.getAllAlbums(), albumID -> {
                    transferBetweenFragments.goFromCommentToComment(albumID);

                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            recyclerView.setAdapter(albumAdapter);
        }



    }


    @Override
    public void showAlbumList(List<Album> albums) {

        if(context!= null) {

            presenter.onSaveAlbums(albums);



            AlbumAdapter adapter = new AlbumAdapter(context, albums, new OnAlbumClickListener() {

                @Override
                public void onAlbumClick(int albumID) {

                    transferBetweenFragments.goFromAlbumToAlbum(albumID);
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
