package com.paad.testtask;


import com.paad.testtask.model.Album;
import com.paad.testtask.model.Comment;
import com.paad.testtask.model.Photo;
import com.paad.testtask.model.Post;
import com.paad.testtask.model.User;

import java.util.ArrayList;
import java.util.List;

public class Singleton {

    private static Singleton mInstance;
    private List<User> list = null;
    private List<Post> posts = null;
    private List<Comment> comments = null;
    private List<Album> albums = null;
    private List<Photo> photos = null;


    public static Singleton getInstance() {
        if (mInstance == null)
            mInstance = new Singleton();

        return mInstance;
    }

    private Singleton() {
        list = new ArrayList<User>();
        posts  = new ArrayList<Post>();
        comments = new ArrayList<Comment>();
        albums = new ArrayList<Album>();
        photos = new ArrayList<Photo>();
    }

    // retrieve array from anywhere
    public List<User> getArray() {
        return this.list;
    }

    public List<Post> getPostsArray() {
        return this.posts;
    }

    public List<Comment> getCommentsArray() {
        return this.comments;
    }

    public List<Album> getAlbumsArray(){ return this.albums;}


    //Add element to array
    public void addToArray(User user) {
        list.add(user);
    }

    public void addToPostsArray(Post post) {
        posts.add(post);
    }

    public void addToCommentsArray(Comment comment) {
        comments.add(comment);
    }

    public void addToAlbumsArray(Album album) {
        albums.add(album);
    }

    public void addToPhotosArray(Photo  photo) {
        photos.add(photo);
    }


}