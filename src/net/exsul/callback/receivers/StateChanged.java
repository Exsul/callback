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

        //Toast.makeText(context, state_monitor.IsInited() + "in", Toast.LENGTH_LONG).show();

        if(null == bundle)
            return;

        //Toast.makeText(context, bundle.toString(), Toast.LENGTH_LONG).show();

        String state = bundle.getString(TelephonyManager.EXTRA_STATE);
        //Toast.makeText(context, state, Toast.LENGTH_LONG).show();
        if (OnChangedState(state)) {
           CallEnded(context, saved_phone, state_monitor.PreviosDuration());
        }
        //SavePhone(bundle);
        //Toast.makeText(context, state_monitor.IsInited() + "out", Toast.LENGTH_LONG).show();
    }


    private boolean OnChangedState( String state ) {
      state_monitor.Update(state);
      return state_monitor.ChangedTo(TelephonyManager.EXTRA_STATE_OFFHOOK, TelephonyManager.EXTRA_STATE_IDLE);
    }

    private void SavePhone( Bundle b ) {
      Toast.makeText(debug_context, "Save: " + state_monitor.Current() + b.getString(TelephonyManager.EXTRA_INCOMING_NUMBER), Toast.LENGTH_LONG).show();
      if (!state_monitor.Equal(state_monitor.Current(), TelephonyManager.EXTRA_STATE_OFFHOOK))
          return;
      saved_phone = b.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
    }

    private void CallEnded( Context context, String phone, Long duration ) {
        String info = "END:" + phone + "\nDUR:" + duration;

        Toast.makeText(context, info, Toast.LENGTH_LONG).show();
    }

}