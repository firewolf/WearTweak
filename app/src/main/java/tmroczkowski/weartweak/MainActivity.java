package tmroczkowski.weartweak;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;

import tmroczkowski.weartweak.helper.Common;
import tmroczkowski.weartweak.listener.DisplayActionListener;
import tmroczkowski.weartweak.view.BluetoothSwitch;
import tmroczkowski.weartweak.view.TimeoutRadioButtons;
import tmroczkowski.weartweak.view.WIFISwitch;

public class MainActivity extends WearableActivity {

    TimeoutRadioButtons timeoutRadioButtons;

    private int defaultRadioButtonId = R.id.radioButton5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        this.restoreState();

        timeoutRadioButtons = new TimeoutRadioButtons(this, defaultRadioButtonId);

        new DisplayActionListener(this.getApplicationContext());
        new WIFISwitch(this);
        new BluetoothSwitch(this);

        setAmbientEnabled(); // Enables Always-on
    }

    private void restoreState () {

        SharedPreferences sharedPreferences = Common.getPreferences(this);
        defaultRadioButtonId = sharedPreferences.getInt("timeoutId", defaultRadioButtonId);
    }

    @Override
    protected void onDestroy () {

        timeoutRadioButtons.saveState ();
        super.onDestroy();
    }
}
