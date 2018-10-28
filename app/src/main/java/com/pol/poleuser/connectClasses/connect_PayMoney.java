package com.pol.poleuser.connectClasses;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class connect_PayMoney extends AsyncTask {

    public String link = "";
    public int IDPost = 0;
    public int MetodPaimant = 0;
    private connect_PayMoney.IPayMoney _IPayMoneyResult;
    StringBuilder stringBuilder;

    public connect_PayMoney(String link, connect_PayMoney.IPayMoney result, int IDPost, int MetodPaimant) {
        this.link = link;
        this.IDPost = IDPost;
        this.MetodPaimant = MetodPaimant;
        _IPayMoneyResult = result;
    }

    public interface IPayMoney {
        public void getPayMoneyResult(String res);
    }


    @Override
    protected Object doInBackground(Object[] objects) {

        try {

            String sendData = URLEncoder.encode("IDPost", "UTF8") + "=" + URLEncoder.encode(String.valueOf(IDPost), "UTF8");
            sendData += "&" + URLEncoder.encode("MetodPaimant", "UTF8") + "=" + URLEncoder.encode(String.valueOf(MetodPaimant), "UTF8");

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

        if (_IPayMoneyResult != null) {
            _IPayMoneyResult.getPayMoneyResult(stringBuilder.toString());
        }

    }
}
