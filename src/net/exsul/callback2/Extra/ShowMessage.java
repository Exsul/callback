package net.exsul.callback2.Extra;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import net.exsul.callback2.receivers.StateChanged;

public class ShowMessage extends CustomMessages {
    Context c;
    @Override
    public void OnMessage(final Context _c) {
        c = _c;
        final AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setMessage("У вас закончились средства? Попросить перезвонить " + StateChanged.saved_phone + "?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MessageManager.Instance().OnAccept(c);
                        MessageManager.Instance().OnMessageClose(c);
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MessageManager.Instance().OnDeny(c);
                        MessageManager.Instance().OnMessageClose(c);
                    }
                })
                .setCancelable(false);
        builder.show();
    }
}
