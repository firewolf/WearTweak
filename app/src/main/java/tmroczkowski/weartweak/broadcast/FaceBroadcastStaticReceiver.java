package tmroczkowski.weartweak.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import tmroczkowski.weartweak.service.WakeManager;

public class FaceBroadcastStaticReceiver extends BroadcastReceiver {

    WakeManager wakeManager;

    public FaceBroadcastStaticReceiver() {}

    @Override
    public void onReceive(Context context, Intent intent) {

        boolean visible = intent.getBooleanExtra("watch_face_visible", false);

        Log.d ("FaceBroadcastStaticReceiver", "Watch visible: [" + visible + "]");

//        if (visible) {
//            this.wakeManager.wakeLock ();
//        }

    }
}
