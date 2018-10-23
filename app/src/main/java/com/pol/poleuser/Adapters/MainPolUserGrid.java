package com.pol.poleuser.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.pol.poleuser.R;


public class MainPolUserGrid extends BaseAdapter {



    private Context mContext;
    private String[] MainMenuPolUser;


    // Constructor
    public MainPolUserGrid(Context c  , String[] data) {
        mContext = c;
        MainMenuPolUser = data;
    }

    public int getCount() {
        return MainMenuPolUser.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.custom_gridview_mainactivity_poluser, null);
        }


        ImageView imgCustomGrdMain = (ImageView) convertView.findViewById(R.id.imgCustomGrdMain);
        TextView txtCustomGrdMain = (TextView) convertView.findViewById(R.id.txtCustomGrdMain);


        txtCustomGrdMain.setText(MainMenuPolUser[position]);

        return convertView;
    }


}
