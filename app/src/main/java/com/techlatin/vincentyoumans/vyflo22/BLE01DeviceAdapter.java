package com.techlatin.vincentyoumans.vyflo22;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * BLE Device Adapter
 *
 * Created by vincentyoumans on 3/27/16.
 */
public class BLE01DeviceAdapter extends RecyclerView.Adapter<BLE01DeviceAdapter.ViewHolder>
        implements View.OnClickListener {

    private static final String STATE_ADAPTER_DEVICES = "stateAdapterDevices";
    private static final String STATE_ADAPTER_SELECTED = "stateAdapterSelected";

    private ArrayList<BLE01Device> mDevices;
    private SparseBooleanArray mSelected;


    public BLE01DeviceAdapter(ArrayList<BLE01Device> devices){
        mDevices = devices;
        mSelected = null;
    }

    @SuppressWarnings("unchecked")
    public BLE01DeviceAdapter(@Nullable Bundle savedState) {
        if(savedState != null){
            mDevices = (ArrayList<BLE01Device>) savedState.getSerializable(STATE_ADAPTER_DEVICES);
            mSelected = new SparseBooleanArray();
            ArrayList<BLE01Device> selected = (ArrayList<BLE01Device>) savedState.getSerializable(STATE_ADAPTER_SELECTED);
            if (selected != null) {
                for(BLE01Device device : selected){
                    mSelected.put(mDevices.indexOf(device), true);
                }
            }
        }
        else {
            mDevices = new ArrayList<>();
            mSelected = new SparseBooleanArray();
        }
    }

    public void AddBLEDevice (BLE01Device device){
        // check to see if its there...
        if (!mDevices.contains(device)){
            Log.e("add device", "Address:" + device.getAddress());
            mDevices.add(device);
            notifyItemChanged(mDevices.indexOf(device));
        }
    }

    public void putAdapterState(Bundle outState) {
        outState.putSerializable(STATE_ADAPTER_DEVICES, mDevices);
        outState.putSerializable(STATE_ADAPTER_SELECTED, getSelectedDevices());
    }

    public ArrayList<BLE01Device> getSelectedDevices()
    {
        ArrayList<BLE01Device> checked = new ArrayList<>();
        for(int position = 0; position < mSelected.size(); position++){
            if(mSelected.valueAt(position)){
                checked.add(mDevices.get(mSelected.keyAt(position)));
            }
        }
        Collections.sort(checked);
        return checked;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View item = inflater.inflate(R.layout.rv01, parent, false);
        item.setOnClickListener(this);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BLE01Device device = mDevices.get(position);
        holder.device_name.setText(device.getDeviceName());
        holder.device_address.setText(device.getAddress());
        holder.manufacturer_data.setText(device.getManufacturerData());
        holder.service_data.setText(device.getServiceData());
        holder.services_uuid.setText(device.getServiceUUID());
        holder.txpower.setText(device.getTxPower());

        holder.itemView.setTag(position);
        if(mSelected != null) {
            holder.itemView.setSelected(mSelected.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mDevices.size();
    }

    @Override
    public void onClick(View v)
    {
        int position = (int) v.getTag();
        if(mSelected != null) {
            boolean checked = mSelected.get(position);
            mSelected.put(position, !checked);
            notifyItemChanged(position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView device_name;
        public TextView device_address;
        public TextView txpower;
        public TextView manufacturer_data;
        public TextView services_uuid;
        public TextView service_data;

        public ViewHolder(View view){
            super(view);
            device_name = (TextView) view.findViewById(R.id.device_name);
            device_address = (TextView) view.findViewById(R.id.device_address);
            txpower = (TextView) view.findViewById(R.id.txpower);
            manufacturer_data = (TextView) view.findViewById(R.id.manufacturer_data);
            services_uuid = (TextView) view.findViewById(R.id.services_uuid);
            service_data = (TextView) view.findViewById(R.id.service_data);
        }
    }
}
