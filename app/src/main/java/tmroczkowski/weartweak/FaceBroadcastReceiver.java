package tmroczkowski.weartweak;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

public class FaceBroadcastReceiver extends BroadcastReceiver {

    WakeManager wakeManager;

    public FaceBroadcastReceiver (WakeManager wakeManager) {
        this.wakeManager = wakeManager;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        boolean visible = intent.getBooleanExtra("watch_face_visible", false);

        Log.d ("FaceBroadcastReceiver", "Watch visible: [" + visible + "]");

        if (visible) {
            this.wakeManager.wakeLock ();
        }

    }
}
