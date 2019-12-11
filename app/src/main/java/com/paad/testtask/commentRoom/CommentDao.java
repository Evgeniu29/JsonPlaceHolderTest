package com.paad.testtask.commentRoom;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.paad.testtask.model.Comment;

import java.util.List;

@Dao
public interface CommentDao {


    @Query("SELECT * FROM  comment")
    List<Comment> getComments();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insertAllComments(List<Comment> comments);

    @Query("DELETE FROM comment")
    void deleteAll();

}