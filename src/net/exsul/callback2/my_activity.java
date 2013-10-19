package net.exsul.callback2;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class my_activity extends Activity {
    private Context context = null;
    private PackageManager pm = null;
    private ComponentName cn = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        context = getApplicationContext();

        if (getThisActivityHided()) {
            showToast("Служба уже зарегистрирована.");
            showToast("Иконка скроется после перезагрузки телефона.");
        }
        else {
          hideFromMenu();
          showToast("Регистрация службы успешно завершена.");
        }
        finish();
    }

    private void hideFromMenu() {
        pm().setComponentEnabledSetting(cn(), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        assert(getThisActivityHided());
    }

    private boolean getThisActivityHided() {
        return pm().getComponentEnabledSetting(cn()) == PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
    }

    private void showToast( String text ) {
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();
    }

    private PackageManager pm() {
        if (pm == null)
            pm = getPackageManager();
        return pm;
    }

    private ComponentName cn() {
        if (cn == null)
            cn = new ComponentName(context, Activity.class);
        return cn;
    }

    @Override
    public void onStop() {
        context = null;
        pm = null;
        cn = null;
    }
}
