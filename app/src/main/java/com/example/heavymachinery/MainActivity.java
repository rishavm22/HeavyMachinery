package com.example.heavymachinery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.heavymachinery.adapter.ViewPagerAdapter;
import com.example.heavymachinery.fragment.FirstFragment;
import com.example.heavymachinery.fragment.SecondFragment;
import com.example.heavymachinery.screen.InputActivity;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;

    FirstFragment firstFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tab view
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        firstFragment = new FirstFragment();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(firstFragment, "Product List");
        viewPagerAdapter.addFragment(new SecondFragment(), "Select For Update");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        setSupportActionBar(toolbar);

    }





    //To select Menu Resource file
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //To check selected menu

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.add) {
             startActivity(new Intent(getApplicationContext(), InputActivity.class));
            return true;
        } else if (item.getItemId() == R.id.delete) {

                alertBlock("Do u wanna delete All Items?", 1);

            return true;
        } else{
            return false;
        }
    }

    private void alertBlock(String msg, int type){
        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom))
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Are You Sure?")
                .setMessage(msg)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(type==0){
                            firstFragment.deleteRow();
                        }else if(type==1){
                            firstFragment.clearAllData();
                        }
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }

}

