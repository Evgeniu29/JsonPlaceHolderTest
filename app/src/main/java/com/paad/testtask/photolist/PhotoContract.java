package com.paad.testtask.photolist;



import com.paad.testtask.model.Comment;
import com.paad.testtask.model.Photo;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface PhotoContract {

    interface View {


        void showPhotoList(List<Photo> photos);
        void showErrorMessage();
    }

    interface Presenter {

        void onLoadPhoto(long id);

        void onSavePhotos(List<Photo> photos);

        List<Photo> getAllPhotos() throws ExecutionException, InterruptedException;
    }
}
