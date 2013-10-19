package net.exsul.callback2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import net.exsul.callback2.receivers.StateChanged;

/**
 * Created with IntelliJ IDEA.
 * User: Enelar
 * Date: 13.10.13
 * Time: 16:54
 * To change this template use File | Settings | File Templates.
 */
public class DialogActivity extends Activity {
    @Override
    public void onCreate( final Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        //Toast.makeText(this, "In activity", Toast.LENGTH_LONG).show();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("У вас закончились средства? Попросить перезвонить " + StateChanged.saved_phone + "?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MakeCallback();
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        // User cancelled the dialog
                    }
                })
                .setCancelable(false);
        // Create the AlertDialog object and return it
        builder.show();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void MakeCallback(){
        final Intent intent = new Intent(Intent.ACTION_DIAL);

        final String enc = "tel:*" + Uri.encode(getPreValue()) + "*" + StateChanged.saved_phone + Uri.encode("#");
        intent.setData(Uri.parse(enc));
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    private String getPreValue() {
        if (StateChanged.pre_value != null)
            return StateChanged.pre_value;
        final SharedPreferences pref = getSharedPreferences("v2", MODE_PRIVATE);
        return StateChanged.pre_value = pref.getString("pre", "144");
    }
}
