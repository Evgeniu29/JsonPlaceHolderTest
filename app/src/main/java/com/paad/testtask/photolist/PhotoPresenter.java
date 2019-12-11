package com.paad.testtask.photolist;

import android.app.Application;
import android.os.AsyncTask;

import com.paad.testtask.Repository;
import com.paad.testtask.model.Photo;
import com.paad.testtask.photoRoom.PhotoDao;
import com.paad.testtask.photoRoom.PhotoDatabaseManager;

import com.paad.testtask.remote.ApiResult;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PhotoPresenter implements PhotoContract.Presenter {

    private PhotoContract.View view;
    private Repository repository;
    private Application application;
    private PhotoDatabaseManager photoManager;


    public PhotoPresenter(PhotoContract.View view, Application application) {
        this.view = view;
        this.application =  application;
        repository = new Repository(application);
        this.photoManager = new PhotoDatabaseManager().getInstance();

    }

    public void onLoadPhoto(long photoId) {

        repository.getPhoto(photoId, new ApiResult<List<Photo>>() {
            @Override
            public void onSuccess(List<Photo> result) {

                view.showPhotoList(result);
            }

            @Override
            public void onFail() {
                view.showErrorMessage();
            }
        });
    }




    @Override
    public void onSavePhotos(List<Photo> photos) {

        new PhotoPresenter.insertAsyncTask(photoManager.init(application).getPhotoDao()).execute(photos);

    }
    @Override
    public List<Photo> getAllPhotos() throws ExecutionException, InterruptedException {

        List<Photo> photos  = new PhotoPresenter.GetPostsAsyncTask(photoManager.init(application).getPhotoDao()).execute().get();


        return photos;
    }

    private static class insertAsyncTask extends AsyncTask<List<Photo>, Void, Void> {
        private PhotoDao photoDao;

        insertAsyncTask(PhotoDao _photoDao) {
            photoDao = _photoDao;
        }

        @Override
        protected Void doInBackground(final List<Photo>... params) {
            photoDao.insertAllPhotos(params[0]); // This line throws the exception
            return null;
        }
    }


    private class GetPostsAsyncTask extends AsyncTask<Void, Void, List<Photo>>
    {
        private PhotoDao photoDao;

        GetPostsAsyncTask(PhotoDao _photoDao) {
            photoDao = _photoDao;
        }
        @Override
        protected List<Photo> doInBackground(Void... url) {
            return photoDao.getPhotos();
        }
    }




}
