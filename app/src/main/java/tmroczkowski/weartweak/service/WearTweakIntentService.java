package tmroczkowski.weartweak.service;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.support.wearable.watchface.WatchFaceService;
import android.util.Log;

import tmroczkowski.weartweak.broadcast.FaceBroadcastReceiver;

public class WearTweakIntentService extends IntentService {

    long timeout = 5 * 1000;

    BroadcastReceiver broadcastReceiver;

    WakeManager wakeManager;

    public WearTweakIntentService() {
        super("WearTweakIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        timeout = intent.getLongExtra("timeout", timeout);

        Log.d ("WearTweakService", "timeout: [" + timeout + "]");

        wakeManager = new WakeManager((PowerManager) this.getSystemService(Context.POWER_SERVICE));
        wakeManager.setTimeout(timeout);

        broadcastReceiver = new FaceBroadcastReceiver(wakeManager);
        IntentFilter intentFilter = new IntentFilter(WatchFaceService.ACTION_REQUEST_STATE);
        this.registerReceiver(broadcastReceiver, intentFilter);

        Log.d ("WearTweakService", "Service onCreate, FaceBroadcastReceiver registered.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("WearTweakService", "onDestroy");

        if (broadcastReceiver != null) {
            this.unregisterReceiver(broadcastReceiver);
        }

    }
}
