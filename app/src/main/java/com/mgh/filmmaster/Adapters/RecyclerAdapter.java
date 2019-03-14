package com.mgh.filmmaster.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mgh.filmmaster.DetailsScrollingActivity;
import com.mgh.filmmaster.Models.moviesModel;
import com.mgh.filmmaster.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {
    Context context;
    ArrayList<moviesModel> modelArrayList;

    public RecyclerAdapter(Context context, ArrayList<moviesModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public Holder onCreateViewHolder( ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_card_layout,viewGroup,false);
        return new Holder(view);

    }

    @Override
    public void onBindViewHolder( Holder holder, int i) {
        final moviesModel moviesModel = modelArrayList.get(i);


        Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/ColabBol.otf");
        Typeface typeReg = Typeface.createFromAsset(context.getAssets(),"fonts/ColabReg.otf");


        Picasso.with(context).load("http://image.tmdb.org/t/p/w342/"+moviesModel.getPoster_path()).into(holder.imageViewFilm);
        holder.textViewTitleFilm.setText(moviesModel.getTitle());
        holder.textViewRateFilm.setText(String.format("%s/10", moviesModel.getVote_average()));


        if(moviesModel.isAdult()){
            holder.textViewAdultFilm.setText("+18");
        }else{
            holder.textViewAdultFilm.setText("Safe");
        }

        holder.textViewTitleFilm.setTypeface(type);
        holder.textViewRateFilm.setTypeface(typeReg);
        holder.textViewAdultFilm.setTypeface(typeReg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailsScrollingActivity.class);
                intent.putExtra("modelMoviesRes",moviesModel);
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView textViewAdultFilm;
        ImageView imageViewFilm;
        TextView textViewRateFilm;
        TextView textViewTitleFilm;

        public Holder( View itemView) {
            super(itemView);

            imageViewFilm = itemView.findViewById(R.id.movie_pic_iv);
            textViewRateFilm = itemView.findViewById(R.id.movie_rate_tv);
            textViewTitleFilm = itemView.findViewById(R.id.movie_name_tv);
            textViewAdultFilm = itemView.findViewById(R.id.movie_adult_tv);



        }

    }
}
