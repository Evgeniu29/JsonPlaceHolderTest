package com.paad.testtask.postRoom;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.paad.testtask.model.Comment;
import com.paad.testtask.model.Post;
import com.paad.testtask.model.User;

import java.util.List;


@Dao
public interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllPosts(List<Post> posts);


    @Query("SELECT * FROM post")
   List<Post> getAllPosts();

    //Delete All
    @Query("DELETE FROM post")
    void deleteAll();



}