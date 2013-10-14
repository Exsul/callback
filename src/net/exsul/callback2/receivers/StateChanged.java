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
    static Switch<String> state_monitor = new SwitchString();
    public static String saved_phone = "bug: deinited";
    public static Context debug_context;

    public StateChanged() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        debug_context = context;
        Bundle bundle = intent.getExtras();

        if(null == bundle)
            return;

        String state = bundle.getString(TelephonyManager.EXTRA_STATE);
        if (OnChangedState(state)) {
           CallEnded(context, state_monitor.PreviosDuration());
        }
    }


    private boolean OnChangedState( String state ) {
      state_monitor.Update(state);
      return state_monitor.ChangedTo(TelephonyManager.EXTRA_STATE_OFFHOOK, TelephonyManager.EXTRA_STATE_IDLE);
    }

    private void CallEnded( Context context, Long duration ) {
        if (duration > 10)
            return;
        //Toast.makeText(context, "Pre activity", Toast.LENGTH_LONG).show();

        Intent i = new Intent();
        i.setClassName("net.exsul.callback2", "net.exsul.callback2.DialogActivity");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        context.startActivity(i);
    }
}