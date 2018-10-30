package com.pol.poleuser;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Activity_SubCategory_PolUser extends AppCompatActivity {

    ListView lstSubCategory;
    ArrayAdapter arrayListSubCategory;
    String STSubCategory;
    String[] stringArrSubCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category_poluser);
        STSubCategory = getIntent().getStringExtra("SubSubject");
        lstSubCategory = (ListView) findViewById(R.id.lstSubCategory);

        if (STSubCategory.equals("null")) {
            Toast.makeText(this, getString(R.string.ToastNothingToShow), Toast.LENGTH_SHORT).show();
        } else {
            stringArrSubCategory = STSubCategory.split(",");
            arrayListSubCategory = new ArrayAdapter(this, R.layout.custom_listview_subcategory, stringArrSubCategory) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    convertView = getLayoutInflater().inflate(R.layout.custom_listview_subcategory, parent, false);
                    TextView txtListViewsubcategory = convertView.findViewById(R.id.txtListViewsubcategory);
                    txtListViewsubcategory.setText(stringArrSubCategory[position]);
                    return convertView;
                }
            };
            lstSubCategory.setAdapter(arrayListSubCategory);

            lstSubCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(Activity_SubCategory_PolUser.this,Activity_SubmitReq_PolUser.class);
                    intent.putExtra("Subject",lstSubCategory.getItemAtPosition(position).toString());
                    startActivity(intent);
                    finish();
                }
            });

        }
    }
}
