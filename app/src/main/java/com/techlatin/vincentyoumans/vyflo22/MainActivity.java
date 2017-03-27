package com.techlatin.vincentyoumans.vyflo22;
//  vy this is also an example of my Button Menu System
//     A template where I can do Menus based on buttons with a little
//     more elegance.  Think a scafolding system.
//      But peer reveiw request.  I need to improve on this.

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "will sink with Server", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DoButtons();  //  just sets up the Button navigation




    }
//=========================================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




   private Void DoButtons (){
       //  vy  - just to clean up the code...
       //  1...  add the Button to the ID's

       int[] ids = {
               R.id.btnScanBLE01, R.id.btnScanBLE02,
               R.id.btnScanBLE03, R.id.btnScanBLE04
       };
       for(int id : ids){
           Button button = (Button)findViewById(id);
           if (button != null) {
               button.setOnClickListener(mClickListener);
           }
       }

       //  check to confirm ANDROID PHONE capabilities...
       vyConfirmBTService();
       // VY  Confirm that BLE is supported on device...
       confirmBLE();


       return null;
   }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//  vy...  2...  add a case for the button.  run code there.
            final Intent intent;
            switch(v.getId()){
                case R.id.btnScanBLE01:
                    Log.d("==== inClickListener===","clicking Button 1");
                    intent = new Intent(v.getContext(), vyScanForBLE01.class);
                    break;
                case R.id.btnScanBLE02:
                    Log.d("==== inClickListener===","clicking Button 1");
                    intent = new Intent(v.getContext(), vyScanForBLE02.class);
                    break;
                case R.id.btnScanBLE03:
                    Log.d("==== inClickListener===","clicking Button 1");
                    intent = new Intent(v.getContext(), vyScanForBLE03.class);
                    break;
                case R.id.btnScanBLE04:
                    Log.d("==== inClickListener===","clicking Button 1");
                    intent = new Intent(v.getContext(), vyScanForBLE04.class);
                    break;
                default:
                    Log.d("==== inClickListener===","NO CLICK LISTENER");
                    intent = null;
                    break;
            }
            if(intent != null) {
                startActivity(intent);
            }
        }
    };

    //========  vy stuff....
    //  add blue tooth here
    public void vyConfirmBTService (){
        //  vy to confirm that the ANDROID device can deal with more devices.
        Log.d("=============", "in the confirm box");
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        int version = Build.VERSION.SDK_INT;
        String versionRelease = Build.VERSION.RELEASE;

        Log.e("MyActivity", "manufacturer " + manufacturer
                        + " \n model " + model
                        + " \n version " + version
                        + " \n versionRelease " + versionRelease
        );
        TextView tvAdvertStatus = (TextView)findViewById(R.id.tvAdvertSupported);
        if (tvAdvertStatus != null) {
            tvAdvertStatus.setText("ver:"+ version);
        }
        return;
    }

    //  vy-----------------------------------
    private void confirmBLE(){
                /*
        *  --------  move this to the MainActivity  -------------------------
         * Use this check to determine whether BLE is supported on the device.
         * Then you can selectively disable BLE-related features.
         */
        if (!getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, R.string.ble_not_supported, Toast.LENGTH_SHORT)
                    .show();
            finish();
        }else {
            Toast.makeText(this, R.string.ble_is_supported, Toast.LENGTH_SHORT)
                    .show();
            //finish();
        }

    }



}
