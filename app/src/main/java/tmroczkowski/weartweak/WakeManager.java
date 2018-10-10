package tmroczkowski.weartweak;

import android.os.PowerManager;

public class WakeManager {

    PowerManager powerManager;

    PowerManager.WakeLock wakeLock;

    private String tag = "ScreenTimeout::FullWakeLock";

    /**
     *
     * @param powerManager
     */
    public WakeManager (PowerManager powerManager) {
        this.powerManager = powerManager;
    }

    /**
     *
     * @param timeout
     */
    public void wakeLock (long timeout) {

        if (wakeLock != null && wakeLock.isHeld()) {
            releaseLock();
        }

        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, this.tag);

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
}
