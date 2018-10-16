package com.pol.poleuser;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Activity_Login_PolUser extends AppCompatActivity {

    //LinearLayout
    LinearLayout LinearLogin, LinearRegPhoneNum, LinearRegCheckCode, LinearRegFinish, LinearRegForgetPass;


    //Login
    EditText edtPhoneNumLogin, edtPassLogin;
    Button btnRegisterLogin;

    //LinearRegPhoneNum
    EditText edtPhoneNumRegister;
    Button btnSendCode;

    //LinearRegCheckCode
    TextView txtPhoneNum, txtTimer , txtShowCode;
    EditText edtGetCode;
    Button btnCheckCode;

    //LinearRegFinish
    EditText edtFirstName, edtLastName, edtPhoneNumber, edtPassword, edtRePassword, edtPostalCode, edtAddress;
    Spinner spnState, spnCity;
    Button btnFinishRegister;

    //LinearRegForgetPass
    EditText edtPhoneNumberForget;
    TextView txtYourPass;
    Button btnGetPass;


    //public Variable
    private CountDownTimer countDownTimer;
    private String ranNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_poluser);

        //LinearLayout
        LinearLogin = (LinearLayout) findViewById(R.id.LinearLogin);
        LinearRegPhoneNum = (LinearLayout) findViewById(R.id.LinearRegPhoneNum);
        LinearRegCheckCode = (LinearLayout) findViewById(R.id.LinearRegCheckCode);
        LinearRegFinish = (LinearLayout) findViewById(R.id.LinearRegFinish);
        LinearRegForgetPass = (LinearLayout) findViewById(R.id.LinearRegForgetPass);

        //Login
        edtPhoneNumLogin = (EditText) findViewById(R.id.edtPhoneNumLogin);
        edtPassLogin = (EditText) findViewById(R.id.edtPassLogin);
        btnRegisterLogin = (Button) findViewById(R.id.btnRegisterLogin);

        //LinearRegPhoneNum
        edtPhoneNumRegister = (EditText) findViewById(R.id.edtPhoneNumRegister);
        btnSendCode = (Button) findViewById(R.id.btnSendCode);

        //LinearRegCheckCode
        txtPhoneNum = (TextView) findViewById(R.id.txtPhoneNum);
        txtTimer = (TextView) findViewById(R.id.txtTimer);
        txtShowCode = (TextView) findViewById(R.id.txtShowCode);
        edtGetCode = (EditText) findViewById(R.id.edtGetCode);
        btnCheckCode = (Button) findViewById(R.id.btnCheckCode);

        //LinearRegFinish
        edtFirstName = (EditText) findViewById(R.id.edtFirstName);
        edtLastName = (EditText) findViewById(R.id.edtLastName);
        edtPhoneNumber = (EditText) findViewById(R.id.edtPhoneNumber);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtRePassword = (EditText) findViewById(R.id.edtRePassword);
        edtPostalCode = (EditText) findViewById(R.id.edtPostalCode);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        spnState = (Spinner) findViewById(R.id.spnState);
        spnCity = (Spinner) findViewById(R.id.spnCity);
        btnFinishRegister = (Button) findViewById(R.id.btnFinishRegister);

        //LinearRegForgetPass
        edtPhoneNumberForget = (EditText) findViewById(R.id.edtPhoneNumberForget);
        txtYourPass = (TextView) findViewById(R.id.txtYourPass);
        btnGetPass = (Button) findViewById(R.id.btnGetPass);

        LinearLogin.setVisibility(View.VISIBLE);
        LinearRegPhoneNum.setVisibility(View.GONE);
        LinearRegCheckCode.setVisibility(View.GONE);
        LinearRegFinish.setVisibility(View.GONE);
        LinearRegForgetPass.setVisibility(View.GONE);

        //Login
        btnRegisterLogin.setOnClickListener(btnRegisterLoginOnClick);

        //LinearRegPhoneNum
        btnSendCode.setOnClickListener(btnSendCodeOnClick);


        //LinearRegCheckCode
        btnCheckCode.setOnClickListener(btnCheckCodeOnClick);


    }


    //Login **************************************************************************

    public void btnLoginOnClick(View view) {

    }

    View.OnClickListener btnRegisterLoginOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LinearLogin.setVisibility(View.GONE);
            LinearRegPhoneNum.setVisibility(View.VISIBLE);
            LinearRegCheckCode.setVisibility(View.GONE);
            LinearRegFinish.setVisibility(View.GONE);
            LinearRegForgetPass.setVisibility(View.GONE);
        }
    };


    //LinearRegPhoneNum **********************************************************************

    public void btnBackOnClickRegister(View view) {
        LinearLogin.setVisibility(View.VISIBLE);
        LinearRegPhoneNum.setVisibility(View.GONE);
        LinearRegCheckCode.setVisibility(View.GONE);
        LinearRegFinish.setVisibility(View.GONE);
        LinearRegForgetPass.setVisibility(View.GONE);
    }

    View.OnClickListener btnSendCodeOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String checkNum = edtPhoneNumRegister.getText().toString();

            if (checkNum.isEmpty() || checkNum.trim().length() == 0){
                Toast.makeText(Activity_Login_PolUser.this, "لطفا شماره تلفن خود را وارد کنید!", Toast.LENGTH_SHORT).show();
            }else {

                ranNum = RandomNum();

                LinearLogin.setVisibility(View.GONE);
                LinearRegPhoneNum.setVisibility(View.GONE);
                LinearRegCheckCode.setVisibility(View.VISIBLE);
                LinearRegFinish.setVisibility(View.GONE);
                LinearRegForgetPass.setVisibility(View.GONE);

                countDownTimer = new CountDownTimer(120000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        String timer = String.format(Locale.getDefault(), " %02d " + getString(R.string.min) + " , %02d " + getString(R.string.sec),
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60);


                        txtTimer.setText(timer);
                    }

                    @Override
                    public void onFinish() {
                        txtTimer.setText(R.string.finishTimer);
                    }
                }.start();


                txtPhoneNum.setText("شماره تلفن شما" + "\n" +
                        edtPhoneNumRegister.getText().toString().trim().replaceAll(" ","")
                );

                txtShowCode.setText(ranNum);

            }
        }
    };


    //LinearRegCheckCode *******************************************************************************

    public void btnBackOnClickWrongNum(View view) {
        LinearLogin.setVisibility(View.GONE);
        LinearRegPhoneNum.setVisibility(View.VISIBLE);
        LinearRegCheckCode.setVisibility(View.GONE);
        LinearRegFinish.setVisibility(View.GONE);
        LinearRegForgetPass.setVisibility(View.GONE);

        countDownTimer.cancel();

    }

    View.OnClickListener btnCheckCodeOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (txtTimer.getText().toString() == getString(R.string.finishTimer)){
                LinearLogin.setVisibility(View.GONE);
                LinearRegPhoneNum.setVisibility(View.VISIBLE);
                LinearRegCheckCode.setVisibility(View.GONE);
                LinearRegFinish.setVisibility(View.GONE);
                LinearRegForgetPass.setVisibility(View.GONE);

                countDownTimer.cancel();
            }else if (edtGetCode.getText().toString().equals(ranNum)){
                Toast.makeText(Activity_Login_PolUser.this, "کد صحیح می باشد!", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(Activity_Login_PolUser.this, "کد غلط می باشد!", Toast.LENGTH_SHORT).show();
            }
        }
    };





    public void btnBackOnClickFinish(View view) {

    }

    public void btnBackOnClickForgetPass(View view) {

    }

    //Random Number ***************************************************************

    private String RandomNum(){
        String Num = "";

        for (int i = 0 ; i < 5 ; i++){
            int a = (int)((Math.random()*((9-1)+1))+1);
            Num = Num + a;
        }

        return Num;
    }



}
