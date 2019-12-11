package com.paad.testtask.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
@Entity(tableName = "album")
public class Album implements Serializable {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Long id;


    @ColumnInfo(name = "userId")
    @SerializedName("userId")
    @Expose
    private Long userId;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    @Expose
    private String title;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}