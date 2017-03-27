package com.techlatin.vincentyoumans.vyflo22;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * BLE Device class for an individual device detected.
 *
 * Created by vincentyoumans on 3/27/16.
 */
public class BLE01Device implements Serializable, Comparable<BLE01Device> {
    private final String address;
    private String deviceName;
    private String txPower;
    private String manufacturerData;
    private String serviceUUID;
    private String serviceData;

    public BLE01Device(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getTxPower() {
        return txPower;
    }

    public String getManufacturerData() {
        return manufacturerData;
    }

    public String getServiceUUID() {
        return serviceUUID;
    }

    public String getServiceData() {
        return serviceData;
    }


    public void setServiceData(String serviceData) {
        this.serviceData = serviceData;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setTxPower(String txPower) {
        this.txPower = txPower;
    }

    public void setManufacturerData(String manufacturerData) {
        this.manufacturerData = manufacturerData;
    }

    public void setServiceUUID(String serviceUUID) {
        this.serviceUUID = serviceUUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BLE01Device that = (BLE01Device) o;

        return !(address != null ? !address.equals(that.address) : that.address != null);

    }

    @Override
    public int hashCode() {
        return address != null ? address.hashCode() : 0;
    }

    @Override
    public int compareTo(@NonNull BLE01Device another) {
        return address.compareTo(another.address);
    }
}



