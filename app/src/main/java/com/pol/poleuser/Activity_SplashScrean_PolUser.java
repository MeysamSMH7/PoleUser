package com.pol.poleuser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_SplashScrean_PolUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screan_poluser);
        SharedPreferences firstOpen = getSharedPreferences("polUser" , 0);

        final boolean getStatus = firstOpen.getBoolean("statusLogin?" , false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getStatus){
                    Intent intent = new Intent(Activity_SplashScrean_PolUser.this , Activity_Login_PolUser.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(Activity_SplashScrean_PolUser.this , Activity_Wellcome_PolUser.class);
                    startActivity(intent);
                    finish();
                }
            }
        },3000);


    }
}
