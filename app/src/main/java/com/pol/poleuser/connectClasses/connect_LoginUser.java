package com.pol.poleuser.connectClasses;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class connect_LoginUser extends AsyncTask {

    public String link = "";
    public String PhoneNum = "";
    public String PassWord = "";
    private connect_LoginUser.IshowLoginRes _IAddUserResult;
    StringBuilder stringBuilder;

    public connect_LoginUser(String link, connect_LoginUser.IshowLoginRes result, String PhoneNum, String PassWord) {
        this.link = link;
        this.PhoneNum = PhoneNum;
        this.PassWord = PassWord;
        _IAddUserResult = result;
    }

    public interface IshowLoginRes {
        public void loginUserResult(String res);
    }


    @Override
    protected Object doInBackground(Object[] objects) {

        try {

            String sendData = URLEncoder.encode("PhoneNum", "UTF8") + "=" + URLEncoder.encode(PhoneNum, "UTF8");
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

        if (_IAddUserResult != null) {
            _IAddUserResult.loginUserResult(stringBuilder.toString());
        }

    }
}
