package net.exsul.callback2.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class OutgoingCallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive( final Context context, final Intent intent ) {
        if(intent.getExtras() == null)
            return;
        StateChanged.saved_phone = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
    }
}