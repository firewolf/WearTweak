package tmroczkowski.weartweak.listener;

import android.app.Activity;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.view.Display;

import tmroczkowski.weartweak.service.WakeManager;

public class DisplayActionListener implements DisplayManager.DisplayListener {

    private String [] state2String = {
            "UNKNOWN",
            "OFF",
            "ON",
            "DOZE",
            "DOZE_SUSPEND",
            "VR",
            "ON_SUSPEND"
    };

    /**
     *
     */
    private WakeManager wakeManager;

    /**
     *
     */
    private DisplayManager displayManager;

    public DisplayActionListener (Activity activity, WakeManager wakeManager) {

        this.wakeManager = wakeManager;
        this.displayManager = (DisplayManager) activity.getSystemService(Context.DISPLAY_SERVICE);
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
            this.wakeManager.wakeLock ();
        }

        //displayManager.unregisterDisplayListener(displayActionListener);
    }

    private void printState (int state, String msg) {
        System.out.println ("--------[" + msg + "] -- [" + state2String [state] + "] --- [" + state + "] --AAA---");
    }
}
