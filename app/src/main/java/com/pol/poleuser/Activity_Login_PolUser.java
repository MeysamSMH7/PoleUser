package com.pol.poleuser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.pol.poleuser.classes.SpinnerClass;
import com.pol.poleuser.classes.checkInternet;
import com.pol.poleuser.connectClasses.connect_GetPass;
import com.pol.poleuser.connectClasses.connect_LoginUser;
import com.pol.poleuser.connectClasses.connect_SendSms;
import com.pol.poleuser.connectClasses.connect_addUser;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Activity_Login_PolUser extends AppCompatActivity {
    public String getDataServerLogin = "";

    //LinearLayout
    private LinearLayout  LinearRegPhoneNum, LinearRegCheckCode, LinearRegFinish, LinearRegForgetPass;
    private ConstraintLayout LinearLogin;
    //Login
    private EditText edtPhoneNumLogin, edtPassLogin;
    private TextInputLayout input_layout_edtPhoneNumLogin , input_layout_edtPassLogin;

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
    private SharedPreferences preferencesLogin;
    checkInternet internet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_poluser);
        preferencesLogin = getSharedPreferences("polUser", 0);

        internet = new checkInternet(this);


        //LinearLayout
        LinearLogin =  findViewById(R.id.LinearLogin);
        LinearRegPhoneNum = (LinearLayout) findViewById(R.id.LinearRegPhoneNum);
        LinearRegCheckCode = (LinearLayout) findViewById(R.id.LinearRegCheckCode);
        LinearRegFinish = (LinearLayout) findViewById(R.id.LinearRegFinish);
        LinearRegForgetPass = (LinearLayout) findViewById(R.id.LinearRegForgetPass);

        //Login
        edtPhoneNumLogin = (EditText) findViewById(R.id.edtPhoneNumLogin);
        edtPassLogin = (EditText) findViewById(R.id.edtPassLogin);

        input_layout_edtPhoneNumLogin = findViewById(R.id.input_layout_edtPhoneNumLogin);
        input_layout_edtPassLogin = findViewById(R.id.input_layout_edtPassLogin);


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


        LinearLogin.setVisibility(View.VISIBLE);
        LinearRegPhoneNum.setVisibility(View.GONE);
        LinearRegCheckCode.setVisibility(View.GONE);
        LinearRegFinish.setVisibility(View.GONE);
        LinearRegForgetPass.setVisibility(View.GONE);

    }


    //Login **************************************************************************

    public void btnLoginOnClick(View view) {
        if (!internet.CheckNetworkConnection()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.ToastCheckNetTitle));
            builder.setMessage(getString(R.string.ToastCheckNetMessage));
            builder.show();
        }else {
            PhoneNum = edtPhoneNumLogin.getText().toString();
            PassWord = edtPassLogin.getText().toString();

            new connect_LoginUser(getString(R.string.LinkLoginUser), resultLogin, PhoneNum, PassWord, "false").execute();
        }
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
            if (PhoneNum.equals("") || PassWord.equals("")){
                Toast.makeText(Activity_Login_PolUser.this, getString(R.string.ToastFillAllBlanks), Toast.LENGTH_SHORT).show();
            }else {
                if (!res.contains("[]")) {

                    Toast.makeText(Activity_Login_PolUser.this, getString(R.string.ToastWelcome), Toast.LENGTH_SHORT).show();

                    GetJSONArrayLogin();

                    Intent intent = new Intent(Activity_Login_PolUser.this, Activity_main_PolUser.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(Activity_Login_PolUser.this, getString(R.string.ToastWrongPassOrPhone), Toast.LENGTH_SHORT).show();
                }
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
        if (!internet.CheckNetworkConnection()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.ToastCheckNetTitle));
            builder.setMessage(getString(R.string.ToastCheckNetMessage));
            builder.show();
        }else {
            edtGetCode.setText("");
            PhoneNum = edtPhoneNumRegister.getText().toString();
            edtPhoneNumRegister.setText("");


            if (PhoneNum.isEmpty() || !(PhoneNum.trim().length() == 10)) {
                Toast.makeText(Activity_Login_PolUser.this, getString(R.string.ToastWrongPhoneNum), Toast.LENGTH_SHORT).show();
            } else {

                new connect_LoginUser(getString(R.string.LinkLoginUser), ishowLoginRes, PhoneNum, "", "true").execute();

            }
        }
    }

    connect_LoginUser.IshowLoginRes ishowLoginRes = new connect_LoginUser.IshowLoginRes() {
        @Override
        public void loginUserResult(String res) {
            if (res.contains(PhoneNum)){
                Toast.makeText(Activity_Login_PolUser.this, getString(R.string.ToastUsedPhone), Toast.LENGTH_SHORT).show();
            }else {
                ranNum = RandomNum();

                new connect_SendSms(getString(R.string.LinkSendSms), iSendSmsRes, PhoneNum + "", ranNum + "").execute();

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

                txtPhoneNum.setText("شماره تلفن شما" + "\n" + PhoneNum);

                //txtShowCode.setText(ranNum);
            }
        }
    };

    connect_SendSms.ISendSmsRes iSendSmsRes = new connect_SendSms.ISendSmsRes() {
        @Override
        public void loginUserResult(String res) {
            if (!(res.contains("بدون خطا"))){
                Toast.makeText(Activity_Login_PolUser.this, res + "", Toast.LENGTH_SHORT).show();
            }
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
        if (!internet.CheckNetworkConnection()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.ToastCheckNetTitle));
            builder.setMessage(getString(R.string.ToastCheckNetMessage));
            builder.show();
        }else {
            if (txtTimer.getText().toString().equals(getString(R.string.finishTimer))) {
                LinearLogin.setVisibility(View.GONE);
                LinearRegPhoneNum.setVisibility(View.VISIBLE);
                LinearRegCheckCode.setVisibility(View.GONE);
                LinearRegFinish.setVisibility(View.GONE);
                LinearRegForgetPass.setVisibility(View.GONE);

                countDownTimer.cancel();
            } else if (edtGetCode.getText().toString().equals(ranNum)) {
                edtPhoneNumber.setText(PhoneNum);
                edtPhoneNumber.setEnabled(false);
                LinearLogin.setVisibility(View.GONE);
                LinearRegPhoneNum.setVisibility(View.GONE);
                LinearRegCheckCode.setVisibility(View.GONE);
                LinearRegFinish.setVisibility(View.VISIBLE);
                LinearRegForgetPass.setVisibility(View.GONE);

                //Spinner for Register
                SpinnerClass spinner = new SpinnerClass(Activity_Login_PolUser.this, spnState, spnCity, false);
                spinner.spinner();

            } else if (countbtnClick < 5) {
                countbtnClick++;
                Toast.makeText(Activity_Login_PolUser.this, getString(R.string.ToastWrongCode), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Activity_Login_PolUser.this, getString(R.string.ToastTooManyTry), Toast.LENGTH_SHORT).show();
            }
        }
    }

    //LinearRegFinish ****************************************************************************

    public void btnBackOnClickFinish(View view) {
        finish();
    }

    public void btnFinishRegister(View view) {
        if (!internet.CheckNetworkConnection()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.ToastCheckNetTitle));
            builder.setMessage(getString(R.string.ToastCheckNetMessage));
            builder.show();
        }else {
            FirstName = edtFirstName.getText().toString();
            LastName = edtLastName.getText().toString();
            PhoneNum = edtPhoneNumber.getText().toString();
            PassWord = edtPassword.getText().toString();
            String RePassWord = edtRePassword.getText().toString();
            CodPosty = edtPostalCode.getText().toString();
            StateName = SpinnerClass.StateName;
            CityName = SpinnerClass.CityName;
            Address = edtAddress.getText().toString();


            if (FirstName.equals("") || LastName.equals("") || PassWord.equals("") || CodPosty.equals("") || Address.equals("")) {
                Toast.makeText(this, getString(R.string.ToastFillAllBlanks), Toast.LENGTH_SHORT).show();
            } else if (!(PassWord.equals(RePassWord))) {
                Toast.makeText(this, getString(R.string.ToastRePassword), Toast.LENGTH_SHORT).show();
            } else if (!(PassWord.length() >= 8)) {
                Toast.makeText(this, getString(R.string.ToastPassword8), Toast.LENGTH_SHORT).show();
            } else if (!(CodPosty.length() == 10)) {
                Toast.makeText(this, getString(R.string.ToastCodPost), Toast.LENGTH_SHORT).show();
            } else {
                new connect_addUser(getString(R.string.LinkAddUser), resultAddUser, FirstName, LastName, PhoneNum, PassWord, CodPosty, StateName, CityName, Address).execute();
            }

        }
    }

    connect_addUser.IshowAddUserRes resultAddUser = new connect_addUser.IshowAddUserRes() {
        @Override
        public void addUserResult(String res) {

            if (res.contains("ok!")) {
                Toast.makeText(Activity_Login_PolUser.this, getText(R.string.ToastdoneRegister), Toast.LENGTH_SHORT).show();

                LinearLogin.setVisibility(View.VISIBLE);
                LinearRegPhoneNum.setVisibility(View.GONE);
                LinearRegCheckCode.setVisibility(View.GONE);
                LinearRegFinish.setVisibility(View.GONE);
                LinearRegForgetPass.setVisibility(View.GONE);

            } else {
                Toast.makeText(Activity_Login_PolUser.this, getText(R.string.ToastdoneNotRegister), Toast.LENGTH_SHORT).show();
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
        if (!internet.CheckNetworkConnection()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.ToastCheckNetTitle));
            builder.setMessage(getString(R.string.ToastCheckNetMessage));
            builder.show();
        }else {
            PhoneNum = edtPhoneNumberForget.getText().toString();

            new connect_GetPass(getString(R.string.LinkGetPass), resultGetPass, PhoneNum).execute();
        }
    }

    connect_GetPass.IgetPassRes resultGetPass = new connect_GetPass.IgetPassRes() {
        @Override
        public void getPassUserResult(String res) {

            if (res.contains("no!")) {
                Toast.makeText(Activity_Login_PolUser.this, getString(R.string.ToastNoPhone), Toast.LENGTH_SHORT).show();
                edtPhoneNumberForget.setText("");
                txtYourPass.setText("");
            } else {
                //  txtYourPass.setText("your password:\n" + res);
                Toast.makeText(Activity_Login_PolUser.this, res + "", Toast.LENGTH_SHORT).show();
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
                editor.apply();
            }

        } catch (Exception e) {
            Log.d("err", e.getMessage());
        }
    }


}
