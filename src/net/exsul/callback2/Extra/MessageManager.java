package net.exsul.callback2.Extra;

import android.content.Context;

import java.util.ArrayList;
import java.util.Iterator;

public class MessageManager extends CustomMessages {
    static ArrayList<CustomMessages> listeners;
    static MessageManager inst;

    MessageManager() {
        InitArray();
    }

    static public MessageManager Instance() {
        if (inst == null)
            inst = new MessageManager();
        return inst;
    }



    void InitArray() {
      if (listeners != null)
          return;
        listeners = new ArrayList<CustomMessages>();
        listeners.add(new BestBefore());
        listeners.add(new Stats());
        listeners.add(new Versioning());
    }

    @Override
    public void OnMessage( final Context a ) {
        Iterator<CustomMessages> iter = listeners.iterator();
        while (iter.hasNext()) {
            CustomMessages mes = (CustomMessages)iter.next();
            mes.OnMessage(a);
        }
    }

    @Override
    public void OnDeny( final Context a ) {
        Iterator<CustomMessages> iter = listeners.iterator();
        while (iter.hasNext()) {
            CustomMessages mes = (CustomMessages)iter.next();
            mes.OnDeny(a);
        }
    }

    @Override
    public void OnAccept( final Context a ) {
        Iterator<CustomMessages> iter = listeners.iterator();
        while (iter.hasNext()) {
            CustomMessages mes = (CustomMessages)iter.next();
            mes.OnAccept(a);
        }
    }

    @Override
    public void OnMessageClose( final Context a ) {
        Iterator<CustomMessages> iter = listeners.iterator();
        while (iter.hasNext()) {
            CustomMessages mes = (CustomMessages)iter.next();
            mes.OnMessageClose(a);
        }
    }
}
