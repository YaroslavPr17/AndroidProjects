package com.example.configchangesdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BatChargeReceiverUnplug extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, R.string.power_disconnected, Toast.LENGTH_LONG).show();
    }
}