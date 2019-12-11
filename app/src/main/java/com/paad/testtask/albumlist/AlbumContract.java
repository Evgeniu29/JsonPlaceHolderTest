package com.paad.testtask.albumlist;



import com.paad.testtask.model.Album;
import com.paad.testtask.model.Comment;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface AlbumContract {

    interface View {


        void showAlbumList(List<Album> albums);
        void showErrorMessage();
    }

    interface Presenter {

        void onLoadAlbum();

        void onSaveAlbums(List<Album> albums);

        List<Album> getAllAlbums() throws ExecutionException, InterruptedException;
    }
}
