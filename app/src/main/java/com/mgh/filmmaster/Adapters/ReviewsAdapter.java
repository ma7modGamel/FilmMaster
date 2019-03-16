package com.mgh.filmmaster.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mgh.filmmaster.Models.ReviewModel;
import com.mgh.filmmaster.R;

import java.util.ArrayList;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.holder>{

    Context  context;
    ArrayList<ReviewModel> reviewModelArrayList;

    public ReviewsAdapter(Context context, ArrayList<ReviewModel> reviewModelArrayList) {
        this.context = context;
        this.reviewModelArrayList = reviewModelArrayList;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.reviews_item,viewGroup,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int i) {

        ReviewModel reviewModel = reviewModelArrayList.get(i);
        holder.contentTv.setText(reviewModel.getContent());
        holder.nameTv.setText(reviewModel.getAuthor());

    }

    @Override
    public int getItemCount() {
        return reviewModelArrayList.size();
    }

    class holder extends RecyclerView.ViewHolder {
     TextView  nameTv,contentTv;
        public holder(View itemView) {
            super(itemView);
            nameTv=itemView.findViewById(R.id.author);
            contentTv=itemView.findViewById(R.id.content);
        }
    }
}
