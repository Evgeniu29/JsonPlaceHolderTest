package com.paad.testtask.postlist;



import com.paad.testtask.model.Post;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface PostContract {

    interface View {


        void showPostList(List<Post> posts);
        void showErrorMessage();
    }

    interface Presenter {

        void onLoadPost();

        void onSavePosts(List<Post> posts);

        List<Post> getAllPosts() throws ExecutionException, InterruptedException;
    }
}
