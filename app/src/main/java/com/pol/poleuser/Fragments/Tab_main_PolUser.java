package com.pol.poleuser.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.pol.poleuser.Activity_SubCategory_PolUser;
import com.pol.poleuser.Activity_SubmitReq_PolUser;
import com.pol.poleuser.Activity_main_PolUser;
import com.pol.poleuser.Adapters.MainPolUserGrid;
import com.pol.poleuser.R;
import com.pol.poleuser.classes.checkInternet;
import com.pol.poleuser.connectClasses.connect_GetSubSubjects;
import com.pol.poleuser.varClass.varClass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Tab_main_PolUser extends Fragment {

    //public Variable
    private SharedPreferences preferencesMain;
    private String StatePre = "";
    public String getSubSubjectsServer = "";
    private String AllSubSbjects = null;
    private AutoCompleteTextView autoComTxtViewMain;
    ArrayAdapter<String> adapterSearch;
    List<String> lstSearch;
    checkInternet internet;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ft_main_layout_poluser, container, false);

        internet = new checkInternet(getContext());

        preferencesMain = getActivity().getSharedPreferences("polUser", 0);
        StatePre = preferencesMain.getString("StateName_User", "aaa");

        //Grid View Object *******************************************************************
        GridView gridViewMain = (GridView) view.findViewById(R.id.gridViewMain);
        MainPolUserGrid mainPolUserGrid = new MainPolUserGrid(view.getContext(), varClass.STMainPage1);
        gridViewMain.setAdapter(mainPolUserGrid);

        new connect_GetSubSubjects(getString(R.string.LinkGetSubSubject), resultGetSubSubject, StatePre).execute();


        //GridView Click Items ***************************************************************
        gridViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                if (!internet.CheckNetworkConnection()) {
                    checkNet();
                } else {

                    if (GetJSONArraySubSubjects(varClass.STMainPage1[position]).equals("null") || GetJSONArraySubSubjects(varClass.STMainPage1[position]).equals("")) {
                        Toast.makeText(view.getContext(), "چیزی ارائه نشده", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(view.getContext(), Activity_SubCategory_PolUser.class);
                        intent.putExtra("NameCategory", varClass.STMainPage1[position]);
                        intent.putExtra("SubSubject", GetJSONArraySubSubjects(varClass.STMainPage1[position]));
                        startActivity(intent);
                    }


                }
            }
        });


//        autoComTxtViewMain = view.findViewById(R.id.autoComTxtViewMain);

        autoComTxtViewMain = view.findViewById(R.id.autoComTxtViewMain);
        autoComTxtViewMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (AllSubSbjects == null) {
                    AllSubSbjects = GetJSONArraySubSubjects("جستجو کردن");
                    lstSearch = new ArrayList<>();

                    String[] aaa = AllSubSbjects.split(",");
                    for (int a = 0; a < aaa.length; a++) {
                        if (!(aaa[a].equals("null"))) {
                            lstSearch.add(aaa[a]);
                        }
                    }
                    autoComTxtViewMain.setThreshold(1);//will start working from first character
                    adapterSearch = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_item, lstSearch);
                    autoComTxtViewMain.setAdapter(adapterSearch);//setting the adapter data into the AutoCompleteTextView
                }


            }
        });
        autoComTxtViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getContext(), Activity_SubmitReq_PolUser.class);
                intent.putExtra("Subject", parent.getAdapter().getItem(position).toString());
                startActivity(intent);
                autoComTxtViewMain.setText("");
            }
        });


        return view;
    }

    connect_GetSubSubjects.IgetSubRes resultGetSubSubject = new connect_GetSubSubjects.IgetSubRes() {
        @Override
        public void getSubSujectsResult(String res) {
            getSubSubjectsServer = res;
        }
    };

    //GetJson *****************************************************************************

    private String GetJSONArraySubSubjects(String cat) {
        String PicVideo = "", KhadamateMajales = "", HamlNaghl = "", KhadamateBagh = "", DamPezeshki = "", KHAdamatNezafati = "", MashinAlat = "", TamirLavazemKhanehi = "", DekoRasion = "", KhadaMatFaniBuild = "", TamirBuild = "", KhadaMatCar = "", KhadaMatCompuTer = "", KharidTicket = "", SearchString = "";
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

                SearchString = PicVideo + "," + KhadamateMajales + "," + HamlNaghl + "," + KhadamateBagh + "," + DamPezeshki + "," + KHAdamatNezafati + "," + MashinAlat + "," + TamirLavazemKhanehi + "," + DekoRasion + "," + KhadaMatFaniBuild + "," + TamirBuild + "," + KhadaMatCar + "," + KhadaMatCompuTer + "," + KharidTicket + "";


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
            case "جستجو کردن":
                return SearchString;
            default:
                return "null";
        }
    }

    private void checkNet() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.ToastCheckNetTitle));
        builder.setMessage(getString(R.string.ToastCheckNetMessage));
        builder.show();
    }


}