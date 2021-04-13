package com.example.heavymachinery.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heavymachinery.MainActivity;
import com.example.heavymachinery.R;
import com.example.heavymachinery.fragment.FirstFragment;
import com.example.heavymachinery.utilites.MyHelper;

public class UpdateActivity extends AppCompatActivity {

    MyHelper helper;
    String productId;
    TextView pId;
    EditText pName, pModelNo, pStock, pPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        helper = new MyHelper(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            productId = extras.getString("pId");
            //The key argument here must match that used in the other activity
        }

        pId = findViewById(R.id.pId);
        pName = findViewById(R.id.pName);
        pModelNo = findViewById(R.id.pModelNo);
        pStock = findViewById(R.id.pStock);
        pPrice = findViewById(R.id.pPrice);

        getDataForUpdate();
    }

    private void getDataForUpdate() {
        Cursor res = helper.getTableData(Integer.parseInt(productId));
        if(res!=null){

            res.moveToFirst();
            String idText="Id "+res.getString(0);
            String nameText="Name "+res.getString(1);
            String modelNoText="Md No "+res.getString(2);
            String stockText="Stock "+res.getString(3);
            String priceText="Price "+res.getString(4);
            pId.setText(idText);
            pName.setText(nameText);
            pModelNo.setText(modelNoText);
            pStock.setText(stockText);
            pPrice.setText(priceText);
        }
    }


    public void update(View view) {
        String[] idText=pId.getText().toString().split(" ");
        String[] nameText=pName.getText().toString().split(" ");
        String[] modelNoText=pModelNo.getText().toString().split(" ");
        String[] stockText=pStock.getText().toString().split(" ");
        String[] priceText=pPrice.getText().toString().split(" ");

        if(nameText[1].equalsIgnoreCase("")){
            pName.setError("Enter Name Of Machinery");
        }
        if(modelNoText[2].equalsIgnoreCase("")){
            pModelNo.setError("Enter Name Of Model");
        }
        if(stockText[1].equalsIgnoreCase("")){
            pStock.setError("No Of Items");
        }
        if(priceText[1].equalsIgnoreCase("")){
            pPrice.setError("Price Per Unit");
        }

        if(
                nameText[1].isEmpty()
                        ||modelNoText[2].isEmpty()
                        ||stockText[1].isEmpty()
                        ||priceText[1].isEmpty()
        ){
            Toast.makeText(getApplicationContext(), "Please Enter the value", Toast.LENGTH_SHORT).show();
        }else {
            Boolean check = helper.Update(
                    Integer.valueOf(idText[1]),
                    nameText[1], modelNoText[2],
                    stockText[1],
                    Float.valueOf(priceText[1])
            );
            if(check){
                getDataForUpdate();
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void back(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}