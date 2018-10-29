package com.pol.poleuser;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.pol.poleuser.classes.SpinnerClass;

public class Activity_EditProfile_PoleUser extends AppCompatActivity {

    //LinearEditProfile
    private EditText edtFirstNameEdit, edtLastNameEdit, edtPhoneNumberEdit, edtPasswordEdit, edtRePasswordEdit, edtPostalCodeEdit, edtAddressEdit;
    private Spinner spnStateEdit, spnCityEdit;
    SharedPreferences EditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile_poleuser);
        EditProfile   = getSharedPreferences("polUser", 0);
        int StateNameIDEdit = EditProfile.getInt("StateNameID_User", 5);
        int CityNameIDEdit = EditProfile.getInt("CityNameID_User", 7);

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

        SpinnerClass spinner = new SpinnerClass(Activity_EditProfile_PoleUser.this, spnStateEdit, spnCityEdit);
        spinner.spinner();

        spnStateEdit.setSelection(5);
        spnCityEdit.setSelection(7);

    }


    public void btnBackOnClickFinishEdit(View view) {

    }


    public void btnFinishRegisterEdit(View view) {

    }

}
