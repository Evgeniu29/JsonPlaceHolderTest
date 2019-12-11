package com.paad.testtask.postlist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paad.testtask.CommentActivity;
import com.paad.testtask.R;
import com.paad.testtask.model.Post;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private List<Post> postList;
    private OnPostClickListener listener;
    Context context;
    String name;
    int id;

    public PostAdapter(Context  context, List<Post> postList, OnPostClickListener listener) {
        this.context = context;
        this.postList = postList;
        this.listener = listener;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View result = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new MyViewHolder(result);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.setIsRecyclable(false);

        holder.onBind(postList.get(position));

    }

    @Override
    public int getItemCount() {

        if(postList == null)
            return 0;
        return postList.size();
    }

    public void setListData(List<Post> postList) {
        postList = postList;
        notifyDataSetChanged();
    }




    class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout item;

        private TextView userId;

        private TextView posts_postId;

        private TextView title;

        private TextView posts_body;


        MyViewHolder(View itemView) {

            super(itemView);

            item = itemView.findViewById(R.id.post_item);

            userId = itemView.findViewById(R.id.userId);

            posts_postId = itemView.findViewById(R.id.posts_postId);

            title = itemView.findViewById(R.id.title);

            posts_body = itemView.findViewById(R.id.posts_body);



        }

        public void onBind(final Post post) {

            userId.setText(post.getUserId().toString());


            posts_postId.setText(post.getId().toString());

            title.setText(post.getTitle());

            posts_body.setText(post.getBody());

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, CommentActivity.class);

                    intent.putExtra("id", post.getId());

                    intent.putExtra("userId", post.getUserId());

                    startActivity(context, intent, null);

                }
            });

        }


    }




    public List<Post> getArrayList(){
        return this.postList;
    }

}
