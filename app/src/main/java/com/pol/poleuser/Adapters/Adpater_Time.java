package com.pol.poleuser.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pol.poleuser.R;

import java.util.ArrayList;

public class Adpater_Time extends BaseAdapter {

    Context context;
    ArrayList<String> nameWeak, nameMonth;
    ArrayList<Integer> day, year;

    public Adpater_Time(Context context, ArrayList<String> nameWeak, ArrayList<Integer> day, ArrayList<String> nameMonth, ArrayList<Integer> year) {
        this.context = context;
        this.nameWeak = nameWeak;
        this.day = day;
        this.nameMonth = nameMonth;
        this.year = year;
    }


    @Override
    public int getCount() {
        return nameWeak.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.custom_gridview_reqactivity_poluser, null);
        }


        TextView txtListTime1 = convertView.findViewById(R.id.txtListTime1);
        TextView txtListTime2 = convertView.findViewById(R.id.txtListTime2);
        TextView txtListTime3 = convertView.findViewById(R.id.txtListTime3);
        TextView txtListTime4 = convertView.findViewById(R.id.txtListTime4);
        txtListTime1.setText(nameWeak.get(position));
        txtListTime2.setText(day.get(position).toString());
        txtListTime3.setText(nameMonth.get(position));
        txtListTime4.setText(year.get(position).toString());


        return convertView;
    }
}
