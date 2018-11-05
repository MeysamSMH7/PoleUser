package com.pol.poleuser;

import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.pol.poleuser.classes.SpinnerClass;
import com.pol.poleuser.classes.checkInternet;
import com.pol.poleuser.connectClasses.connect_EditPro;


public class Activity_EditProfile_PoleUser extends AppCompatActivity {

    //LinearEditProfile
    private EditText edtFirstNameEdit, edtLastNameEdit, edtPhoneNumberEdit, edtPasswordEdit, edtRePasswordEdit, edtPostalCodeEdit, edtAddressEdit;
    private Spinner spnStateEdit, spnCityEdit;
    SharedPreferences EditProfile;
    private String IDUser = "", FirstName = "", LastName = "", PhoneNum = "", PassWord = "", CodPosty = "", StateName = "", CityName = "", Address = "";

    checkInternet internet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile_poleuser);

        internet = new checkInternet(this);

        EditProfile = getSharedPreferences("polUser", 0);
        IDUser = EditProfile.getInt("ID_User", 0) + "";

        //LinearRegFinish
        edtFirstNameEdit = (EditText) findViewById(R.id.edtFirstNameEdit);
        edtLastNameEdit = (EditText) findViewById(R.id.edtLastNameEdit);
        edtPhoneNumberEdit = (EditText) findViewById(R.id.edtPhoneNumberEdit);
        edtPasswordEdit = (EditText) findViewById(R.id.edtPasswordEdit);
        edtRePasswordEdit = (EditText) findViewById(R.id.edtRePasswordEdit);
        edtPostalCodeEdit = (EditText) findViewById(R.id.edtPostalCodeEdit);
        edtAddressEdit = (EditText) findViewById(R.id.edtAddressEdit);
        spnStateEdit = (Spinner) findViewById(R.id.spnStateEdit);
        spnCityEdit = (Spinner) findViewById(R.id.spnCityEdit);

        SpinnerClass spinner = new SpinnerClass(Activity_EditProfile_PoleUser.this, spnStateEdit, spnCityEdit, true);
        spinner.spinner();

        edtFirstNameEdit.setText(EditProfile.getString("FirstName_User", "خالی") + "");
        edtLastNameEdit.setText(EditProfile.getString("LastName_User", "خالی") + "");
        edtPhoneNumberEdit.setText(EditProfile.getInt("PhoneNum_User", 0) + "");
        edtPasswordEdit.setText(EditProfile.getString("Password_User", "خالی") + "");
        edtRePasswordEdit.setText(EditProfile.getString("Password_User", "خالی") + "");
        edtPostalCodeEdit.setText(EditProfile.getInt("CodPosty_User", 0) + "");
        edtAddressEdit.setText(EditProfile.getString("Address_User", "خالی") + "");

    }

    public void btnFinishRegisterEdit(View view) {


        if (!internet.CheckNetworkConnection()) {
            checkNet();
        } else {

            FirstName = edtFirstNameEdit.getText().toString();
            LastName = edtLastNameEdit.getText().toString();
            PhoneNum = edtPhoneNumberEdit.getText().toString();
            PassWord = edtPasswordEdit.getText().toString();
            String RePassWord = edtRePasswordEdit.getText().toString();
            CodPosty = edtPostalCodeEdit.getText().toString();
            StateName = SpinnerClass.StateName;
            CityName = SpinnerClass.CityName;
            Address = edtAddressEdit.getText().toString();

            if (FirstName.equals("") || LastName.equals("") || PassWord.equals("") || CodPosty.equals("") || Address.equals("")) {
                Toast.makeText(this, getString(R.string.ToastFillAllBlanks), Toast.LENGTH_SHORT).show();
            } else if (!(PassWord.equals(RePassWord))) {
                Toast.makeText(this, getString(R.string.ToastRePassword), Toast.LENGTH_SHORT).show();
            } else if (!(PassWord.length() == 8)) {
                Toast.makeText(this, getString(R.string.ToastPassword8), Toast.LENGTH_SHORT).show();
            } else if (!(CodPosty.length() == 10)) {
                Toast.makeText(this, getString(R.string.ToastCodPost), Toast.LENGTH_SHORT).show();
            } else {
                new connect_EditPro(getString(R.string.LinkEditPro), ishowEditProRes, IDUser, FirstName, LastName, PhoneNum, PassWord, CodPosty, StateName, CityName, Address).execute();
            }

        }
    }


    connect_EditPro.IshowEditProRes ishowEditProRes = new connect_EditPro.IshowEditProRes() {
        @Override
        public void EditProResult(String res) {

            if (res.contains("OK!")) {

                SharedPreferences.Editor editor = EditProfile.edit();
                editor.putString("FirstName_User", FirstName);
                editor.putString("LastName_User", LastName);
                editor.putInt("CodPosty_User", Integer.parseInt(CodPosty));
                editor.putString("Password_User", PassWord);
                editor.putString("StateName_User", StateName);
                editor.putString("CityName_User", CityName);
                editor.putString("Address_User", Address);
                editor.apply();

                Toast.makeText(Activity_EditProfile_PoleUser.this, getString(R.string.ToastChangeSave), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Activity_EditProfile_PoleUser.this, getString(R.string.ToastChangeNotSave), Toast.LENGTH_SHORT).show();
            }


        }
    };


    public void btnBackOnClickFinishEdit(View view) {
        finish();
    }

    private void checkNet() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Activity_EditProfile_PoleUser.this);
        builder.setTitle(getString(R.string.ToastCheckNetTitle));
        builder.setMessage(getString(R.string.ToastCheckNetMessage));
        builder.show();
    }


}
