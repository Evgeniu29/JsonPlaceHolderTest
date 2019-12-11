package com.paad.testtask.commentRoom;



import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.paad.testtask.model.Comment;
import com.paad.testtask.model.Post;


@Database(entities = {Comment.class}, version = 1)
public abstract class CommentDatabase extends RoomDatabase {


        public abstract CommentDao getCommentDao();


}





