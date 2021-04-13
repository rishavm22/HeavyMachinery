package com.example.heavymachinery.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heavymachinery.MainActivity;
import com.example.heavymachinery.R;
import com.example.heavymachinery.adapter.MyListAdapter;
import com.example.heavymachinery.model.MyList;
import com.example.heavymachinery.screen.UpdateActivity;
import com.example.heavymachinery.utilites.MyHelper;

import java.util.ArrayList;


public class FirstFragment extends Fragment {


    MyHelper helper;
    ArrayList<MyList> rows;
    ArrayList<Integer> productsIds;
    TextView w_message,hintBox;
    ListView listView;

    MyListAdapter adapter;
    boolean availability=false;
    int selectedItem;
    public FirstFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        helper = new MyHelper(getContext());
        w_message = view.findViewById(R.id.w_message);
        hintBox = view.findViewById(R.id.hintBox);
        listView = view.findViewById(R.id.listView);

        getData();

        if(availability){
            w_message.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            hintBox.setVisibility(View.VISIBLE);
        }else{
            w_message.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
            hintBox.setVisibility(View.GONE);
        }
        return view;
    }

    public void getData() {
        rows = new ArrayList<>();
        productsIds = new ArrayList<>();
        Cursor res = null;

        try {
            res = helper.getAllData();
        }catch (Exception e){
            Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_LONG).show();
        }

        if (res != null){
            if (res.getCount() == 0) {
                Toast.makeText(getContext(), "No Record Found", Toast.LENGTH_LONG).show();
            }else {

                availability = true;
                w_message.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                hintBox.setVisibility(View.VISIBLE);

                res.moveToFirst();
                while(!res.isAfterLast()){
                    productsIds.add(Integer.parseInt(res.getString(0)));
                    rows.add( new MyList(res.getString(0),
                            res.getString(1),
                            res.getString(2),
                            res.getString(3),
                            res.getString(4)));
                    res.moveToNext();
                }

                adapter = new MyListAdapter(getActivity(),rows);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();


            }
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), ""+i, Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                selectedItem = i;
                alertBlock("Do u wanna delete this Item?");
                return true;
            }
        });

    }

    private void alertBlock(String msg){
        new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.AlertDialogCustom))
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Are You Sure?")
                .setMessage(msg)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            deleteRow();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }

    public void deleteRow() {
        Boolean check = helper.deleteData(productsIds.get(selectedItem));
        if(check){
            getData();
            Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void clearAllData() {
        Boolean check = helper.clearData();
        if(check){
            getData();
            Toast.makeText(getActivity(), "Deleted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }
}