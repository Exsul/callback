package net.exsul.callback2.Extra;

import android.app.Activity;
import android.content.Context;

public class Finish extends CustomMessages {
    @Override
    public void OnMessageClose( final Context c ) {
        ((Activity)c).finish();
    }
}
