package tmroczkowski.weartweak;

import android.app.Activity;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.PowerManager;
import android.view.Display;
import android.widget.Toast;

public class DisplayActionListener implements DisplayManager.DisplayListener {

    private int timeout = 5 * 1000;

    private String [] state2String = {
            "UNKNOWN",
            "OFF",
            "ON",
            "DOZE",
            "DOZE_SUSPEND",
            "VR",
            "ON_SUSPEND"
    };

    private WakeManager wakeManager;

    private DisplayManager displayManager;

    public DisplayActionListener (
            Activity main,
            WakeManager wakeManager) {

        this.displayManager = (DisplayManager) main.getSystemService(Context.DISPLAY_SERVICE);
        this.wakeManager = wakeManager;

        this.displayManager.registerDisplayListener(this, null);
    }

    @Override
    public void onDisplayAdded(int displayId) {}

    @Override
    public void onDisplayRemoved(int displayId) {}

    @Override
    public void onDisplayChanged(int displayId) {

        int state = displayManager.getDisplay(displayId).getState ();

        if (state == Display.STATE_ON) {
            this.wakeManager.wakeLock();
        }
    }

    public void setTimeout(int timeout) {

        this.timeout = timeout;
    }

    private void printState (int state, String msg) {

        System.out.println ("--------[" + msg + "] -- [" + state2String [state] + "] --- [" + state + "] --AAA---");
    }
}
