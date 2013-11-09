package net.exsul.callback2.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;
import net.exsul.callback2.Switch;

public class StateChanged extends BroadcastReceiver {
    static Switch state_monitor = null;
    public static String saved_phone = "";
    public static String pre_value = null;
    static boolean disabled = false;

    private Switch GetMonitor() {
        if (state_monitor == null)
            state_monitor = new Switch();
        return state_monitor;
    }

    @Override
    public void onReceive( final Context context, final Intent intent ) {
        Bundle bundle = intent.getExtras();

        if(null == bundle)
            return;

        String state = bundle.getString(TelephonyManager.EXTRA_STATE);
        if (OnChangedState(state)) {
           if (disabled) {
               Toast.makeText(context, "(Маячок) DEBUG: Звонок входящий, игнорирую.", Toast.LENGTH_SHORT).show();
           }
           else
             CallEnded(context, GetMonitor().PreviosDuration());
        }
        if (GetMonitor().Equal(GetMonitor().Current(), TelephonyManager.EXTRA_STATE_IDLE))
            disabled = false;
    }


    private boolean OnChangedState( final String state ) {
      GetMonitor().Update(state);
      if (GetMonitor().ChangedTo(TelephonyManager.EXTRA_STATE_RINGING, TelephonyManager.EXTRA_STATE_OFFHOOK))
          disabled = true;
      return GetMonitor().ChangedTo(TelephonyManager.EXTRA_STATE_OFFHOOK, TelephonyManager.EXTRA_STATE_IDLE);
    }

    private void CallEnded( final Context context, final Long duration ) {
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