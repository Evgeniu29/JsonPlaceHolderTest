package com.paad.testtask.photoRoom;

import android.content.Context;

import androidx.room.Room;

public class PhotoDatabaseManager {

    private static PhotoDatabaseManager photoManager;

    private PhotoDatabase photoDatabase;


    public PhotoDatabase getPhotoDatabase(){
        return photoDatabase;
    }

    public static PhotoDatabaseManager getInstance(){

        if (photoManager == null) {
            photoManager = new PhotoDatabaseManager();
        }

        return photoManager;

    }

    public PhotoDatabase init(Context context){

        photoDatabase = Room.databaseBuilder(context, PhotoDatabase.class, "photo").build();

        return photoDatabase;
    }

}
