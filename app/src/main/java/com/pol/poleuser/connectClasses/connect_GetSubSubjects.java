package com.pol.poleuser.connectClasses;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class connect_GetSubSubjects extends AsyncTask {

    public String link = "";
    public String stateName = "";
    private connect_GetSubSubjects.IgetSubRes _IGetSubSujectsResult;
    StringBuilder stringBuilder;

    public connect_GetSubSubjects(String link, connect_GetSubSubjects.IgetSubRes result, String stateName) {
        this.link = link;
        this.stateName = stateName;
        _IGetSubSujectsResult = result;
    }

    public interface IgetSubRes {
        public void getSubSujectsResult(String res);
    }


    @Override
    protected Object doInBackground(Object[] objects) {

        try {

            String sendData = URLEncoder.encode("State", "UTF8") + "=" + URLEncoder.encode(stateName, "UTF8");

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
            if (_IGetSubSujectsResult != null) {
                _IGetSubSujectsResult.getSubSujectsResult(stringBuilder.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
