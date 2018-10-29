package com.pol.poleuser.connectClasses;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class connect_SendSms extends AsyncTask {

    public String link = "";
    public String PhoneNum = "";
    public String Code = "";
    private connect_SendSms.ISendSmsRes _ISendSmsResult;
    StringBuilder stringBuilder;

    public connect_SendSms(String link, connect_SendSms.ISendSmsRes result, String PhoneNum, String Code) {
        this.link = link;
        this.PhoneNum = PhoneNum;
        this.Code = Code;
        _ISendSmsResult = result;
    }

    public interface ISendSmsRes {
        public void loginUserResult(String res);
    }


    @Override
    protected Object doInBackground(Object[] objects) {

        try {

            String sendData = URLEncoder.encode("PhoneNum", "UTF8") + "=" + URLEncoder.encode(PhoneNum, "UTF8");
            sendData += "&" + URLEncoder.encode("Code", "UTF8") + "=" + URLEncoder.encode(Code, "UTF8");

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


//            Activity_Login_PolUser.getData_SendSms = stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        try {
            if (_ISendSmsResult != null) {
                _ISendSmsResult.loginUserResult(stringBuilder.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
