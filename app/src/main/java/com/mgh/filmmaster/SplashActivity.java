package com.mgh.filmmaster;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {

    private static final long  TIME_SPLASH =3000 ;
  Button buttonSplash;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        buttonSplash=findViewById(R.id.splash_btn);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashActivity.this, ListActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, TIME_SPLASH);


        if(buttonSplash.isClickable()) {
            buttonSplash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SplashActivity.this, ListActivity.class);
                    handler.removeCallbacksAndMessages(null);
                    startActivity(intent);

                    finish();
                }
            });
        }


        }

}
