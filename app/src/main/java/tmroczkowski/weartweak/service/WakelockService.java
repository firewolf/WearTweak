package tmroczkowski.weartweak.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import tmroczkowski.weartweak.R;
import tmroczkowski.weartweak.listener.DisplayActionListener;
import tmroczkowski.weartweak.preferences.Timeout;

public class WakelockService extends Service {

    private SensorManager mSensorManager;

    private PowerManager.WakeLock wakeLock;

    private DisplayActionListener displayActionListener;

    private SensorListener sensorListener;

    @Override
    public void onCreate() {

        PowerManager powerManager = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorListener = new SensorListener();

        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, getString(R.string.lock_tag));
        mSensorManager.registerListener(sensorListener, mProximity, SensorManager.SENSOR_DELAY_NORMAL);

        displayActionListener = new DisplayActionListener(this.getApplicationContext());
        displayActionListener.register(null);
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

        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        displayActionListener.unregister();
        mSensorManager.unregisterListener(sensorListener);
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

    private class SensorListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {

            float distance = event.values[0];
            Log.d(WakelockService.class.toString(), "onSensorChanged: [" + distance + "]");
            boolean isSomethingVeryClose = false;

            if (isSomethingVeryClose) {
                releaseLock ();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    }
}
