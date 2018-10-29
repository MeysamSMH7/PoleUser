package com.pol.poleuser.connectClasses;

import android.os.AsyncTask;

import com.pol.poleuser.Activity_Login_PolUser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class connect_addUser extends AsyncTask {
    public String link = "";
    public String FirstName = "";
    public String LastName = "";
    public String PhoneNum = "";
    public String PassWord = "";
    public String CodPosty = "";
    public String StateName = "";
    public String CityName = "";
    public String Address = "";
    private IshowAddUserRes _IAddUserResult;
    StringBuilder stringBuilder;

    public connect_addUser(String link, IshowAddUserRes result, String FirstName, String LastName, String PhoneNum, String PassWord, String CodPosty,
                           String StateName, String CityName, String Address) {
        this.link = link;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.PhoneNum = PhoneNum;
        this.PassWord = PassWord;
        this.CodPosty = CodPosty;
        this.StateName = StateName;
        this.CityName = CityName;
        this.Address = Address;
        _IAddUserResult = result;
    }

    public interface IshowAddUserRes {
        public void addUserResult(String res);
    }


    @Override
    protected Object doInBackground(Object[] objects) {

        try {

            String sendData = URLEncoder.encode("FirstName", "UTF8") + "=" + URLEncoder.encode(FirstName, "UTF8");
            sendData += "&" + URLEncoder.encode("LastName", "UTF8") + "=" + URLEncoder.encode(LastName, "UTF8");
            sendData += "&" + URLEncoder.encode("PhoneNum", "UTF8") + "=" + URLEncoder.encode(PhoneNum, "UTF8");
            sendData += "&" + URLEncoder.encode("StateName", "UTF8") + "=" + URLEncoder.encode(StateName, "UTF8");
            sendData += "&" + URLEncoder.encode("CityName", "UTF8") + "=" + URLEncoder.encode(CityName, "UTF8");
            sendData += "&" + URLEncoder.encode("CodPosty", "UTF8") + "=" + URLEncoder.encode(CodPosty, "UTF8");
            sendData += "&" + URLEncoder.encode("Address", "UTF8") + "=" + URLEncoder.encode(Address, "UTF8");
            sendData += "&" + URLEncoder.encode("Password", "UTF8") + "=" + URLEncoder.encode(PassWord, "UTF8");

            URL url = new URL(link);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(sendData);

            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();

            String data = null;

            while ((data = reader.readLine()) != null) {
                stringBuilder.append(data);
            }


//            Activity_Login_PolUser.getData_AddUser = stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        try {
            if (_IAddUserResult != null) {
                _IAddUserResult.addUserResult(stringBuilder.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
