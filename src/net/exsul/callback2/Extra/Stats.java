package net.exsul.callback2.Extra;



import android.content.*;

public class Stats extends CustomMessages {
    @Override
    public void OnMessage( final Context c ) {
        IncrPreference(c, "stats.messages");
    }

    @Override
    public void OnAccept( final Context c ) {
        IncrPreference(c, "stats.accept");
    }

    private void IncrPreference( final Context c, String name ) {
        final SharedPreferences pref = c.getSharedPreferences("v2", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        int mes = pref.getInt(name, 0);
        editor.putInt(name, mes + 1);
        editor.commit();
    }
}
