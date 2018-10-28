package com.pol.poleuser;


import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.ViewGroup;


import com.pol.poleuser.Fragments.Tab_WaitMoney_PolUser;
import com.pol.poleuser.Fragments.Tab_accOne_PolUser;
import com.pol.poleuser.Fragments.Tab_main_PolUser;

public class Activity_main_PolUser extends AppCompatActivity {

    Tab_main_PolUser fragment1;
    Tab_accOne_PolUser fragment2;
    Tab_WaitMoney_PolUser fragment3;
    ViewGroup frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_poluser);

        frameLayout = (ViewGroup) findViewById(R.id.frameMainPolUser);
        fragment1 = new Tab_main_PolUser();
        fragment2 = new Tab_accOne_PolUser();
        fragment3 = new Tab_WaitMoney_PolUser();

        BottomNavigationView navMainPolUser = findViewById(R.id.navMainPolUser);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameMainPolUser, fragment1);
        transaction.commit();

        navMainPolUser.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                switch (menuItem.getItemId()) {
                    case R.id.MainPageFrame:
                        transaction.replace(R.id.frameMainPolUser, fragment1);
                        transaction.commit();
                        break;

                    case R.id.AccOneFrame:
                        transaction.replace(R.id.frameMainPolUser, fragment2);
                        transaction.commit();
                        break;

                    case R.id.WaitForMoneyFrame:
                        transaction.replace(R.id.frameMainPolUser, fragment3);
                        transaction.commit();
                        break;
                }
                return true;
            }
        });
    }
}

