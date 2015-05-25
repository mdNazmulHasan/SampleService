package com.nerdcastle.nazmulhasan.sampleservice;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

public class JustService extends Service {
    private int result = Activity.RESULT_CANCELED;

    public static final String RESULT = "result";
    public static final String NOTIFICATION = "com.nerdcastle.nazmulhasan.sampleservice";
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        result = Activity.RESULT_OK;
        publishResults(result);

        intent.putExtra("result","service is running");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        // Let it continue running until it is stopped.
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        result = Activity.RESULT_CANCELED;
        publishResults(result);

        Intent intent=new Intent().putExtra("result", "service is destroyed");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
    private void publishResults(int result) {
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(RESULT, result);
        sendBroadcast(intent);
    }
}