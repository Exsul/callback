package net.exsul.callback;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import net.exsul.callback.receivers.StateChanged;

/**
 * Created with IntelliJ IDEA.
 * User: Enelar
 * Date: 13.10.13
 * Time: 16:54
 * To change this template use File | Settings | File Templates.
 */
public class DialogActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "In activity", Toast.LENGTH_LONG).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want callback")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MakeCallback();
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        builder.show();
    }

    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(this, "OnStop", Toast.LENGTH_LONG).show();
    }

    public void MakeCallback(){
        Intent intent = new Intent(Intent.ACTION_DIAL);

        String enc = "tel:*144*" + StateChanged.saved_phone + Uri.encode("#");
        intent.setData(Uri.parse(enc));
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }
}
