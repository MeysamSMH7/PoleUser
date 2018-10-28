package com.pol.poleuser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pol.poleuser.classes.SpinnerClass;
import com.pol.poleuser.connectClasses.connect_GetPass;
import com.pol.poleuser.connectClasses.connect_LoginUser;
import com.pol.poleuser.connectClasses.connect_SendSms;
import com.pol.poleuser.connectClasses.connect_addUser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Activity_Login_PolUser extends AppCompatActivity {

    //LinearLayout
    private LinearLayout LinearLogin, LinearRegPhoneNum, LinearRegCheckCode, LinearRegFinish, LinearRegForgetPass;

    //Login
    private EditText edtPhoneNumLogin, edtPassLogin;

    //LinearRegPhoneNum
    private EditText edtPhoneNumRegister;

    //LinearRegCheckCode
    private TextView txtPhoneNum, txtTimer, txtShowCode;
    private EditText edtGetCode;

    //LinearRegFinish
    private EditText edtFirstName, edtLastName, edtPhoneNumber, edtPassword, edtRePassword, edtPostalCode, edtAddress;
    private Spinner spnState, spnCity;

    //LinearRegForgetPass
    private EditText edtPhoneNumberForget;
    private TextView txtYourPass;

    //public Variable
    private CountDownTimer countDownTimer;
    private String ranNum;
    private int countbtnClick = 0;
    private String FirstName = "", LastName = "", PhoneNum = "", PassWord = "", CodPosty = "", StateName = "", CityName = "", Address = "";
    public String getDataServerLogin = "";
    private SharedPreferences preferencesLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_poluser);
        preferencesLogin = getSharedPreferences("polUser", 0);

        //LinearLayout
        LinearLogin = (LinearLayout) findViewById(R.id.LinearLogin);
        LinearRegPhoneNum = (LinearLayout) findViewById(R.id.LinearRegPhoneNum);
        LinearRegCheckCode = (LinearLayout) findViewById(R.id.LinearRegCheckCode);
        LinearRegFinish = (LinearLayout) findViewById(R.id.LinearRegFinish);
        LinearRegForgetPass = (LinearLayout) findViewById(R.id.LinearRegForgetPass);

        //Login
        edtPhoneNumLogin = (EditText) findViewById(R.id.edtPhoneNumLogin);
        edtPassLogin = (EditText) findViewById(R.id.edtPassLogin);

        //LinearRegPhoneNum
        edtPhoneNumRegister = (EditText) findViewById(R.id.edtPhoneNumRegister);

        //LinearRegCheckCode
        txtPhoneNum = (TextView) findViewById(R.id.txtPhoneNum);
        txtTimer = (TextView) findViewById(R.id.txtTimer);
        txtShowCode = (TextView) findViewById(R.id.txtShowCode);
        edtGetCode = (EditText) findViewById(R.id.edtGetCode);

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

        //LinearRegForgetPass
        edtPhoneNumberForget = (EditText) findViewById(R.id.edtPhoneNumberForget);
        txtYourPass = (TextView) findViewById(R.id.txtYourPass);


        LinearLogin.setVisibility(View.VISIBLE);
        LinearRegPhoneNum.setVisibility(View.GONE);
        LinearRegCheckCode.setVisibility(View.GONE);
        LinearRegFinish.setVisibility(View.GONE);
        LinearRegForgetPass.setVisibility(View.GONE);

    }


    //Login **************************************************************************

    public void btnLoginOnClick(View view) {

        PhoneNum = edtPhoneNumLogin.getText().toString();
        PassWord = edtPassLogin.getText().toString();

        new connect_LoginUser(getString(R.string.LinkLoginUser), resultLogin, PhoneNum, PassWord).execute();

    }

    public void btnRegisterLogin(View view) {
        LinearLogin.setVisibility(View.GONE);
        LinearRegPhoneNum.setVisibility(View.VISIBLE);
        LinearRegCheckCode.setVisibility(View.GONE);
        LinearRegFinish.setVisibility(View.GONE);
        LinearRegForgetPass.setVisibility(View.GONE);
    }

    connect_LoginUser.IshowLoginRes resultLogin = new connect_LoginUser.IshowLoginRes() {
        @Override
        public void loginUserResult(String res) {

            getDataServerLogin = res;
            if (!res.contains("[]")) {
                if (res.contains("NO!")) {
                    Toast.makeText(Activity_Login_PolUser.this, "شماره تلفن یا پسورد شما اشتباه می باشد!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Activity_Login_PolUser.this, "خوش اومدی!", Toast.LENGTH_SHORT).show();

                    GetJSONArrayLogin();

                    Intent intent = new Intent(Activity_Login_PolUser.this, Activity_main_PolUser.class);
                    startActivity(intent);
                    finish();

                }
            }else {
                Toast.makeText(Activity_Login_PolUser.this, "هیچی پر نکردید!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    //LinearRegPhoneNum **********************************************************************

    public void btnBackOnClickRegister(View view) {
        LinearLogin.setVisibility(View.VISIBLE);
        LinearRegPhoneNum.setVisibility(View.GONE);
        LinearRegCheckCode.setVisibility(View.GONE);
        LinearRegFinish.setVisibility(View.GONE);
        LinearRegForgetPass.setVisibility(View.GONE);

        edtPhoneNumRegister.setText("");
    }

    public void btnSendCode(View view) {
        edtGetCode.setText("");

        String checkNum = edtPhoneNumRegister.getText().toString();

        if (checkNum.isEmpty() || checkNum.trim().length() == 0) {
            Toast.makeText(Activity_Login_PolUser.this, "لطفا شماره تلفن خود را وارد کنید!", Toast.LENGTH_SHORT).show();
        } else {

            ranNum = RandomNum();
            String Phone = edtPhoneNumRegister.getText().toString().trim().replaceAll(" ", "");

            new connect_SendSms(getString(R.string.LinkSendSms), iSendSmsRes , Phone+"" , ranNum+"").execute();

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
                    edtPhoneNumRegister.getText().toString().trim().replaceAll(" ", "")
            );

            //txtShowCode.setText(ranNum);


        }
    }

    connect_SendSms.ISendSmsRes iSendSmsRes = new connect_SendSms.ISendSmsRes() {
        @Override
        public void loginUserResult(String res) {
            Toast.makeText(Activity_Login_PolUser.this, res+"", Toast.LENGTH_SHORT).show();
        }
    };






    public void txtForgetPass(View view) {
        LinearLogin.setVisibility(View.GONE);
        LinearRegPhoneNum.setVisibility(View.GONE);
        LinearRegCheckCode.setVisibility(View.GONE);
        LinearRegFinish.setVisibility(View.GONE);
        LinearRegForgetPass.setVisibility(View.VISIBLE);
    }

    //LinearRegCheckCode *******************************************************************************

    public void btnBackOnClickWrongNum(View view) {
        LinearLogin.setVisibility(View.GONE);
        LinearRegPhoneNum.setVisibility(View.VISIBLE);
        LinearRegCheckCode.setVisibility(View.GONE);
        LinearRegFinish.setVisibility(View.GONE);
        LinearRegForgetPass.setVisibility(View.GONE);

        countDownTimer.cancel();

        edtPhoneNumRegister.setText("");
    }

    public void btnCheckCode(View view) {
        if (txtTimer.getText().toString().equals(getString(R.string.finishTimer))) {
            LinearLogin.setVisibility(View.GONE);
            LinearRegPhoneNum.setVisibility(View.VISIBLE);
            LinearRegCheckCode.setVisibility(View.GONE);
            LinearRegFinish.setVisibility(View.GONE);
            LinearRegForgetPass.setVisibility(View.GONE);

            countDownTimer.cancel();
        } else if (edtGetCode.getText().toString().equals(ranNum)) {
            edtPhoneNumber.setText(edtPhoneNumRegister.getText().toString().trim().replaceAll(" ", ""));
            edtPhoneNumber.setEnabled(false);
            LinearLogin.setVisibility(View.GONE);
            LinearRegPhoneNum.setVisibility(View.GONE);
            LinearRegCheckCode.setVisibility(View.GONE);
            LinearRegFinish.setVisibility(View.VISIBLE);
            LinearRegForgetPass.setVisibility(View.GONE);

            //Spinner for Register
            SpinnerClass spinner = new SpinnerClass(Activity_Login_PolUser.this, spnState, spnCity);
            spinner.spinner();

        } else if (countbtnClick < 5) {
            countbtnClick++;
            Toast.makeText(Activity_Login_PolUser.this, "کد غلط می باشد!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Activity_Login_PolUser.this, "بیش از اندازه امتحان کردید!", Toast.LENGTH_SHORT).show();
        }
    }

    //LinearRegFinish ****************************************************************************

    public void btnBackOnClickFinish(View view) {
        finish();
    }

    public void btnFinishRegister(View view) {
        FirstName = edtFirstName.getText().toString();
        LastName = edtLastName.getText().toString();
        PhoneNum = edtPhoneNumber.getText().toString();
        PassWord = edtPassword.getText().toString();
        CodPosty = edtPostalCode.getText().toString();
        StateName = SpinnerClass.StateName;
        CityName = SpinnerClass.CityName;
        Address = edtAddress.getText().toString();


        new connect_addUser(getString(R.string.LinkAddUser), resultAddUser, FirstName, LastName, PhoneNum, PassWord, CodPosty, StateName, CityName, Address).execute();


    }

    connect_addUser.IshowAddUserRes resultAddUser = new connect_addUser.IshowAddUserRes() {
        @Override
        public void addUserResult(String res) {

            if (res.contains("ok!")) {

                SharedPreferences.Editor editor = preferencesLogin.edit();
                editor.putBoolean("statusLogin?", true);
                editor.commit();

                Toast.makeText(Activity_Login_PolUser.this, getText(R.string.doneRegister), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Activity_Login_PolUser.this, Activity_main_PolUser.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(Activity_Login_PolUser.this, getText(R.string.doneNotRegister), Toast.LENGTH_SHORT).show();
            }
        }
    };

//LinearRegForgetPass **************************************************************************

    public void btnBackOnClickForgetPass(View view) {
        LinearLogin.setVisibility(View.VISIBLE);
        LinearRegPhoneNum.setVisibility(View.GONE);
        LinearRegCheckCode.setVisibility(View.GONE);
        LinearRegFinish.setVisibility(View.GONE);
        LinearRegForgetPass.setVisibility(View.GONE);

        edtPhoneNumberForget.setText("");
    }

    public void btnGetPass(View view) {

        PhoneNum = edtPhoneNumberForget.getText().toString();

        new connect_GetPass(getString(R.string.LinkGetPass), resultGetPass, PhoneNum).execute();

    }

    connect_GetPass.IgetPassRes resultGetPass = new connect_GetPass.IgetPassRes() {
        @Override
        public void getPassUserResult(String res) {

            if (res.contains("no!")) {
                Toast.makeText(Activity_Login_PolUser.this, "شماره وارد شده موجود نمی باشد!", Toast.LENGTH_SHORT).show();
                edtPhoneNumberForget.setText("");
                txtYourPass.setText("");
            } else {
                txtYourPass.setText("your password:\n" + res);
            }

        }
    };

//Random Number ***************************************************************

    private String RandomNum() {
        String Num = "";

        for (int i = 0; i < 5; i++) {
            int a = (int) ((Math.random() * ((9 - 1) + 1)) + 1);
            Num = Num + a;
        }

        return Num;
    }



//GetJson *****************************************************************************

    public void GetJSONArrayLogin() {

        try {
            SharedPreferences.Editor editor = preferencesLogin.edit();
            JSONArray jsonArray = new JSONArray(getDataServerLogin);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject object = jsonArray.getJSONObject(i);

                editor.putInt("ID_User", object.getInt("ID"));
                editor.putString("FirstName_User", object.getString("FirstName"));
                editor.putString("LastName_User", object.getString("LastName"));
                editor.putInt("PhoneNum_User", object.getInt("PhoneNum"));
                editor.putString("StateName_User", object.getString("StateName"));
                editor.putString("CityName_User", object.getString("CityName"));
                editor.putInt("CodPosty_User", object.getInt("CodPosty"));
                editor.putString("Address_User", object.getString("Address"));
                editor.putString("Password_User", object.getString("Password"));
                editor.putBoolean("statusLogin?", true);
                editor.commit();
            }

        } catch (Exception e) {
            Log.d("err", e.getMessage());
        }
    }


}
