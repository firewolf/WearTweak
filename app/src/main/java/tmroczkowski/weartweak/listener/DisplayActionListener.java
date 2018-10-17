package tmroczkowski.weartweak.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.util.Log;
import android.view.Display;

import tmroczkowski.weartweak.service.WakeManager;

public class DisplayActionListener implements DisplayManager.DisplayListener {

    private Context context;

    private String [] state2String = {
            "UNKNOWN",
            "OFF",
            "ON",
            "DOZE",
            "DOZE_SUSPEND",
            "VR",
            "ON_SUSPEND"
    };

    private DisplayManager displayManager;

    public DisplayActionListener (Context context) {

        this.context = context;
        this.displayManager = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
        this.displayManager.registerDisplayListener(this, null);
    }

    @Override
    public void onDisplayAdded(int displayId) {}

    @Override
    public void onDisplayRemoved(int displayId) {}

    @Override
    public void onDisplayChanged(int displayId) {

        int state = displayManager.getDisplay(displayId).getState ();

        Log.d (this.getClass().toString(), "[" + state + "] [" + state2String [state] + "]");

        if (state == Display.STATE_ON) {
            this.sendBroadcastMessage();
        }

        //displayManager.unregisterDisplayListener(displayActionListener);
    }

    private void sendBroadcastMessage () {
        Intent intent = new Intent("tmroczkowski.weartweak.SCREEN_ON");
        intent.putExtra("watch_face_visible", true);

        context.sendBroadcast(intent);
    }
}
