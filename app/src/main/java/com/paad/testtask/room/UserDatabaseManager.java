package com.paad.testtask.room;

import android.content.Context;

import androidx.room.Room;

public class UserDatabaseManager {

    private static UserDatabaseManager databaseManager;

    private UserDatabase userDatabase;

    private UserDatabaseManager(){

    }

    public UserDatabase getUserDatabase(){
        return userDatabase;
    }

    public static UserDatabaseManager getInstance(){

        if (databaseManager == null) {
            databaseManager = new UserDatabaseManager();
        }

        return databaseManager;

    }

    public UserDatabase init(Context context){

        userDatabase = Room.databaseBuilder(context, UserDatabase.class, "user").build();

        return userDatabase;
    }

}
