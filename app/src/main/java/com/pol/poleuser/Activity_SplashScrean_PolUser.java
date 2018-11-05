package com.pol.poleuser;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.pol.poleuser.classes.checkInternet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Activity_SplashScrean_PolUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screan_poluser);
        SharedPreferences firstOpen = getSharedPreferences("polUser", 0);

        final boolean getStatusWellCome = firstOpen.getBoolean("statusWellcome?", false);
        final boolean getStatusLogin = firstOpen.getBoolean("statusLogin?", false);

        final checkInternet internet = new checkInternet(Activity_SplashScrean_PolUser.this);

        if (internet.CheckNetworkConnection()){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!getStatusWellCome) {
                        Intent intent = new Intent(Activity_SplashScrean_PolUser.this, Activity_Wellcome_PolUser.class);
                        startActivity(intent);
                        finish();
                    } else if (!getStatusLogin) {
                        Intent intent = new Intent(Activity_SplashScrean_PolUser.this, Activity_Login_PolUser.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(Activity_SplashScrean_PolUser.this, Activity_main_PolUser.class);
                        startActivity(intent);
                        finish();
                    }


                }

            }, 500);
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.ToastCheckNetTitle));
            builder.setMessage(getString(R.string.ToastCheckNetMessage));
            builder.setNeutralButton("OK!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (internet.CheckNetworkConnection()){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (!getStatusWellCome) {
                                    Intent intent = new Intent(Activity_SplashScrean_PolUser.this, Activity_Wellcome_PolUser.class);
                                    startActivity(intent);
                                    finish();
                                } else if (!getStatusLogin) {
                                    Intent intent = new Intent(Activity_SplashScrean_PolUser.this, Activity_Login_PolUser.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Intent intent = new Intent(Activity_SplashScrean_PolUser.this, Activity_main_PolUser.class);
                                    startActivity(intent);
                                    finish();
                                }


                            }

                        }, 500);
                    }
                }
            });
            builder.show();
        }
    }
}
