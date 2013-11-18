package net.exsul.callback2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import net.exsul.callback2.Extra.MessageManager;
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
        MessageManager.Instance().OnMessage((Context)this);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
