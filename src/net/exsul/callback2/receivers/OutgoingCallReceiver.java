package net.exsul.callback2.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class OutgoingCallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if(null == bundle)
            return;
        StateChanged.saved_phone = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
    }
}