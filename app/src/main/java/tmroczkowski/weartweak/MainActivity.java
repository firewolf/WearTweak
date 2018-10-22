package tmroczkowski.weartweak;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;

import tmroczkowski.weartweak.listener.DisplayActionListener;
import tmroczkowski.weartweak.preferences.Timeout;
import tmroczkowski.weartweak.service.WakelockService;
import tmroczkowski.weartweak.view.TimeoutRadioButtons;
import tmroczkowski.weartweak.view.button.BluetoothButton;
import tmroczkowski.weartweak.view.button.WifiButton;

public class MainActivity extends WearableActivity {

    long timeout;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        intent = new Intent (this, WakelockService.class);

        new TimeoutRadioButtons(this, (new Timeout(this)).read(), timeout -> this.timeout = timeout);
        new WifiButton(this);
        new BluetoothButton(this);
    }

    @Override
    protected void onDestroy() {

        if (timeout != -1) {
            startService(intent);
        } else {
            stopService(intent);
        }

        super.onDestroy();
    }
}
