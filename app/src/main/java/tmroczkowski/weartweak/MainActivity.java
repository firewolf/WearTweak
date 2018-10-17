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

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        sharedPreferences = Common.getPreferences(this);
        defaultRadioButtonId = sharedPreferences.getInt("timeoutId", defaultRadioButtonId);

        new DisplayActionListener(this.getApplicationContext());
        timeoutRadioButtons = new TimeoutRadioButtons(this, defaultRadioButtonId);

        new WIFISwitch(this);
        new BluetoothSwitch(this);

        setAmbientEnabled(); // Enables Always-on
    }

    @Override
    protected void onDestroy () {

        timeoutRadioButtons.saveState ();

        super.onDestroy();
    }
}
