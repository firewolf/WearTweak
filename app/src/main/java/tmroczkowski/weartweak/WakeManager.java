package tmroczkowski.weartweak;

import android.os.PowerManager;
import android.util.Log;

public class WakeManager {

    PowerManager powerManager;

    PowerManager.WakeLock wakeLock;

    long timeout;

    private String tag = "ScreenTimeout::FullWakeLock";

    public WakeManager (PowerManager powerManager) {
        this.powerManager = powerManager;
    }

    public void wakeLock () {

        if (wakeLock != null && wakeLock.isHeld()) {
            this.releaseLock();
        }

        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, this.tag);

        Log.d ("WakeManager", "timeout: [" + timeout + "]");

        if (timeout > 0) {
            wakeLock.acquire(timeout);
        } else {
            wakeLock.acquire();
        }
    }

    public void releaseLock () {
        if (wakeLock.isHeld()) {
            wakeLock.release();
        }
    }

    public void setTimeout (long timeout) {
        this.timeout = timeout;
    }
}
