package com.mgh.filmmaster;
//01000862782

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mgh.filmmaster.Adapters.RecyclerAdapter;
import com.mgh.filmmaster.Models.moviesModel;
import com.mgh.filmmaster.Utils.MySingleton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "ListActivity";
    ArrayList<moviesModel> moviesModelArrayList;
    Button buttonClose;
    LinearLayout linearLayoutButton;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    RecyclerAdapter adapter;
    TextView textViewCurrentPage;
    private int lastPages, currentPage;
    Button buttonNextPage, buttonPreviesPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final FloatingActionButton fab =  findViewById(R.id.fab);
        buttonNextPage = findViewById(R.id.btnNext);
        buttonPreviesPage = findViewById(R.id.btnPrevies);
        recyclerView = findViewById(R.id.idRecyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0){
                    fab.hide();
                    linearLayoutButton.setVisibility(View.VISIBLE);
                }else {
                    fab.show();
                    linearLayoutButton.setVisibility(View.INVISIBLE);

                }
            }
        });
        textViewCurrentPage=findViewById(R.id.tv_currentpage);
        manager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);


        buttonClose = findViewById(R.id.btnCloseLayout);
        linearLayoutButton = findViewById(R.id.layoutButton);


        currentPage=1;
        buttonPreviesPage.setEnabled(false);

        getDataaFromVolly(url,currentPage );

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayoutButton.setVisibility(View.VISIBLE);
                fab.hide();
            }
        });
        buttonNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage =currentPage+1;
                getDataaFromVolly(url,currentPage );

                Log.e(TAG, "URL "+currentPage+"   "+url );
            }
        });
        buttonPreviesPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage =currentPage - 1;
                getDataaFromVolly(url,currentPage );

                Log.e(TAG, "URL "+currentPage+"   "+url );
            }
        });
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutButton.setVisibility(View.INVISIBLE);
                fab.show();

            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
   final String url = "https://api.themoviedb.org/3/movie/popular?api_key=654ef11f06c4e69ea63cbcb409be9cd7&language=en-US&page=";

    

    private void getDataaFromVolly(String currentUrl, final int currentPage) {

        String urlVolly = currentUrl+currentPage;
        Log.e(TAG, "getDataaFromVolly: "+urlVolly );
        moviesModelArrayList = new ArrayList();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlVolly, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                JSONArray results = null;
                try {
                    results = response.getJSONArray("results");
                    lastPages = response.getInt("total_pages");
                    textViewCurrentPage.setText(currentPage+" / "+lastPages);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < results.length(); i++) {
                    try {
                        JSONObject newMovieJson = results.getJSONObject(i);
                        double vote_average = newMovieJson.getDouble("vote_average");
                        int id = newMovieJson.getInt("id");
                        int vote_count = newMovieJson.getInt("vote_count");
                        boolean adult = newMovieJson.getBoolean("adult");
                        String backdrop_path = newMovieJson.getString("backdrop_path");
                        String original_title = newMovieJson.getString("original_title");
                        String poster_path = newMovieJson.getString("poster_path");
                        String overview = newMovieJson.getString("overview");
                        String release_date = newMovieJson.getString("release_date");
                        moviesModel moviesModel = new moviesModel(id, original_title, poster_path, backdrop_path, adult, overview, release_date, vote_count, vote_average);
                        moviesModelArrayList.add(moviesModel);
                    } catch (Exception e) {
                        Toast.makeText(ListActivity.this, "ex", Toast.LENGTH_SHORT).show();
                    }

                }
                adapter = new RecyclerAdapter(ListActivity.this, moviesModelArrayList);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
              //  pagination = new Pagination(20, moviesModelArrayList);
              //  lastPages = pagination.getLastPage();
                updateData();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListActivity.this, "error", Toast.LENGTH_SHORT).show();

            }
        });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);


    }

    private void updateData() {
     //   ArrayList<moviesModel> generateDataPage = pagination.generateDataPage(currentPage);
     //   recyclerView.setAdapter(new RecyclerAdapter(ListActivity.this, generateDataPage));

        if (currentPage == 1) {
            buttonPreviesPage.setEnabled(false);
            buttonNextPage.setEnabled(true);
        } else if (currentPage == lastPages) {
            buttonPreviesPage.setEnabled(true);
            buttonNextPage.setEnabled(false);
        } else {
            buttonPreviesPage.setEnabled(true);
            buttonNextPage.setEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer =findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
