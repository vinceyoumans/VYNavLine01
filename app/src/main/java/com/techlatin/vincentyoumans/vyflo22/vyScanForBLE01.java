package com.techlatin.vincentyoumans.vyflo22;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class vyScanForBLE01 extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private BLE01DeviceAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private BLEScanner01 mBLEScanner;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vy_scan_for_ble01);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //  vy the Mail icon to sink with Server
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Sinks list of devices with server 01", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        //vy standard intent view...
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //vy - using RecycleView as oppose to a List view.
        mRecyclerView = (RecyclerView) findViewById(R.id.rvBLEList01);

        //Improves performance if there are not size changes
        mRecyclerView.setHasFixedSize(true);


        mLayoutManager = new LinearLayoutManager(this);

        //  Using Linear Layout Manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        //  This needs to goto Library
        //Specify an Adapter
        mAdapter = new BLE01DeviceAdapter(savedInstanceState);
        mRecyclerView.setAdapter(mAdapter);


        mBLEScanner = new BLEScanner01(this, new BLEScanner01.CallBack() {
            @Override
            public void onScanResult(BluetoothDevice device, int rssi, byte[] scanRecord) {
                Log.w("ScanResult", "address :" + device.getAddress());
                BLE01Device ble01Device = new BLE01Device(device.getAddress());
                ble01Device.setDeviceName(device.getName());
                //  when I figure out how to get other parameters... but here
                ble01Device.setTxPower("power");
                ble01Device.setManufacturerData("manufacturer");
                ble01Device.setServiceUUID("uuid");
                ble01Device.setServiceData("data");
                mAdapter.AddBLEDevice(ble01Device);
            }
        });

        Button button1 = (Button) findViewById(R.id.btn011);
        assert button1 != null;
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BLEApp.get(v.getContext()).getDataStore().save(mAdapter.getSelectedDevices());
                finish();
            }
        });

    }

    private String toHex(byte[] data)
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < data.length; i++){
            sb.append(String.format("%02X ", data[i]));
        }
        return sb.toString();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBLEScanner.scanLeDevice(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mBLEScanner.scanLeDevice(false);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mAdapter.putAdapterState(outState);
    }
}
