package com.paad.testtask.remote;



import com.paad.testtask.model.Album;
import com.paad.testtask.model.Comment;
import com.paad.testtask.model.Photo;
import com.paad.testtask.model.Post;
import com.paad.testtask.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/users")
    Call<List<User>> getAllUsers();

    @GET("/posts")
    Call<List<Post>> getAllPosts();

    @GET("/albums")
    Call<List<Album>> getAlbums();

    @GET("photos")
    Call<List<Photo>> getAlbumPhotos(@Query("albumId") long albumId);

    @GET("comments")
    Call<List<Comment>>  getAllCommentsByPostId(@Query("postId") int postId);

}
