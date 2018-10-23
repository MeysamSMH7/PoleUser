package com.pol.poleuser.connectClasses;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class connect_SubmitReq extends AsyncTask {

    public String link = "";
    public String SubjectServer = "";
    public String DateDayServer = "";
    public String DateMonthServer = "";
    public String NameWeekServer = "";
    public String DateYearServer = "";
    public String PeriodTimeServer = "";
    public String AddressServer = "";
    public String UserIDServer = "";
    public String txtServer = "";
    public String StateNameServer = "";

    private connect_SubmitReq.ISubmitReq _ISubmitReq;
    StringBuilder stringBuilder;

    public connect_SubmitReq(String link, connect_SubmitReq.ISubmitReq result, String SubjectServer, String DateDayServer, String DateMonthServer, String NameWeekServer, String DateYearServer, String PeriodTimeServer, String AddressServer, String UserIDServer, String txtServer, String StateNameServer) {
        this.link = link;
        this.SubjectServer = SubjectServer;
        this.DateDayServer = DateDayServer;
        this.DateMonthServer = DateMonthServer;
        this.NameWeekServer = NameWeekServer;
        this.DateYearServer = DateYearServer;
        this.PeriodTimeServer = PeriodTimeServer;
        this.AddressServer = AddressServer;
        this.UserIDServer = UserIDServer;
        this.txtServer = txtServer;
        this.StateNameServer = StateNameServer;
        _ISubmitReq = result;
    }

    public interface ISubmitReq {
        public void SubmitReqResult(String res);
    }


    @Override
    protected Object doInBackground(Object[] objects) {

        try {

            String sendData = URLEncoder.encode("Subject", "UTF8") + "=" + URLEncoder.encode(SubjectServer, "UTF8");
            sendData += "&" + URLEncoder.encode("DateDay", "UTF8") + "=" + URLEncoder.encode(DateDayServer, "UTF8");
            sendData += "&" + URLEncoder.encode("DateMonth", "UTF8") + "=" + URLEncoder.encode(DateMonthServer, "UTF8");
            sendData += "&" + URLEncoder.encode("NameWeek", "UTF8") + "=" + URLEncoder.encode(NameWeekServer, "UTF8");
            sendData += "&" + URLEncoder.encode("DateYear", "UTF8") + "=" + URLEncoder.encode(DateYearServer, "UTF8");
            sendData += "&" + URLEncoder.encode("PeriodTime", "UTF8") + "=" + URLEncoder.encode(PeriodTimeServer, "UTF8");
            sendData += "&" + URLEncoder.encode("Address", "UTF8") + "=" + URLEncoder.encode(AddressServer, "UTF8");
            sendData += "&" + URLEncoder.encode("UserID", "UTF8") + "=" + URLEncoder.encode(UserIDServer, "UTF8");
            sendData += "&" + URLEncoder.encode("txt", "UTF8") + "=" + URLEncoder.encode(txtServer, "UTF8");
            sendData += "&" + URLEncoder.encode("StateName", "UTF8") + "=" + URLEncoder.encode(StateNameServer, "UTF8");

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
            if (_ISubmitReq != null) {
                _ISubmitReq.SubmitReqResult(stringBuilder.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
