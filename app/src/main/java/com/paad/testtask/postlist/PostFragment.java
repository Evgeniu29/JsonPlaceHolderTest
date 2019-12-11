package com.paad.testtask.postlist;


import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.paad.testtask.R;
import com.paad.testtask.Singleton;
import com.paad.testtask.TransferBetweenFragments;
import com.paad.testtask.model.Post;
import com.paad.testtask.userlist.UserAdapter;


import java.util.List;
import java.util.concurrent.ExecutionException;


public class PostFragment extends Fragment implements PostContract.View {

    private PostContract.Presenter presenter;
    TransferBetweenFragments transferBetweenFragments;
    private RecyclerView recyclerView;
    private Context context;
    private PostAdapter postAdapter;



    @Override
    public void onAttach(Context  context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.context = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    public static PostFragment newInstance() {

        Bundle args = new Bundle();

        PostFragment fragment = new PostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        presenter = new PostPresenter(this, (Application) this.context.getApplicationContext());

        recyclerView = view.findViewById(R.id.post_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);


        if (isNetworkAvailable()) {

            presenter.onLoadPost();
        }

        else {
            try {
                postAdapter = new PostAdapter(this.context,presenter.getAllPosts(), postID -> {
                    transferBetweenFragments.goFromPostToPost(postID);

                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            recyclerView.setAdapter(postAdapter);


        }

    }


    @Override
    public void showPostList(List<Post> posts) {

        if (context != null) {

            presenter.onSavePosts(posts);

            postAdapter = new PostAdapter(getContext(), posts, postID -> {
                transferBetweenFragments.goFromUserToPost(postID);
            });


            recyclerView.setAdapter(postAdapter);
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
