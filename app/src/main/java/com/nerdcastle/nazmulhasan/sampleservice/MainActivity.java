package com.nerdcastle.nazmulhasan.sampleservice;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView textView;
    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {

                int resultCode = bundle.getInt(JustService.RESULT);
                if (resultCode == RESULT_OK) {

                    textView.setText("Service running");
                } else if (resultCode == RESULT_CANCELED)

                    textView.setText("Service destroyed");
            } else {

            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);


    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(JustService.NOTIFICATION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    // Method to start the service
    public void startService(View view) {
        startService(new Intent(getBaseContext(), JustService.class));


    }

    // Method to stop the service
    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), JustService.class));

    }
}