package tmroczkowski.weartweak;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends WearableActivity {

    private int defaultRadioSelected = R.id.radioButton5;

    private boolean defaultSwitch1Checked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onSelfCreate ();
        setAmbientEnabled(); // Enables Always-on
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {

        super.onEnterAmbient(ambientDetails);
    }

    private void onSelfCreate () {

        //startService(new Intent(this, ScreenTimeoutIntentService.class));

        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        WakeManager wakeManager = new WakeManager(powerManager);

        //DisplayActionListener displayActionListener =
        new DisplayActionListener(this, wakeManager);

        SwitchManager switchManager = new SwitchManager(this, this.defaultSwitch1Checked);
        switchManager.addObserver(new SwitchObserver(wakeManager));

        RadioButtonsManager radioButtonsManager = new RadioButtonsManager(this, this.defaultRadioSelected);
        radioButtonsManager.addObserver(new RadioButtonsObserver(wakeManager));

    }

    protected void onDestroy () {

        super.onDestroy();

        Log.d("MAIN_ACTIVITY", "onDestroy called");
        //displayManager.unregisterDisplayListener(displayActionListener);
    }
}
