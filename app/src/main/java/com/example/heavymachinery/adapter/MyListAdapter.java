package com.example.heavymachinery.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.heavymachinery.R;
import com.example.heavymachinery.model.MyList;

import java.util.ArrayList;

public class MyListAdapter extends BaseAdapter {


    Context context;
    ArrayList<MyList> arrayList;

    //Constructor
    public MyListAdapter(@NonNull Activity context, ArrayList<MyList> rows) {
        this.context = context;
        this.arrayList = rows;

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_layout, null);

        TextView name, modelNo, stock, price, id;

        name = (TextView) view.findViewById(R.id.pName);
        modelNo = (TextView) view.findViewById(R.id.pModelNo);
        stock = (TextView) view.findViewById(R.id.pStock);
        price = (TextView) view.findViewById(R.id.pPrice);
        id = (TextView) view.findViewById(R.id.pId);


        MyList list = arrayList.get(i);

        name.setText("Name:- "+list.getName());
        modelNo.setText("ModelNo:- "+list.getModelNo());
        stock.setText("No Of Items:- "+list.getStock());
        id.setText("Id:- "+list.getId());
        price.setText("Price:- "+list.getPrice());

        return view;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }


}