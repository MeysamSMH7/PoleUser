package com.pol.poleuser;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
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

    DrawerLayout drawerLayout;
    NavigationView navigationview;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_tolbar_dr);

        sharedPreferences = getSharedPreferences("polUser", 0);
        getVersionInfo();

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_jafar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationview = (NavigationView) findViewById(R.id.navigationview);
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {

                    case R.id.itmLogOff:

                        AlertDialogLogout();

                        break;

                    case R.id.itmShowAllFinished:

                        Intent intent1 = new Intent(Activity_main_PolUser.this, Activity_ShowAllFinished_PoleUser.class);
                        startActivity(intent1);

                        break;

                    case R.id.first7:

                        Intent intent = new Intent(Activity_main_PolUser.this, Activity_EditProfile_PoleUser.class);
                        startActivity(intent);

                        break;
                }
                drawerLayout.closeDrawer(Gravity.RIGHT);
                return true;
            }
        });


    }

    private void AlertDialogLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Activity_main_PolUser.this);
        builder.create();
        builder.setTitle("خروج از حساب کاربری");
        builder.setMessage("آیا میخواهید از حساب کاربریتان خارج شوید؟");
        builder.setPositiveButton("آره", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putInt("ID_User", 0);
                editor.putString("FirstName_User", null);
                editor.putString("LastName_User", null);
                editor.putInt("PhoneNum_User", 0);
                editor.putString("StateName_User", null);
                editor.putString("CityName_User", null);
                editor.putInt("CodPosty_User", 0);
                editor.putString("Address_User", null);
                editor.putString("Password_User", null);
                editor.putBoolean("statusLogin?", false);
                editor.apply();

                Intent intent = new Intent(Activity_main_PolUser.this, Activity_Login_PolUser.class);
                startActivity(intent);
                finish();
            }
        });

        builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_dr) {
            drawerLayout.openDrawer(Gravity.RIGHT);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout.closeDrawer(Gravity.RIGHT);
        } else {
            super.onBackPressed();
        }

    }

    private void getVersionInfo() {
        String versionName = "";
        int versionCode = -1;
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionName = packageInfo.versionName;
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        //Toast.makeText(this, String.format("Version name = %s \nVersion code = %d", versionName, versionCode) + "", Toast.LENGTH_SHORT).show();
    }


}

