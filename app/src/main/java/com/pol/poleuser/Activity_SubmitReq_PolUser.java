package com.pol.poleuser;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pol.poleuser.Adapters.Adpater_Time;
import com.pol.poleuser.classes.CalendarTool;
import com.pol.poleuser.connectClasses.connect_SubmitReq;

import java.util.ArrayList;
import java.util.Calendar;

public class Activity_SubmitReq_PolUser extends AppCompatActivity {

    //Public Variable ****************************************************************
    private TextView txtShowReqSubmitReq;
    private EditText edtExplainSubmitReq, edtAddressSubmitReq;
    private RadioGroup radioGPSubmitReq;
    private Button btnsubmitSubmitReq;
    private Adpater_Time adpater_time1;
    private GridView gridViewDate;
    private Calendar calendar;
    private int hour;
    private boolean IsCurrentday = false, IsDateClicked = false;
    private String SubjectServer = "", DateDayServer = "", DateMonthServer = "", NameWeekServer = "", DateYearServer = "", PeriodTimeServer = "", AddressServer = "", UserIDServer = "", txtServer = "", StateNameServer = "";
    private SharedPreferences preferencesLogin;
    boolean TimeIsTrue = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitreq_poluser);
        preferencesLogin = getSharedPreferences("polUser", 0);
        StateNameServer = preferencesLogin.getString("StateName_User", "");
        AddressServer = preferencesLogin.getString("Address_User", "");
        UserIDServer = preferencesLogin.getInt("ID_User", 0) + "";
        SubjectServer = getIntent().getStringExtra("Subject");

        //Create Objects *************************************************************************
        CalendarTool calendarTool = new CalendarTool();
        txtShowReqSubmitReq = findViewById(R.id.txtShowReqSubmitReq);
        edtExplainSubmitReq = findViewById(R.id.edtExplainSubmitReq);
        edtAddressSubmitReq = findViewById(R.id.edtAddressSubmitReq);
        radioGPSubmitReq = findViewById(R.id.radioGPSubmitReq);
        btnsubmitSubmitReq = findViewById(R.id.btnsubmitSubmitReq);
        txtShowReqSubmitReq.setText(SubjectServer + "");
        edtAddressSubmitReq.setText(AddressServer + "");

        final ArrayList<Integer> arrayListDayS = new ArrayList<>();
        final ArrayList<String> arrayListMonthS = new ArrayList<>();
        final ArrayList<Integer> arrayListYearS = new ArrayList<>();
        final ArrayList<String> arrayListWeekName = new ArrayList<>();

        //Get 4 days *************************************************************************
        calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (hour > 20) {
            calendarTool.nextDay(1);
            IsCurrentday = false;
        }
        for (int i = 0; i < 4; i++) {
            int Day = calendarTool.getIranianDay();
            int Year = calendarTool.getIranianYear();
            String NameMoth = calendarTool.getIranianMonthStr();
            String NameWeek = calendarTool.getIranianWeekDayStr();

            arrayListDayS.add(Day);
            arrayListMonthS.add(NameMoth);
            arrayListYearS.add(Year);
            arrayListWeekName.add(NameWeek);

            calendarTool.nextDay(1);
        }

        //Grid View Object *******************************************************************
        gridViewDate = (GridView) findViewById(R.id.gridViewDate);
        adpater_time1 = new Adpater_Time(this, arrayListWeekName, arrayListDayS, arrayListMonthS, arrayListYearS);
        gridViewDate.setAdapter(adpater_time1);

        //GridView Click Items ***************************************************************
        gridViewDate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                DateDayServer = arrayListDayS.get(position).toString();
                DateMonthServer = arrayListMonthS.get(position).toString();
                NameWeekServer = arrayListWeekName.get(position).toString();
                DateYearServer = arrayListYearS.get(position).toString();
                IsDateClicked = true;
                if (position == 0) {
                    IsCurrentday = true;
                } else {
                    IsCurrentday = false;
                }
            }
        });

        gridViewDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return event.getAction() == MotionEvent.ACTION_MOVE;
            }
        });

        //Period Time ************************************************************************
        radioGPSubmitReq.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radiobtnSubmitReq1:
                        if (!(IsDateClicked)) {
                            Toast.makeText(Activity_SubmitReq_PolUser.this, getString(R.string.ToastChoseDate), Toast.LENGTH_SHORT).show();
                            radioGPSubmitReq.clearCheck();
                        } else if (IsCurrentday) {
                            if (hour < 12) {
                                PeriodTimeServer = getString(R.string.radiobtnPeriodTime1SubReq);
                            } else {
                                Toast.makeText(Activity_SubmitReq_PolUser.this, getString(R.string.ToastNotThisTime), Toast.LENGTH_SHORT).show();
                                radioGPSubmitReq.clearCheck();
                            }
                        } else {
                            PeriodTimeServer = getString(R.string.radiobtnPeriodTime1SubReq);
                            TimeIsTrue = true;
                        }

                        break;
                    case R.id.radiobtnSubmitReq2:
                        if (!(IsDateClicked)) {
                            Toast.makeText(Activity_SubmitReq_PolUser.this, getString(R.string.ToastChoseDate), Toast.LENGTH_SHORT).show();
                            radioGPSubmitReq.clearCheck();
                        } else if (IsCurrentday) {
                            if (hour < 17) {
                                PeriodTimeServer = getString(R.string.radiobtnPeriodTime2SubReq);
                            } else {
                                Toast.makeText(Activity_SubmitReq_PolUser.this, getString(R.string.ToastNotThisTime), Toast.LENGTH_SHORT).show();
                                radioGPSubmitReq.clearCheck();
                            }
                        } else {
                            PeriodTimeServer = getString(R.string.radiobtnPeriodTime2SubReq);
                            TimeIsTrue = true;
                        }

                        break;
                    case R.id.radiobtnSubmitReq3:
                        if (!(IsDateClicked)) {
                            Toast.makeText(Activity_SubmitReq_PolUser.this, getString(R.string.ToastChoseDate), Toast.LENGTH_SHORT).show();
                            radioGPSubmitReq.clearCheck();
                        } else if (IsCurrentday) {
                            if (hour < 20) {
                                PeriodTimeServer = getString(R.string.radiobtnPeriodTime3SubReq);
                            } else {
                                Toast.makeText(Activity_SubmitReq_PolUser.this, getString(R.string.ToastNotThisTime), Toast.LENGTH_SHORT).show();
                                radioGPSubmitReq.clearCheck();
                            }
                        } else {
                            PeriodTimeServer = getString(R.string.radiobtnPeriodTime3SubReq);
                            TimeIsTrue = true;
                        }
                        break;
                }
            }
        });


    }


    public void SubmitReq(View view) {
        txtServer = edtExplainSubmitReq.getText().toString();


        if (txtServer.equals("") || AddressServer.equals("")) {
            Toast.makeText(this, getString(R.string.ToastFillAllBlanks), Toast.LENGTH_SHORT).show();
        } else if (!TimeIsTrue) {
            Toast.makeText(this, getString(R.string.ToastTimeIsNotRight), Toast.LENGTH_SHORT).show();
        } else {
            new connect_SubmitReq(getString(R.string.LinkRequestUser), resultSubReq, SubjectServer, DateDayServer, DateMonthServer, NameWeekServer, DateYearServer, PeriodTimeServer, AddressServer, UserIDServer, txtServer, StateNameServer).execute();
        }


    }

    connect_SubmitReq.ISubmitReq resultSubReq = new connect_SubmitReq.ISubmitReq() {
        @Override
        public void SubmitReqResult(String res) {
            Toast.makeText(Activity_SubmitReq_PolUser.this, res + "", Toast.LENGTH_SHORT).show();
            finish();
        }
    };

}
