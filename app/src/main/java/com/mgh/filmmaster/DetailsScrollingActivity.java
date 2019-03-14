package com.mgh.filmmaster;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mgh.filmmaster.Adapters.RecyclerAdapter;
import com.mgh.filmmaster.Models.ActorModel;
import com.mgh.filmmaster.Models.moviesModel;
import com.mgh.filmmaster.Utils.MySingleton;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailsScrollingActivity extends AppCompatActivity {

    ImageView imageView1,imageView2,imageView3,imageView4,imageView5;
    TextView textViewName1,textViewName2,textViewName3,textViewName4,textViewName5;
    TextView textViewchar1,textViewchar2,textViewchar3,textViewchar4,textViewchar5;
    TextView countValueTv, voteValueTv, nameValueTv ,overviewValueTv ,adultValueTv ,dateValueTv;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView moviePosterIv;
    String idMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupWedget();

        moviesModel modelMovies = getIntent().getParcelableExtra("modelMoviesRes");

        idMovies=modelMovies.getId()+"";
        String urlActor="https://api.themoviedb.org/3/movie/"+idMovies+"?api_key=654ef11f06c4e69ea63cbcb409be9cd7&append_to_response=credits";

        detDataFromVolly(urlActor);

        BindingData(modelMovies);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    ArrayList<ActorModel> actorModels;
    ActorModel actorModel;
    private void detDataFromVolly(final String urlActor) {


        actorModels=new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlActor, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject credits = response.getJSONObject("credits");
                    JSONArray cast = credits.getJSONArray("cast");
                    for (int i = 0; i <5; i++) {

                        JSONObject jsonObjectCasti = cast.getJSONObject(i);

                        String character = jsonObjectCasti.getString("character");
                        String name = jsonObjectCasti.getString("name");
                        String profile_path = jsonObjectCasti.getString("profile_path");
                        int  id = jsonObjectCasti.getInt("id");
                        Log.e(i+"  name",name);
                        Log.e(i+"  profile_path",profile_path);
                        Log.e(i+"  character",character);
                        actorModel=new ActorModel(id,name,character,profile_path);

                        actorModels.add(actorModel);
                    }

                    setUpAlCast();



                } catch (JSONException e) {
                  Log.e("WWWWW" ,e.getMessage());
                }

            }
            }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailsScrollingActivity.this, "error", Toast.LENGTH_SHORT).show();

            }
        });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);




    }

    ProgressBar castProgres;
    LinearLayout  castLinearLayout;
    private void setUpAlCast() {
        castProgres.setVisibility(View.GONE);
        castLinearLayout.setVisibility(View.VISIBLE);
        textViewName1.setText(actorModels.get(0).getName());
        textViewchar1.setText(actorModels.get(0).getCharacterName());
        Picasso.with(DetailsScrollingActivity.this).load("http://image.tmdb.org/t/p/w342/"+actorModels.get(0).getPicUrl()).into(imageView1);

        textViewName2.setText(actorModels.get(1).getName());
        textViewchar2.setText(actorModels.get(1).getCharacterName());
        Picasso.with(DetailsScrollingActivity.this).load("http://image.tmdb.org/t/p/w342/"+actorModels.get(1).getPicUrl()).into(imageView2);

        textViewName3.setText(actorModels.get(2).getName());
        textViewchar3.setText(actorModels.get(2).getCharacterName());
        Picasso.with(DetailsScrollingActivity.this).load("http://image.tmdb.org/t/p/w342/"+actorModels.get(2).getPicUrl()).into(imageView3);

        textViewName4.setText(actorModels.get(3).getName());
        textViewchar4.setText(actorModels.get(3).getCharacterName());
        Picasso.with(DetailsScrollingActivity.this).load("http://image.tmdb.org/t/p/w342/"+actorModels.get(3).getPicUrl()).into(imageView4);

        textViewName5.setText(actorModels.get(4).getName());
        textViewchar5.setText(actorModels.get(4).getCharacterName());
        Picasso.with(DetailsScrollingActivity.this).load("http://image.tmdb.org/t/p/w342/"+actorModels.get(4).getPicUrl()).into(imageView5);
    }


    private void setupWedget() {
        castProgres =findViewById(R.id.cast_progressbar);
        castLinearLayout=findViewById(R.id.castLayout);

        imageView1 =findViewById(R.id.profile_image1);
        imageView2=findViewById(R.id.profile_image2);
        imageView3=findViewById(R.id.profile_image3);
        imageView4=findViewById(R.id.profile_image4);
        imageView5=findViewById(R.id.profile_image5);

        textViewName1=findViewById(R.id.name1);
        textViewName2=findViewById(R.id.name2);
        textViewName3=findViewById(R.id.name3);
        textViewName4=findViewById(R.id.name4);
        textViewName5=findViewById(R.id.name5);
        
        textViewchar1=findViewById(R.id.character_name1);
        textViewchar2=findViewById(R.id.character_name2);
        textViewchar3=findViewById(R.id.character_name3);
        textViewchar4=findViewById(R.id.character_name4);
        textViewchar5=findViewById(R.id.character_name5);

        countValueTv=findViewById(R.id.count_value_tv);
        voteValueTv=findViewById(R.id.vote_value_tv);
        nameValueTv=findViewById(R.id.name_value_tv);
        overviewValueTv=findViewById(R.id.overview_value_tv);
        adultValueTv=findViewById(R.id.adult_value_tv);
        dateValueTv=findViewById(R.id.date_value_tv);
        moviePosterIv=findViewById(R.id.movie_poster_iv);
        collapsingToolbarLayout=findViewById(R.id.toolbar_layout);
    }

    private void BindingData(moviesModel modelMovies) {

        countValueTv.setText(modelMovies.getVote_count()+"");
        voteValueTv.setText(modelMovies.getVote_average()+"");
        nameValueTv.setText(modelMovies.getTitle());
        dateValueTv.setText(modelMovies.getRelease_date());
        overviewValueTv.setText(modelMovies.getOverview());
        if(modelMovies.isAdult()){
            adultValueTv.setText("+18");
        }else{
            adultValueTv.setText("Safe");
        }
        Picasso.with(this).load("http://image.tmdb.org/t/p/w342/" + modelMovies.getPoster_path()).into(moviePosterIv);

       Target toolbarlayoutTarget = new Target() {

            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    collapsingToolbarLayout.setBackground(new BitmapDrawable(getResources(), bitmap));
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Toast.makeText(DetailsScrollingActivity.this, "FailedLoading", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        collapsingToolbarLayout.setTag(toolbarlayoutTarget);
        Picasso.with(this).load("http://image.tmdb.org/t/p/w342/" + modelMovies.getBackdrop_path()).into((Target) collapsingToolbarLayout.getTag());

        collapsingToolbarLayout.setTitle(modelMovies.getTitle());

    }
}
