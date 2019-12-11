package com.paad.testtask.photoRoom;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.paad.testtask.model.Comment;
import com.paad.testtask.model.Photo;

import java.util.List;

@Dao
public interface PhotoDao {


    @Query("SELECT * FROM  photo")
    List<Photo> getPhotos();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insertAllPhotos(List<Photo> photos);

    @Query("DELETE FROM photo")
    void deleteAll();

}