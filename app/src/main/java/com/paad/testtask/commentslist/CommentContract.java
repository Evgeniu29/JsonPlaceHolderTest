package com.paad.testtask.commentslist;



import com.paad.testtask.model.Comment;
import com.paad.testtask.model.Post;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface CommentContract {

    interface View {


        void showCommentsList(List<Comment> comments);
        void showErrorMessage();

    }

    interface Presenter {

        void onLoadComment(int idUser);

        void onSaveComments(List<Comment> comments);

        List<Comment> getAllComments() throws ExecutionException, InterruptedException;
    }
}
