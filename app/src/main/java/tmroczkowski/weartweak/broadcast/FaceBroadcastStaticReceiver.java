package tmroczkowski.weartweak.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import tmroczkowski.weartweak.preferences.Timeout;
import tmroczkowski.weartweak.service.WakelockService;

public class FaceBroadcastStaticReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        boolean visible = intent.getBooleanExtra("watch_face_visible", false);
        long timeout = (new Timeout(context)).read ();

        if (timeout >= 0) {
            Intent wakelock = new Intent(context, WakelockService.class);
            wakelock.putExtra("watch_face_visible", visible);
            context.startService(wakelock);
        }
    }
}
