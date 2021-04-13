package com.example.heavymachinery.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heavymachinery.R;
import com.example.heavymachinery.adapter.MyListAdapter;
import com.example.heavymachinery.model.MyList;
import com.example.heavymachinery.screen.UpdateActivity;
import com.example.heavymachinery.utilites.MyHelper;

import java.util.ArrayList;


public class SecondFragment extends Fragment {

    MyHelper helper;
    ArrayList<MyList> rows;
    ArrayList<Integer> productsIds;

    TextView s_message;
    ListView s_listView;

    MyListAdapter adapter;
    boolean availability=false;
    int selectedItem;
    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        helper = new MyHelper(getContext());
        s_message = view.findViewById(R.id.s_message);
        s_listView = view.findViewById(R.id.s_listView);

        if(availability){
            s_message.setVisibility(View.GONE);
            s_listView.setVisibility(View.VISIBLE);
        }else{
            s_message.setVisibility(View.VISIBLE);
            s_listView.setVisibility(View.GONE);
        }

        getData();
        return view;
    }

    public void getData() {
        rows = new ArrayList<>();
        productsIds = new ArrayList<>();
        Cursor res = null;

        try {
            res = helper.getAllData();
        } catch (Exception e) {
            Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_LONG).show();
        }

        if (res != null) {
            if (res.getCount() == 0) {
                Toast.makeText(getContext(), "No Record Found", Toast.LENGTH_LONG).show();
            } else {

                availability = true;
                s_message.setVisibility(View.GONE);
                s_listView.setVisibility(View.VISIBLE);

                res.moveToFirst();
                while (!res.isAfterLast()) {
                    productsIds.add(Integer.parseInt(res.getString(0)));
                    rows.add(new MyList(res.getString(0),
                            res.getString(1),
                            res.getString(2),
                            res.getString(3),
                            res.getString(4)));
                    res.moveToNext();
                }

                adapter = new MyListAdapter(getActivity(), rows);
                s_listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        }

    s_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            selectedItem = i;
            Log.i("selected item", ""+productsIds.get(selectedItem));
            Intent intent = new Intent(getActivity(), UpdateActivity.class);
            intent.putExtra("pId", productsIds.get(selectedItem).toString());
            startActivity(intent);
        }
    });

    }
}