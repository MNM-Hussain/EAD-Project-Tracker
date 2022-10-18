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

public class SplashScreen extends AppCompatActivity {

    private static int NEXT_SCREEN = 7000;

//    ImageView splashLogo, bgImage;
    TextView logo_Name;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        splashLogo = findViewById(R.id.splashLogo);
        logo_Name = findViewById(R.id.LogoName);
//        bgImage = findViewById(R.id.splash_bg);
        lottieAnimationView = findViewById(R.id.splashAnimation);


//        bgImage.animate().translationY(-1600).setDuration(3000).setStartDelay(6000);
//        splashLogo.animate().translationY(1400).setDuration(2000).setStartDelay(5000);
        logo_Name.animate().translationY(1400).setDuration(3000).setStartDelay(6000);
        lottieAnimationView.animate().translationY(1400).setDuration(3000).setStartDelay(6000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, Login.class);
                startActivity(intent);
                finish();
            }
        }, NEXT_SCREEN);
    }
}