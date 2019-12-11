package com.paad.testtask.albumRoom;



import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.paad.testtask.model.Album;
import com.paad.testtask.model.Comment;


@Database(entities = {Album.class}, version = 1)
public abstract class AlbumDatabase extends RoomDatabase {


        public abstract AlbumDao getAlbumDao();


}





