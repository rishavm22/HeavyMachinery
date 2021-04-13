package com.example.heavymachinery.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.heavymachinery.MainActivity;
import com.example.heavymachinery.R;

public class SplashActivity extends AppCompatActivity {

    boolean appMode=false;
    ImageView logoIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logoIcon = findViewById(R.id.logoIcon);

        logoIcon.animate().rotation(360).setDuration(1000);
        logoIcon.setAlpha((float) 0.5);
        logoIcon.animate().alpha(1).setDuration(1000);
    }

    @Override
    protected void onStart() {
        super.onStart();

        /****** Create Thread that will sleep for 5 seconds****/
        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep(6*1000);
                    if(!appMode){
                        // After 5 seconds redirect to another intent
                        Intent i=new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        //Remove activity
                        finish();
                    }
                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();
    }

    public void goToApp(View view) {
        appMode=true;
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}