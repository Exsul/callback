package net.exsul.callback.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class OutgoingCallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if(null == bundle)
            return;
        StateChanged.saved_phone = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
    }
}