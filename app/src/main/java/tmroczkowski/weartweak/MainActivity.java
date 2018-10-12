package tmroczkowski.weartweak;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends WearableActivity {

    RadioButtonsManager radioButtonsManager;

    private int defaultRadioButton = R.id.radioButton5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        defaultRadioButton = sharedPref.getInt("defaultRadioButton", defaultRadioButton);

        WakeManager wakeManager = new WakeManager(this);

        new DisplayActionListener(this, wakeManager);
        radioButtonsManager = new RadioButtonsManager(this, defaultRadioButton);
        radioButtonsManager.addObserver(new RadioButtonObserver(wakeManager));

        new WIFISwitch(this);
        new BluetoothSwitch(this);

        setAmbientEnabled(); // Enables Always-on
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {

        super.onEnterAmbient(ambientDetails);
    }

    private void saveData () {

        SharedPreferences.Editor editor = this.getPreferences(Context.MODE_PRIVATE).edit();
        editor.putInt("defaultRadioButton", radioButtonsManager.getCheckedId ());
        editor.apply ();
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        this.saveData();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    protected void onStop() {

        super.onStop();
    }
}
