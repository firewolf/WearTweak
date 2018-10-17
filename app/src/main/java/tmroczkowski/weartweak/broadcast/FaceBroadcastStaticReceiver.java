package tmroczkowski.weartweak.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PowerManager;
import android.util.Log;

import tmroczkowski.weartweak.R;
import tmroczkowski.weartweak.service.WakelockManager;

public class FaceBroadcastStaticReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.preferencesFile), Context.MODE_PRIVATE);

        long timeout = sharedPreferences.getLong("timeout", WakelockManager.DEFAULT_TIMEOUT);
        boolean visible = intent.getBooleanExtra("watch_face_visible", false);

        Log.d (this.getClass().toString(), "wakeLock [" + timeout + "]");

        new WakelockManager((PowerManager) context.getSystemService(Context.POWER_SERVICE)).wakeLock(visible, timeout);
    }

}
