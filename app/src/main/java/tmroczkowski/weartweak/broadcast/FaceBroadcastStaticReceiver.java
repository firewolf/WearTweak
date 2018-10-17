package tmroczkowski.weartweak.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

import tmroczkowski.weartweak.WearTweakData;
import tmroczkowski.weartweak.service.WakeManager;

public class FaceBroadcastStaticReceiver extends BroadcastReceiver {

    WakeManager wakeManager;

    private long timeout;

    private Context context;

    public FaceBroadcastStaticReceiver() {}

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d (this.getClass().toString(), "onReceive intent");

        this.context = context;

        if (this.wakeManager == null) {
            this.wakeManager = new WakeManager((PowerManager) context.getSystemService(Context.POWER_SERVICE));
        }

        this.wakeLock(intent);
    }

    private void wakeLock (Intent intent) {

        boolean visible = intent.getBooleanExtra("watch_face_visible", false);

        Log.d (this.getClass().toString(), "wakeLock [" + this.timeout + "]");

        if (visible) {

            WearTweakData wearTweakData = (WearTweakData) this.context.getApplicationContext();
            wearTweakData.getTimeout();
            this.wakeManager.setTimeout(wearTweakData.getTimeout());
            this.wakeManager.wakeLock();
        }

        Log.d (this.getClass().toString(), "Watch visible: [" + visible + "]");
    }

}
