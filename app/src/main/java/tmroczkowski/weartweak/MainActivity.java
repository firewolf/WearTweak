package tmroczkowski.weartweak;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;

import tmroczkowski.weartweak.helper.Common;
import tmroczkowski.weartweak.listener.DisplayActionListener;
import tmroczkowski.weartweak.view.TimeoutRadioButtons;
import tmroczkowski.weartweak.view.button.BluetoothButton;
import tmroczkowski.weartweak.view.button.WifiButton;

public class MainActivity extends WearableActivity {

    TimeoutRadioButtons timeoutRadioButtons;

    private long defaultTimeout = 5 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        this.restoreState();

        timeoutRadioButtons = new TimeoutRadioButtons(this, defaultTimeout);

        new DisplayActionListener(this.getApplicationContext());
        new WifiButton(this);
        new BluetoothButton(this);
    }

    private void restoreState () {

        SharedPreferences sharedPreferences = Common.getPreferences(this);
        defaultTimeout = sharedPreferences.getLong ("timeout", defaultTimeout);
    }
}
