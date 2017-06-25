package com.ceco.oosdndtest;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    private NotificationManager mNm;
    private TextView mTxtDndMode;

    private BroadcastReceiver mDndModeChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateDndModeView();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtDndMode = (TextView) findViewById(R.id.dnd_mode);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mNm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        IntentFilter intentFilter = new IntentFilter(NotificationManager.ACTION_INTERRUPTION_FILTER_CHANGED);
        registerReceiver(mDndModeChangeReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mDndModeChangeReceiver);
        mNm = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateDndModeView();
    }

    private void updateDndModeView() {
        mTxtDndMode.setText(getDndModeAsString(mNm.getCurrentInterruptionFilter()));
    }

    static String getDndModeAsString(int dndMode) {
        switch (dndMode) {
            case NotificationManager.INTERRUPTION_FILTER_ALARMS:
                return "4 - Alarms only";
            case NotificationManager.INTERRUPTION_FILTER_ALL:
                return "1 - DnD off (Ring mode)";
            case NotificationManager.INTERRUPTION_FILTER_NONE:
                return "3 - Total silence";
            case NotificationManager.INTERRUPTION_FILTER_PRIORITY:
                return "2 - Priority only";
            default:
            case NotificationManager.INTERRUPTION_FILTER_UNKNOWN:
                return "0 - Unknown";
        }
    }
}
