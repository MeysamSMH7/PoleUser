package com.pol.poleuser.connectClasses;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class connect_AcceptOne extends AsyncTask {

    public String link = "";
    public int UserID = 0;
    public int IDPost = 0;
    private connect_AcceptOne.IAcceptOne _IAcceptOneResult;
    StringBuilder stringBuilder;

    public connect_AcceptOne(String link, connect_AcceptOne.IAcceptOne result, int UserID, int IDPost) {
        this.link = link;
        this.UserID = UserID;
        this.IDPost = IDPost;
        _IAcceptOneResult = result;
    }

    public interface IAcceptOne {
        public void getAcceptOneResult(String res);
    }


    @Override
    protected Object doInBackground(Object[] objects) {

        try {

            String sendData = URLEncoder.encode("UserID", "UTF8") + "=" + URLEncoder.encode(String.valueOf(UserID), "UTF8");
            sendData += "&" + URLEncoder.encode("IDPost", "UTF8") + "=" + URLEncoder.encode(String.valueOf(IDPost), "UTF8");

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

        if (_IAcceptOneResult != null) {
            _IAcceptOneResult.getAcceptOneResult(stringBuilder.toString());
        }

    }
}
