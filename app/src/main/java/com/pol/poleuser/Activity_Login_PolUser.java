package com.pol.poleuser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pol.poleuser.connectClasses.connect_addUser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Activity_Login_PolUser extends AppCompatActivity {

    //LinearLayout
    LinearLayout LinearLogin, LinearRegPhoneNum, LinearRegCheckCode, LinearRegFinish, LinearRegForgetPass;


    //Login
    EditText edtPhoneNumLogin, edtPassLogin;

    //LinearRegPhoneNum
    EditText edtPhoneNumRegister;

    //LinearRegCheckCode
    TextView txtPhoneNum, txtTimer, txtShowCode;
    EditText edtGetCode;

    //LinearRegFinish
    EditText edtFirstName, edtLastName, edtPhoneNumber, edtPassword, edtRePassword, edtPostalCode, edtAddress;
    Spinner spnState, spnCity;

    //LinearRegForgetPass
    EditText edtPhoneNumberForget;
    TextView txtYourPass;

    //public Variable
    private CountDownTimer countDownTimer;
    private String ranNum;
    private int countbtnClick = 0;
    String FirstName = "", LastName = "", PhoneNum = "", PassWord = "", CodPosty = "", StateName = "", CityName = "", Address = "";


    //public Static Strings for Connect Classes ************************************
    public static String getData_AddUser = "";




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

    }

    public void btnRegisterLogin(View view) {
        LinearLogin.setVisibility(View.GONE);
        LinearRegPhoneNum.setVisibility(View.VISIBLE);
        LinearRegCheckCode.setVisibility(View.GONE);
        LinearRegFinish.setVisibility(View.GONE);
        LinearRegForgetPass.setVisibility(View.GONE);
    }

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

            txtShowCode.setText(ranNum);


        }
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


        new connect_addUser("http://10.0.3.2/pol/addUser.php" , FirstName , LastName , PhoneNum , PassWord , CodPosty , StateName , CityName , Address).execute();

//        Toast.makeText(this, "اطلاعات شما ثبت گردید", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, getData_AddUser+"", Toast.LENGTH_SHORT).show();

//        SharedPreferences wellcome = getSharedPreferences("polUser" , 0);
//        SharedPreferences.Editor editor = wellcome.edit();
//        editor.putBoolean("statusLogin?" , true);
//        editor.commit();

//        Intent intent = new Intent(Activity_Login_PolUser.this , Activity_main_PolUser.class);
//        startActivity(intent);
//        finish();


    }


    //LinearRegForgetPass **************************************************************************

    public void btnBackOnClickForgetPass(View view) {

    }

    public void btnGetPass(View view) {

    }

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

//    public String GetJSONArray(){
//
//        try{
//
//            JSONArray jsonArray = new JSONArray(getData_AddUser);
//
//            for (int i = 0 ; i<jsonArray.length() ; i++){
//
//                JSONObject object = jsonArray.getJSONObject(i);
//
//
//
//
//            }
//
//
//
//        }catch (Exception e){
//            Log.d("err" , e.getMessage());
//        }
//
//        return confirm;
//
//    }


}
