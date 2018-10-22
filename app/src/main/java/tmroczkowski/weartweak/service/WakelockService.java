package tmroczkowski.weartweak.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.display.DisplayManager;
import android.os.IBinder;
import android.os.PowerManager;
import android.service.wallpaper.WallpaperService;
import android.support.wearable.watchface.WatchFaceService;
import android.util.Log;

import tmroczkowski.weartweak.R;
import tmroczkowski.weartweak.broadcast.FaceBroadcastStaticReceiver;
import tmroczkowski.weartweak.listener.DisplayActionListener;
import tmroczkowski.weartweak.preferences.Timeout;

public class WakelockService extends Service {

    private PowerManager.WakeLock wakeLock;

    private DisplayActionListener displayActionListener;

    BroadcastReceiver broadcastReceiver;

    @Override
    public void onCreate() {


        PowerManager powerManager = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, getString(R.string.lock_tag));

        displayActionListener = new DisplayActionListener(this.getApplicationContext());
        displayActionListener.register(null);

        broadcastReceiver = new FaceBroadcastStaticReceiver();
        IntentFilter filter = new IntentFilter(DisplayActionListener.ACTION_SCREEN_ON);
        filter.addAction(WatchFaceService.ACTION_REQUEST_STATE);
        this.registerReceiver(broadcastReceiver, filter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        boolean visible = intent.getBooleanExtra("watch_face_visible", false);

        if (visible) {
            this.acquire (intent.getLongExtra("timeout", Timeout.DEFAULT_TIMEOUT));
        } else {
            this.releaseLock();
        }

        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        displayActionListener.unregister();
        this.unregisterReceiver(broadcastReceiver);
        this.releaseLock();
    }

    private void acquire (long timeout) {

        this.releaseLock();

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
