package com.paad.testtask.albumRoom;

import android.content.Context;

import androidx.room.Room;

public class AlbumDatabaseManager {

    private static AlbumDatabaseManager albumManager;

    private AlbumDatabase albumDatabase;


    public AlbumDatabase getDatabase(){
        return  albumDatabase;
    }

    public static AlbumDatabaseManager getInstance(){

        if (albumManager == null) {
            albumManager = new AlbumDatabaseManager();
        }

        return albumManager;

    }

    public AlbumDatabase init(Context context){

        albumDatabase = Room.databaseBuilder(context, AlbumDatabase.class, "album").build();

        return albumDatabase;
    }

}
