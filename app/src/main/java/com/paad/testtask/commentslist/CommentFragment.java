package com.paad.testtask.commentslist;


import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.paad.testtask.R;
import com.paad.testtask.Singleton;
import com.paad.testtask.TransferBetweenFragments;
import com.paad.testtask.model.Comment;
import com.paad.testtask.postlist.PostAdapter;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class CommentFragment extends Fragment implements CommentContract.View {

    private CommentContract.Presenter presenter;
    TransferBetweenFragments transferBetweenFragments;
    private RecyclerView recyclerView;
    private ProgressBar spinner;
    private Context context;
    public CommentAdapter commentAdapter;
    int id;
    int userId;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    public CommentFragment(int _userId){
        userId = _userId;
    }

    public CommentFragment newInstance() {

        Bundle args = new Bundle();

        CommentFragment fragment = new CommentFragment(userId);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new CommentPresenter(this, (Application)this.context.getApplicationContext());

        recyclerView = view.findViewById(R.id.post_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);


        if (isNetworkAvailable()) {

            presenter.onLoadComment(userId);
        }

        else {
            try {
                commentAdapter = new CommentAdapter(this.context,presenter.getAllComments(), commentID -> {
                    transferBetweenFragments.goFromCommentToComment(commentID);

                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            recyclerView.setAdapter(commentAdapter);
        }

    }


    public void showCommentsList(List<Comment> comments) {

        if(context!= null) {

            presenter.onSaveComments(comments);

            CommentAdapter adapter = new CommentAdapter(context, comments, new OnCommentClickListener() {

                @Override
                public void onCommentClick(int commentID) {

                    transferBetweenFragments.goFromCommentToComment(commentID);

                }
            });

            recyclerView.setAdapter(adapter);

        }
    }



    @Override
    public void showErrorMessage() {

        Toast.makeText(this.getContext(),"Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager manager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;

        if (networkInfo != null && networkInfo.isConnected()) {
            // Network is present and connected
            isAvailable = true;
        }

        return isAvailable;
    }
}
