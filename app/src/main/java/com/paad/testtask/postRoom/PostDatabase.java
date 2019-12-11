package com.paad.testtask.postRoom;



import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.paad.testtask.model.Comment;
import com.paad.testtask.model.Post;
import com.paad.testtask.model.User;


@Database(entities = {Post.class}, version = 1)
public abstract class PostDatabase extends RoomDatabase {


        public abstract PostDao getPostDao();

}





