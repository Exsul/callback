package net.exsul.callback2.Extra;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import net.exsul.callback2.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BestBefore extends CustomMessages {
    @Override
    public void OnMessageClose( final Context c ) {
        Date a = GetBestBeforeDate(c), b = GetCurrentDate(c);
        if (a == null || b == null)
            return;
        if (a.getTime() < b.getTime())
            MakeNotification(c);
    }

    private Date GetBestBeforeDate( final Context a ) {
        try {
            String best_before = a.getString(R.string.best_before);
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            Date result =  df.parse(best_before);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    private Date GetCurrentDate( final Context a ) {
      return new Date();
    }

    private void MakeNotification( final Context a ) {
// this
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager)a.getSystemService(ns);

        int icon = R.drawable.icon;
        CharSequence tickerText = "Необходимо обновление"; // ticker-text
        long when = System.currentTimeMillis();
        Context context = a;
        CharSequence contentTitle = "Программа устарела";
        CharSequence contentText = "Вы используете слишком старую версию";
        //Intent notificationIntent = null; //new Intent(this, Example.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new Intent(), 0);
                //null;//PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification = new Notification(icon, tickerText, when);
        notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);

// and this
        final int HELLO_ID = 1;
        mNotificationManager.notify(HELLO_ID, notification);
    }
}
