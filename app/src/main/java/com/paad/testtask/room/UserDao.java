package com.paad.testtask.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.paad.testtask.model.User;

import java.util.List;


@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllUsers(List<User> users);


    @Query("SELECT * FROM user")
   List<User> getAllUsers();

    //Delete All
    @Query("DELETE FROM user")
    void deleteAll();

}