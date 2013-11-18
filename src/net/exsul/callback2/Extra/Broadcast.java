package net.exsul.callback2.Extra;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class Broadcast extends CustomMessages {
    private boolean isPackageExisted(Context c, String targetPackage){
        PackageManager pm   = c.getPackageManager();
        try {
            PackageInfo info=pm.getPackageInfo(targetPackage,PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
        return true;
    }
}
