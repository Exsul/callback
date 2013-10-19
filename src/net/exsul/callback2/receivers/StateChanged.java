package net.exsul.callback2.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;
import net.exsul.callback2.Switch;
import net.exsul.callback2.SwitchString;

public class StateChanged extends BroadcastReceiver {
    static Switch<String> state_monitor = null;
    public static String saved_phone = "bug: deinited";

    public StateChanged() {
    }

    private Switch<String> GetMonitor() {
        if (state_monitor == null)
            state_monitor = new SwitchString();
        return state_monitor;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        if(null == bundle)
            return;

        String state = bundle.getString(TelephonyManager.EXTRA_STATE);
        if (OnChangedState(state)) {
           CallEnded(context, GetMonitor().PreviosDuration());
        }
    }


    private boolean OnChangedState( String state ) {
      GetMonitor().Update(state);
      return GetMonitor().ChangedTo(TelephonyManager.EXTRA_STATE_OFFHOOK, TelephonyManager.EXTRA_STATE_IDLE);
    }

    private void CallEnded( Context context, Long duration ) {
        state_monitor = null;
        if (duration > 10)
            return;

        Intent i = new Intent();
        i.setClassName("net.exsul.callback2", "net.exsul.callback2.DialogActivity");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        context.startActivity(i);
    }
}