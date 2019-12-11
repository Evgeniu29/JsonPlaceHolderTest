package com.paad.testtask.photoRoom;



import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.paad.testtask.model.Comment;
import com.paad.testtask.model.Photo;


@Database(entities = {Photo.class}, version = 1)
public abstract class PhotoDatabase extends RoomDatabase {


        public abstract PhotoDao getPhotoDao();


}





