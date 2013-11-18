package net.exsul.callback2.Extra;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import net.exsul.callback2.receivers.StateChanged;

public class FallbackAction extends CustomMessages {
    @Override
    public void OnAccept( final Context c ) {
         MakeCallback(c);
    }

    public void MakeCallback( final Context c ){
        final Intent intent = new Intent(Intent.ACTION_DIAL);

        final String enc = "tel:*" + Uri.encode(getPreValue(c)) + "*" + StateChanged.saved_phone + Uri.encode("#");
        intent.setData(Uri.parse(enc));
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        c.startActivity(intent);
    }

    private String getPreValue( final Context c ) {
        if (StateChanged.pre_value != null)
            return StateChanged.pre_value;
        final SharedPreferences pref = c.getSharedPreferences("v2", c.MODE_PRIVATE);
        return StateChanged.pre_value = pref.getString("pre", "144");
    }
}
