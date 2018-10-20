package tmroczkowski.weartweak.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import tmroczkowski.weartweak.preferences.Timeout;

public class WakelockService extends Service {

    int mStartMode = START_NOT_STICKY;

    private String tag = "tmroczkowski.weartweak.WakelockService";

    PowerManager.WakeLock wakeLock;

    @Override
    public void onCreate() {
        PowerManager powerManager = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, this.tag);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        boolean visible = intent.getBooleanExtra("watch_face_visible", false);
        long timeout = (new Timeout(this)).read();

        if (visible) {
            this.acquire (timeout);
        } else {
            this.releaseLock();
        }

        return mStartMode;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        this.releaseLock();
    }

    private void acquire (long timeout) {

        if (timeout > 0) {
            wakeLock.acquire(timeout);
        } else {
            wakeLock.acquire();
        }
    }

    private void releaseLock () {
        if (wakeLock != null && wakeLock.isHeld()) {
            wakeLock.release();
        }
    }
}
