package com.pol.poleuser.connectClasses;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class connect_GetPass extends AsyncTask {

    public String link = "";
    public String PhoneNum = "";
    private connect_GetPass.IgetPassRes _IGetPassUserResult;
    StringBuilder stringBuilder;

    public connect_GetPass(String link, connect_GetPass.IgetPassRes result, String PhoneNum) {
        this.link = link;
        this.PhoneNum = PhoneNum;
        _IGetPassUserResult = result;
    }

    public interface IgetPassRes {
        public void getPassUserResult(String res);
    }


    @Override
    protected Object doInBackground(Object[] objects) {

        try {

            String sendData = URLEncoder.encode("PhoneNum", "UTF8") + "=" + URLEncoder.encode(PhoneNum, "UTF8");

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

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        try {
            if (_IGetPassUserResult != null) {
                _IGetPassUserResult.getPassUserResult(stringBuilder.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
