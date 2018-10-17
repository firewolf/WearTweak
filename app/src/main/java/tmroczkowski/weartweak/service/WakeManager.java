package tmroczkowski.weartweak.service;

import android.os.PowerManager;
import android.util.Log;

public class WakeManager {

    public static long DEFAULT_TIMEOUT = 5 * 1000L;

    PowerManager powerManager;

    PowerManager.WakeLock wakeLock;

    private String tag = "ScreenTimeout::FullWakeLock";

    public WakeManager (PowerManager powerManager) {
        this.powerManager = powerManager;
    }

    public void wakeLock (boolean visible, long timeout) {

        this.releaseLock();

        if (visible) {

            wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, this.tag);

            Log.d ("WakeManager", "timeout: [" + timeout + "]");

            if (timeout > 0) {
                wakeLock.acquire(timeout);
            }
        }
    }

    public void releaseLock () {
        if (wakeLock != null && wakeLock.isHeld()) {
            wakeLock.release();
        }
    }
}
