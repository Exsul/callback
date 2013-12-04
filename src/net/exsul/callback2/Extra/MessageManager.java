package net.exsul.callback2.Extra;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.Iterator;

public class MessageManager extends CustomMessages {
    static ArrayList<CustomMessages> listeners;
    static MessageManager inst;

    MessageManager() {
    }

    static public MessageManager Instance() {
        if (inst == null)
            inst = new MessageManager();
        return inst;
    }

    ArrayList<CustomMessages> GetArray( final Context c ) {
        if (listeners == null)
            InitArray(c);
        return listeners;
    }

    public void ClearArray() {
        listeners = null;
    }

    void InitArray( final Context c ) {
      if (listeners != null)
          return;
        listeners = new ArrayList<CustomMessages>();
        listeners.add(new ShowMessage());

        if (isPackageExisted(c, "net.exsul.callback_direct"))
            listeners.add(new Broadcast());
        else
            listeners.add(new FallbackAction());

        //listeners.add(new BestBefore());
        listeners.add(new Stats());
        //listeners.add(new Versioning());
        listeners.add(new Finish());
    }

    private boolean isPackageExisted(Context c, String targetPackage) {
        PackageManager pm = c.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(targetPackage, PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
        return true;
    }

    @Override
    public void OnMessage( final Context a ) {
        Iterator<CustomMessages> iter = GetArray(a).iterator();
        while (iter.hasNext()) {
            CustomMessages mes = (CustomMessages)iter.next();
            mes.OnMessage(a);
        }
    }

    @Override
    public void OnDeny( final Context a ) {
        Iterator<CustomMessages> iter = GetArray(a).iterator();
        while (iter.hasNext()) {
            CustomMessages mes = (CustomMessages)iter.next();
            mes.OnDeny(a);
        }
    }

    @Override
    public void OnAccept( final Context a ) {
        Iterator<CustomMessages> iter = GetArray(a).iterator();
        while (iter.hasNext()) {
            CustomMessages mes = (CustomMessages)iter.next();
            mes.OnAccept(a);
        }
    }

    @Override
    public void OnMessageClose( final Context a ) {
        Iterator<CustomMessages> iter = GetArray(a).iterator();
        while (iter.hasNext()) {
            CustomMessages mes = (CustomMessages)iter.next();
            mes.OnMessageClose(a);
        }
    }
}
