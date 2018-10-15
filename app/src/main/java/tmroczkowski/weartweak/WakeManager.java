package tmroczkowski.weartweak;

import android.app.Activity;
import android.content.Context;
import android.os.PowerManager;

public class WakeManager {

    PowerManager powerManager;

    PowerManager.WakeLock wakeLock;

    long timeout;

    private String tag = "ScreenTimeout::FullWakeLock";

    public WakeManager (Activity activity) {
        this.powerManager = (PowerManager) activity.getSystemService(Context.POWER_SERVICE);
    }

    public void wakeLock () {

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

    public void releaseLock () {
        if (wakeLock.isHeld()) {
            wakeLock.release();
        }
    }

    public void setTimeout (long timeout) {
        this.timeout = timeout;
    }
}
