package com.pol.poleuser;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.pol.poleuser.connectClasses.connect_LoginUser;

import org.json.JSONArray;
import org.json.JSONObject;


public class Activity_main_PolUser extends AppCompatActivity {
    SharedPreferences preferencesMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_poluser);
        preferencesMain = getSharedPreferences("polUser", 0);





    }


//    public void GetJSONArrayLogin() {
//
//        try {
//            SharedPreferences.Editor editor = preferencesMain.edit();
//            JSONArray jsonArray = new JSONArray(getDataServerLogin);
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//
//                JSONObject object = jsonArray.getJSONObject(i);
//
//                editor.putInt("ID_User", object.getInt("ID"));
//                editor.putString("FirstName_User", object.getString("FirstName"));
//                editor.putString("LastName_User", object.getString("LastName"));
//                editor.putInt("PhoneNum_User", object.getInt("PhoneNum"));
//                editor.putString("StateName_User", object.getString("StateName"));
//                editor.putString("CityName_User", object.getString("CityName"));
//                editor.putInt("CodPosty_User", object.getInt("CodPosty"));
//                editor.putString("Address_User", object.getString("Address"));
//                editor.putString("Password_User", object.getString("Password"));
//                editor.putBoolean("statusLogin?", true);
//                editor.commit();
//            }
//
//        } catch (Exception e) {
//            Log.d("err", e.getMessage());
//        }
//    }
}
