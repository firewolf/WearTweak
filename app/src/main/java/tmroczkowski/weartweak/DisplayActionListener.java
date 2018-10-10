package tmroczkowski.weartweak;

import android.hardware.display.DisplayManager;
import android.os.PowerManager;
import android.view.Display;
import android.widget.Toast;

public class DisplayActionListener implements DisplayManager.DisplayListener {

    /**
     *
     * @var WakeManager
     */
    WakeManager wakeManager;

    DisplayManager displayManager;

    private int timeout = 5 * 1000;

    /**
     *
     * @param powerManager
     * @param displayManager
     */
    public DisplayActionListener (
            PowerManager powerManager,
            DisplayManager displayManager) {

        this.displayManager = displayManager;
        this.wakeManager = new WakeManager(powerManager);
    }

    @Override
    public void onDisplayAdded(int displayId) {}

    @Override
    public void onDisplayRemoved(int displayId) {}

    @Override
    public void onDisplayChanged(int displayId) {

        int state = displayManager.getDisplay(displayId).getState ();

        if (state == Display.STATE_ON) {
            this.wakeManager.wakeLock(timeout);
        }

    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    private void printState (int state, String msg) {
        System.out.println ("---AAA-----[" + msg + "] -- [" + state2String [state] + "] --- [" + state + "] --AAA---");
    }

    private String [] state2String = {
            "UNKNOWN",
            "OFF",
            "ON",
            "DOZE",
            "DOZE_SUSPEND",
            "VR",
            "ON_SUSPEND"
    };
}
