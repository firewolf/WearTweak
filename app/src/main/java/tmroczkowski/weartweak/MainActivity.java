package tmroczkowski.weartweak;

import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends WearableActivity {

    private Switch switch1;

    private boolean switch1isChecked = false;

    private int spinnerPos = 0;

    private DisplayManager displayManager;

    private DisplayActionListener displayActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MAIN_ACTIVITY", "startService " + ScreenTimeoutIntentService.class);

        startService(
                new Intent(
                        this,
                        ScreenTimeoutIntentService.class
                )
        );

//        displayInit ();
//        switchInit ();
//        spinnerInit ();

        setAmbientEnabled(); // Enables Always-on
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
    }

    private void displayInit () {

        displayManager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
        displayActionListener = new DisplayActionListener((PowerManager) getSystemService(Context.POWER_SERVICE), displayManager);
        displayManager.registerDisplayListener(displayActionListener, null);
    }

    private void switchInit () {

        switch1 = (Switch) findViewById(R.id.switch1);
        switch1.setChecked(switch1isChecked);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch1isChecked = isChecked;
            }
        });
    }

    private void spinnerInit () {

        final String [] spinnerValues = getResources().getStringArray(R.array.timeout_array);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setSelection (spinnerPos, false);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.timeout_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// Specify the layout to use when the list of choices appears
        spinner.setAdapter(adapter);// Apply the adapter to the spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener () {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                displayActionListener.setTimeout (Integer.parseInt (spinnerValues [position]) * 1000);
                spinnerPos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private boolean getSwitch1 () {
        return switch1.isChecked();
    }

    protected void onDestroy () {
        super.onDestroy();

        Log.d("MAIN_ACTIVITY", "onDestroy called");
        //displayManager.unregisterDisplayListener(displayActionListener);
    }
}
