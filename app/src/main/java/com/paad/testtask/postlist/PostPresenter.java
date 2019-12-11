package com.paad.testtask.postlist;

import android.app.Application;
import android.os.AsyncTask;

import com.paad.testtask.Repository;
import com.paad.testtask.model.Post;
import com.paad.testtask.postRoom.PostDao;
import com.paad.testtask.postRoom.PostDatabaseManager;
import com.paad.testtask.remote.ApiResult;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PostPresenter  implements PostContract.Presenter {

    private PostContract.View view;
    private Repository repository;
    private Application application;
    private PostDatabaseManager postManager;

    public PostPresenter(PostContract.View view, Application application) {
        this.view = view;
        this.application =  application;
        this.repository = new Repository(application);
        this.postManager = new PostDatabaseManager().getInstance();

    }

    @Override
    public void onLoadPost() {


        repository.getPost(new ApiResult<List<Post>>() {
            @Override
            public void onSuccess(List<Post> result) {

                view.showPostList(result);
            }

            @Override
            public void onFail() {
                view.showErrorMessage();
            }
        });
    }

    @Override
    public void onSavePosts(List<Post> posts) {

        new PostPresenter.insertAsyncTask(postManager.init(application).getPostDao()).execute(posts);

    }
    @Override
    public List<Post> getAllPosts() throws ExecutionException, InterruptedException {

        List<Post> posts  = new PostPresenter.GetPostsAsyncTask(postManager.init(application).getPostDao()).execute().get();


        return posts;
    }

    private static class insertAsyncTask extends AsyncTask<List<Post>, Void, Void> {
        private PostDao postDao;

        insertAsyncTask(PostDao _postDao) {
            postDao = _postDao;
        }

        @Override
        protected Void doInBackground(final List<Post>... params) {
            postDao.insertAllPosts(params[0]); // This line throws the exception
            return null;
        }
    }


    private class GetPostsAsyncTask extends AsyncTask<Void, Void, List<Post>>
    {
        private PostDao postDao;

        GetPostsAsyncTask(PostDao _postDao) {
            postDao = _postDao;
        }
        @Override
        protected List<Post> doInBackground(Void... url) {
            return postDao.getAllPosts();
        }
    }







}
