package tmroczkowski.weartweak;

import android.os.PowerManager;

import java.util.Observable;
import java.util.Observer;

public class WakeManager {

    PowerManager powerManager;

    PowerManager.WakeLock wakeLock;

    private String tag = "ScreenTimeout::FullWakeLock";

    private int timeout = 5 * 1000;

    boolean enabled;
    /**
     *
     * @param powerManager
     */
    public WakeManager (PowerManager powerManager) {
        this.powerManager = powerManager;
    }

    public void wakeLock () {

        if (this.enabled) {

            if (wakeLock != null && wakeLock.isHeld()) {
                this.releaseLock();
            }

            wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, this.tag);

            if (timeout > 0) {
                wakeLock.acquire(timeout);
            } else {
                wakeLock.acquire();
            }
        }
    }

    public void releaseLock () {
        if (wakeLock.isHeld()) {
            wakeLock.release();
        }
    }

    public void setEnabled (boolean enabled) {
        this.enabled = enabled;
    }

    public void setTimeout (int timeout) {
        this.timeout = timeout;
    }
}
