package com.example.heavymachinery.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.heavymachinery.MainActivity;
import com.example.heavymachinery.R;
import com.example.heavymachinery.utilites.MyHelper;

public class InputActivity extends AppCompatActivity {

    MyHelper helper;

    EditText name,modelNo,stock,price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        helper = new MyHelper(this);
        name = findViewById(R.id.itemName);
        modelNo = findViewById(R.id.modelNo);
        stock = findViewById(R.id.stock);
        price = findViewById(R.id.price);
    }

    public void submit(View view) {
        if(name!=null||modelNo!=null||stock!=null||price!=null){
            String name_text, modelNo_text, stock_text, price_text;
            assert name != null;
            name_text = name.getText().toString().trim();
            modelNo_text = modelNo.getText().toString().trim();
            stock_text = stock.getText().toString().trim();
            price_text = price.getText().toString().trim();



            if(name_text.equalsIgnoreCase("")){
                name.setError("Enter Name Of Machinery");
            }
            if(modelNo_text.equalsIgnoreCase("")){
                modelNo.setError("Enter Name Of Model");
            }
            if(stock_text.equalsIgnoreCase("")){
                stock.setError("No Of Items");
            }
            if(price_text.equalsIgnoreCase("")){
                price.setError("Price Per Unit");
            }

            if(
                    name_text.isEmpty()
                    ||modelNo_text.isEmpty()
                    ||stock_text.isEmpty()
                    ||price_text.isEmpty()
            ){
                Toast.makeText(getApplicationContext(), "Please Enter the value", Toast.LENGTH_SHORT).show();
            }else {

                Boolean result = helper.insertData(name_text
                        , modelNo_text
                        , stock_text
                        , Float.parseFloat(price_text));
                String errorMessage;
                if (result) {
                    errorMessage= "New Entry Inserted";
                } else {
                    errorMessage= "Failed";
                }
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();

            }
        }
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);

    }

    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}