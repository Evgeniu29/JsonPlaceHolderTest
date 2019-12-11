package com.paad.testtask.room;



import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.paad.testtask.model.User;


@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {


        public abstract UserDao getUserDao();


}





