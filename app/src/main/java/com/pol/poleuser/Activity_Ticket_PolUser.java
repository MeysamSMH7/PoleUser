package com.pol.poleuser;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.pol.poleuser.classes.CalendarTool;
import com.pol.poleuser.connectClasses.connect_AcceptOne;
import com.pol.poleuser.connectClasses.connect_Ticket;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Activity_Ticket_PolUser extends AppCompatActivity {

    private AlertDialog ShowTicketAlert;
    private Spinner spnSubjectTicket;
    private EditText edtCustomSubjectTicket, edtTextTicket;
    private ListView lstSentTicket;

    private String[] subjects = {"انتخاب کنید", "مشکل1", "مشکل2", "مشکل3", "مشکل4", "متفرقه"};
    private ArrayAdapter adapterTicket;
    private String Subject = "", SubjectCustom = "", text = "", Year = "", Month = "", Day = "", Matn = "", MatnAlet = "";
    private int IDUser = 0;
    private CalendarTool calendarTool;
    private SharedPreferences preferencesTicketUser;
    private ArrayAdapter adapterSentTicket;

    private List<String> listTicket, listTicketAlert;
    private List<Integer> listTicketInt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_poluser);
        preferencesTicketUser = getSharedPreferences("polUser", 0);
        IDUser = preferencesTicketUser.getInt("ID_User", 0);

        listTicket = new ArrayList<>();
        listTicketAlert = new ArrayList<>();
        listTicketInt = new ArrayList<>();

        spnSubjectTicket = findViewById(R.id.spnSubjectTicket);
        edtCustomSubjectTicket = findViewById(R.id.edtCustomSubjectTicket);
        edtTextTicket = findViewById(R.id.edtTextTicket);
        lstSentTicket = findViewById(R.id.lstSentTicket);


        // Send Ticket **************************************************************************************
        calendarTool = new CalendarTool();

        adapterTicket = new ArrayAdapter(Activity_Ticket_PolUser.this, android.R.layout.simple_spinner_item, subjects);
        adapterTicket.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSubjectTicket.setAdapter(adapterTicket);

        spnSubjectTicket.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Subject = parent.getItemAtPosition(position).toString();

                if (position == 5) {
                    edtCustomSubjectTicket.setVisibility(View.VISIBLE);
                } else {
                    edtCustomSubjectTicket.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // Sent Ticket **************************************************************************************
        new connect_AcceptOne(getString(R.string.LinkSelectTicket), ishowAccOneRes, IDUser, 1).execute();

        lstSentTicket.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialogSentTicket(position);

            }
        });

    }

    // send ticket ****************************************************************************
    public void btnSendTicket(View view) {

        text = edtTextTicket.getText().toString() + "";

        if (!(Subject.equals("انتخاب کنید") || text.equals(""))) {
            if (Subject.equals("متفرقه")) {
                String CustomSubject = edtCustomSubjectTicket.getText().toString() + "";
                if (!CustomSubject.equals("")) {
                    SubjectCustom = "متفرقه - " + CustomSubject;
                    sendTicket(SubjectCustom);
                } else {
                    Toast.makeText(this, getText(R.string.ToastFillAllBlanks), Toast.LENGTH_SHORT).show();
                }
            } else {
                sendTicket(Subject);
            }
        } else {
            Toast.makeText(this, getText(R.string.ToastFillAllBlanks), Toast.LENGTH_SHORT).show();
        }


    }

    private void sendTicket(String subjectFun) {

        Year = calendarTool.getIranianYear() + "";
        Month = calendarTool.getIranianMonth() + "";
        Day = calendarTool.getIranianDay() + "";

        new connect_Ticket(getString(R.string.LinkTicket), ishowTicketRes, subjectFun, text, Year, Month, Day, "1", IDUser + "").execute();
    }

    connect_Ticket.IshowTicketRes ishowTicketRes = new connect_Ticket.IshowTicketRes() {
        @Override
        public void TicketUserResult(String res) {
            if (res.contains("OK!")) {
                Toast.makeText(Activity_Ticket_PolUser.this, getString(R.string.ToastTicketSend), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Activity_Ticket_PolUser.this, getString(R.string.ToastTicketNotSend), Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void btnBackTicket(View view) {
        finish();
    }

    // get ticket ******************************************************************************
    connect_AcceptOne.IAcceptOne ishowAccOneRes = new connect_AcceptOne.IAcceptOne() {
        @Override
        public void getAcceptOneResult(String res) {
            if (!(res.contains("[]") || res.contains("Nothing!"))) {
                GetJsonTicketSent(res);

                adapterSentTicket = new ArrayAdapter(Activity_Ticket_PolUser.this, R.layout.custom_listview_sentticket, listTicket) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        convertView = getLayoutInflater().inflate(R.layout.custom_listview_sentticket, parent, false);
                        TextView txtTextTicket = convertView.findViewById(R.id.txtTextTicket);
                        TextView txtAnswer = convertView.findViewById(R.id.txtAnswer);
                        txtTextTicket.setText(listTicket.get(position));

                        if (listTicketInt.get(position) == 0) {
                            txtAnswer.setText(getString(R.string.AnswerNotTicket));
                            txtAnswer.setTextColor(getResources().getColor(R.color.colorNotAnswer));
                        } else {
                            txtAnswer.setText(getString(R.string.AnswerTicket));
                            txtAnswer.setTextColor(getResources().getColor(R.color.colorAnswer));
                        }

                        return convertView;
                    }
                };
                lstSentTicket.setAdapter(adapterSentTicket);

            }
        }
    };

    private void GetJsonTicketSent(String res) {

        try {
            JSONArray jsonArray = new JSONArray(res);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject object = jsonArray.getJSONObject(i);

                int userID = object.getInt("userID");
                String subject = object.getString("subject");
                String text = object.getString("text");
                int day = object.getInt("day");
                int month = object.getInt("month");
                int year = object.getInt("year");
                int supportID = object.getInt("supportID");
                String textRes = object.getString("textRes");
                String dateSent = year + "/" + month + "/" + day;

                Matn = "" +
                        "موضوع: " + subject + "\n" +
                        "متن شما: " + text + "\n" +
                        "تاریخ ارسال شده: " + dateSent;

                if (supportID == 0) {
                    MatnAlet = "\n" +
                            "موضوع: " + subject + "\n" +
                            "متن شما: " + text + "\n" +
                            "تاریخ ارسال شده: " + dateSent + "\n";
                } else {
                    MatnAlet = "\n" +
                            "موضوع: " + subject + "\n" +
                            "متن شما: " + text + "\n" +
                            "تاریخ ارسال شده: " + dateSent + "\n" +
                            "ای دی پشتبان: " + supportID + "\n" +
                            "متن ما: " + textRes + "\n";
                }


                listTicket.add(Matn);
                listTicketAlert.add(MatnAlet);
                listTicketInt.add(supportID);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void AlertDialogSentTicket(int position) {

        AlertDialog.Builder builder_Ticket = new AlertDialog.Builder(Activity_Ticket_PolUser.this);
        LinearLayout layout_Ticket = (LinearLayout) getLayoutInflater().inflate(R.layout.custom_listview_sentticket, null, false);
        TextView txtTextTicket = layout_Ticket.findViewById(R.id.txtTextTicket);
        TextView txtAnswer = layout_Ticket.findViewById(R.id.txtAnswer);

        txtTextTicket.setText(listTicketAlert.get(position));

        if (listTicketInt.get(position) == 0) {
            txtAnswer.setText(getString(R.string.AnswerNotTicket));
            txtAnswer.setTextColor(getResources().getColor(R.color.colorNotAnswer));
        } else {
            txtAnswer.setText("");
        }

        builder_Ticket.setView(layout_Ticket);
        ShowTicketAlert = builder_Ticket.create();
        ShowTicketAlert.show();
    }

}
