package com.paad.testtask.postRoom;

import android.content.Context;

import androidx.room.Room;

public class PostDatabaseManager {

    private static PostDatabaseManager postManager;

    private PostDatabase postDatabase;


    public PostDatabase getPostDatabase(){
        return postDatabase;
    }

    public static PostDatabaseManager getInstance(){

        if (postManager == null) {
            postManager = new PostDatabaseManager();
        }

        return postManager;

    }

    public PostDatabase init(Context context){

        postDatabase = Room.databaseBuilder(context, PostDatabase.class, "post").build();

        return postDatabase;
    }

}
