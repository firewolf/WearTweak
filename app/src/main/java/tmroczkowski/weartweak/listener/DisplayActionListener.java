package tmroczkowski.weartweak.listener;

import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.util.Log;
import android.view.Display;

public class DisplayActionListener implements DisplayManager.DisplayListener {

    private Context context;

    private DisplayManager displayManager;

    public DisplayActionListener (Context context) {

        this.context = context;
        this.displayManager = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
    }

    @Override
    public void onDisplayAdded(int displayId) {}

    @Override
    public void onDisplayRemoved(int displayId) {}

    @Override
    public void onDisplayChanged(int displayId) {

        int state = displayManager.getDisplay(displayId).getState ();

        if (state == Display.STATE_ON) {
            this.sendBroadcastMessage();
        }
    }

    private void sendBroadcastMessage () {
        Intent intent = new Intent("tmroczkowski.weartweak.SCREEN_ON");
        intent.putExtra("watch_face_visible", true);

        context.sendBroadcast(intent);
    }

    public void register (Handler handler) {
        this.displayManager.registerDisplayListener(this, handler);
    }

    public void unregister () {
        this.displayManager.unregisterDisplayListener(this);
    }
}
