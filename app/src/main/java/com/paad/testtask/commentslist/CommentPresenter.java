package com.paad.testtask.commentslist;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import com.paad.testtask.Repository;
import com.paad.testtask.commentRoom.CommentDao;
import com.paad.testtask.commentRoom.CommentDatabaseManager;
import com.paad.testtask.model.Comment;
import com.paad.testtask.remote.ApiResult;

import java.util.List;
import java.util.concurrent.ExecutionException;

public  class CommentPresenter  implements CommentContract.Presenter {

    private CommentContract.View view;
    private Repository repository;
    private Application  application;
    private CommentDatabaseManager commentManager;


    public CommentPresenter(CommentContract.View view, Application application) {
        this.view = view;
        this.application = application;
        this.repository = new Repository(application);
        this.commentManager = new CommentDatabaseManager().getInstance();
    }

    public void onLoadComment(int userId) {


        repository.getComment(userId, new ApiResult<List<Comment>>() {
            @Override
            public void onSuccess(List<Comment> result) {

                view.showCommentsList(result);
            }

            @Override
            public void onFail() {
                view.showErrorMessage();
            }
        });
    }

    @Override
    public void onSaveComments(List<Comment> comments) {

        new CommentPresenter.insertAsyncTask(commentManager.init(application).getCommentDao()).execute(comments);

    }
    @Override
    public List<Comment> getAllComments() throws ExecutionException, InterruptedException {

        List<Comment> comments = new CommentPresenter.GetCommentsAsyncTask(commentManager.init(application).getCommentDao()).execute().get();


        return comments;
    }

    private static class insertAsyncTask extends AsyncTask<List<Comment>, Void, Void> {
        private CommentDao commentDao;

        insertAsyncTask(CommentDao _commentDao) {
            commentDao = _commentDao;
        }

        @Override
        protected Void doInBackground(final List<Comment>... params) {
            commentDao.insertAllComments(params[0]); // This line throws the exception
            return null;
        }
    }


    private class GetCommentsAsyncTask extends AsyncTask<Void, Void, List<Comment>>
    {
        private CommentDao commentDao;

        GetCommentsAsyncTask(CommentDao _commentDao) {
            commentDao = _commentDao;
        }
        @Override
        protected List<Comment> doInBackground(Void... url) {
            return commentDao.getComments();
        }
    }

}
