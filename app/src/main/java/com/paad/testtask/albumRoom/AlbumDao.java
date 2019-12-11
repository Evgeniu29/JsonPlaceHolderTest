package com.paad.testtask.albumRoom;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.paad.testtask.model.Album;
import com.paad.testtask.model.Comment;

import java.util.List;

@Dao
public interface AlbumDao {


    @Query("SELECT * FROM  album")
    List<Album> getAllAlbums();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insertAllAlbums(List<Album> albums);

    @Query("DELETE FROM album")
    void deleteAll();

}