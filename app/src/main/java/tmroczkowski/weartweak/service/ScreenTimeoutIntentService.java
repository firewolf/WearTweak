package tmroczkowski.weartweak.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ScreenTimeoutIntentService extends IntentService {

    public ScreenTimeoutIntentService () {
        super ("ScreenTimeoutIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("SOME_TAG", "New intent: " + intent);
        while (true) {
            try {
                Thread.sleep(1 * 1000);
                Toast.makeText(this, "onHandleIntent", Toast.LENGTH_SHORT).show();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
