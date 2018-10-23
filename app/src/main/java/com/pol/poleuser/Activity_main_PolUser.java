package com.pol.poleuser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


import com.pol.poleuser.Adapters.MainPolUserGrid;
import com.pol.poleuser.connectClasses.connect_GetSubSubjects;
import com.pol.poleuser.varClass.varClass;

import org.json.JSONArray;
import org.json.JSONObject;


public class Activity_main_PolUser extends AppCompatActivity {


    //public Variable
    SharedPreferences preferencesMain;
    String StatePre = "";
    public String getSubSubjectsServer = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_poluser);
        preferencesMain = getSharedPreferences("polUser", 0);
        StatePre = preferencesMain.getString("StateName_User", "aaa");


        //Grid View Object *******************************************************************
        GridView gridViewMain = (GridView) findViewById(R.id.gridViewMain);
        MainPolUserGrid mainPolUserGrid = new MainPolUserGrid(this, varClass.STMainPage1);
        gridViewMain.setAdapter(mainPolUserGrid);

        new connect_GetSubSubjects(getString(R.string.LinkGetSubSubject), resultGetSubSubject, StatePre.toString()).execute();


        //GridView Click Items ***************************************************************
        gridViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                if (GetJSONArraySubSubjects(varClass.STMainPage1[position]).equals("null")) {
                    Toast.makeText(Activity_main_PolUser.this, "چیزی ارائه نشده", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Activity_main_PolUser.this, Activity_SubCategory_PolUser.class);
                    intent.putExtra("NameCategory", varClass.STMainPage1[position]);
                    intent.putExtra("SubSubject", GetJSONArraySubSubjects(varClass.STMainPage1[position]));
                    startActivity(intent);
                }
            }
        });


    }


    connect_GetSubSubjects.IgetSubRes resultGetSubSubject = new connect_GetSubSubjects.IgetSubRes() {
        @Override
        public void getSubSujectsResult(String res) {
            getSubSubjectsServer = res;
        }
    };

//GetJson *****************************************************************************

    public String GetJSONArraySubSubjects(String cat) {
        String PicVideo = "", KhadamateMajales = "", HamlNaghl = "", KhadamateBagh = "", DamPezeshki = "", KHAdamatNezafati = "", MashinAlat = "", TamirLavazemKhanehi = "", DekoRasion = "", KhadaMatFaniBuild = "", TamirBuild = "", KhadaMatCar = "", KhadaMatCompuTer = "", KharidTicket = "";
        try {

            JSONArray jsonArray = new JSONArray(getSubSubjectsServer);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject object = jsonArray.getJSONObject(i);


                PicVideo = object.getString("PicVideo");
                KhadamateMajales = object.getString("KhadamateMajales");
                HamlNaghl = object.getString("HamlNaghl");
                KhadamateBagh = object.getString("KhadamateBagh");
                DamPezeshki = object.getString("DamPezeshki");
                KHAdamatNezafati = object.getString("KHAdamatNezafati");
                MashinAlat = object.getString("MashinAlat");
                TamirLavazemKhanehi = object.getString("TamirLavazemKhanehi");
                DekoRasion = object.getString("DekoRasion");
                KhadaMatFaniBuild = object.getString("KhadaMatFaniBuild");
                TamirBuild = object.getString("TamirBuild");
                KhadaMatCar = object.getString("KhadaMatCar");
                KhadaMatCompuTer = object.getString("KhadaMatCompuTer");
                KharidTicket = object.getString("KharidTicket");


            }

        } catch (Exception e) {
            Log.d("err", e.getMessage());
        }

        switch (cat) {
            case "عکاس و فیلمبردار":
                return PicVideo;
            case "مجالس":
                return KhadamateMajales;
            case "حمل و نقل":
                return HamlNaghl;
            case "باغبانی":
                return KhadamateBagh;
            case "دامپزشکی":
                return DamPezeshki;
            case "نظافت":
                return KHAdamatNezafati;
            case "ماشین آلات راهداری":
                return MashinAlat;
            case "تعمیرات لوازم خانگی":
                return TamirLavazemKhanehi;
            case "دکوراسیون":
                return DekoRasion;
            case "فنی ساختمان":
                return KhadaMatFaniBuild;
            case "تعمیرات کلی ساختمان":
                return TamirBuild;
            case "خودرو":
                return KhadaMatCar;
            case "کامپیوتری":
                return KhadaMatCompuTer;
            case "خرید بلیط":
                return KharidTicket;
            default:
                return "null";
        }
    }
}

