package com.pol.poleuser;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.pol.poleuser.classes.SpinnerClass;

import static com.pol.poleuser.classes.SpinnerClass.aaa;


public class Activity_EditProfile_PoleUser extends AppCompatActivity {

    //LinearEditProfile
    private EditText edtFirstNameEdit, edtLastNameEdit, edtPhoneNumberEdit, edtPasswordEdit, edtRePasswordEdit, edtPostalCodeEdit, edtAddressEdit;
    private Spinner spnStateEdit, spnCityEdit;
    SharedPreferences EditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile_poleuser);
        EditProfile = getSharedPreferences("polUser", 0);

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

        edtFirstNameEdit.setText(EditProfile.getString("FirstName_User", "خالی")+"");
        edtLastNameEdit.setText(EditProfile.getString("LastName_User", "خالی")+"");
        edtPhoneNumberEdit.setText(EditProfile.getInt("PhoneNum_User", 0)+"");
        edtPasswordEdit.setText(EditProfile.getString("Password_User", "خالی")+"");
        edtRePasswordEdit.setText(EditProfile.getString("Password_User", "خالی")+"");
        edtPostalCodeEdit.setText(EditProfile.getInt("CodPosty_User", 0)+"");
        edtAddressEdit.setText(EditProfile.getString("Address_User", "خالی")+"");


    }


    public void btnFinishRegisterEdit(View view) {

    }

    public void btnBackOnClickFinishEdit(View view) {
        finish();
    }


}
