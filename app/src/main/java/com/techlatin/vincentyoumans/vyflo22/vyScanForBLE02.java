package com.techlatin.vincentyoumans.vyflo22;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;

public class vyScanForBLE02 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vy_scan_for_ble02);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ArrayList<BLE01Device> devices = BLEApp.get(this).getDataStore().load();
        if(devices == null){
            Toast.makeText(this, "Sorry, no BLE devices!", Toast.LENGTH_LONG).show();
            finish();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        if (recyclerView != null) {
            recyclerView.setAdapter(new BLE01DeviceAdapter(devices));
        }
    }

}
