package net.exsul.callback2.Extra;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class Broadcast extends CustomMessages {
    @Override
    public void OnAccept( final Context c ) {
        FallbackAction fb = new FallbackAction();

        Intent i = new Intent();
        i.setAction("net.exsul.callback2.make_callback");
        i.putExtra("URI", fb.GetURI(c));
        c.sendBroadcast(i, "net.exsul.callback2.ListenCallbackRequests");
    }
}
