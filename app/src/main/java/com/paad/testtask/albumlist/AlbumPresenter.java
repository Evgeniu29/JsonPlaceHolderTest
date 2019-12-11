package com.paad.testtask.albumlist;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import com.paad.testtask.Repository;
import com.paad.testtask.albumRoom.AlbumDao;
import com.paad.testtask.albumRoom.AlbumDatabaseManager;
import com.paad.testtask.model.Album;
import com.paad.testtask.model.Post;
import com.paad.testtask.postRoom.PostDao;
import com.paad.testtask.postRoom.PostDatabaseManager;
import com.paad.testtask.postlist.PostContract;
import com.paad.testtask.postlist.PostPresenter;
import com.paad.testtask.remote.ApiResult;
import com.paad.testtask.room.UserDao;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AlbumPresenter implements AlbumContract.Presenter {

    private AlbumContract.View view;
    private Repository repository;
    private Application application;
    private AlbumDatabaseManager albumManager;


    public AlbumPresenter(AlbumContract.View view, Application application) {
        this.view = view;
        this.application = application;
        repository = new Repository(application);
        this.albumManager = new AlbumDatabaseManager().getInstance();
    }

    @Override
    public void onLoadAlbum() {


        repository.getAlbum(new ApiResult<List<Album>>() {
            @Override
            public void onSuccess(List<Album> result) {

                view.showAlbumList(result);
            }

            @Override
            public void onFail() {
                view.showErrorMessage();
            }
        });
    }


    @Override
    public void onSaveAlbums(List<Album> albums) {

        new AlbumPresenter.insertAsyncTask(albumManager.init(application).getAlbumDao()).execute(albums);

    }
    @Override
    public List<Album> getAllAlbums() throws ExecutionException, InterruptedException {

        List<Album> albums  = new AlbumPresenter.GetAlbumsAsyncTask(albumManager.init(application).getAlbumDao()).execute().get();


        return albums;
    }

    private static class insertAsyncTask extends AsyncTask<List<Album>, Void, Void> {
        private AlbumDao albumDao;

        insertAsyncTask(AlbumDao _albumDao) {
            albumDao = _albumDao;
        }

        @Override
        protected Void doInBackground(final List<Album>... params) {
            albumDao.insertAllAlbums(params[0]); // This line throws the exception
            return null;
        }
    }


    private class GetAlbumsAsyncTask extends AsyncTask<Void, Void, List<Album>>
    {
        private AlbumDao albumDao;

        GetAlbumsAsyncTask(AlbumDao _albumDao) {
            albumDao = _albumDao;
        }
        @Override
        protected List<Album> doInBackground(Void... url) {
            return albumDao.getAllAlbums();
        }
    }



}
