package tmroczkowski.weartweak;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.wearable.watchface.WatchFaceService;
import android.util.Log;

public class WearTweakService extends Service {

    long timeout = 5 * 1000;

    BroadcastReceiver broadcastReceiver;

    WakeManager wakeManager;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        timeout = intent.getLongExtra("timeout", timeout);

        Log.d ("WearTweakService", "timeout: [" + timeout + "]");

        wakeManager = new WakeManager((PowerManager) this.getSystemService(Context.POWER_SERVICE));
        wakeManager.setTimeout(timeout);

        broadcastReceiver = new FaceBroadcastReceiver(wakeManager);
        IntentFilter intentFilter = new IntentFilter(WatchFaceService.ACTION_REQUEST_STATE);
        this.registerReceiver(broadcastReceiver, intentFilter);

        Log.d ("WearTweakService", "Service onCreate, FaceBroadcastReceiver registered.");

        return START_STICKY;
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
