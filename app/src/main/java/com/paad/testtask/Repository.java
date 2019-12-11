package com.paad.testtask;


import android.content.Context;

import com.paad.testtask.model.Album;
import com.paad.testtask.model.Comment;
import com.paad.testtask.model.Photo;
import com.paad.testtask.model.Post;
import com.paad.testtask.model.User;
import com.paad.testtask.remote.ApiClient;
import com.paad.testtask.remote.ApiResult;
import com.paad.testtask.remote.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Repository {

    private ApiService service;

    private Context  context;

    public Repository(Context  context) {

        this.context = context;


        service = ApiClient.getRetrofitInstance().create(ApiService.class);

    }

    public void getUsers(final ApiResult<List<User>> callback) {

        Call<List<User>> call = service.getAllUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFail();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

                callback.onFail();
            }

        });
    }

    public void getPost(final ApiResult<List<Post>> callback) {

        Call<List<Post>> call = service.getAllPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFail();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                callback.onFail();
            }
        });
    }


    public void getComment(int postId, final ApiResult<List<Comment>> callback) {


        Call<List<Comment>> call = service.getAllCommentsByPostId(postId);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFail();
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

                callback.onFail();
            }
        });
    }

    public void getAlbum(final ApiResult<List<Album>> callback) {

        Call<List<Album>> call = service.getAlbums();

        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {

                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFail();
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

                callback.onFail();
            }
        });
    }



    public void getPhoto(long albumId, final ApiResult<List<Photo>> callback) {


        Call<List<Photo>> call = service.getAlbumPhotos(albumId);

        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {

                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());

                } else {
                    callback.onFail();
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

                callback.onFail();
            }
        });
    }



}
