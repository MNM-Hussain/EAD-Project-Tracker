package com.example.ead_project_frontend.ui.splash_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ead_project_frontend.R;
import com.example.ead_project_frontend.ui.login.Login;
import com.example.ead_project_frontend.ui.welcomePage.WelcomePage;

//reference [02]
public class SplashScreen extends AppCompatActivity {

    private static int NEXT_SCREEN = 7000;

    //    ImageView splashLogo, bgImage;
    TextView logo_Name;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Initializing with ID
        logo_Name = findViewById(R.id.LogoName);
        lottieAnimationView = findViewById(R.id.splashAnimation);

        //animation
        logo_Name.animate().translationY(1400).setDuration(3000).setStartDelay(6000);
        lottieAnimationView.animate().translationY(1400).setDuration(3000).setStartDelay(6000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, WelcomePage.class);
                startActivity(intent);
                finish();
            }
        }, NEXT_SCREEN);
    }
}