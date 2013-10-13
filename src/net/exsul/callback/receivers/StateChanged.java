package net.exsul.callback.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;
import net.exsul.callback.Switch;
import net.exsul.callback.SwitchString;

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
           CallEnded(context, saved_phone, state_monitor.PreviosDuration());
        }
    }


    private boolean OnChangedState( String state ) {
      state_monitor.Update(state);
      return state_monitor.ChangedTo(TelephonyManager.EXTRA_STATE_OFFHOOK, TelephonyManager.EXTRA_STATE_IDLE);
    }

    private void CallEnded( Context context, String phone, Long duration ) {
        String info = "END:" + phone + "\nDUR:" + duration;

        Toast.makeText(context, info, Toast.LENGTH_LONG).show();
    }

}