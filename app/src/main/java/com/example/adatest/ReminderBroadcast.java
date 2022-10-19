package com.example.adatest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReminderBroadcast extends BroadcastReceiver {

    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, "notifyStore");
        builder.setSmallIcon(R.drawable.shoppingcartpart96);
        builder.setContentTitle("Student Handel");
        builder.setContentText("Glöm inte kolla veckans erbjudanden!");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);
        notificationManager.notify(200, builder.build());

    }
}
