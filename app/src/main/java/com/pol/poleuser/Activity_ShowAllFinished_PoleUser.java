package com.pol.poleuser;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pol.poleuser.connectClasses.connect_AcceptOne;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Activity_ShowAllFinished_PoleUser extends AppCompatActivity {

    ListView lstFinishedWork;
    ArrayAdapter arrayAdapter;
    List<String> listTinyFinished;
    List<String> listShowAllFinished;
    AlertDialog alertDialogF;


    private SharedPreferences preferencesShowAllFinished;
    int IDUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showallfinished_poleuser);

        preferencesShowAllFinished = getSharedPreferences("polUser" , 0);

        IDUser = preferencesShowAllFinished.getInt("ID_User" , 0);

        listTinyFinished = new ArrayList<>();
        listShowAllFinished = new ArrayList<>();


        lstFinishedWork = findViewById(R.id.lstFinishedWork);


        new connect_AcceptOne(getString(R.string.LinkShowAllFinishedWork) , iAcceptOne , IDUser , 0).execute();


        lstFinishedWork.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builderF = new AlertDialog.Builder(Activity_ShowAllFinished_PoleUser.this);
                LinearLayout linearLayoutF = (LinearLayout) getLayoutInflater().inflate(R.layout.custom_listview_waitprice , null , false);

                TextView txtCustomlstWaitPrice = linearLayoutF.findViewById(R.id.txtCustomlstWaitPrice);

                txtCustomlstWaitPrice.setText(listShowAllFinished.get(position));

                builderF.setView(linearLayoutF);
                alertDialogF = builderF.create();
                alertDialogF.show();

            }
        });

    }

    connect_AcceptOne.IAcceptOne iAcceptOne = new connect_AcceptOne.IAcceptOne() {
        @Override
        public void getAcceptOneResult(String res) {
            GetJsonWaitPrice(res);

            arrayAdapter = new ArrayAdapter(Activity_ShowAllFinished_PoleUser.this , R.layout.custom_listview_waitprice , listTinyFinished){
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    convertView = getLayoutInflater().inflate(R.layout.custom_listview_waitprice , parent , false);

                    TextView txtCustomlstWaitPrice = convertView.findViewById(R.id.txtCustomlstWaitPrice);

                    txtCustomlstWaitPrice.setText(listTinyFinished.get(position));

                    return convertView;
                }
            };

            lstFinishedWork.setAdapter(arrayAdapter);

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


                listTinyFinished.add("" +
                        "موضوع: " + Subject + "\n" +
                        "تاریخ: " + DateDay + "/" + DateMonth + "/" + DateYear + "\t" + NameWeek + "\n" +
                        "نام و نام خانوادگی متخصص: " + FirstName + " " + LastName + "\n" +
                        "قیمت: " + Price +
                        "بازه زمانی کار: " + PeriodWork + "");
                listShowAllFinished.add("" +
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

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
