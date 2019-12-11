package com.paad.testtask.commentslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paad.testtask.R;
import com.paad.testtask.model.Comment;
import com.paad.testtask.model.Photo;
import com.paad.testtask.model.Post;

import java.util.List;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    private List<Comment> commentList;
    private OnCommentClickListener listener;
    Context context;
    String name;
    int id;

    public CommentAdapter(Context  context, List<Comment> commentList, OnCommentClickListener listener) {
        this.context = context;
        this.commentList = commentList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View result = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new MyViewHolder(result);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.setIsRecyclable(false);

        holder.onBind(commentList.get(position));

    }

    @Override
    public int getItemCount() {

        if(commentList == null)
            return 0;
        return commentList.size();
    }

    public void setListData(List<Post> postList) {
        postList = postList;
        notifyDataSetChanged();
    }




    class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout item;


        private TextView commTitle;

        private TextView commEmail;

        private TextView commBody;


        MyViewHolder(final View itemView) {

            super(itemView);

            item = itemView.findViewById(R.id.comment_item);

            commTitle = itemView.findViewById(R.id.commTitle);

            commEmail = itemView.findViewById(R.id.commEmail);

            commBody = itemView.findViewById(R.id.commBody);

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });


        }

        public void onBind(Comment comment) {


            commTitle.setText(comment.getName());

            commEmail.setText(comment.getEmail());

            commBody.setText(comment.getBody());



        }




    }

    public List<Comment> getArrayList(){
        return this.commentList;
    }





}
