package com.paad.testtask.commentRoom;

import android.content.Context;

import androidx.room.Room;

public class CommentDatabaseManager {

    private static CommentDatabaseManager commentManager;

    private CommentDatabase commentDatabase;


    public CommentDatabase getCommentDatabase(){
        return commentDatabase;
    }

    public static CommentDatabaseManager getInstance(){

        if (commentManager == null) {
            commentManager = new CommentDatabaseManager();
        }

        return commentManager;

    }

    public CommentDatabase init(Context context){

        commentDatabase = Room.databaseBuilder(context, CommentDatabase.class, "comment").build();

        return commentDatabase;
    }

}
