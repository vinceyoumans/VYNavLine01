package com.techlatin.vincentyoumans.vyflo22;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Handler;

/**
 * BLE Scanner
 *
 * Created by vincentyoumans on 3/27/16.
 */
public class BLEScanner01 {

    /* Stops scanning after 10 seconds. */
    private static final long SCAN_PERIOD = 10000;

    private BluetoothAdapter mBluetoothAdapter;
    private boolean mScanning;
    private Handler mHandler;

    private CallBack mCallBack;



    public BLEScanner01(Context context, CallBack callBack){

        mHandler = new Handler();

        mCallBack = callBack;

        final BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        /* Checks if Bluetooth is supported on the device. */
        if (mBluetoothAdapter == null) { throw new IllegalStateException("Can not load BlueTooth");}
    }



    @SuppressWarnings("deprecation")
    public synchronized void scanLeDevice(final boolean enable) {
        if (enable) {
            /* Stops scanning after a pre-defined scan period. */
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mScanning) {
                        mScanning = false;
                        mBluetoothAdapter.stopLeScan(mLeScanCallback);
                    }
                }
            }, SCAN_PERIOD);

            mScanning = true;
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else if (mScanning) {
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
    }



    /* Device scan callback. */
    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {

        @Override
        public void onLeScan(final BluetoothDevice device, final int rssi,
                             final byte[] scanRecord) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mCallBack.onScanResult(device, rssi, scanRecord);
                }
            });
        }
    };


    public interface CallBack {
        void onScanResult(BluetoothDevice device, int rssi, byte[] scanRecord);
    }

}
