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
import android.widget.Toolbar;

import com.pol.poleuser.R;
import com.pol.poleuser.connectClasses.connect_AcceptOne;
import com.pol.poleuser.connectClasses.connect_PayMoney;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Tab_WaitMoney_PolUser extends Fragment {

    AlertDialog ShowWaitPriceAlert;
    ListView lts_WaitMoney;
    SharedPreferences preferencesMain;
    int IDUser = 0, IDPost = 0;
    List<String> listWaitPrice, listWaitPriceAlert;
    List<Integer> listIDPostWaitPrice;
    ArrayAdapter arrayWaitPrice;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ft_waitmoney_poluser, container, false);

        preferencesMain = getActivity().getSharedPreferences("polUser", 0);
        IDUser = preferencesMain.getInt("ID_User", 0);

        listWaitPrice = new ArrayList<>();
        listWaitPriceAlert = new ArrayList<>();
        listIDPostWaitPrice = new ArrayList<>();

        lts_WaitMoney = view.findViewById(R.id.lts_WaitMoney);

        new connect_AcceptOne(getString(R.string.LinkWaiteForPrice), iWaitePrice, IDUser, 0).execute();

        lts_WaitMoney.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder_WaitPrice = new AlertDialog.Builder(getContext());
                LinearLayout layout_WaitPrice = (LinearLayout) getLayoutInflater().inflate(R.layout.custom_listview_waitprice_alert, null, false);
                TextView txtCustomlstWaitPriceAlert = layout_WaitPrice.findViewById(R.id.txtCustomlstWaitPriceAlert);
                Button btnCustomlstWaitPriceAlert_Cash = layout_WaitPrice.findViewById(R.id.btnCustomlstWaitPriceAlert_Cash);
                Button btnCustomlstWaitPriceAlert_Online = layout_WaitPrice.findViewById(R.id.btnCustomlstWaitPriceAlert_Online);
                Button btnCustomlstWaitPriceAlert_Cancel = layout_WaitPrice.findViewById(R.id.btnCustomlstWaitPriceAlert_Cancel);
                txtCustomlstWaitPriceAlert.setText(listWaitPriceAlert.get(position));


                btnCustomlstWaitPriceAlert_Cash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new connect_PayMoney(getString(R.string.LinkPayMoney), iPayMoney, listIDPostWaitPrice.get(position), 0).execute();
                        ShowWaitPriceAlert.dismiss();
                    }
                });

                btnCustomlstWaitPriceAlert_Online.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new connect_PayMoney(getString(R.string.LinkPayMoney), iPayMoney, listIDPostWaitPrice.get(position), 1).execute();
                        ShowWaitPriceAlert.dismiss();

                    }
                });


                btnCustomlstWaitPriceAlert_Cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShowWaitPriceAlert.dismiss();
                    }
                });

                builder_WaitPrice.setView(layout_WaitPrice);
                ShowWaitPriceAlert = builder_WaitPrice.create();
                ShowWaitPriceAlert.show();
            }
        });

        return view;
    }

    connect_AcceptOne.IAcceptOne iWaitePrice = new connect_AcceptOne.IAcceptOne() {
        @Override
        public void getAcceptOneResult(String res) {
            GetJsonWaitPrice(res);
            arrayWaitPrice = new ArrayAdapter(getContext(), R.layout.custom_listview_accone, listWaitPrice) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    convertView = getLayoutInflater().inflate(R.layout.custom_listview_accone, parent, false);
                    TextView txtCustomlstAccOne = convertView.findViewById(R.id.txtCustomlstAccOne);
                    txtCustomlstAccOne.setText(listWaitPrice.get(position));
                    return convertView;
                }
            };
            lts_WaitMoney.setAdapter(arrayWaitPrice);

        }
    };

    connect_PayMoney.IPayMoney iPayMoney = new connect_PayMoney.IPayMoney() {
        @Override
        public void getPayMoneyResult(String res) {


            Toast.makeText(getContext(), res + "", Toast.LENGTH_SHORT).show();
        }
    };

    public void GetJsonWaitPrice(String res) {

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
                int ChengePrice = object.getInt("ChengePrice");
                String ChengePriceReason = object.getString("ChengePriceReason");
                String FirstName = object.getString("FirstName");
                String LastName = object.getString("LastName");
                int PhoneNum = object.getInt("PhoneNum");
                String ChengePriceST = "";
                String ChengePriceReasonST = "";
                int finalPrice = 0;

                if (ChengePrice == 0) {
                    ChengePriceST = "خیر";
                    finalPrice = Price;
                } else {
                    ChengePriceST = ChengePrice + "";
                    finalPrice = Price + ChengePrice;
                }
                if (ChengePriceReason.equals("خالی")) {
                    ChengePriceReasonST = "ندارد";
                } else {
                    ChengePriceReasonST = ChengePriceReason + "";
                }


                listWaitPrice.add("" +
                        "موضوع: " + Subject + "\n" +
                        "تاریخ: " + DateDay + "/" + DateMonth + "/" + DateYear + "\t" + NameWeek + "\n" +
                        "نام و نام خانوادگی متخصص: " + FirstName + " " + LastName + "\n" +
                        "قیمت: " + Price +
                        "بازه زمانی کار: " + PeriodWork + "");
                listWaitPriceAlert.add("" +
                        "موضوع: " + Subject + "\n" +
                        "تاریخ: " + DateDay + "/" + DateMonth + "/" + DateYear + "\t" + NameWeek + "\n" +
                        "بازه زمانی: " + PeriodTime + "\n" +
                        "آدرس: " + Address + "\n" +
                        "مشکل: " + txt + "\n\t" +
                        "نام و نام خانوادگی متخصص: " + FirstName + " " + LastName + "\n\t" +
                        "بازه زمانی کار: " + PeriodWork + "\n\t" +
                        "شماره تلفن: " + PhoneNum + "\n\t\t" +
                        "قیمت اولیه: " + Price + "\n\t\t" +
                        "تغییر قیمت: " + ChengePriceST + "\n\t\t" +
                        "علت تغییر قیمت: " + ChengePriceReasonST + "\n\t\t" +
                        "قیمت کل: " + finalPrice +
                        "");
                listIDPostWaitPrice.add(IDPost);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}