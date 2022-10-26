package com.example.ead_project_frontend.ui.navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.ead_project_frontend.R;
import com.example.ead_project_frontend.config.SysConfig;
import com.example.ead_project_frontend.ui.fragment.FragmentHome;
import com.example.ead_project_frontend.ui.fragment.FragmentProfile;
import com.example.ead_project_frontend.ui.login.Login;
import com.example.ead_project_frontend.ui.updateProfile.UpdateProfile;

public class NavigationBar extends AppCompatActivity {



    private final int ID_HOME=1;
    private final int ID_PROFILE=2;
    private final int ID_LOGOUT=3;

    //creation of bottom navigation bar
    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar);


        bottomNavigation = findViewById(R.id.bottom_navigation);

        //add icons to bottom navigation
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME,R.drawable.home_icon));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_PROFILE,R.drawable.profile));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_LOGOUT,R.drawable.logout));


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentHome()).commit();

        //handling click events in notification bar
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //navigation
                Toast.makeText(getApplicationContext(),"clicked item"+item.getId(),Toast.LENGTH_SHORT).show();

            }
        });

        //show icons on  menue
        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener(){

            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                 String name;
                Fragment select_fragment=null;
                 switch(item.getId()){
                     case ID_HOME:
                         name ="this is home page where user can able to search petrol shed";
                         select_fragment = new FragmentHome();

                         break;
                     case ID_PROFILE:
                         name ="this profile page where user item are here";
                         select_fragment = new FragmentProfile();
                         break;
                     case ID_LOGOUT:
                         Intent intent = new Intent(NavigationBar.this, Login.class);
                         startActivity(intent);
                         break;
                     default:
                         name ="";
                         select_fragment= new FragmentHome();

                 }

                 getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,select_fragment).commit();
            }
        });





    }


}