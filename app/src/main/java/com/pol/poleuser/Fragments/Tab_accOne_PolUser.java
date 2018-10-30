package com.pol.poleuser.Fragments;


import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pol.poleuser.Activity_SubmitReq_PolUser;
import com.pol.poleuser.R;
import com.pol.poleuser.connectClasses.connect_AcceptOne;
import com.pol.poleuser.connectClasses.connect_SubmitReq;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Tab_accOne_PolUser extends Fragment {

    ListView lst_accone;
    ArrayAdapter adapter_accone;
    List<String> listAccOne, listAccOneAlert;
    List<Integer> listIDPost;
    SharedPreferences preferencesMain;
    int IDUser = 0, IDPost = 0;
    AlertDialog ShowAccOneAlert;
    boolean IsAccepting = false;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ft_accone_poluser, container, false);

        preferencesMain = getActivity().getSharedPreferences("polUser", 0);
        IDUser = preferencesMain.getInt("ID_User", 0);
        listAccOne = new ArrayList<>();
        listAccOneAlert = new ArrayList<>();
        listIDPost = new ArrayList<>();

        new connect_AcceptOne(getString(R.string.LinkAcceptOne), iAcceptOne, IDUser, 0).execute();

        lst_accone = view.findViewById(R.id.lst_accone);
        lst_accone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder_AccOne = new AlertDialog.Builder(getContext());
                LinearLayout layout_AccOne = (LinearLayout) getLayoutInflater().inflate(R.layout.custom_listview_accone_alert, null, false);
                TextView txtCustomlstAccOneAlert = layout_AccOne.findViewById(R.id.txtCustomlstAccOneAlert);
                Button btnCustomlstAccOneAlert_Accept = layout_AccOne.findViewById(R.id.btnCustomlstAccOneAlert_Accept);
                Button btnCustomlstAccOneAlert_Cancel = layout_AccOne.findViewById(R.id.btnCustomlstAccOneAlert_Cancel);

                txtCustomlstAccOneAlert.setText(listAccOneAlert.get(position) + "");

                btnCustomlstAccOneAlert_Cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShowAccOneAlert.dismiss();
                    }
                });

                btnCustomlstAccOneAlert_Accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new connect_AcceptOne(getString(R.string.LinkAcceptOne), iAcceptOne, 0, listIDPost.get(position)).execute();
                        IsAccepting = true;
                        IDPost = position;
                        ShowAccOneAlert.dismiss();
                    }
                });

                builder_AccOne.setView(layout_AccOne);
                ShowAccOneAlert = builder_AccOne.create();
                ShowAccOneAlert.show();

            }
        });

        return view;
    }


    connect_AcceptOne.IAcceptOne iAcceptOne = new connect_AcceptOne.IAcceptOne() {
        @Override
        public void getAcceptOneResult(String res) {

            if (IsAccepting) {
                Toast.makeText(getContext(), res + "", Toast.LENGTH_SHORT).show();
                IsAccepting = false;

            } else {
                GetJSONAccOne(res);
                adapter_accone = new ArrayAdapter(getContext(), R.layout.custom_listview_accone, listAccOne) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        convertView = getLayoutInflater().inflate(R.layout.custom_listview_accone, parent, false);
                        TextView txtCustomlstAccOne = convertView.findViewById(R.id.txtCustomlstAccOne);
                        if (listAccOne.isEmpty()){
                            txtCustomlstAccOne.setText("شما هیچ درخواست تایید شده ای ندارید!");
                        }else {
                            txtCustomlstAccOne.setText(listAccOne.get(position));
                        }

                        return convertView;
                    }
                };

                lst_accone.setAdapter(adapter_accone);
            }

        }
    };


    public void GetJSONAccOne(String res) {

        try {

            JSONArray jsonArray = new JSONArray(res);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                int IDPost = object.getInt("ID");
                String Subject = object.getString("Subject");
                int DateDay = object.getInt("DateDay");
                String DateMonth = object.getString("DateMonth");
                int DateYear = object.getInt("DateYear");
                String NameWeek = object.getString("NameWeek");
                String PeriodTime = object.getString("PeriodTime");
                String Address = object.getString("Address");
                String txt = object.getString("txt");
                int UserID = object.getInt("UserID");
                int TechID = object.getInt("TechID");
                int AcceptOne = object.getInt("AcceptOne");
                int Price = object.getInt("Price");
                int PeriodWork = object.getInt("PeriodWork");
                int AcceptTwo = object.getInt("AcceptTwo");
                int AccpectPriceUser = object.getInt("AccpectPriceUser");
                String FirstName = object.getString("FirstName");
                String LastName = object.getString("LastName");
                int PhoneNum = object.getInt("PhoneNum");

                listAccOne.add("" +
                        "موضوع: " + Subject + "\n" +
                        "تاریخ: " + DateDay + "/" + DateMonth + "/" + DateYear + "\t" + NameWeek + "\n" +
                        "نام و نام خانوادگی متخصص: " + FirstName + " " + LastName + "\n" +
                        "قیمت: " + Price +
                        "بازه زمانی کار: " + PeriodWork + "");
                listAccOneAlert.add("" +
                        "موضوع: " + Subject + "\n" +
                        "تاریخ: " + DateDay + "/" + DateMonth + "/" + DateYear + "\t" + NameWeek + "\n" +
                        "بازه زمانی: " + PeriodTime + "\n" +
                        "آدرس: " + Address + "\n" +
                        "مشکل: " + txt + "\n" +
                        "نام و نام خانوادگی متخصص: " + FirstName + " " + LastName + "\n" +
                        "قیمت: " + Price +
                        "بازه زمانی کار: " + PeriodWork +
                        "شماره تلفن: " + PhoneNum + "");
                listIDPost.add(IDPost);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}