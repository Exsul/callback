package net.exsul.callback2;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
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
        }
        else {
          hideFromMenu();
          //showToast("Регистрация службы успешно завершена.");
        }
        //showToast("Иконка установки скроется после перезагрузки.");
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
            cn = new ComponentName("net.exsul.callback2", "net.exsul.callback2.my_activity");
        return cn;
    }

    @Override
    public void onStop() {
        EditText view = (EditText)findViewById(R.id.editText);
        String pre = view.getText().toString();
        SharedPreferences pref = getSharedPreferences("v2", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("pre", pre);
        editor.commit();

        context = null;
        pm = null;
        cn = null;
        super.onStop();
    }
}
